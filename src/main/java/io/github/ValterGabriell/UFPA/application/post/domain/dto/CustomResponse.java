package io.github.ValterGabriell.UFPA.application.post.domain.dto;

public class CustomResponse<T> {
    private T data;
    private String message;
    private boolean successful;


    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccessful() {
        return successful;
    }

    public void setSuccessful(boolean successful) {
        this.successful = successful;
    }
}
