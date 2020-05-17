package cn.edu.scau.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BlockChainNode extends BlockChainInput {
    private String from;
    private String txHash;


    public BlockChainNode(BlockChainInput input, String from, String txHash) {
        super(input.getData(), input.getDate(), input.getPrev());
        this.from = from;
        this.txHash = txHash;
    }

    public BlockChainNode(String from, String txHash) {
        this.from = from;
        this.txHash = txHash;
    }
}
