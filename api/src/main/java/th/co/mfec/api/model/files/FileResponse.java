package th.co.mfec.api.model.files;

public class FileResponse {

    private String fileName;
    private String urlPreview;
    private String urlDownload;
    
    public String getFileName() {
        return fileName;
    }
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    public String getUrlPreview() {
        return urlPreview;
    }
    public void setUrlPreview(String urlPreview) {
        this.urlPreview = urlPreview;
    }
    public String getUrlDownload() {
        return urlDownload;
    }
    public void setUrlDownload(String urlDownload) {
        this.urlDownload = urlDownload;
    }
    
}
