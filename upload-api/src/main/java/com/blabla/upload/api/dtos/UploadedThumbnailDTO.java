package com.blabla.upload.api.dtos;

import lombok.NonNull;

public record UploadedThumbnailDTO(
    @NonNull String thumbnailUrl,
    @NonNull String originalFileName
) {
    public static UploadedThumbnailDTO fromDomain(@NonNull String thumbnailUrl, @NonNull String originalFileName) {
        return new UploadedThumbnailDTO(
            thumbnailUrl,
            originalFileName
        );
    }
}
