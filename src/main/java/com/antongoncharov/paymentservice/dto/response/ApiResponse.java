package com.antongoncharov.paymentservice.dto.response;

import java.util.HashMap;
import java.util.Map;

public class ApiResponse<T> {

    enum Result {
        SUCCESS("success"),
        ERROR("error");

        private String value;

        Result(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    private String result;
    private T data;
    private Map<String,String> links;

    public ApiResponse(T data) {
        this.data = data;
    }

    public ApiResponse(String result, T data, Map<String,String> links) {
        this.result = result;
        this.data = data;
        this.links = links;
    }

    public ApiResponse<T> withSelfLink(String url) {
        links = links == null ? new HashMap<>() : links;
        links.put("self",url);
        return this;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Map<String, String> getLinks() {
        return links;
    }

    public void setLinks(Map<String, String> links) {
        this.links = links;
    }
}
