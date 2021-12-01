package com.upchina.spring.jdbc.repository;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by anjunli on  2021/12/1
 **/
@Data
@NoArgsConstructor
public class LoginUser {
    /**
     * 要导入 hibernate-validator 依赖
     */
    @NotEmpty(message = "用户名不能为空")
    private String name;
    @Length(max = 7, min = 7, message = "密码长度为7位")
    private String pwd;

}
