package io.github.ValterGabriell.UFPA.application.post.domain.dto;

public class CustomResponse<T> {
    private T object;
    private String message;
    private boolean successful;


    public T getObject() {
        return object;
    }

    public void setObject(T object) {
        this.object = object;
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
