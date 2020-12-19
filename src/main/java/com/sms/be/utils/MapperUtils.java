package com.sms.be.utils;

import com.sms.be.constant.CommonConstants;
import com.sms.be.constant.ErrorMessage;
import com.sms.be.dto.response.*;
import com.sms.be.model.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.ArrayList;
import java.util.Objects;
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

    public static ProductResponse productToProductResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .image(product.getImage())
                .price(product.getPrice())
                .productType(product.getProductType().getName())
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
        salonResponse.setStylishResponses(new ArrayList<>());
        salonResponse.setServices(new ArrayList<>());
        return salonResponse;
    }

    public static EmployeeResponse employeeToEmployeeResponse(Employee employee)
    {
        return EmployeeResponse.builder()
                .id(employee.getId())
                .name(employee.getName())
                .address(employee.getAddress())
                .phoneNumber(employee.getPhoneNumber())
                .idCard(employee.getIdCard())
                .avatar(employee.getAvatar())
                .avgRating(employee.getAvgRating())
                .salon(mapSalonResponse(employee.getSalon()))
                .account(employee.getAccount())
                .build();
    }

    public static BookingResponse bookingToBookingResponse(Booking booking) {
        CustomerResponse customer = null;
        String walkInGuest = null;
        if (Objects.nonNull(booking.getCustomer())) {
            customer = customerToCustomerResponse(booking.getCustomer());
        } else {
            walkInGuest = booking.getWalkInGuest();
        }
        return BookingResponse.builder()
                .bookingId(booking.getId())
                .salon(mapSalonResponse(booking.getSalon()))
                .dateTime(booking.getDate() + StringUtils.SPACE +(booking.getTime()))
                .services(booking.getServices().stream()
                        .map(MapperUtils::serviceToServiceBookingResponse)
                        .collect(Collectors.toList()))
                .bookingStatus(booking.getStatus())
                .stylist(booking.getStylist().getName())
                .customer(customer)
                .walkInGuest(walkInGuest)
                .build();
    }

    public static OrderResponse orderToOrderResponse(Order order, List<OrderDetailResponse> orderDetails) {
        return OrderResponse.builder()
                .orderId(order.getId())
                .date(order.getDateTime() + StringUtils.SPACE)
                .name(order.getName())
                .email(order.getEmail())
                .address(order.getAddress())
                .phone(order.getPhone())
                .orderStatus(order.getStatus())
                .products(orderDetails)
                .total(order.getTotal())
                .customer(customerToCustomerResponse(order.getCustomer()))
                .build();
    }

    public static OrderDetailResponse orderDetailToOrderDetailResponse(OrderDetail orderDetail) {
        return OrderDetailResponse.builder()
                .orderId(orderDetail.getOrder().getId())
                .product(orderDetail.getProduct())
                .price(orderDetail.getPrice())
                .quantity(orderDetail.getQuantity())
                .build();
    }

    public static CustomerResponse customerToCustomerResponse(Customer customer) {
        return CustomerResponse.builder()
                .customerId(customer.getId())
                .email(customer.getEmail())
                .phone(customer.getPhoneNumber())
                .name(customer.getName())
                .build();
    }

    public static StylistSchedulerResponse bookingToStylistSchedulerResponse(Booking booking) {
        String customerName = booking.getCustomer() == null ?
                booking.getWalkInGuest() : booking.getCustomer().getName();
        String image1 = null, image2 = null, image3 = null, image4 = null;
        if (booking.getImages() != null) {
            image1 = booking.getImages().getImage1();
            image2 = booking.getImages().getImage2();
            image3 = booking.getImages().getImage3();
            image4 = booking.getImages().getImage4();
        }
        StylistSchedulerResponse scheduler = StylistSchedulerResponse.builder()
                .id(booking.getId())
                .customer(customerName)
                .services(booking.getServices().stream()
                        .map(MapperUtils::serviceToServiceBookingResponse)
                        .collect(Collectors.toList()))
                .status(booking.getStatus())
                .start(booking.getDate().toString() + "T" + booking.getTime().toString())
                .image1(image1).image2(image2).image3(image3).image4(image4)
                .build();
        Integer duration = scheduler.getServices().stream().map(ServiceBookingResponse::getDuration)
                .reduce(Integer::sum).orElse(0);
        scheduler.setEnd(booking.getDate().toString() + "T" + booking.getTime().plusMinutes(duration).toString());
        return scheduler;
    }

}
