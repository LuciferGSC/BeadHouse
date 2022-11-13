package cloud.truesun.service.impl;

import cloud.truesun.domain.Code;
import cloud.truesun.domain.Result;
import cloud.truesun.service.IPicUpload;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;
@Service
public class PicUploadService implements IPicUpload {
    @Override
    public String upload(MultipartFile file) throws IOException {

        if (file.isEmpty()) {
            return null;
//            return new Result(Code.UPDATE_ERR, null, "上传图片为空");
        }
        // 获取文件后缀名
        String fileSuffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);

        // 用UUID生成一个文件名
        String fileName = UUID.randomUUID().toString().replace("-", "") + "." + fileSuffix;

        File f = new File("C:/Users/gsc_p/Pictures/upload/", fileName);

        // 传入到f中
        file.transferTo(f);
        return f.getName();
    }
}
