package com.codestates.member.entity;

import com.codestates.audit.Auditable;
import com.codestates.order.entity.Order;
import com.codestates.stamp.Stamp;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

//@AllArgsConstructor // 파라미터가 없는 기본 생성자를 자동으로 생성
@Getter
@Setter // Member 클래스 내부적으로 getter, setter 메서드가 작성되어 있다
@NoArgsConstructor // Member 클래스에 추가된 모든 멤버 변수를 피라미터로 갖는 Member 생성자를 자동으로 생성
@Entity
public class Member extends Auditable {
    @Id // @Entity 와 @Id를 추가하면 JPA에서 해당 클래스를 엔티티 클래스로 인식
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    @Column(nullable = false, updatable = false, unique = true)
    private String email;

    @Column(length = 100, nullable = false) // 문자 길이 (기본 값은 255)
    private String name;

    @Column(length = 13, nullable = false, unique = true)
    private String phone;

    // 회원 상태를 저장하기 위한 enum 필드
    @Enumerated(value = EnumType.STRING)
    @Column(length = 20, nullable = false)
    private MemberStatus memberStatus = MemberStatus.MEMBER_ACTIVE; // 회원이 처음 등록될 때의 디폴트 값

//    @Column(nullable = false)
//    private LocalDateTime createdAt = LocalDateTime.now();
//
//    @Column(nullable = false, name = "LAST_MODIFIED_AT")
//    private LocalDateTime modifiedAt = LocalDateTime.now();

    // Member와 Order 간의 1:N 연관관계 매핑
    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();

    // Member와 Stamp 간의 1:1 연관관계 매핑
    @OneToOne(mappedBy = "member", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private Stamp stamp;

    public Member(String email) {
        this.email = email;
    }

    public Member(String email, String name, String phone) {
        this.email = email;
        this.name = name;
        this.phone = phone;
    }

    // Member와 Order 간의 양방향 연관관계 매핑
    public void setOrder(Order order) {
        orders.add(order);

        if (order.getMember() != this) {
            order.setMember(this);
        }
    }

    // Member와 Stamp 간의 연관관계 매핑
    public void setStamp(Stamp stamp) {
        this.stamp = stamp;
        if (stamp.getMember() != this) {
            stamp.setMember(this);
        }
    }

    // 회원 상태
    public enum MemberStatus {
        MEMBER_ACTIVE("활동중"),
        MEMBER_SLEEP("휴면 상태"),
        MEMBER_QUIT("탈퇴 상태");

        @Getter
        private String status;

        MemberStatus(String status) {
            this.status = status;
        }
    }
}
