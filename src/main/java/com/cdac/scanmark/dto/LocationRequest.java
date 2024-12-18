package com.cdac.scanmark.dto;

import org.hibernate.validator.constraints.pl.NIP;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LocationRequest {
    private Double latitude;
    private Double longitude;
}

