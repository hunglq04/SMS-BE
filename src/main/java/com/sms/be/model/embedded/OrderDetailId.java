package com.sms.be.model.embedded;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
public class OrderDetailId implements Serializable {
    @Column(name = "product_id")
    private Long productId;
    @Column(name = "order_id")
    private Long orderId;
}
