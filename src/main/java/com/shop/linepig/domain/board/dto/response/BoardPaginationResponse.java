package com.shop.linepig.domain.board.dto.response;

import com.querydsl.core.annotations.QueryProjection;
import com.shop.linepig.domain.board.entity.enumeration.BoardCategory;
import com.shop.linepig.domain.board.entity.enumeration.BoardStatus;
import com.shop.linepig.domain.board.entity.enumeration.BoardType;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BoardPaginationResponse {

    private Long id;

    private String titleKo;//제목
    private String titleEn;//제목

    private String contentKo;//글내용
    private String contentEn;//글내용

    private Long memberId;

    private String writer;//작성자

    private LocalDateTime writtenOn;//게시글 작성일

    private Boolean isTop;//상단에 위치할 게시글인지 아닌지

    private Boolean isHidden;//비밀글 여부

    private int viewCount;//조회수

    private Integer sequence;//순서

    private BoardCategory category;//카테고리

    private BoardStatus status;//상태

    private BoardType type;//상태

    @QueryProjection
    public BoardPaginationResponse(Long id, String titleKo, String titleEn, String contentKo, String contentEn, Long memberId, String writer, LocalDateTime writtenOn, Boolean isTop, Boolean isHidden, int viewCount, Integer sequence, BoardCategory category, BoardStatus status, BoardType type) {
        this.id = id;
        this.titleKo = titleKo;
        this.titleEn = titleEn;
        this.contentKo = contentKo;
        this.contentEn = contentEn;
        this.memberId = memberId;
        this.writer = writer;
        this.writtenOn = writtenOn;
        this.isTop = isTop;
        this.isHidden = isHidden;
        this.viewCount = viewCount;
        this.sequence = sequence;
        this.category = category;
        this.status = status;
        this.type = type;
    }
}
