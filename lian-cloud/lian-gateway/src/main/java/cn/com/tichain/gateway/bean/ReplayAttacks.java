package cn.com.tichain.gateway.bean;

public class ReplayAttacks {
    private long timestamp;
    private long nonce;
    private String sign;

    public ReplayAttacks(long timestamp, long nonce, String sign) {
        this.timestamp = timestamp;
        this.nonce = nonce;
        this.sign = sign;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public long getNonce() {
        return nonce;
    }

    public String getSign() {
        return sign;
    }
}
