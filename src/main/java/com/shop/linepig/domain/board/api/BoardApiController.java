package com.shop.linepig.domain.board.api;

import com.shop.linepig.common.argumentresolver.AdminLogin;
import com.shop.linepig.common.argumentresolver.Login;
import com.shop.linepig.domain.board.dto.request.BoardCreateByAdminRequest;
import com.shop.linepig.domain.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class BoardApiController {

    private final BoardService boardService;

    @PostMapping(value = "/api/boards")
    public ResponseEntity create(@Login Long memberId, @AdminLogin Long adminId, @RequestBody BoardCreateByAdminRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
