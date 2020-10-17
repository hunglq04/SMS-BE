package com.sms.be.model;

import com.sms.be.model.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@SequenceGenerator(name = "SEQ_ID", sequenceName = "SEQ_SALON", allocationSize = 1, initialValue=100)
public class Salon extends BaseEntity {
    @Column
    private String street;

    @ManyToOne(fetch = FetchType.LAZY)
    private Employee manager;

    @ManyToOne(fetch = FetchType.LAZY)
    private Ward ward;

    @ManyToOne(fetch = FetchType.LAZY)
    private District district;

    @ManyToOne(fetch = FetchType.LAZY)
    private Province province;
}
