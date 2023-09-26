package com.shop.linepig.domain.board.api;

import com.shop.linepig.common.argumentresolver.AdminLogin;
import com.shop.linepig.common.argumentresolver.Login;
import com.shop.linepig.domain.board.dto.request.BoardCreateByAdminRequest;
import com.shop.linepig.domain.board.dto.request.BoardCreateByUserRequest;
import com.shop.linepig.domain.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
public class BoardApiController {

    private final BoardService boardService;

    @GetMapping("/api/boards")
    public ResponseEntity findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(boardService.findAll());
    }

    @GetMapping("/api/boards/{id}")
    public ResponseEntity findById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(boardService.findById(id));
    }

    @PostMapping(value = "/api/admins/boards")
    public ResponseEntity create(@AdminLogin Long adminId, @RequestBody BoardCreateByAdminRequest request) {
        if (adminId != null) {
            boardService.createByAdmin(request, adminId);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @PostMapping("/api/members/boards")
    public ResponseEntity create(@Login Long memberId, @RequestBody BoardCreateByUserRequest request) {
        if (memberId != null) {
            boardService.createByUser(request, memberId);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}
