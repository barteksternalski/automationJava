package models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SpinResponseFinished {

    @SerializedName("code")
    @Expose
    private Integer code;
    @SerializedName("data")
    @Expose
    private DataFinished data;
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

    public DataFinished getData() {
        return data;
    }

    public void setData(DataFinished data) {
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
