package th.co.mfec.api.controller.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import th.co.mfec.api.model.common.SuccessResponse;
import th.co.mfec.api.model.files.FileResponse;
import th.co.mfec.api.service.util.FilesStorageService;

@RestController
@RequestMapping("/api/v1/file")
public class FileController {
    
    @Autowired
    private FilesStorageService filesStorageService;

    @PostMapping("/upload")
    public ResponseEntity<SuccessResponse<FileResponse>> uploadFile(@RequestParam("file") MultipartFile file){
        return ResponseEntity.ok(new SuccessResponse<FileResponse>(filesStorageService.uploadFile(file)));
    }

    @GetMapping("preview/{filename:.+}")
    public ResponseEntity<Resource> previewFile(@PathVariable String filename){
        Resource file = filesStorageService.downloadFile(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "image/jpeg; filename=\"" + file.getFilename() + "\"").body(file);
    }

    @GetMapping("download/{filename:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String filename){
        Resource file = filesStorageService.downloadFile(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }
}
