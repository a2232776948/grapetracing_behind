package cn.edu.scau.blockchain;

import cn.edu.scau.dao.FarmOPDao;
import cn.edu.scau.dao.TreeDao;
import cn.edu.scau.dao.UserDao;
import cn.edu.scau.model.*;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web3j.protocol.core.Response;
import org.web3j.protocol.core.methods.response.Transaction;
import org.web3j.utils.Numeric;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Service
public class BlockchainService {
    @Autowired
    FarmOPDao farmOPDao;

    @Autowired
    TreeDao treeDao;

    @Autowired
    UserDao userDao;

    @Autowired
    GethClient gethClient;

    public int push() {
        List<FarmOP> farmOPS = farmOPDao.selectmanyByEditable(true);
        int count = 0;
        for (FarmOP farmOP : farmOPS) {
            BlockChainInput blockChainInput = new BlockChainInput();
            blockChainInput.setData(farmOP.getCategory() + " " + farmOP.getDesc());
            blockChainInput.setDate(farmOP.getDate());
            Tree tree = treeDao.selectTreeById(farmOP.getTree_id());
            blockChainInput.setPrev(tree.getHash());
            Object json = JSON.toJSON(blockChainInput);
            BlockChainAccount account = userDao.selectBlockChainAccount(farmOP.getUser_id());
            try {
                String txHash;
                while ((txHash = gethClient.input(account.getHash(), account.getPassword(), json.toString(), gethClient.accounts().get(0))) == null) {
                    System.out.println(txHash);
                    gethClient.sendTransaction(gethClient.accounts().get(0), account.getHash(), BigInteger.valueOf(1_000_000_000_000_000_000L));
                }
                tree.setHash(txHash);
                treeDao.updateHashById(tree.getId(), tree.getHash());
                farmOP.setEditable(false);
                farmOPDao.updateOne(farmOP);
                count++;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return count - farmOPS.size();
    }

    public String init(Tree tree, BlockChainAccount account) {
        BlockChainInput input = new BlockChainInput();
        input.setData("种植于" + tree.getArea() + " " + "种类：" + tree.getCategory() + " " + "");
        input.setDate(tree.getPlant_date());
        input.setPrev("");
        String json = JSON.toJSON(input).toString();
        try {
            String txHash;
            while ((txHash = gethClient.input(account.getHash(), account.getPassword(), json, gethClient.accounts().get(0))) == null) {
                System.out.println(txHash);
                gethClient.sendTransaction(gethClient.accounts().get(0), account.getHash(), BigInteger.valueOf(1_000_000_000_000_000_000L));
            }
            return txHash;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public List<BlockChainNode> pop(long tree_id) {
        String hash = treeDao.selectTreeById(tree_id).getHash();
        ArrayList<BlockChainNode> chainNodes = new ArrayList<>();
        try {
            do {
                Transaction transaction = gethClient.output(hash);
                if (transaction != null) {
                    BlockChainInput input = JSON.parseObject(new String(Numeric.hexStringToByteArray(transaction.getInput())), BlockChainInput.class);
                    chainNodes.add(new BlockChainNode(input, transaction.getFrom(), transaction.getHash()));
                    hash = input.getPrev();
                }
            } while (hash != null && hash.startsWith("0x"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return chainNodes;
    }

    public BigInteger getBlockNumber() {
        return gethClient.getCurrentBlockNumber();
    }

    public boolean startMiner(){
        try {
            gethClient.startMiner(1);
            return gethClient.mining();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean stopMiner(){
        try {
            gethClient.stopMiner();
            return !gethClient.mining();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean mining() {
        try {
            return gethClient.mining();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
