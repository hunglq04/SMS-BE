package com.sms.be.model;

import com.sms.be.model.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@SequenceGenerator(name = "SEQ_ID", sequenceName = "SEQ_BILL", allocationSize = 1, initialValue=100)
public class Bill extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    private Booking booking;

    @ManyToOne(fetch = FetchType.LAZY)
    private Employee cashier;

    @Column
    private LocalDateTime dateTime;

    @Column
    private Long total;
}
