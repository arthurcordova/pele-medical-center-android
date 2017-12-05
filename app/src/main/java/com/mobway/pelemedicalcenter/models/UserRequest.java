package com.mobway.pelemedicalcenter.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by arthur.stapassoli on 05/12/2017.
 */

public class UserRequest {
    @SerializedName("codcliente")
    public String uuid;
    @SerializedName("nome")
    public String name;
    public String email;
    @SerializedName("senha")
    public String pwd;

    public UserRequest(String email, String pwd) {
        this.email = email;
        this.pwd = pwd;
    }

    public UserRequest(String name, String email, String pwd) {
        this.name = name;
        this.email = email;
        this.pwd = pwd;
    }
}
