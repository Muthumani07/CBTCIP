package onlineexam;

import java.io.Serializable;

public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    private String username;
    private String password;
    private String fullName;

    public User(String username, String password, String fullName) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
    }

    public String getUsername() {
        return username;
    }

    public String getFullName() {
        return fullName;
    }

    public boolean checkPassword(String password) {
        return this.password.equals(password);
    }

    public void updateProfile(String newFullName) {
        this.fullName = newFullName;
    }

    public void updatePassword(String newPassword) {
        this.password = newPassword;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", fullName='" + fullName + '\'' +
                '}';
    }
}
