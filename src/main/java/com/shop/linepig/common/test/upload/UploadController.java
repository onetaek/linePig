package com.shop.linepig.common.test.upload;

import com.shop.linepig.domain.common.embeddable.UploadFile;
import com.shop.linepig.domain.upload.UploadBase64EncodedFileRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@Slf4j
public class UploadController {

    private final UploadService uploadService;


    @GetMapping("/test/upload")
    public String uploadPage() {
        return "/test/upload";
    }

    // Service단을 구분하지 않고 한번에 구현하겠습니다.
    @PostMapping("/test/upload")
    public ResponseEntity<?> upload(@RequestBody UploadDto uploadDto) {
        log.info("테스트 파일업로드 controller 시작");

        String fileBase64 = uploadDto.getFileBase64();
//        log.info("fileBase64 = {}",fileBase64);

        //예외처리
        if(fileBase64 == null || fileBase64.equals("")) {
            log.info("fileBase64가 없습니다.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } else if(fileBase64.length() > 400000 ) {
            log.info("fileBase64가 너무 큽니다.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        //Base64 인코딩된 이미지 데이터를 파일로 디코딩하여 저장
        byte[] decodedBytes = Base64.getDecoder().decode(fileBase64);
//        log.info("decodeBytes = {}",decodedBytes);

        String imageLink = uploadService.uploadFile(decodedBytes, uploadDto.getFileName(), uploadDto.getContentType());
        log.info("mediaLink = {}",imageLink);

        Map<String,String> responseMap = new HashMap<>();
        responseMap.put("imageLink",imageLink);

        return ResponseEntity.status(HttpStatus.OK).body(responseMap);
    }

    @PostMapping("/test/uploads")
    public ResponseEntity<?> uploads(@RequestBody List<UploadBase64EncodedFileRequest> requests) {
        log.info("테스트 파일업로드 controller 시작");
        log.info("uploadDtos = {}",requests);

        List<UploadFile> uploadFiles = uploadService.uploadFiles(requests);

        return ResponseEntity.status(HttpStatus.OK).body(uploadFiles);
    }

}
