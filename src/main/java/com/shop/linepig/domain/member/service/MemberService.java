package com.shop.linepig.domain.member.service;


import com.shop.linepig.common.util.PasswdEncry;
import com.shop.linepig.domain.member.dto.request.MemberJoinRequest;
import com.shop.linepig.domain.member.dto.request.MemberLoginRequest;
import com.shop.linepig.domain.member.dto.response.MemberResponse;
import com.shop.linepig.domain.member.entity.Member;
import com.shop.linepig.domain.member.entity.enumeration.MemberStatus;
import com.shop.linepig.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final ModelMapper modelMapper;


    public Long join(MemberJoinRequest memberJoinRequest){

        //비밀번호 암호화
        memberJoinRequest.setPassword(getPasswdEncry(memberJoinRequest));

        //회원 등급 NORMAL로 설정
        memberJoinRequest.setMemberStatus(MemberStatus.NORMAL);

        //dto를 entity로 변환
        Member mappedMember = modelMapper.map(memberJoinRequest, Member.class);

        //db에 저장
        Member savedMember = memberRepository.save(mappedMember);

        //정장 로직이면 true를 리턴
        return savedMember.getId();
    }


    private String getPasswdEncry(MemberJoinRequest memberJoinRequest) {
        /* 비밀번호 암호화 */
        PasswdEncry passwdEncry = new PasswdEncry();
        // 난수 생성 및 dto에 세팅
        String salt = passwdEncry.getSalt();
        memberJoinRequest.setSalt(salt);
        // 입력받은 비밀번호 + 난수 => 암호화
        String SHA256Pw = passwdEncry.getEncry(memberJoinRequest.getPassword(), salt);
        return SHA256Pw;
    }


    public MemberResponse login(MemberLoginRequest memberLoginRequest) {

        String loginId = memberLoginRequest.getLoginId();

        Member findByLoginId = memberRepository.findByLoginId(loginId);

        if(findByLoginId == null){//해당되는 아이디 없으면...
            return null;
        }

        //암호화된 비밀번호찾기
        String SHA256Pw = this.getString(memberLoginRequest, findByLoginId);
        //회원 찾음
        Member findMember = memberRepository.findByloginIdAndPassword(loginId, SHA256Pw);

        return MemberResponse.fromEntity(findMember);
    }


    public boolean isLoginIdDuplicate(String loginId) {
        boolean isExist = memberRepository.existsByLoginId(loginId);
        return isExist;
    }
    public MemberResponse findById(Long id) {
        Member findMember = memberRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Session에서 꺼낸 id에 해당하는 회원이 없습니다."));
        return MemberResponse.fromEntity(findMember);
    }

    public List<MemberResponse> findAll() {
        List<Member> findMembers = memberRepository.findAll();
        return findMembers.stream()
                .map(MemberResponse::fromEntity)
                .collect(Collectors.toList());
    }





    private String getString(MemberLoginRequest memberLoginRequest, Member findByLoginId) {
        //난수
        String salt = findByLoginId.getSalt();
        //비밀번호 암호화
        PasswdEncry passwdEncry = new PasswdEncry();
        //입력받은 비번 + 난수 => 암호화
        String SHA256Pw = memberLoginRequest.getPassword() != null ?
                passwdEncry.getEncry(memberLoginRequest.getPassword(), salt) : null;
        return SHA256Pw;
    }
}
