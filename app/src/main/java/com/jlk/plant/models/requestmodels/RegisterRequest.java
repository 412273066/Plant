package com.jlk.plant.models.requestmodels;

public class RegisterRequest extends BaseRequest {
    protected String user;
    protected String password;
    protected String com_password;
    protected String nickname;
    protected String captcha;

    public RegisterRequest(String user, String password, String com_password, String nickname, String captcha) {
        this.user = user;
        this.password = password;
        this.com_password = com_password;
        this.nickname = nickname;
        this.captcha = captcha;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCom_password() {
        return com_password;
    }

    public void setCom_password(String com_password) {
        this.com_password = com_password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }
}
