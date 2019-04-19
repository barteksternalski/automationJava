package models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PromotionPending {

    @SerializedName("promotionType")
    @Expose
    private Object promotionType;
    @SerializedName("balance")
    @Expose
    private Object balance;
    @SerializedName("prepaidBase")
    @Expose
    private Object prepaidBase;
    @SerializedName("spinsLeft")
    @Expose
    private Integer spinsLeft;
    @SerializedName("spinsPlayed")
    @Expose
    private Integer spinsPlayed;
    @SerializedName("totalSpins")
    @Expose
    private Integer totalSpins;
    @SerializedName("totalWin")
    @Expose
    private Object totalWin;

    public Object getPromotionType() {
        return promotionType;
    }

    public void setPromotionType(Object promotionType) {
        this.promotionType = promotionType;
    }

    public Object getBalance() {
        return balance;
    }

    public void setBalance(Object balance) {
        this.balance = balance;
    }

    public Object getPrepaidBase() {
        return prepaidBase;
    }

    public void setPrepaidBase(Object prepaidBase) {
        this.prepaidBase = prepaidBase;
    }

    public Integer getSpinsLeft() {
        return spinsLeft;
    }

    public void setSpinsLeft(Integer spinsLeft) {
        this.spinsLeft = spinsLeft;
    }

    public Integer getSpinsPlayed() {
        return spinsPlayed;
    }

    public void setSpinsPlayed(Integer spinsPlayed) {
        this.spinsPlayed = spinsPlayed;
    }

    public Integer getTotalSpins() {
        return totalSpins;
    }

    public void setTotalSpins(Integer totalSpins) {
        this.totalSpins = totalSpins;
    }

    public Object getTotalWin() {
        return totalWin;
    }

    public void setTotalWin(Object totalWin) {
        this.totalWin = totalWin;
    }

}
