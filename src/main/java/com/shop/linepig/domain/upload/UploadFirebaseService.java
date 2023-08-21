package com.shop.linepig.domain.upload;

import com.google.cloud.storage.Bucket;
import com.google.firebase.cloud.StorageClient;
import com.shop.linepig.domain.product.entity.embeddable.UploadFile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
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

    public List<UploadFile> uploadFiles(List<? extends UploadBase64EncodedFileRequest> files) {
        return files.stream()
                .map(this::uploadFile)
                .collect(Collectors.toList());
    }

    public UploadFile uploadFile(UploadBase64EncodedFileRequest request) {
        byte[] decodedFile = Base64.getDecoder().decode(request.getFileBase64());
        String storeFileName = this.createStoreFileName(request.getFileName());
        Bucket bucket = StorageClient.getInstance().bucket(firebaseBucket);
        ByteArrayInputStream content = new ByteArrayInputStream(decodedFile);
        bucket.create(storeFileName,content,request.getContentType());
        return UploadFile.builder()
                .uploadFileLink(rootPath+firebaseBucket+"/"+storeFileName)
                .originFileName(request.getFileName())
                .build();
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
