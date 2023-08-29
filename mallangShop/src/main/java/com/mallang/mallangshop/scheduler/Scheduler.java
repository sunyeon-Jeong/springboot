package com.mallang.mallangshop.scheduler;

import com.mallang.mallangshop.entity.Product;
import com.mallang.mallangshop.naver.dto.ItemDto;
import com.mallang.mallangshop.naver.service.NaverApiService;
import com.mallang.mallangshop.repository.ProductRepository;
import com.mallang.mallangshop.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
@RequiredArgsConstructor
public class Scheduler {

    private final NaverApiService naverApiService;
    private final ProductService productService;
    private final ProductRepository productRepository;

    // 초, 분, 시, 일, 월, 주 순서
    @Scheduled(cron = "0 0 1 * * *")
    public void updateLprice() throws InterruptedException {

        log.info("최저가 업데이트 실행");

        List<Product> productList = productRepository.findAll();

        for (Product product : productList) {
            // 1초에 한 상품씩 조회
            TimeUnit.SECONDS.sleep(1);

            String title = product.getTitle();

            List<ItemDto> itemDtoList = naverApiService.searchItems(title);

            ItemDto itemDto = itemDtoList.get(0);

            // 1번째 관심상품 최저가를 업데이트
            Long id = product.getId();

            productService.updateBySearch(id, itemDto);
        }

    }

}