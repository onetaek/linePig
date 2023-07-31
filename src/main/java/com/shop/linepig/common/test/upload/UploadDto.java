package com.shop.linepig.common.test.upload;

import lombok.Data;

@Data
public class UploadDto {
    private String fileBase64;
    private String fileName;
    private String contentType;
}
