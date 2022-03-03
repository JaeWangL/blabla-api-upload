package com.blabla.upload.api.services;

import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.specialized.BlockBlobClient;
import com.blabla.upload.api.dtos.UploadedThumbnailDTO;
import com.blabla.upload.api.exceptions.FileUploadException;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@Service
public class BlobServiceImpl implements BlobService {
    private final static Logger logger = LoggerFactory.getLogger(BlobServiceImpl.class);
    private final BlobServiceClient blobClient;

    @Override
    public UploadedThumbnailDTO uploadFile(@NonNull MultipartFile file, String containerName) {
        BlobContainerClient blobContainerClient = getBlobContainerClient(containerName);
        String filename = file.getOriginalFilename();

        assert filename != null;
        String blobName = String.format(
            "%s_%s",
            file.getOriginalFilename(),
            DateTimeFormatter.BASIC_ISO_DATE.format(LocalDate.now()));
        BlockBlobClient blockBlobClient = blobContainerClient.getBlobClient(blobName).getBlockBlobClient();
        try {
            /*
            // delete file if already exists in that container
            // NOTE: this is maybe unnecessary because of overwriting features of 'upload' method
            if (blockBlobClient.exists()) {
                blockBlobClient.delete();
            }
            */
            // upload file to azure blob storage
            final var result = blockBlobClient.upload(new BufferedInputStream(file.getInputStream()), file.getSize(), true);

            return UploadedThumbnailDTO.fromDomain(blockBlobClient.getBlobUrl(), filename);
        } catch (IOException e) {
            logger.error(e.getMessage());
            throw new FileUploadException("Cannot upload image %s".formatted(filename));
        }
    }


    private @NonNull BlobContainerClient getBlobContainerClient(@NonNull String containerName) {
        // create container if not exists
        BlobContainerClient blobContainerClient = blobClient.getBlobContainerClient(containerName);
        if (!blobContainerClient.exists()) {
            blobContainerClient.create();
        }
        return blobContainerClient;
    }
}
