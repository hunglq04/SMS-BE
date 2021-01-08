package com.sms.be.dto.response;

import com.sms.be.dto.ManagerInfoDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SalonInfoResponse {
    private Long id;
    private ManagerInfoDto managerInfoDto;
    private String image;
    private String street;
    private ProvinceResponse provinceResponse;
    private DistrictResponse districtResponse;
    private String ward;
}
