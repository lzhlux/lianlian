package cn.com.tichain.gateway.mdel;

import java.util.List;

public class AccountDTO<T> {

    /**
     * success : true
     * data : {"token":"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpYXQiOjE1MzYzMTU2NTksImV4cCI6MTUzNjQwMjA4OSwidW5hbWUiOiJyb290IiwidWlkIjoyfQ.Af6x4RibvqI79qM3SdGVLrp44GJM4zu66LF1hy5SLdY","id":2,"account":"root","email":null,"phone":"15699999999","nikename":"root","avatar":null,"status":0,"roles":["root","normal"],"created_at":"2018-09-07 18:21:29","updated_at":"2018-09-07 18:21:29"}
     * message : 登录成功
     */

    private boolean success;
    private T data;
    private String message;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static class DataBean {

        /**
         * token : eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpYXQiOjE1MzYzMTU2NTksImV4cCI6MTUzNjQwMjA4OSwidW5hbWUiOiJyb290IiwidWlkIjoyfQ.Af6x4RibvqI79qM3SdGVLrp44GJM4zu66LF1hy5SLdY
         * id : 2
         * account : root
         * email : null
         * phone : 15699999999
         * nikename : root
         * avatar : null
         * status : 0
         * roles : ["root","normal"]
         * created_at : 2018-09-07 18:21:29
         * updated_at : 2018-09-07 18:21:29
         */

        private String token;
        private int id;
        private String account;
        private Object email;
        private String phone;
        private boolean can_pay;
        private String nikename;
        private Object avatar;
        private int status;
        private String created_at;
        private String updated_at;
        private List<String> roles;


        public boolean isCan_pay() {
            return can_pay;
        }

        public void setCan_pay(boolean can_pay) {
            this.can_pay = can_pay;
        }

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
}
