package cloud.truesun.controller;

import cloud.truesun.domain.Code;
import cloud.truesun.domain.Result;
import cloud.truesun.service.IPicUpload;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;


@Slf4j
@RestController
@RequestMapping("/uploads")
@CrossOrigin
public class PicController {
    @Autowired
    IPicUpload picUpload;

    @PostMapping("/pic")
    public Result fileUpload(MultipartFile file) throws IOException {
        String upload = picUpload.upload(file);

        return new Result(upload != null ? Code.UPLOAD_OK : Code.UPLOAD_ERR, upload);

    }

}
