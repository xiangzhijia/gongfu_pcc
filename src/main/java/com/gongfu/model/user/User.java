package com.gongfu.model.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

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
    private byte state; //用户状态,0:创建未认证、1:正常状态、2:用户被锁定
    private String lastLoginIp;
}
