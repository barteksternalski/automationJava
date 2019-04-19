package models;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BetPending {

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
    private BetdataPending betdata;
    @SerializedName("eventdata")
    @Expose
    private EventdataPending eventdata;
    @SerializedName("prizes")
    @Expose
    private Object prizes;
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

    public BetdataPending getBetdata() {
        return betdata;
    }

    public void setBetdata(BetdataPending betdata) {
        this.betdata = betdata;
    }

    public EventdataPending getEventdata() {
        return eventdata;
    }

    public void setEventdata(EventdataPending eventdata) {
        this.eventdata = eventdata;
    }

    public Object getPrizes() {
        return prizes;
    }

    public void setPrizes(Object prizes) {
        this.prizes = prizes;
    }

    public Integer getPrepaidId() {
        return prepaidId;
    }

    public void setPrepaidId(Integer prepaidId) {
        this.prepaidId = prepaidId;
    }

}