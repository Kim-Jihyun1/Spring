package com.codestates.stamp;

import com.codestates.audit.Auditable;
import com.codestates.member.entity.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Stamp extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long stampId;

    @Column(nullable = false)
    private int stampCount;

    // Stamp와 Member 간의 1:1 연관관계 매핑
    @OneToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    // Stamp와 Member 간의 양방향 연관관계 매핑
    public void setMember(Member member) {
        this.member = member;

        if (member.getStamp() != this) {
            member.setStamp(this);
        }
    }
}
