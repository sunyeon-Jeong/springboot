package com.mallang.mallangmemo.service;

import com.mallang.mallangmemo.dto.MemoRequestDto;
import com.mallang.mallangmemo.entity.Memo;
import com.mallang.mallangmemo.repository.MemoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor // final, @Notnull -> 생성자 자동생성
public class MemoService {

    private final MemoRepository memoRepository;

    // Memo 생성하기
    // DB 작업처리 중, 오류발생 -> 모든 작업 원상태로 돌림
    @Transactional
    public Memo createMemo(MemoRequestDto memoRequestDto) {
        // Memo : 반환타입(Memo 클래스의 객체반환)
        Memo memo = new Memo(memoRequestDto);
        memoRepository.save(memo);
        return memo;
    }

    // Memo 조회하기
    @Transactional(readOnly = true)
    public List<Memo> getMemos() {
        // 생성시간기준 내림차순으로 데이터 가져옴
        // Repository랑 연결필수 (반환타입 + 조회조건)
        return memoRepository.findAllByOrderByModifiedAtDesc();
    }
}