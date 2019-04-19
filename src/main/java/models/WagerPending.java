package models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WagerPending {

    @SerializedName("wagerid")
    @Expose
    private String wagerid;
    @SerializedName("timestamp")
    @Expose
    private Long timestamp;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("prepaidBase")
    @Expose
    private Object prepaidBase;
    @SerializedName("superSpinBalance")
    @Expose
    private Object superSpinBalance;
    @SerializedName("bets")
    @Expose
    private List<BetPending> bets = null;
    @SerializedName("promotion")
    @Expose
    private PromotionPending promotion;

    public String getWagerid() {
        return wagerid;
    }

    public void setWagerid(String wagerid) {
        this.wagerid = wagerid;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getPrepaidBase() {
        return prepaidBase;
    }

    public void setPrepaidBase(Object prepaidBase) {
        this.prepaidBase = prepaidBase;
    }

    public Object getSuperSpinBalance() {
        return superSpinBalance;
    }

    public void setSuperSpinBalance(Object superSpinBalance) {
        this.superSpinBalance = superSpinBalance;
    }

    public List<BetPending> getBets() {
        return bets;
    }

    public void setBets(List<BetPending> bets) {
        this.bets = bets;
    }

    public PromotionPending getPromotion() {
        return promotion;
    }

    public void setPromotion(PromotionPending promotion) {
        this.promotion = promotion;
    }

}
