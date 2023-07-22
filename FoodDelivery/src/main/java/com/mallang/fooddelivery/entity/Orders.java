import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor // class 모든필드 -> 기본생성자 자동추가
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // PK생성 -> DB에 위임전략
    private Long id;

    // JPA 연관관계설정
    // 다대일 -> 연관관계주인 : member, Member Entity PK로 연결
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "food_id")
    private Food food;

    // 생성자
    public Orders(Member member, Food food) {
        this.member = member;
        this.food = food;
    }
}