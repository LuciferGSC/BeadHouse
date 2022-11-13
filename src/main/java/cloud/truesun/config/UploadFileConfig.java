package cloud.truesun.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;

import javax.servlet.MultipartConfigElement;

@Configuration
public class UploadFileConfig {
    @Value("${file.uploadFolder}")
    private String uploadFolder;

    @Bean
    MultipartConfigElement multipartConfigElement(){
        MultipartConfigFactory factory = new MultipartConfigFactory();
        // 设置单文件最大
        factory.setMaxFileSize(DataSize.ofMegabytes(30));
        // 设置总上传数据总大小
        factory.setMaxFileSize(DataSize.ofMegabytes(120));

        return factory.createMultipartConfig();

    }
}
