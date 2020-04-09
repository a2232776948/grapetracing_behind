package cn.edu.scau.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role implements Serializable {
    private Integer id;

    private String name;

    private String nameZh;

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

}
