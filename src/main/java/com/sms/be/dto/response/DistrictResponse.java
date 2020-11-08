package com.sms.be.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DistrictResponse {
    private Long id;
    private String name;
    private List<String> wards;
}
