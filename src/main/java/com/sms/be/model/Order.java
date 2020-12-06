package com.sms.be.model;

import com.sms.be.constant.OrderStatus;
import com.sms.be.constant.PaymentMethod;
import com.sms.be.model.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@SequenceGenerator(name = "SEQ_ID", sequenceName = "SEQ_ORDER", allocationSize = 1, initialValue=100)
@Table(name = "\"ORDER\"")
public class Order extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    private Customer customer;

    private LocalDate date;

    private String name;

    private String email;

    private String phone;

    private String address;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    private Long total;

}
