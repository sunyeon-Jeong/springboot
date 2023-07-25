package com.mallang.fooddelivery.global;

import com.mallang.fooddelivery.entity.Food;
import com.mallang.fooddelivery.entity.Member;
import com.mallang.fooddelivery.entity.Orders;
import com.mallang.fooddelivery.repository.FoodRepository;
import com.mallang.fooddelivery.repository.MemberRepository;
import com.mallang.fooddelivery.repository.OrdersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component // 개발자 생성 class -> SpringBean 등록
@RequiredArgsConstructor // final, @Notnull 필드 -> 생성자 자동으로 만들어줌
public class Restaurant implements ApplicationRunner {

    private final MemberRepository memberRepository;
    private final FoodRepository foodRepository;
    private final OrdersRepository ordersRepository;

    @Override
    public void run(ApplicationArguments arguments) throws Exception {
        List<Food> foods = new ArrayList<>();
        Food food1 = new Food("후라이드", 10000);
        foods.add(food1);
        Food food2 = new Food("양념치킨", 12000);
        foods.add(food2);
        Food food3 = new Food("반반치킨", 13000);
        foods.add(food3);
        Food food4 = new Food("고구마피자", 9000);
        foods.add(food4);
        Food food5 = new Food("아보카도피자", 110000);
        foods.add(food5);

        foodRepository.saveAll(foods);

        System.out.println("==================================================================");
        // FoodRepository -> 객체를 전부 읽어옴 -> 반복문을 통해 FoodName만 get
        List<Food> findFoods = foodRepository.findAll();
        for (Food findFood : findFoods) {
            System.out.println("findFood = " + findFood.getFoodName());
        }


        List<Member> members = new ArrayList<>();
        Member member1 = new Member("말랑이");
        members.add(member1);
        Member member2 = new Member("쿼카");
        members.add(member2);

        memberRepository.saveAll(members);

        System.out.println("==================================================================");
        // MemberRepository -> 객체를 전부 읽어옴 -> 반복문을 통해 MemberName만 get
        System.out.println("Member 데이터");
        List<Member> findMembers = memberRepository.findAll();
        for (Member findMember : findMembers) {
            System.out.println("findMember = " + findMember.getMemberName());
        }


        List<Orders> ordersList = new ArrayList<>();
        // Java List -> 순서보장됨 -> Entity 순서에 맞춰 초기값 넣어주기
        Orders orders1 = new Orders(findMembers.get(0), findFoods.get(0));
        ordersList.add(orders1);
        Orders orders2 = new Orders(findMembers.get(1), findFoods.get(3));
        ordersList.add(orders2);
        Orders orders3 = new Orders(findMembers.get(1), findFoods.get(4));
        ordersList.add(orders3);
        Orders orders4 = new Orders(findMembers.get(0), findFoods.get(2));
        ordersList.add(orders4);
        Orders orders5 = new Orders(findMembers.get(0), findFoods.get(2));
        ordersList.add(orders5);
        Orders orders6 = new Orders(findMembers.get(1), findFoods.get(1));
        ordersList.add(orders6);
        Orders orders7 = new Orders(findMembers.get(0), findFoods.get(1));
        ordersList.add(orders7);
        Orders orders8 = new Orders(findMembers.get(1), findFoods.get(3));
        ordersList.add(orders8);

        ordersRepository.saveAll(ordersList);

        System.out.println("==================================================================");
        int num = 1;

        System.out.println("Orders 데이터");
        List<Orders> orderList = ordersRepository.findAll();
        for (Orders orders : orderList) {
            System.out.println(num);
            System.out.println("주문자 = " + orders.getMember().getMemberName());
            System.out.println("주문음식 = " + orders.getFood().getFoodName());
            num++;
        }

        System.out.println("==================================================================");
        System.out.println("말랑이가 주문한 음식");
        Member mallang = memberRepository.findById(1L).orElseThrow(
                () -> new RuntimeException("없음")
        );

        num = 1;

        for (Orders orders : mallang.getOrders()) {
            System.out.println(num);
            System.out.println("주문음식 = " + orders.getFood().getFoodName());
            System.out.println("주문음식가격 = " + orders.getFood().getPrice());
            num++;
        }

        System.out.println("==================================================================");

        System.out.println("아보카도피자 주문자");
        Food abocado = foodRepository.findById(5L).orElseThrow(
                () -> new RuntimeException("없음")
        );

        for (Orders orders : abocado.getOrders()) {
            System.out.println("주문자 = " + orders.getMember().getMemberName());
        }
    }
}