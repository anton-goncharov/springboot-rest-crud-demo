package com.antongoncharov.paymentservice.dto.response;

public class ErrorResponse extends ApiResponse {

    private String reason;

    public ErrorResponse(String reason) {
        super(Result.ERROR.getValue(),null,null);
        this.reason = reason;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Override
    public String toString() {
        return "ErrorResponse{" +
                "reason='" + reason + '\'' +
                '}';
    }
}
