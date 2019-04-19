package models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataFinished {

    @SerializedName("wager")
    @Expose
    private WagerFinished wager;
    @SerializedName("buyBal")
    @Expose
    private BuyBalFinished buyBal;
    @SerializedName("resultBal")
    @Expose
    private ResultBalFinished resultBal;
    @SerializedName("superSpinBalance")
    @Expose
    private Object superSpinBalance;
    @SerializedName("promotion")
    @Expose
    private PromotionFinished promotion;
    @SerializedName("obj")
    @Expose
    private Object obj;
    @SerializedName("prepaidBalance")
    @Expose
    private Object prepaidBalance;
    @SerializedName("cashRace")
    @Expose
    private CashRaceFinished cashRace;
    @SerializedName("missionState")
    @Expose
    private Object missionState;

    public WagerFinished getWager() {
        return wager;
    }

    public void setWager(WagerFinished wager) {
        this.wager = wager;
    }

    public BuyBalFinished getBuyBal() {
        return buyBal;
    }

    public void setBuyBal(BuyBalFinished buyBal) {
        this.buyBal = buyBal;
    }

    public ResultBalFinished getResultBal() {
        return resultBal;
    }

    public void setResultBal(ResultBalFinished resultBal) {
        this.resultBal = resultBal;
    }

    public Object getSuperSpinBalance() {
        return superSpinBalance;
    }

    public void setSuperSpinBalance(Object superSpinBalance) {
        this.superSpinBalance = superSpinBalance;
    }

    public PromotionFinished getPromotion() {
        return promotion;
    }

    public void setPromotion(PromotionFinished promotion) {
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

    public CashRaceFinished getCashRace() {
        return cashRace;
    }

    public void setCashRace(CashRaceFinished cashRace) {
        this.cashRace = cashRace;
    }

    public Object getMissionState() {
        return missionState;
    }

    public void setMissionState(Object missionState) {
        this.missionState = missionState;
    }

}
