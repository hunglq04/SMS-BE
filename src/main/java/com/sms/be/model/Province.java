package com.sms.be.model;

import com.sms.be.model.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@SequenceGenerator(name = "SEQ_ID", sequenceName = "SEQ_PROVINCE", allocationSize = 1, initialValue=100)
public class Province extends BaseEntity {
    @Column
    private String name;

    @Column
    private String code;
}
