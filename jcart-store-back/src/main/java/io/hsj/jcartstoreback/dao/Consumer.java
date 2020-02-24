package io.hsj.jcartstoreback.dao;

public class Consumer {
    private String username;
    private String realName;
    private String mobile;
    private String email;
    private String password;
    private Boolean newsSubscribed;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
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

    public Boolean getNewsSubscribed() {
        return newsSubscribed;
    }

    public void setNewsSubscribed(Boolean newsSubscribed) {
        this.newsSubscribed = newsSubscribed;
    }

    public Consumer(String username, String realName, String mobile, String email, String password, Boolean newsSubscribed) {
        this.username = username;
        this.realName = realName;
        this.mobile = mobile;
        this.email = email;
        this.password = password;
        this.newsSubscribed = newsSubscribed;
    }

    public Consumer() {
    }

    @Override
    public String toString() {
        return "Consumer{" +
                "username='" + username + '\'' +
                ", realName='" + realName + '\'' +
                ", mobile='" + mobile + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", newsSubscribed=" + newsSubscribed +
                '}';
    }
}
