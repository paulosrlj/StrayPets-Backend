package com.paulosrlj.straypets.config.aws;

import com.amazonaws.regions.Regions;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.nio.file.Path;

@Data
@Component
@ConfigurationProperties("straypets.storage")
public class StorageProperties {

    private S3 s3 = new S3();

    @Data
    public class S3 {

        private String idAcessKey;
        private String secretAcessKey;
        private String bucket;
        private Regions region;
        private String photoDirectory;

    }


}
