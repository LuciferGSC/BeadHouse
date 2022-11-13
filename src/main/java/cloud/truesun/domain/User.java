package cloud.truesun.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableLogic;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 *
 * </p>
 *
 * @author 盖世诚
 * @since 2022-11-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String telephone;

    @TableField(select = false)
    private String userpwd;

    private String emaile;

    private String sex;

    private String username;

    @TableLogic
    private Integer deleted;


}
