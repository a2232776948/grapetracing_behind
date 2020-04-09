package cn.edu.scau.model;

import lombok.*;

import java.util.Base64;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Area {
    public long id;
    public String name;
    public int treeCount;
    public String address;
    public String status;
}
