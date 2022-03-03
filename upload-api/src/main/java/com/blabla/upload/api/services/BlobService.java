package com.blabla.upload.api.services;

import com.blabla.upload.api.dtos.UploadedThumbnailDTO;
import lombok.NonNull;
import org.springframework.web.multipart.MultipartFile;

public interface BlobService {
    UploadedThumbnailDTO uploadFile(@NonNull MultipartFile file, String containerName);
}
