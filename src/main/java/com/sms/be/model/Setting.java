package com.sms.be.model;

import com.sms.be.model.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@SequenceGenerator(name = "SEQ_ID", sequenceName = "SEQ_SETTING", allocationSize = 1, initialValue=100)
public class Setting extends BaseEntity {

    @Column(unique = true, nullable = false)
    @NotNull
    private String key;

    @Column(nullable = false)
    @NotNull
    private String value1;

    @Column
    private String value2;
}
