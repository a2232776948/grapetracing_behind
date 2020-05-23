package cn.edu.scau.blockchain;

import cn.edu.scau.model.BlockChainAccount;
import org.springframework.stereotype.Service;
import org.web3j.protocol.admin.Admin;
import org.web3j.protocol.admin.methods.response.PersonalUnlockAccount;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.*;
import org.web3j.protocol.geth.Geth;
import org.web3j.protocol.http.HttpService;
import org.web3j.utils.Numeric;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import static org.web3j.tx.Contract.GAS_LIMIT;
import static org.web3j.tx.ManagedTransaction.GAS_PRICE;

@Service
public class  GethClient {
    private Admin admin;
    private Geth geth;

    public GethClient(String gethClientUrl) {
        this.admin = Admin.build(new HttpService(gethClientUrl));
        this.geth = Geth.build(new HttpService(gethClientUrl));
    }

    public GethClient() {
        this.admin = Admin.build(new HttpService("http://127.0.0.1:8545"));
        this.geth = Geth.build(new HttpService("http://127.0.0.1:8545"));
    }

    public BigInteger getCurrentBlockNumber() {
        try {
            EthBlockNumber blockNumber = admin.ethBlockNumber().send();
            return blockNumber.getBlockNumber();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return BigInteger.valueOf(0L);
    }

    public List<String> accounts() {
        List<String> ids = new ArrayList<>();
        try {
            ids = admin.personalListAccounts().send().getAccountIds();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ids;
    }


    public BigInteger getBalance(String address) {
        BigInteger balance = null;
        try {
            balance = admin.ethGetBalance(address, DefaultBlockParameterName.LATEST).send().getBalance();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return balance;
    }

    public String input(String from, String passphrase, String data, String to) throws Exception {
        String hexString = Numeric.toHexString(data.getBytes());

        //解锁账户
        PersonalUnlockAccount personalUnlockAccount = admin
                .personalUnlockAccount(from, passphrase).send();

        if (personalUnlockAccount.accountUnlocked()) {
            //nonce
            EthGetTransactionCount ethGetTransactionCount = admin
                    .ethGetTransactionCount(from, DefaultBlockParameterName.LATEST)
                    .send();
            BigInteger nonce = ethGetTransactionCount.getTransactionCount();

            //发送交易
            org.web3j.protocol.core.methods.request.Transaction transaction = org.web3j.protocol.core.methods.request.Transaction.createFunctionCallTransaction(from,
                    nonce, GAS_PRICE, GAS_LIMIT, to, BigInteger.valueOf(20L), hexString);
            EthSendTransaction transactionResponse = admin
                    .ethSendTransaction(transaction).send();

            //获取交易块的交易hash
            return transactionResponse.getTransactionHash();
        } else {
            return "";
        }

    }

    public Transaction output(String txHash) throws IOException {
        //根据交易hash查找交易
        EthTransaction ethTransaction = admin.ethGetTransactionByHash(txHash).send();

        //返回交易块
        return ethTransaction.getResult();
    }

    public BlockChainAccount createAccount(String password) {
        String hash = null;
        try {
            hash = admin.personalNewAccount(password).send().getAccountId();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (hash != null && hash.startsWith("0x")) {
            return new BlockChainAccount(hash, password);
        }
        return null;
    }

    public String sendTransaction(String from, String to, BigInteger value) throws IOException {
        PersonalUnlockAccount personalUnlockAccount = admin.personalUnlockAccount(accounts().get(0), "123456").send();
        if (personalUnlockAccount.accountUnlocked()) {
            //nonce
            EthGetTransactionCount ethGetTransactionCount = admin
                    .ethGetTransactionCount(from, DefaultBlockParameterName.LATEST)
                    .send();
            BigInteger nonce = ethGetTransactionCount.getTransactionCount();

            org.web3j.protocol.core.methods.request.Transaction transaction = org.web3j.protocol.core.methods.request.Transaction.createEtherTransaction(from, nonce, GAS_PRICE, GAS_LIMIT, to, value);
            return admin.ethSendTransaction(transaction).send().getTransactionHash();
        }
        return "";
    }

    public void startMiner(int i) throws IOException {
        geth.minerStart(i).send();
    }

    public void stopMiner() throws IOException {
        geth.minerStop().send();
    }

    public boolean mining() throws IOException {
        return admin.ethMining().send().isMining();
    }
}
