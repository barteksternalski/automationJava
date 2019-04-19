package models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PrizeFinished {

    @SerializedName("prizeid")
    @Expose
    private String prizeid;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("descr")
    @Expose
    private String descr;
    @SerializedName("wonamount")
    @Expose
    private String wonamount;
    @SerializedName("netamount")
    @Expose
    private String netamount;
    @SerializedName("woncurrency")
    @Expose
    private String woncurrency;
    @SerializedName("wonAspect")
    @Expose
    private String wonAspect;
    @SerializedName("prizedata")
    @Expose
    private Object prizedata;
    @SerializedName("gameId")
    @Expose
    private Integer gameId;

    public String getPrizeid() {
        return prizeid;
    }

    public void setPrizeid(String prizeid) {
        this.prizeid = prizeid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public String getWonamount() {
        return wonamount;
    }

    public void setWonamount(String wonamount) {
        this.wonamount = wonamount;
    }

    public String getNetamount() {
        return netamount;
    }

    public void setNetamount(String netamount) {
        this.netamount = netamount;
    }

    public String getWoncurrency() {
        return woncurrency;
    }

    public void setWoncurrency(String woncurrency) {
        this.woncurrency = woncurrency;
    }

    public String getWonAspect() {
        return wonAspect;
    }

    public void setWonAspect(String wonAspect) {
        this.wonAspect = wonAspect;
    }

    public Object getPrizedata() {
        return prizedata;
    }

    public void setPrizedata(Object prizedata) {
        this.prizedata = prizedata;
    }

    public Integer getGameId() {
        return gameId;
    }

    public void setGameId(Integer gameId) {
        this.gameId = gameId;
    }

}
