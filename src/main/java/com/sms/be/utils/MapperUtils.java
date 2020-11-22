package com.sms.be.utils;

import com.sms.be.constant.CommonConstants;
import com.sms.be.constant.ErrorMessage;
import com.sms.be.dto.response.BookingResponse;
import com.sms.be.dto.response.DistrictResponse;
import com.sms.be.dto.response.SalonResponse;
import com.sms.be.dto.response.ServiceBookingResponse;
import com.sms.be.model.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;

import java.util.stream.Collectors;

public class MapperUtils {

    private MapperUtils() {
        //Utils class
    }

    public static HttpStatus errorMessageToHttpStatus(ErrorMessage errorMessage) {
        switch (errorMessage.getValue()) {
            case 400:
                return HttpStatus.BAD_REQUEST;
            case 401:
                return HttpStatus.UNAUTHORIZED;
            case 403:
                return HttpStatus.FORBIDDEN;
            case 404:
                return HttpStatus.NOT_FOUND;
            case 409:
                return HttpStatus.CONFLICT;
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
                .name(service.getName())
                .description(service.getDescription())
                .bookingImage(service.getBookingImage())
                .bookingRecommendImage(service.getBookingRecommendImage())
                .isRecommend(service.isRecommend())
                .price(service.getPrice())
                .duration(service.getDuration())
                .serviceType(service.getServiceType().getName())
                .build();
    }
    
    public static SalonResponse mapSalonResponse(Salon salon) {
        SalonResponse salonResponse = new SalonResponse();
        salonResponse.setId(salon.getId());
        salonResponse.setStreet(salon.getStreet());
        salonResponse.setDistrict(String.join(StringUtils.SPACE, salon.getDistrict().getPrefix(), salon.getDistrict().getName()));
        salonResponse.setWard(String.join(StringUtils.SPACE, salon.getWard().getPrefix(), salon.getWard().getName()));
        salonResponse.setProvince(salon.getProvince().getName());
        salonResponse.setImage(salon.getImage());
        return salonResponse;
    }

    public static BookingResponse bookingToBookingResponse(Booking booking) {
        return BookingResponse.builder()
                .bookingId(booking.getId())
                .salon(mapSalonResponse(booking.getSalon()))
                .dateTime(booking.getDate() + StringUtils.SPACE +(booking.getTime()))
                .services(booking.getServices().stream()
                        .map(MapperUtils::serviceToServiceBookingResponse)
                        .collect(Collectors.toList()))
                .bookingStatus(booking.getStatus())
                .stylist(booking.getStylist().getName())
                .build();

    }
    
}
