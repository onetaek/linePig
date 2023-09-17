package com.shop.linepig.domain.board.dto.request;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
public class BoardUpdateByAdminRequest {

    private String titleKo;//제목
    private String titleEn;//제목
    private String subTitleKo;//부제목
    private String subTitleEn;//부제목
    private String contentKo;//글내용
    private String contentEn;//글내용
    private Boolean isTop;//상단에 위치할 게시글인지 아닌지
    private String category;//상태
    private String status;//상태
}
