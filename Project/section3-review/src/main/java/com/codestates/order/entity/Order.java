package com.codestates.order.entity;

import com.codestates.audit.Auditable;
import com.codestates.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter // 클래스 레벨에 @Getter / @Setter 추가하면 모든 필드에 getter/setter 메서드가 생김
@Setter
@NoArgsConstructor // 파라미터가 없는 디폴트 생성자 추가
@Entity(name = "ORDERS")
public class Order extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long orderId;

    /*
     * enum 타입과 매핑할 때 사용
     * EnumType.STRING : enum의 이름을 테이블에 저장
     * EnumType.ORDINAL : enum의 순서를 나타내는 숫자를 테이블에 저장 (순서 불일치 문제 발생 가능)
     */
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus = OrderStatus.ORDER_REQUEST;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    // Order와 OrderCoffee 간의 1:N 연관관계 매핑
    @OneToMany(mappedBy = "order", cascade = CascadeType.PERSIST)
    private List<OrderCoffee> orderCoffees = new ArrayList<>();

    // Order와 Member 간의 양방향 연관관계 매핑
    public void setMember(Member member) {
        this.member = member;
    }

    public void addOrderCoffee(OrderCoffee orderCoffee) {
        this.orderCoffees.add(orderCoffee);
        if (orderCoffee.getOrder() != this) {
            orderCoffee.addOrder(this);
        }
    }

    // 주문 상태
    public enum OrderStatus {
        ORDER_REQUEST(1, "주문 요청"),
        ORDER_CONFIRM(2, "주문 확정"),
        ORDER_COMPLETE(3, "주문 완료"),
        ORDER_CANCEL(4, "주문 취소");

        @Getter
        private int stepNumber;

        @Getter
        private String stepDescription;

        OrderStatus(int stepNumber, String stepDescription) {
            this.stepNumber = stepNumber;
            this.stepDescription = stepDescription;
        }
    }
}
