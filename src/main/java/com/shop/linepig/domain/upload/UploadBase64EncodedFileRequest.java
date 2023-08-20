package com.shop.linepig.domain.upload;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UploadBase64EncodedFileRequest {
    private int sequence;
    private String fileBase64;
    private String fileName;
    private String contentType;
}
