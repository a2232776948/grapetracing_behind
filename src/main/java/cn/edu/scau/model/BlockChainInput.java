package cn.edu.scau.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BlockChainInput {
    private String data;
    private Date date;
    private String prev;

    public void setInput(BlockChainInput input) {
        this.data = input.data;
        this.date = input.date;
        this.prev = input.prev;
    }
}
