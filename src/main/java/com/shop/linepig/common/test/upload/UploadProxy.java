package com.shop.linepig.common.test.upload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class UploadProxy {
    private String fileBase64Decoded;
    private String fileName;
    private String contentType;

    public static UploadProxy fromDto(UploadDto uploadDto) {
        UploadProxy uploadProxy = UploadProxy.builder()

                .build();
        return null;
    }


}
