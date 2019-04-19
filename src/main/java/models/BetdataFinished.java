package models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BetdataFinished {

    @SerializedName("doubleA")
    @Expose
    private String doubleA;
    @SerializedName("doubleN")
    @Expose
    private Integer doubleN;
    @SerializedName("cmd")
    @Expose
    private String cmd;
    @SerializedName("cheat")
    @Expose
    private Object cheat;
    @SerializedName("lines")
    @Expose
    private String lines;
    @SerializedName("coin")
    @Expose
    private String coin;
    @SerializedName("nCoins")
    @Expose
    private Integer nCoins;
    @SerializedName("variant")
    @Expose
    private Object variant;
    @SerializedName("restoredAccumulatedWonCoin")
    @Expose
    private Integer restoredAccumulatedWonCoin;

    public String getDoubleA() {
        return doubleA;
    }

    public void setDoubleA(String doubleA) {
        this.doubleA = doubleA;
    }

    public Integer getDoubleN() {
        return doubleN;
    }

    public void setDoubleN(Integer doubleN) {
        this.doubleN = doubleN;
    }

    public String getCmd() {
        return cmd;
    }

    public void setCmd(String cmd) {
        this.cmd = cmd;
    }

    public Object getCheat() {
        return cheat;
    }

    public void setCheat(Object cheat) {
        this.cheat = cheat;
    }

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

    public Object getVariant() {
        return variant;
    }

    public void setVariant(Object variant) {
        this.variant = variant;
    }

    public Integer getRestoredAccumulatedWonCoin() {
        return restoredAccumulatedWonCoin;
    }

    public void setRestoredAccumulatedWonCoin(Integer restoredAccumulatedWonCoin) {
        this.restoredAccumulatedWonCoin = restoredAccumulatedWonCoin;
    }

}
