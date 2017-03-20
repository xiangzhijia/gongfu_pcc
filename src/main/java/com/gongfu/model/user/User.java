package com.gongfu.model.user;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Data
@Table(name = "gongfu_user")
public class User implements Serializable {
    private static final long serialVersionUID = 5580339211793592522L;
    @Id
    private Long id;
    private String name;
    private Date createdAt;
    private String nick;
    @Column(name = "password")
    private String password;
    private String lastLoginIp;
}
