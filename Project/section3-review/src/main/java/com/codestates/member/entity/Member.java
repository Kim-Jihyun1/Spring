package com.codestates.member.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter // Member 클래스 내부적으로 getter, setter 메서드가 작성되어 있다
@NoArgsConstructor // Member 클래스에 추가된 모든 멤버 변수를 피라미터로 갖는 Member 생성자를 자동으로 생성
//@AllArgsConstructor // 파라미터가 없는 기본 생성자를 자동으로 생성
@Entity
public class Member {
    @Id // @Entity 와 @Id를 추가하면 JPA에서 해당 클래스를 엔티티 클래스로 인식
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    @Column(nullable = false, updatable = false, unique = true)
    private String email;

    @Column(length = 100, nullable = false) // 문자 길이 (기본 값은 255)
    private String name;

    @Column(length = 13, nullable = false, unique = true)
    private String phone;

    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now(); // 회원 정보가 등록될 때의 시간과 날짜를 매핑하기 위한 필드

    @Column(nullable = false, name = "LAST_MODIFIED_AT") // name 애트리뷰트를 생략하면 기본 값으로 엔티티 클래스 필드명으로 칼럼이 생성됨
    private LocalDateTime modifiedAt = LocalDateTime.now();

    @Transient // JPA가 테이블 칼럼과 매핑하지 않겠다는 의미로 인식 (주로 임시 데이터를 메모리에서 사용하기 위함)
    private String age;

    public Member(String email) {
        this.email = email;
    }

    public Member(String email, String name, String phone) {
        this.email = email;
        this.name = name;
        this.phone = phone;
    }
}
