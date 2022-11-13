package cloud.truesun.domain;

import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

import java.io.Serializable;

@Data
public class House implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String housename;
    private String areas;
    private String address;
    private String description;
    private Integer min;
    private Integer max;
    private String url;
    @TableLogic
    private String deleted;
}
