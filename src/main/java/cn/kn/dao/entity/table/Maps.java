package cn.kn.dao.entity.table;

import lombok.Data;

import java.io.Serializable;

/**
 * @version 1.0
 * @Author 马国宁
 * @Date 2020/1/9 15:19
 * @Description
 */

@Data
public class Maps implements Serializable {
    private String key;
    private String value;
}
