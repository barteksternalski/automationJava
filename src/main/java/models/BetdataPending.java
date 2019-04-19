package models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BetdataPending {

    @SerializedName("lines")
    @Expose
    private String lines;
    @SerializedName("coin")
    @Expose
    private String coin;
    @SerializedName("nCoins")
    @Expose
    private Integer nCoins;
    @SerializedName("cheat")
    @Expose
    private Object cheat;
    @SerializedName("clientData")
    @Expose
    private Object clientData;
    @SerializedName("initialBalance")
    @Expose
    private String initialBalance;

    public String getLines() {
        return lines;
    }

    public void setLines(String lines) {
        this.lines = lines;
    }

    public String getCoin() {
        return coin;
    }

    public void setCoin(String coin) {
        this.coin = coin;
    }

    public Integer getNCoins() {
        return nCoins;
    }

    public void setNCoins(Integer nCoins) {
        this.nCoins = nCoins;
    }

    public Object getCheat() {
        return cheat;
    }

    public void setCheat(Object cheat) {
        this.cheat = cheat;
    }

    public Object getClientData() {
        return clientData;
    }

    public void setClientData(Object clientData) {
        this.clientData = clientData;
    }

    public String getInitialBalance() {
        return initialBalance;
    }

    public void setInitialBalance(String initialBalance) {
        this.initialBalance = initialBalance;
    }

}
