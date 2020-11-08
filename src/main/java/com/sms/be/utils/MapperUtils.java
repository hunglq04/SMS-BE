package com.sms.be.utils;

import com.sms.be.constant.CommonConstants;
import com.sms.be.constant.ErrorMessage;
import com.sms.be.dto.response.DistrictResponse;
import com.sms.be.dto.response.ServiceBookingResponse;
import com.sms.be.model.District;
import com.sms.be.model.Service;
import com.sms.be.model.Ward;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.stream.Collectors;

public class MapperUtils {

    private MapperUtils() {
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
        return new DistrictResponse(district.getId(), district.getFullName(), district.getWards().stream()
                .map(Ward::getFullName)
                .collect(Collectors.toList()));
    }

    public static String wardNameWithPrefixToNameOnly(final String wardName) {
        return (StringUtils.isEmpty(wardName)) ? CommonConstants.EMPTY
                : wardName.replace("Xã", CommonConstants.EMPTY)
                    .replace("Phường", CommonConstants.EMPTY)
                    .replace("Thị trấn", CommonConstants.EMPTY).trim();
    }

    public static ServiceBookingResponse serviceToServiceBookingResponse(Service service) {
        return ServiceBookingResponse.builder()
                .id(service.getId())
                .bookingImage(service.getBookingImage())
                .bookingRecommendImage(service.getBookingRecommendImage())
                .isRecommend(service.isRecommend())
                .price(service.getPrice())
                .serviceType(service.getServiceType().getName())
                .build();
    }
}
