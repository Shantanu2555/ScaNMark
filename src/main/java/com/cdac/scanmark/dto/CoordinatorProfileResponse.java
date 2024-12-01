package com.cdac.scanmark.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class CoordinatorProfileResponse {
    private String name;
    private String email;

    public CoordinatorProfileResponse(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public CoordinatorProfileResponse() {
    }
}
