package com.fbm.dto;

import com.fbm.dto.PlayerDto;

import java.util.Set;

/**
 * Created by Mocart on 21-Aug-17.
 */
public class Response<T> {
    private String status;
    private String code;
    private Set<T> elements;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Set<T> getElements() {
        return elements;
    }

    public void setElements(Set<T> elements) {
        this.elements = elements;
    }
}
