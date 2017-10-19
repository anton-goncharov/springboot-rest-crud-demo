package com.antongoncharov.paymentservice.dto.response;

import java.util.Map;

public class SuccessResponse<T> extends ApiResponse<T> {

    private String id;

    public SuccessResponse() {
        super(Result.SUCCESS.getValue(),null,null);
    }

    public SuccessResponse(String id) {
        super(Result.SUCCESS.getValue(),null,null);
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "SuccessResponse{" +
                "id='" + id + '\'' +
                '}';
    }
}
