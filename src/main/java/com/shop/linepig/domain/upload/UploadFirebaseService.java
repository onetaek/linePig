package com.shop.linepig.domain.upload;

import com.google.cloud.storage.Bucket;
import com.google.firebase.cloud.StorageClient;
import com.shop.linepig.domain.common.embeddable.UploadFile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UploadFirebaseService {

    @Value("${app.firebase-bucket}")
    private String firebaseBucket;

    @Value("${app.firebase-root}")
    private String rootPath;

    public List<UploadFile> uploadBase64EncodedFiles(List<? extends UploadBase64EncodedFileRequest> files) {
        return files.stream()
                .map(this::uploadBase64EncodedFile)
                .collect(Collectors.toList());
    }

    public UploadFile uploadBase64EncodedFile(UploadBase64EncodedFileRequest request) {
        byte[] decodedFile = Base64.getDecoder().decode(request.getFileBase64());
        String storeFileName = this.createStoreFileName(request.getFileName());
        Bucket bucket = StorageClient.getInstance().bucket(firebaseBucket);
        ByteArrayInputStream content = new ByteArrayInputStream(decodedFile);
        bucket.create(storeFileName,content,request.getContentType());
        return UploadFile.builder()
                .sequence(request.getSequence())
                .uploadFileLink(rootPath+firebaseBucket+"/"+storeFileName)
                .originFileName(request.getFileName())
                .build();
    }

    public String uploadMultiPartFile(MultipartRequest request) throws IOException {

        MultipartFile file = request.getFile("upload");
        String contentType = file.getContentType();
        String originalFilename = file.getOriginalFilename();
        String storeFileName = this.createStoreFileName(originalFilename);

        Bucket bucket = StorageClient.getInstance().bucket(firebaseBucket);
        bucket.create(storeFileName,file.getBytes() ,contentType);
        return rootPath + firebaseBucket + "/" + storeFileName;
    }

    // ex) sample.png -> (UUID).png
    private String createStoreFileName(String originalFileName) {
        String ext = this.extractExt(originalFileName);
        String uuid = UUID.randomUUID().toString();
        return uuid + "." + ext;
    }

    // ex) sample.png -> png
    private String extractExt(String originalFileName) {
        int pos = originalFileName.lastIndexOf(".");
        return originalFileName.substring(pos + 1);
    }
}
