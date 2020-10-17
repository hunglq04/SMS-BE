package com.sms.be.model;

import com.sms.be.model.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@SequenceGenerator(name = "SEQ_ID", sequenceName = "SEQ_CUSTOMER", allocationSize = 1, initialValue=100)
public class Customer extends BaseEntity {
    @Column
    private String name;

    @Column
    private String phoneNumber;

    @Column
    private String address;

    @Column
    private String email;

    @Column
    private String socialId;

    @OneToOne(fetch = FetchType.EAGER)
    private Account account;
}
