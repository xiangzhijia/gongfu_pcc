package com.gongfu.model.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gongfu.util.CryptUtils;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Data
@Table(name = "gongfu_user")
public class User implements Serializable {
    private static final long serialVersionUID = 3310686253938680785L;
    @Id
    private Long id;
    private String userName;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createdAt;
    private String nick;
    private String password;
    /**
     * 密码盐
     * set  方法的访问权限PRIVATE
     *
     * @JsonIgnore 序列化时将java bean中的一些属性忽略掉
     */
    @Setter(AccessLevel.PRIVATE)
    @JsonIgnore
    private String passwordSalt;

    /**
     * 管理员状态 (1、正常 ，2、禁用)
     */
    private int status = 1;

    private String lastLoginIp;

    public void sha256HexPassword(String password) {
        //获取密码盐
        String salt = CryptUtils.generateToken();
        setPassword(CryptUtils.sha256Hex(password + salt));
        setPasswordSalt(salt);
    }

    //登录密码比较
    public boolean validPwd(String password) {
        return CryptUtils.sha256Hex(password + this.getPasswordSalt()).equalsIgnoreCase(this.getPassword());
    }
}
