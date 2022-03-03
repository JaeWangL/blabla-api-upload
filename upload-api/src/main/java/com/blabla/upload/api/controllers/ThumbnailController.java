package com.blabla.upload.api.controllers;

import com.blabla.upload.api.dtos.UploadedThumbnailDTO;
import com.blabla.upload.api.services.BlobService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping("thumbnail")
@RequiredArgsConstructor
@RestController
@Tag(name = "Thumbnail", description = "handling thumbnail rest api group")
public class ThumbnailController {
    private static final String CONTAINER_NAME = "test-thumbnail";
    private final BlobService blobService;

    @CrossOrigin("*")
    @PostMapping(value = "", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<UploadedThumbnailDTO> uploadThumbnail(@RequestPart("file") MultipartFile file) {
        final var response = blobService.uploadFile(file, CONTAINER_NAME);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
