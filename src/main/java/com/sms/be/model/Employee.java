package com.sms.be.model;

import com.sms.be.model.base.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
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
    private Long avgRating;

    @ManyToOne(fetch = FetchType.LAZY)
    private Salon salon;

    @OneToOne(fetch = FetchType.EAGER)
    private Account account;
}
