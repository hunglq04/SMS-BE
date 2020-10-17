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
@SequenceGenerator(name = "SEQ_ID", sequenceName = "SEQ_SERVICE", allocationSize = 1, initialValue=100)
public class Service extends BaseEntity {
    @Column
    private String name;

    @Column
    private String description;

    @Column
    private Long price;

    @Column
    private Integer duration;

    @Column
    private String image;

    @ManyToOne(fetch = FetchType.LAZY)
    private ServiceType serviceType;

}
