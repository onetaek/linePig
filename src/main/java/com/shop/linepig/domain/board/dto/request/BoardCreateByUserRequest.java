package com.shop.linepig.domain.board.dto.request;

import com.shop.linepig.domain.board.entity.Board;
import com.shop.linepig.domain.board.entity.enumeration.BoardCategory;
import com.shop.linepig.domain.board.entity.enumeration.BoardStatus;
import com.shop.linepig.domain.board.entity.enumeration.BoardType;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BoardCreateByUserRequest {

    private String title;//제목
    private String content;//글내용
    private String writer;//작성자
    private String password;//게시글 비밀번호
    private String category;//카테고리
    private String status;//상태

    public static Board toEntity(BoardCreateByUserRequest request, Long memberId) {
        return Board.builder()
                .titleKo(request.getTitle())
                .titleEn(request.getTitle())
                .contentKo(request.getContent())
                .contentEn(request.getContent())
                .memberId(memberId)
                .writer(request.getWriter())
                .writtenOn(LocalDateTime.now())
                .isTop(false)
                .viewCount(0)
                .password(request.getPassword())
                .category(BoardCategory.fromCode(request.getCategory()))
                .status(BoardStatus.NORMAL)
                .type(BoardType.USER)
                .build();
    }

}
