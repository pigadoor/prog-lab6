package com.pigadoor.network;

import java.io.Serial;
import java.io.Serializable;

public class Response implements Serializable {

    @Serial
    private static final long serialVersionUID = 1234568L;
    private String message;

    public Response() {}

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Response{" +
                "message='" + message + '\'' +
                '}';
    }


}
