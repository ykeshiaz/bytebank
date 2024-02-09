package com.techelevator.uploads;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

@Component
public class FileUploadProvider implements UploadProvider
{
    HttpServletRequest request;

    @Autowired
    public FileUploadProvider(HttpServletRequest request)
    {
        this.request = request;
    }

    private String getUploadPath()
    {
        return request.getRealPath("/") + File.separator + "img" + File.separator + "uploads";
    }


    @Override
    public String uploadFile (MultipartFile file, String userName, String fileName ) throws Exception
    {
        String path = getUploadPath() + File.separator + userName;

        String[] nameParts = file.getOriginalFilename().split( "." );
        String extension = (nameParts.length > 1) ? nameParts[1] : "jpg"; //default to jpg
        String fullFileName = fileName + "." + extension;

        // Creating the directory to store file
        File dir = new File(path);
        if (!dir.exists()) dir.mkdirs();

        // format file upload path
        File uploadPath = new File(dir.getAbsolutePath() + File.separator + fullFileName);

        BufferedOutputStream stream = null;
        try
        {
            byte[] bytes = file.getBytes();

            stream = new BufferedOutputStream( new FileOutputStream( uploadPath ) );
            stream.write( bytes );
        }
        catch ( Exception ex )
        {
            throw ex;
        }
        finally
        {
            if(stream != null)
            {
                stream.close();
            }
        }

        // demo/1.jpg
        // img/uploads/demo/1.jpg

        return "/" + "img" + "/" + "uploads" +  "/" + userName + "/" + fullFileName ;
    }
}
