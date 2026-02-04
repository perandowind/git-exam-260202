package com.ll.wiseSaying.repository;

import com.ll.wiseSaying.entity.WiseSaying;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class WiseSayingRepository {
    /**서비스가 아래 저장정보 갖고 있으면 안됨 -> 저장소에 옮김*/
    private List<WiseSaying> wiseSayings = new ArrayList<>();
    private int lastId = 0;

    public WiseSaying findById(int id) {
        int foundedIndex = findIndexById(id);

        if (foundedIndex == -1) {
            return null;
        }

        return wiseSayings.get(foundedIndex);
    }

    private int findIndexById(int id) {
        /**스트림 버전*/
        return IntStream
                .range(0, wiseSayings.size())
                .filter((i) -> wiseSayings.get(i).getId() == id)
                .findFirst()
                .orElse(-1);
        /**반복문 버전*/
//        for (int i = 0; i < wiseSayings.size(); i++) {
//            com.ll.wiseSaying.entity.WiseSaying foundedWiseSaying = wiseSayings.get(i);
//            if (id == foundedWiseSaying.getId()) {
//                return i;
//            }
//        }
    }

    public List<WiseSaying> findList() {

        List<WiseSaying> foundedWiseSayings = new ArrayList<>();

        for(WiseSaying wiseSaying : wiseSayings.reversed()) {
            foundedWiseSayings.add(wiseSaying);
        }

        return foundedWiseSayings;
    }

    public boolean delete(int id) {
        // 편리하고 가독성이 더 좋지만, 성능은 좀 부족함.
        return wiseSayings.removeIf((wiseSaying) -> wiseSaying.getId() == id);
//        int foundIndex = findIndexById(deleteTarget);
//        if (foundIndex == -1) return false;
//        wiseSayings.remove(foundIndex);
    }

    /**관례상 저장(write)을 했으면, 저장한 것을 반환함*/
    public WiseSaying write(String content, String author) {
        WiseSaying wiseSaying = new WiseSaying(++lastId, content, author);
        wiseSayings.add(wiseSaying);

        return wiseSaying;
    }
}
