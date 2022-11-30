package com.codestates.order.entity;

import com.codestates.audit.Auditable;
import com.codestates.coffee.entity.Coffee;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class OrderCoffee extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderCoffeeId;

    @Column(nullable = false)
    private int quantity;

    // OrderCoffee와 Order 간의 N:1 연관관계 매핑
    @ManyToOne
    @JoinColumn(name = "ORDER_ID")
    private Order order;

    // OrderCoffee와 Coffee 간의 N:1 연관관계 매핑
    @ManyToOne
    @JoinColumn(name = "COFFEE_ID")
    private Coffee coffee;

    // OrderCoffee와 Order 간의 양방향 연관관계 매핑
    public void setOrder(Order order) {
        this.order = order;
        if (!this.order.getOrderCoffees().contains(this)) {
            this.order.getOrderCoffees().add(this);
        }
    }

    // OrderCoffee와 Coffee 간의 양방향 연관관계 매핑
    public void setCoffee(Coffee coffee) {
        this.coffee = coffee;
        if (!this.coffee.getOrderCoffees().contains(this)) {
            this.coffee.setOrderCoffee(this);
        }
    }
}
