package com.ajeco.boom.login;

public class User {
    private String userId;
    private String email;
    private String password;
    private String name;
    private String balance;
    private String settings;
    private String image;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getUserId() {
        return userId;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getBalance() {
        return balance;
    }

    public String getSettings() {
        return settings;
    }

    public String getImage() {
        return image;
    }

    public String[] toStringArray() {
        return new String[]{userId, email, password, name, balance, settings, image};
    }

    @Override
    public String toString() {
        return "userId='" + userId + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", balance='" + balance + '\'' +
                ", settings='" + settings + '\'' +
                ", image='" + image;
    }
}
