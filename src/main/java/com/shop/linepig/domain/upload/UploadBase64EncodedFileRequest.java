package com.shop.linepig.domain.upload;

import lombok.Data;

@Data
public class UploadBase64EncodedFileRequest {
    private String fileBase64;
    private String fileName;
    private String contentType;
}
