package models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SpinResponsePending {

    @SerializedName("code")
    @Expose
    private Integer code;
    @SerializedName("data")
    @Expose
    private DataPending data;
    @SerializedName("fn")
    @Expose
    private String fn;
    @SerializedName("utcts")
    @Expose
    private Long utcts;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public DataPending getData() {
        return data;
    }

    public void setData(DataPending data) {
        this.data = data;
    }

    public String getFn() {
        return fn;
    }

    public void setFn(String fn) {
        this.fn = fn;
    }

    public Long getUtcts() {
        return utcts;
    }

    public void setUtcts(Long utcts) {
        this.utcts = utcts;
    }

}
