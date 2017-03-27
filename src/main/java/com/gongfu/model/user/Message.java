package com.gongfu.model.user;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by a on 2017/3/25.
 */
@Data
@Table(name = "gongfu_message")
public class Message implements Serializable {
    private static final long serialVersionUID = 2028582880305944686L;
    @Id
    private Long id;
}
