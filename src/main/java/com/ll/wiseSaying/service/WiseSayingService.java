package com.ll.wiseSaying.service;

import com.ll.wiseSaying.entity.WiseSaying;
import com.ll.wiseSaying.repository.WiseSayingRepository;

import java.time.LocalDateTime;
import java.util.List;

public class WiseSayingService {

    WiseSayingRepository wiseSayingRepository = new  WiseSayingRepository();

    public WiseSaying findById(int id) {
        return wiseSayingRepository.findById(id);
    }

    public void modify(WiseSaying wiseSaying, String content, String author) {
        wiseSaying.setContent(content);
        wiseSaying.setAuthor(author);
        /**실제 DB수준에서도 저장해야하므로 원래는 필요*/
        wiseSayingRepository.save(wiseSaying);
    }

    public boolean delete(int id) {
        return wiseSayingRepository.delete(id);
    }

    public List<WiseSaying> findList() {
        return wiseSayingRepository.findList();
    }

    /**관례상 저장(write)을 했으면, 저장한 것을 반환함*/
    public WiseSaying write(String content, String author) {
        WiseSaying wiseSaying = new WiseSaying(0, content, author);
        return wiseSayingRepository.save(wiseSaying);
    }
}
