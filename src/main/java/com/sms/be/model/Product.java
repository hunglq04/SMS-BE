package com.sms.be.model;

import com.sms.be.model.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@SequenceGenerator(name = "SEQ_ID", sequenceName = "SEQ_PRODUCT", allocationSize = 1, initialValue=100)
public class Product extends BaseEntity {
    @ManyToOne(fetch = FetchType.EAGER)
    private ProductType productType;

    private String name;

    private String image;

    private Long price;

    private String description;


}
