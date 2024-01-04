package com.poploli.onlinefilmreviewsystem.dto;

public class PasswordChangeDTO {

    private String oldPassword;
    private String newPassword;

    // Constructor
    public PasswordChangeDTO() {
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    @Override
    public String toString() {
        return "PasswordChangeDTO{" +
                "oldPassword='" + oldPassword + '\'' +
                ", newPassword='" + newPassword + '\'' +
                '}';
    }
}
