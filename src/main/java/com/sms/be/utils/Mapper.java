package com.sms.be.utils;

import com.sms.be.constant.ErrorMessage;
import com.sms.be.dto.response.DistrictResponse;
import com.sms.be.model.District;
import com.sms.be.model.Ward;
import org.springframework.http.HttpStatus;

import java.util.Collections;
import java.util.stream.Collectors;

public class Mapper {

    private Mapper() {
        //Utils class
    }

    public static HttpStatus errorMessageToHttpStatus(ErrorMessage errorMessage) {
        switch (errorMessage.getValue()) {
            case 403:
                return HttpStatus.FORBIDDEN;
            case 404:
                return HttpStatus.NOT_FOUND;
            default:
                return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }

    public static DistrictResponse districtToDistrictResponse(District district) {
        return new DistrictResponse(district.getId(), district.getFullName(),district.getWards().stream()
                        .map(Ward::getFullName)
                        .collect(Collectors.toList()));
    }
}
