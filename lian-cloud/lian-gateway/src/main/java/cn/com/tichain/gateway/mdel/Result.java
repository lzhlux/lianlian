package cn.com.tichain.gateway.mdel;

import com.google.gson.Gson;
import java.io.Serializable;

public class Result<D> implements Serializable {

    private Boolean success;
    private D data;
    private String message;

    public Result() {
    }

    public Result(Boolean success, D data, String message) {
        this.success = success;
        this.data = data;
        this.message = message;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
