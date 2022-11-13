package cloud.truesun.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Value("${file.staticAccessPath}")
    private String staticAccessPath;
    @Value("${file.uploadFolder}")
    private String uploadFolder;

    public void addResourceHandlers  (ResourceHandlerRegistry registry) {

        /*
            配置server虚拟路径，handler为前台访问的URL目录，locations为files相对应的本地路径
            也就是说如果有一个 upload/avatar/aaa.png 请求，那程序会到后面的目录里面找aaa.png文件
            另外：如果项目中有使用Shiro，则还需要在Shiro里面配置过滤下
         */
        registry.addResourceHandler(staticAccessPath).addResourceLocations("file:" + uploadFolder);

    }

}
