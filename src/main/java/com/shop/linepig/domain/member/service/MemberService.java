package com.shop.linepig.domain.member.service;


import com.shop.linepig.common.util.PasswdEncry;
import com.shop.linepig.domain.member.dto.request.MemberJoinRequest;
import com.shop.linepig.domain.member.dto.request.MemberLoginRequest;
import com.shop.linepig.domain.member.dto.request.MemberUpdateRequest;
import com.shop.linepig.domain.member.dto.response.GenderResponse;
import com.shop.linepig.domain.member.dto.response.MemberBasicResponse;
import com.shop.linepig.domain.member.dto.response.MemberResponse;
import com.shop.linepig.domain.member.dto.response.MemberStatusResponse;
import com.shop.linepig.domain.member.entity.Member;
import com.shop.linepig.domain.member.entity.enumeration.Gender;
import com.shop.linepig.domain.member.entity.enumeration.MemberStatus;
import com.shop.linepig.domain.member.repository.MemberQueryRepository;
import com.shop.linepig.domain.member.repository.MemberRepository;
import com.shop.linepig.domain.member.repository.expression.MemberQueryExpression;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final MemberQueryRepository memberQueryRepository;

    public List<GenderResponse> getGenders() {
        return Stream.of(Gender.values())
                .sorted(Comparator.comparing(
                        Gender::getSequence,
                        Comparator.naturalOrder()))
                .map(GenderResponse::fromEnum)
                .collect(Collectors.toList());
    }

    public List<MemberStatusResponse> getStatuses() {
        return Stream.of(MemberStatus.values())
                .filter(memberStatus -> memberStatus != MemberStatus.SELLER)
                .sorted(Comparator.comparing(
                        MemberStatus::getSequence,
                        Comparator.naturalOrder()))
                .map(MemberStatusResponse::fromEnum)
                .collect(Collectors.toList());
    }

    public MemberResponse findById(Long id) {
        Member findMember = memberRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Session에서 꺼낸 id에 해당하는 회원이 없습니다."));
        return MemberResponse.fromEntity(findMember);
    }

    public MemberBasicResponse findBasicById(Long id) {
        if (id == null) return null;
        return memberQueryRepository.findBasicOne(MemberQueryExpression.eqId(id));
    }

    public List<MemberResponse> findAll() {
        List<Member> findMembers = memberRepository.findAll();
        return findMembers.stream()
                .map(MemberResponse::fromEntity)
                .collect(Collectors.toList());
    }

    public MemberResponse join(MemberJoinRequest request){

        //비밀번호 암호화
        request.setPassword(this.getPasswdEncry(request));

        //dto를 entity로 변환
//        Member mappedMember = modelMapper.map(request, Member.class);
        Member unsavedMember = MemberJoinRequest.toEntity(request);

        //db에 저장
        Member savedMember = memberRepository.save(unsavedMember);

        //정장 로직이면 true를 리턴
        return MemberResponse.fromEntity(savedMember);
    }


    public MemberResponse login(MemberLoginRequest memberLoginRequest) {

        String loginId = memberLoginRequest.getLoginId();

        Member findByLoginId = memberRepository.findByLoginId(loginId);

        if(findByLoginId == null){//해당되는 아이디 없으면...
            return null;
        }

        //암호화된 비밀번호찾기
        String SHA256Pw = this.getSHA256Pw(memberLoginRequest, findByLoginId);
        //회원 찾음
        Member findMember = memberRepository.findByloginIdAndPassword(loginId, SHA256Pw);
        return MemberResponse.fromEntity(findMember);
    }

    public boolean isLoginIdDuplicate(String loginId) {
        return memberRepository.existsByLoginId(loginId);
    }



    public MemberResponse update(Long id, MemberUpdateRequest request) {
        Member findMember = memberRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Session에서 꺼낸 id에 해당하는 회원이 없습니다."));
        Member updateedMember = findMember.updateStatus(MemberStatus.fromCode(request.getStatus()));
        return MemberResponse.fromEntity(updateedMember);
    }



    private String getSHA256Pw(MemberLoginRequest memberLoginRequest, Member findByLoginId) {
        //난수
        String salt = findByLoginId.getSalt();
        //비밀번호 암호화
        PasswdEncry passwdEncry = new PasswdEncry();
        //입력받은 비번 + 난수 => 암호화
        String SHA256Pw = memberLoginRequest.getPassword() != null ?
                passwdEncry.getEncry(memberLoginRequest.getPassword(), salt) : null;
        return SHA256Pw;
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
}
