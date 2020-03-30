package cn.com.tichain.gateway.mdel;

public class AccountCredentials {


    public AccountCredentials(String account, String phone, String email, String password,
            JpushBean jpush, String smsCode) {
        this.account = account;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.jpush = jpush;
        this.smsCode = smsCode;
    }

    /**
     * account : 1
     * password : c
     * jpush : {"app_name":"cx","reg_id":"s"}
     */

    private String account;
    private String phone;
    private String email;
    private String password;
    private JpushBean jpush;
    private String smsCode;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public JpushBean getJpush() {
        return jpush;
    }

    public void setJpush(JpushBean jpush) {
        this.jpush = jpush;
    }

    public String getSmsCode() {
        return smsCode;
    }

    public void setSmsCode(String smsCode) {
        this.smsCode = smsCode;
    }

    public static class JpushBean {

        /**
         * app_name : cx
         * reg_id : s
         */

        private String app_name;
        private String reg_id;

        public String getApp_name() {
            return app_name;
        }

        public void setApp_name(String app_name) {
            this.app_name = app_name;
        }

        public String getReg_id() {
            return reg_id;
        }

        public void setReg_id(String reg_id) {
            this.reg_id = reg_id;
        }
    }
}
