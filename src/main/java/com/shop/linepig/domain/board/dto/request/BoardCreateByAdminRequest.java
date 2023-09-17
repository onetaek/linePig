package com.shop.linepig.domain.board.dto.request;

import com.shop.linepig.domain.board.entity.Board;
import com.shop.linepig.domain.board.entity.enumeration.BoardCategory;
import com.shop.linepig.domain.board.entity.enumeration.BoardStatus;
import com.shop.linepig.domain.board.entity.enumeration.BoardType;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

@Slf4j
@Data
public class BoardCreateByAdminRequest {

    private String titleKo;//제목
    private String titleEn;//제목
    private String contentKo;//글내용
    private String contentEn;//글내용
    private String writer;//작성자
    private Boolean isTop;//상단에 위치할 게시글인지 아닌지
    private String category;//카테고리
    private String status;//상태

    public static Board toEntity(BoardCreateByAdminRequest request, Long adminId) {
        return Board.builder()
                .titleKo(request.getTitleKo())
                .titleEn(request.getTitleEn())
                .contentKo(request.getContentEn())
                .contentEn(request.getContentKo())
                .adminId(adminId)
                .writer(request.getWriter())
                .writtenOn(LocalDateTime.now())
                .isTop(request.getIsTop())
                .viewCount(0)
                .category(BoardCategory.fromCode(request.getCategory()))
                .status(BoardStatus.NORMAL)
                .type(BoardType.ADMIN).build();
    }
}
