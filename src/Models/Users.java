package Models;

import java.time.LocalDateTime;

public class Users {
    private int userId;
    private String userName;
    private String password;
    private String createdBy;
    private String lastUpdatedBy;
    private LocalDateTime lastUpdate;
    private LocalDateTime createDate;

    public Users(int userId, String userName, String password, String createdBy, String lastUpdatedBy, LocalDateTime lastUpdate, LocalDateTime createDate) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.createdBy = createdBy;
        this.lastUpdatedBy = lastUpdatedBy;
        this.lastUpdate = lastUpdate;
        this.createDate = createDate;
    }

    public Users(String userName) {
        this.userName = userName;
    }

    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public LocalDateTime getCreateDate() {
        return createDate;
    }
    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }
    public String getCreatedBy() {
        return createdBy;
    }
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }
    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }


    public String toString() {
        return this.userName;
    }
}
