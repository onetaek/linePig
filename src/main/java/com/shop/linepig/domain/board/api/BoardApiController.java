package com.shop.linepig.domain.board.api;

import com.shop.linepig.common.argumentresolver.AdminLogin;
import com.shop.linepig.common.argumentresolver.Login;
import com.shop.linepig.domain.admin.exception.AdminNotLoggedInException;
import com.shop.linepig.domain.board.dto.request.BoardCreateByAdminRequest;
import com.shop.linepig.domain.board.dto.request.BoardCreateByUserRequest;
import com.shop.linepig.domain.board.service.BoardService;
import com.shop.linepig.domain.member.exception.MemberNotLoggedInException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
public class BoardApiController {

    private final BoardService boardService;

    @GetMapping("/api/boards")
    public ResponseEntity findAll(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) Boolean isTop
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(boardService.findAll(category,isTop));
    }

    @GetMapping("/api/boards/pagination")
    public ResponseEntity findAllWithPagination(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) Boolean isTop,
            Pageable pageable
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(boardService.findAllWithPagination(pageable,category,isTop));
    }

    @GetMapping("/api/boards/{id}")
    public ResponseEntity findById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(boardService.findById(id));
    }

    @PostMapping(value = "/api/admins/boards")
    public ResponseEntity create(@AdminLogin Long adminId, @RequestBody BoardCreateByAdminRequest request) {
        if (adminId == null) throw new AdminNotLoggedInException();
        boardService.createByAdmin(request, adminId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/api/members/boards")
    public ResponseEntity create(@Login Long memberId, @RequestBody BoardCreateByUserRequest request) {
        if (memberId == null) throw new MemberNotLoggedInException();
        boardService.createByUser(request, memberId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/api/admins/boards/{id}")
    public ResponseEntity delete(@AdminLogin Long adminId, @PathVariable Long id) {
        if (adminId == null) throw new AdminNotLoggedInException();
        boardService.deleteByAdmin(id, adminId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
