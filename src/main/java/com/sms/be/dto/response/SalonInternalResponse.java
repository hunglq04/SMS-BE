package com.sms.be.dto.response;

import com.sms.be.constant.CommonConstants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class SalonInternalResponse {
    private Long id;
    private String manager;
    private String image;
    private String address;

    public SalonInternalResponse(Long id, String manager, String image, String street
            , String ward, String district, String province) {
        this.id = id;
        this.manager = manager;
        this.image = image;
        this.address = String.join(CommonConstants.ADDRESS_DEMILITER, street,
                ward, district, province);
    }
}
