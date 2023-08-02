package com.mallang.mallangmemo.service;

import com.mallang.mallangmemo.dto.MemoRequestDto;
import com.mallang.mallangmemo.entity.Memo;
import com.mallang.mallangmemo.repository.MemoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor // final, @Notnull -> 생성자 자동생성
public class MemoService {
    private final MemoRepository memoRepository;

    // DB 작업처리 중, 오류발생 -> 모든 작업 원상태로 돌림
    @Transactional
    public Memo createMemo(MemoRequestDto memoRequestDto) {
        // Memo : 반환타입(Memo 클래스의 객체반환)
        Memo memo = new Memo(memoRequestDto);
        memoRepository.save(memo);
        return memo;
    }
}