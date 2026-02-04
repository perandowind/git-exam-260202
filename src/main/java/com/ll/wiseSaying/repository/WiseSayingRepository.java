package com.ll.wiseSaying.repository;

import com.ll.wiseSaying.entity.WiseSaying;

import java.time.LocalDateTime;
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

    public WiseSaying save(WiseSaying wiseSaying) {

        if(wiseSaying.getId() == 0){
            /**id가 0 = 기존에 없던것 -> 새로 등록*/
            wiseSaying.setId(++lastId);
            wiseSayings.add(wiseSaying);

            wiseSaying.setCreateDate(LocalDateTime.now()); //생성일 이면서
            wiseSaying.setModifyDate(LocalDateTime.now()); //가장 최근 수정일
        }else{
            wiseSaying.setModifyDate(LocalDateTime.now());//수정일때만 실행
        }
        /**id가 0이 아니면 수정, 수정 코드가 필요하다면 작성. 지금은 필요X */

        return wiseSaying;
    }
}
