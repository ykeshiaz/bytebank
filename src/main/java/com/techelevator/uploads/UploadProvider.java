package com.techelevator.uploads;

import org.springframework.web.multipart.MultipartFile;

public interface UploadProvider
{
    public String uploadFile(MultipartFile file, String userName, String fileName ) throws Exception;
}

