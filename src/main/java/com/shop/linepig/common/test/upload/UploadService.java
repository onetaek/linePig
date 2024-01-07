package com.shop.linepig.common.test.upload;

import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Bucket;
import com.google.firebase.cloud.StorageClient;
import com.shop.linepig.domain.common.embeddable.UploadFile;
import com.shop.linepig.domain.upload.UploadBase64EncodedFileRequest;
import com.shop.linepig.domain.upload.UploadFirebaseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UploadService {

    @Value("${app.firebase-bucket}")
    private String firebaseBucket;

    @Value("${app.firebase-root}")
    private String rootPath;

    private final UploadFirebaseService uploadFirebaseService;

    public String uploadFile(byte[] file, String nameFile, String contentType) {
        Bucket bucket = StorageClient.getInstance().bucket(firebaseBucket);
        InputStream content = new ByteArrayInputStream(file);
        Blob blob = bucket.create(nameFile, content, contentType);
        //https://storage.googleapis.com/linepig-81fdb.appspot.com/E-commerce-2.png
        return rootPath+firebaseBucket+"/"+nameFile;
    }

    public List<UploadFile> uploadFiles(List<UploadBase64EncodedFileRequest> requests) {

        List<UploadFile> uploadFiles = uploadFirebaseService.uploadBase64EncodedFiles(requests);

        log.info("uploadFiles = {}",uploadFiles);

        return uploadFiles;
    }
}
