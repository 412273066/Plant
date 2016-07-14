package com.jlk.plant.models.returnmodels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class LoginReturn extends BaseReturn {
    @Expose
    @SerializedName("list")
    private List<User> list; //

    public List<User> getList() {
        return list;
    }

    public void setList(List<User> list) {
        this.list = list;
    }

    public class User {
        @Expose
        @SerializedName("user")
        private String user;
        @Expose
        @SerializedName("password")
        private String password;
        @Expose
        @SerializedName("nickname")
        private String nickname;
        @Expose
        @SerializedName("sex")
        private String sex;
        @Expose
        @SerializedName("img")
        private String img;
        @Expose
        @SerializedName("name")
        private String level;

        public User(String user, String password, String nickname, String sex, String img, String level) {
            this.user = user;
            this.password = password;
            this.nickname = nickname;
            this.sex = sex;
            this.img = img;
            this.level = level;
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

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }
    }
}
