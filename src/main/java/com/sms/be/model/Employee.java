package com.sms.be.model;

import com.sms.be.model.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@SequenceGenerator(name = "SEQ_ID", sequenceName = "SEQ_EMPLOYEE", allocationSize = 1, initialValue=100)
public class Employee extends BaseEntity {
    @Column
    private String name;

    @Column
    private String address;

    @Column
    private String phoneNumber;

    @Column(unique = true)
    private String idCard;

    @Column
    private String avatar;

    @Column
    private Long salary;

    @Column
    private Double avgRating;

    @ManyToOne(fetch = FetchType.LAZY)
    private Salon salon;

    @OneToOne(fetch = FetchType.EAGER)
    private Account account;
}
