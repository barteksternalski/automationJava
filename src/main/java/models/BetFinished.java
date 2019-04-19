package models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BetFinished {

    @SerializedName("step")
    @Expose
    private Integer step;
    @SerializedName("betamount")
    @Expose
    private String betamount;
    @SerializedName("wonamount")
    @Expose
    private String wonamount;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("betdata")
    @Expose
    private BetdataFinished betdata;
    @SerializedName("eventdata")
    @Expose
    private EventdataFinished eventdata;
    @SerializedName("prizes")
    @Expose
    private List<PrizeFinished> prizes = null;
    @SerializedName("prepaidId")
    @Expose
    private Integer prepaidId;

    public Integer getStep() {
        return step;
    }

    public void setStep(Integer step) {
        this.step = step;
    }

    public String getBetamount() {
        return betamount;
    }

    public void setBetamount(String betamount) {
        this.betamount = betamount;
    }

    public String getWonamount() {
        return wonamount;
    }

    public void setWonamount(String wonamount) {
        this.wonamount = wonamount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BetdataFinished getBetdata() {
        return betdata;
    }

    public void setBetdata(BetdataFinished betdata) {
        this.betdata = betdata;
    }

    public EventdataFinished getEventdata() {
        return eventdata;
    }

    public void setEventdata(EventdataFinished eventdata) {
        this.eventdata = eventdata;
    }

    public List<PrizeFinished> getPrizes() {
        return prizes;
    }

    public void setPrizes(List<PrizeFinished> prizes) {
        this.prizes = prizes;
    }

    public Integer getPrepaidId() {
        return prepaidId;
    }

    public void setPrepaidId(Integer prepaidId) {
        this.prepaidId = prepaidId;
    }

}