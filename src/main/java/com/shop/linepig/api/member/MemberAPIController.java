package com.shop.linepig.api.member;


import com.shop.linepig.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/members")
@RestController
public class MemberAPIController {

    private final MemberService memberService;

    @PostMapping("/check-loginId")
    public ResponseEntity checkLoginId(@RequestBody Map<String,String> map){

        String loginId = map.get("loginId");
        log.info("loginId = {}",loginId);
        boolean idDuplicate = memberService.isLoginIdDuplicate(loginId);
        log.info("isDuplicate = {}",idDuplicate);

        return idDuplicate ?
                ResponseEntity.status(HttpStatus.CONFLICT).build() :
                ResponseEntity.status(HttpStatus.OK).build();

    }

}
