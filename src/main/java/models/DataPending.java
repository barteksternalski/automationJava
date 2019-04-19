package models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataPending {

    @SerializedName("wager")
    @Expose
    private WagerPending wager;
    @SerializedName("buyBal")
    @Expose
    private BuyBalPending buyBal;
    @SerializedName("resultBal")
    @Expose
    private ResultBalPending resultBal;
    @SerializedName("superSpinBalance")
    @Expose
    private Object superSpinBalance;
    @SerializedName("promotion")
    @Expose
    private PromotionPending promotion;
    @SerializedName("obj")
    @Expose
    private Object obj;
    @SerializedName("prepaidBalance")
    @Expose
    private Object prepaidBalance;
    @SerializedName("cashRace")
    @Expose
    private CashRacePending cashRace;
    @SerializedName("missionState")
    @Expose
    private Object missionState;

    public WagerPending getWager() {
        return wager;
    }

    public void setWager(WagerPending wager) {
        this.wager = wager;
    }

    public BuyBalPending getBuyBal() {
        return buyBal;
    }

    public void setBuyBal(BuyBalPending buyBal) {
        this.buyBal = buyBal;
    }

    public ResultBalPending getResultBal() {
        return resultBal;
    }

    public void setResultBal(ResultBalPending resultBal) {
        this.resultBal = resultBal;
    }

    public Object getSuperSpinBalance() {
        return superSpinBalance;
    }

    public void setSuperSpinBalance(Object superSpinBalance) {
        this.superSpinBalance = superSpinBalance;
    }

    public PromotionPending getPromotion() {
        return promotion;
    }

    public void setPromotion(PromotionPending promotion) {
        this.promotion = promotion;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

    public Object getPrepaidBalance() {
        return prepaidBalance;
    }

    public void setPrepaidBalance(Object prepaidBalance) {
        this.prepaidBalance = prepaidBalance;
    }

    public CashRacePending getCashRace() {
        return cashRace;
    }

    public void setCashRace(CashRacePending cashRace) {
        this.cashRace = cashRace;
    }

    public Object getMissionState() {
        return missionState;
    }

    public void setMissionState(Object missionState) {
        this.missionState = missionState;
    }

}