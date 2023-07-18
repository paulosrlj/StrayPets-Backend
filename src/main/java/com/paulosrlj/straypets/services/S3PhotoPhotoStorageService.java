package com.paulosrlj.straypets.services;

import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.paulosrlj.straypets.config.aws.StorageProperties;
import com.paulosrlj.straypets.exception.StorageException;
import com.paulosrlj.straypets.interfaces.services.PhotoStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.net.URL;
import java.nio.ByteBuffer;

@Service
public class S3PhotoPhotoStorageService implements PhotoStorageService {

    @Autowired
    private AmazonS3 amazonS3;

    @Autowired
    private StorageProperties storageProperties;

    public String store(MultipartFile photo) {
        try {
            String photoPath = getFilePath(photo.getOriginalFilename());

            var objectMetadata = new ObjectMetadata();
            objectMetadata.setContentType(photo.getContentType());
            objectMetadata.setContentLength(photo.getSize());

            var putObjectRequest = new PutObjectRequest(
                    storageProperties.getS3().getBucket(),
                    photoPath,
                    photo.getInputStream(),
                    objectMetadata
            ).withCannedAcl(CannedAccessControlList.PublicRead);

            amazonS3.putObject(putObjectRequest);

            return getPublicUrl(photoPath);
        } catch (Exception ex) {
            throw new StorageException("Não foi possível enviar arquivo ao Amazon S3", ex);
        }
    }

    private String getFilePath(String fileName) {
        return String.format("%s/%s", storageProperties.getS3().getPhotoDirectory(), fileName);
    }

    private String getPublicUrl(String caminhoArquivo) {
        GeneratePresignedUrlRequest generatePresignedUrlRequest = new GeneratePresignedUrlRequest(
                storageProperties.getS3().getBucket(),
                caminhoArquivo
        ).withMethod(HttpMethod.GET);

        URL url = amazonS3.generatePresignedUrl(generatePresignedUrlRequest);

        return url.toString();
    }
}
