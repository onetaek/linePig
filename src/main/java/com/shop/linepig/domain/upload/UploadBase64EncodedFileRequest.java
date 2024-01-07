package com.shop.linepig.domain.upload;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UploadBase64EncodedFileRequest {
    private Integer sequence;
    private String fileBase64;
    private String fileName;
    private String contentType;

    @JsonCreator
    public UploadBase64EncodedFileRequest(@JsonProperty("sequence") Integer sequence,
                                          @JsonProperty("fileBase64") String fileBase64,
                                          @JsonProperty("fileName") String fileName,
                                          @JsonProperty("contentType") String contentType) {
        this.sequence = sequence;
        this.fileBase64 = fileBase64;
        this.fileName = fileName;
        this.contentType = contentType;
    }
}
