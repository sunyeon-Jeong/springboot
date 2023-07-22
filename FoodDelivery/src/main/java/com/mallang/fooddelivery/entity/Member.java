import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;

@Getter
@Entity
@NoArgsConstructor // class 모든필드 -> 기본생성자 자동추가
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // PK생성 -> DB에 위임전략
    private Long id;

    @Column(nullable = false)
    private String memberName;

    // JPA 연관관계설정
    // 일대다 -> 연관관계주인 : member, 즉시로딩 EAGER사용
    // 즉시로딩 : member + order(연관관계테이블) 함께조회
    // 지연로딩 : member만 조회, 연관관계테이블 조회는 미룸
    @OneToMany(mappedBy = "member", fetch = FetchType.EAGER)
    private List<Orders> orders = new ArrayList<>();

    // 생성자
    public Member(String memberName) {
        this.memberName = memberName;
    }
}