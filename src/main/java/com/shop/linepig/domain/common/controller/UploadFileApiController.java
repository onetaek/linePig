package com.shop.linepig.domain.common.controller;

import com.shop.linepig.domain.upload.UploadFirebaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartRequest;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class UploadFileApiController {

    private final UploadFirebaseService uploadFirebaseService;

    @PostMapping("/api/image/upload")
    public Map<String, Object> imageUpload(MultipartRequest request) throws Exception{

        Map<String, Object> responseData = new HashMap<>();

        try {
            String uploadUri = uploadFirebaseService.uploadMultiPartFile(request);
            responseData.put("uploaded",true);
            responseData.put("url",uploadUri);
            return responseData;
        } catch (Exception e) {
            responseData.put("uploaded",false);
            return responseData;
        }
    }

}
