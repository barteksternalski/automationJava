package models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EventdataPending {

    @SerializedName("accC")
    @Expose
    private Integer accC;
    @SerializedName("accWa")
    @Expose
    private String accWa;
    @SerializedName("doubleAmount")
    @Expose
    private String doubleAmount;
    @SerializedName("doubleCount")
    @Expose
    private Integer doubleCount;
    @SerializedName("nextCmds")
    @Expose
    private String nextCmds;
    @SerializedName("reelSet")
    @Expose
    private String reelSet;
    @SerializedName("reels")
    @Expose
    private List<List<String>> reels = null;
    @SerializedName("rpos")
    @Expose
    private List<Integer> rpos = null;
    @SerializedName("wonCoins")
    @Expose
    private Integer wonCoins;
    @SerializedName("wts")
    @Expose
    private List<String> wts = null;
    @SerializedName("wtw")
    @Expose
    private List<List<String>> wtw = null;

    public Integer getAccC() {
        return accC;
    }

    public void setAccC(Integer accC) {
        this.accC = accC;
    }

    public String getAccWa() {
        return accWa;
    }

    public void setAccWa(String accWa) {
        this.accWa = accWa;
    }

    public String getDoubleAmount() {
        return doubleAmount;
    }

    public void setDoubleAmount(String doubleAmount) {
        this.doubleAmount = doubleAmount;
    }

    public Integer getDoubleCount() {
        return doubleCount;
    }

    public void setDoubleCount(Integer doubleCount) {
        this.doubleCount = doubleCount;
    }

    public String getNextCmds() {
        return nextCmds;
    }

    public void setNextCmds(String nextCmds) {
        this.nextCmds = nextCmds;
    }

    public String getReelSet() {
        return reelSet;
    }

    public void setReelSet(String reelSet) {
        this.reelSet = reelSet;
    }

    public List<List<String>> getReels() {
        return reels;
    }

    public void setReels(List<List<String>> reels) {
        this.reels = reels;
    }

    public List<Integer> getRpos() {
        return rpos;
    }

    public void setRpos(List<Integer> rpos) {
        this.rpos = rpos;
    }

    public Integer getWonCoins() {
        return wonCoins;
    }

    public void setWonCoins(Integer wonCoins) {
        this.wonCoins = wonCoins;
    }

    public List<String> getWts() {
        return wts;
    }

    public void setWts(List<String> wts) {
        this.wts = wts;
    }

    public List<List<String>> getWtw() {
        return wtw;
    }

    public void setWtw(List<List<String>> wtw) {
        this.wtw = wtw;
    }

}