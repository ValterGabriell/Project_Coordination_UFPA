package io.github.ValterGabriell.UFPA.infra.api.dto;

import java.io.Serializable;

public class ResponseImageDTO implements Serializable {
    private Image data;
    private boolean success;
    private int status;

    public Image getData() {
        return data;
    }

    public void setData(Image data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public ResponseImageDTO(Image data, boolean success, int status) {
        this.data = data;
        this.success = success;
        this.status = status;
    }

    public ResponseImageDTO() {
    }
}
