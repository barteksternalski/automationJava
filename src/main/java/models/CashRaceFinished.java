package models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CashRaceFinished {

    @SerializedName("hasWon")
    @Expose
    private Boolean hasWon;
    @SerializedName("prize")
    @Expose
    private Object prize;
    @SerializedName("initialPrize")
    @Expose
    private Object initialPrize;
    @SerializedName("currency")
    @Expose
    private Object currency;
    @SerializedName("resource")
    @Expose
    private Object resource;

    public Boolean getHasWon() {
        return hasWon;
    }

    public void setHasWon(Boolean hasWon) {
        this.hasWon = hasWon;
    }

    public Object getPrize() {
        return prize;
    }

    public void setPrize(Object prize) {
        this.prize = prize;
    }

    public Object getInitialPrize() {
        return initialPrize;
    }

    public void setInitialPrize(Object initialPrize) {
        this.initialPrize = initialPrize;
    }

    public Object getCurrency() {
        return currency;
    }

    public void setCurrency(Object currency) {
        this.currency = currency;
    }

    public Object getResource() {
        return resource;
    }

    public void setResource(Object resource) {
        this.resource = resource;
    }

}
