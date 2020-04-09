package cn.edu.scau.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TreeCategory {
    public String name;
    public int count;
    public int alive;
    public int dead;
}
