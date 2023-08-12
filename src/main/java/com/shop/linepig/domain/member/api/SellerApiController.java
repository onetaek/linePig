package com.shop.linepig.domain.member.api;

import com.shop.linepig.domain.member.dto.request.SellerCreateRequest;
import com.shop.linepig.domain.member.dto.request.SellerUpdateRequest;
import com.shop.linepig.domain.member.dto.response.SellerResponse;
import com.shop.linepig.domain.member.service.SellerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
public class SellerApiController {

    private final SellerService sellerService;

    @PostMapping("/api/admins/members/{id}/sellers")//판매자 등록
    public ResponseEntity create(@PathVariable Long id, @Validated @RequestBody SellerCreateRequest request) {
        SellerResponse sellerResponse = sellerService.create(id, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(sellerResponse);
    }

    @PatchMapping("/api/admins/sellers/{id}")//판매자 수정
    public ResponseEntity update(@PathVariable Long id,@Validated @RequestBody SellerUpdateRequest request) {
        SellerResponse sellerResponse = sellerService.update(id, request);
        return ResponseEntity.status(HttpStatus.OK).body(sellerResponse);
    }

    @DeleteMapping("/api/admins/sellers/{id}")//판매자 삭제
    public ResponseEntity delete(@PathVariable Long id) {
        sellerService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
