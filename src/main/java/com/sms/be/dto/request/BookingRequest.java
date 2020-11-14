package com.sms.be.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BookingRequest {
    @NotNull
    private Long customerId;
    @NotNull
    private Long stylistId;
    @NotNull
    private Long salonId;
    @NotNull
    private String date;
    @NotNull
    private String time;
    @NotNull
    private List<Long> serviceIds;
}