package com.sms.be.dto.response;

import com.sms.be.model.Account;
import com.sms.be.model.Salon;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeResponse {
    private Long id;
    private String name;
    private String address;
    private String phoneNumber;
    private String idCard;
    private String avatar;
    private Long salary;
    private Long avgRating;
    private SalonResponse salon;
    private Account account;
}
