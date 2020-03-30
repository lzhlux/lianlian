package cn.com.tichain.gateway.mdel;

import java.util.List;

public class Account {

    /**
     * token : eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpYXQiOjE1MzYzNzMyMTQsImV4cCI6MTUzNjQ1OTY0NCwidW5hbWUiOiJyb290IiwidWlkIjoyfQ.v_TgN4V6InPjJjGJZ369xveqCebst5wu8qW6z3qg9IY
     * id : 2
     * account : root
     * email : null
     * phone : 15699999999
     * nikename : root
     * avatar : null
     * status : 0
     * roles : ["normal","root"]
     * created_at : 2018-09-08 10:20:44
     * updated_at : 2018-09-08 10:20:44
     */

    private String token;
    private int id;
    private String account;
    private Object email;
    private String phone;
    private String nikename;
    private Object avatar;
    private int status;
    private String created_at;
    private String updated_at;
    private List<String> roles;
    private int verification;
    private Object jpush;

    public Object getJpush() {
        return jpush;
    }

    public void setJpush(Object jpush) {
        this.jpush = jpush;
    }

    public int getVerification() {
        return verification;
    }

    public void setVerification(int verification) {
        this.verification = verification;
    }

    public boolean isCan_pay() {
        return can_pay;
    }

    public void setCan_pay(boolean can_pay) {
        this.can_pay = can_pay;
    }

    private boolean can_pay;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public Object getEmail() {
        return email;
    }

    public void setEmail(Object email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNikename() {
        return nikename;
    }

    public void setNikename(String nikename) {
        this.nikename = nikename;
    }

    public Object getAvatar() {
        return avatar;
    }

    public void setAvatar(Object avatar) {
        this.avatar = avatar;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
