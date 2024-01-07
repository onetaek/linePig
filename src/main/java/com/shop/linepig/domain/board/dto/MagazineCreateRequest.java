package com.shop.linepig.domain.board.dto;

import com.shop.linepig.domain.board.entity.Board;
import com.shop.linepig.domain.board.entity.enumeration.BoardCategory;
import com.shop.linepig.domain.board.entity.enumeration.BoardStatus;
import com.shop.linepig.domain.board.entity.enumeration.BoardType;
import com.shop.linepig.domain.common.embeddable.UploadFile;
import com.shop.linepig.domain.upload.UploadBase64EncodedFileRequest;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MagazineCreateRequest {
    private String titleKo;
    private String titleEn;
    private String subTitleKo;
    private String subTitleEn;
    private String status;
    private String writer;
    private Integer sequence;
    private String contentKo;
    private String contentEn;
    private UploadBase64EncodedFileRequest boardImage;


    public static Board toEntity(MagazineCreateRequest request, UploadFile uploadFile, Long adminId) {
        return Board.builder()
                .titleKo(request.getTitleKo())
                .titleEn(request.getTitleEn())
                .subTitleKo(request.getSubTitleKo())
                .subTitleEn(request.getSubTitleEn())
                .category(BoardCategory.MAGAZINE)
                .status(BoardStatus.fromCode(request.getStatus()))
                .writer(request.getWriter())
                .writtenOn(LocalDateTime.now())
                .sequence(request.getSequence())
                .viewCount(0)
                .type(BoardType.ADMIN)
                .contentKo(request.getContentKo())
                .contentEn(request.getContentEn())
                .image(uploadFile)
                .adminId(adminId)
                .build();
    }
}
