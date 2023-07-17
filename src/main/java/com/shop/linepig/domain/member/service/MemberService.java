package com.shop.linepig.domain.member.service;


import com.shop.linepig.domain.member.dto.MemberDto;
import com.shop.linepig.domain.member.entity.Member;
import com.shop.linepig.domain.member.entity.enumeration.MemberStatus;
import com.shop.linepig.domain.member.dto.MemberJoinDto;
import com.shop.linepig.domain.member.dto.MemberLoginDto;
import com.shop.linepig.domain.member.repository.MemberRepository;
import com.shop.linepig.common.util.PasswdEncry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


@Slf4j
@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final ModelMapper modelMapper;


    public Long join(MemberJoinDto memberJoinDto){

        //비밀번호 암호화
        memberJoinDto.setPassword(getPasswdEncry(memberJoinDto));

        //회원 등급 NORMAL로 설정
        memberJoinDto.setMemberStatus(MemberStatus.NORMAL);

        //dto를 entity로 변환
        Member mappedMember = modelMapper.map(memberJoinDto, Member.class);

        //db에 저장
        Member savedMember = memberRepository.save(mappedMember);

        //정장 로직이면 true를 리턴
        return savedMember.getId();
    }


    private String getPasswdEncry(MemberJoinDto memberJoinDto) {
        /* 비밀번호 암호화 */
        PasswdEncry passwdEncry = new PasswdEncry();
        // 난수 생성 및 dto에 세팅
        String salt = passwdEncry.getSalt();
        memberJoinDto.setSalt(salt);
        // 입력받은 비밀번호 + 난수 => 암호화
        String SHA256Pw = passwdEncry.getEncry(memberJoinDto.getPassword(), salt);
        return SHA256Pw;
    }


    public Member login(MemberLoginDto memberLoginDto) {

        String loginId = memberLoginDto.getLoginId();

        Member findByLoginId = memberRepository.findByLoginId(loginId);

        if(findByLoginId == null){//해당되는 아이디 없으면...
            return null;
        }
        //난수
        String salt = findByLoginId.getSalt();
        //비밀번호 암호화
        PasswdEncry passwdEncry = new PasswdEncry();
        //입력받은 비번 + 난수 => 암호화
        String SHA256Pw = memberLoginDto.getPassword() != null ?
                passwdEncry.getEncry(memberLoginDto.getPassword(), salt) : null;
        //회원 찾음
        Member findMember = memberRepository.findByloginIdAndPassword(loginId, SHA256Pw);

        return findMember;
    }

    public boolean isLoginIdDuplicate(String loginId) {
        boolean isExist = memberRepository.existsByLoginId(loginId);
        return isExist;
    }

    public MemberDto findById(Long id) {
        if(id == null)
            return null;
        else {
            Member findMember = memberRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Session에서 꺼낸 id에 해당하는 회원이 없습니다."));
            MemberDto memberDto = MemberDto.fromEntity(findMember);
            return memberDto;
        }

    }
}
