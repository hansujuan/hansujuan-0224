package com.hansujuan.jcartadministrationback.dto.out;

public class AdministratorListOutDTO {
    private Integer administratorId;
    private String username;
    private Byte status;
    private Long createTimes;

    public Integer getAdministratorId() {
        return administratorId;
    }

    public void setAdministratorId(Integer administratorId) {
        this.administratorId = administratorId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Long getCreateTimes() {
        return createTimes;
    }

    public void setCreateTimes(Long createTimes) {
        this.createTimes = createTimes;
    }
}
