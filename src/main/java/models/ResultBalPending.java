package models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResultBalPending {

    @SerializedName("cash")
    @Expose
    private String cash;

    public String getCash() {
        return cash;
    }

    public void setCash(String cash) {
        this.cash = cash;
    }

}