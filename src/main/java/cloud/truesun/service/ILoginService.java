package cloud.truesun.service;

import cloud.truesun.domain.Result;
import cloud.truesun.domain.User;
import org.springframework.web.bind.annotation.RequestBody;

public interface ILoginService {
    Result checkLogin(User user);
}
