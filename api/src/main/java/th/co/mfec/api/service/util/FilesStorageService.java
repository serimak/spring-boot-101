package th.co.mfec.api.service.util;

import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import th.co.mfec.api.constant.StatusCode;
import th.co.mfec.api.controller.util.FileController;
import th.co.mfec.api.exception.BaseException;
import th.co.mfec.api.model.files.FileResponse;

@Service
public class FilesStorageService {

    @Value("${app.file.uploads.path}") ///Users/serix/Projects/Tutorials/SpringBoot/uploads
    private String fileUploadsPath;

    public FileResponse uploadFile(MultipartFile file) {
        //Validate File
        if(file == null){
            throw new BaseException(HttpStatus.INTERNAL_SERVER_ERROR, StatusCode.ERR_CODE_500, StatusCode.ERR_DESC_500);
        }
        //Validate File Size
        if(file.getSize() > 1048576 * 2){
            throw new BaseException(HttpStatus.INTERNAL_SERVER_ERROR, StatusCode.ERR_CODE_500, StatusCode.ERR_DESC_500);
        }
        //Validate Content Type
        if(file.getContentType() == null){
            throw new BaseException(HttpStatus.INTERNAL_SERVER_ERROR, StatusCode.ERR_CODE_500, StatusCode.ERR_DESC_500);
        }
         //Validate Content Type Support
        List<String> supportedTypes = Arrays.asList("image/jpeg", "image/png");
        if(!(supportedTypes.contains(file.getContentType()))){
            throw new BaseException(HttpStatus.INTERNAL_SERVER_ERROR, StatusCode.ERR_CODE_500, StatusCode.ERR_DESC_500);
        }
        try{
            Path uploads = Paths.get(fileUploadsPath);
            if(!Files.exists(uploads)){
                Files.createDirectories(uploads);
            }
            Path filePath = uploads.resolve(file.getOriginalFilename());
            if(!Files.exists(filePath)){
                Files.copy(file.getInputStream(), filePath);
            }
            Resource resource = new UrlResource(filePath.toUri());
            if((resource.exists()) && (resource.isReadable())){
                //String urlPreview = http://localhost:8080/api/v1/file/preview/abc.jpeg
                String urlPreview = MvcUriComponentsBuilder.fromMethodName(FileController.class, "previewFile", filePath.getFileName().toString()).build().toString();
                // urlDownload = http://localhost:8080/api/v1/file/download/abc.jpeg
                String urlDownload = MvcUriComponentsBuilder.fromMethodName(FileController.class, "downloadFile", filePath.getFileName().toString()).build().toString();
                
                FileResponse fileResponse = new FileResponse();
                fileResponse.setFileName(filePath.getFileName().toString());
                fileResponse.setUrlPreview(urlPreview);
                fileResponse.setUrlDownload(urlDownload);
                return fileResponse;
            }
        }catch(Exception ex){
            ex.printStackTrace();
            throw new BaseException(HttpStatus.INTERNAL_SERVER_ERROR, StatusCode.ERR_CODE_500, StatusCode.ERR_DESC_500);
        }
        
        return null;
    }

    public Resource downloadFile(String filename) {
        try {
            Path uploads = Paths.get(fileUploadsPath);
            Path filePath = uploads.resolve(filename);
            Resource resource = new UrlResource(filePath.toUri());
            if((resource.exists()) && (resource.isReadable())){
                return resource;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
            throw new BaseException(HttpStatus.INTERNAL_SERVER_ERROR, StatusCode.ERR_CODE_500, StatusCode.ERR_DESC_500);
        }
        return null;
    }
    
}
