package cloud.truesun.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface IPicUpload {
    String upload(MultipartFile file) throws IOException;
}
