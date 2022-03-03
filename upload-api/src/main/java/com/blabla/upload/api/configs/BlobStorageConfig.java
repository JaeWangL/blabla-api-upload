package com.blabla.upload.api.configs;

import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import com.azure.storage.common.StorageSharedKeyCredential;
import java.net.URISyntaxException;
import java.util.Locale;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BlobStorageConfig {
    @Value("${app.azure.storage.accountName}")
    private String accountName;

    @Value("${app.azure.storage.accountKey}")
    private String accountKey;

    @Bean
    public BlobServiceClient getBlobServiceClient() {
        return new BlobServiceClientBuilder()
            .endpoint(String.format(Locale.ROOT, "https://%s.blob.core.windows.net", accountName))
            .credential(new StorageSharedKeyCredential(accountName, accountKey))
            .buildClient();
    }
}