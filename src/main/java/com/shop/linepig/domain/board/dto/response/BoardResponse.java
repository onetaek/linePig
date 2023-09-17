package com.shop.linepig.domain.board.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.shop.linepig.domain.board.entity.Board;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@Getter
public class BoardResponse {

    private Long id;

    private String titleKo;//제목
    private String titleEn;//제목

    private String contentKo;//글내용
    private String contentEn;//글내용

    private Long memberId;

    private String writer;//작성자

    private LocalDateTime writtenOn;//게시글 작성일

    private Boolean isTop;//상단에 위치할 게시글인지 아닌지

    private int viewCount;//조회수

    private String category;//카테고리

    private String status;//상태

    private String type;//상태

    public static BoardResponse fromEntity(Board board) {
        return BoardResponse.builder()
                .id(board.getId())
                .titleKo(board.getTitleKo())
                .titleEn(board.getTitleEn())
                .contentKo(board.getContentKo())
                .contentEn(board.getContentEn())
                .memberId(board.getMemberId())
                .writer(board.getWriter())
                .writtenOn(board.getWrittenOn())
                .isTop(board.getIsTop())
                .viewCount(board.getViewCount())
                .category(board.getCategory().getDisplayValue())
                .status(board.getStatus().getDisplayValue())
                .type(board.getType().getDescription())
                .build();
    }
}
