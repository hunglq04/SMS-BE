package com.sms.be.model;

import com.sms.be.model.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@SequenceGenerator(name = "SEQ_ID", sequenceName = "SEQ_SERVICE_TYPE", allocationSize = 1, initialValue=100)
public class ServiceType extends BaseEntity {
    @Column
    private String name;

    @OneToMany(mappedBy = "serviceType", fetch = FetchType.LAZY)
    private List<Service> services;
}
