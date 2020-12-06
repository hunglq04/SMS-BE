package com.sms.be.model;

import com.sms.be.model.embedded.OrderDetailId;
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
@SequenceGenerator(name = "SEQ_ID", sequenceName = "SEQ_ORDER_DETAIL", allocationSize = 1, initialValue=100)
public class OrderDetail {
    @EmbeddedId
    OrderDetailId id;

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @MapsId("orderId")
    @JoinColumn(name = "order_id")
    private Order order;

    private Integer quantity;
    //TODO8
    private Long price;

    public void setOrderDetailId(Long orderId, Long productId) {
        this.id = new OrderDetailId();
        this.id.setOrderId(orderId);
        this.id.setProductId(productId);
    }
}
