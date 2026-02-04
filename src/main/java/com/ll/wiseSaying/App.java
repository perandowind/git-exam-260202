package com.ll.wiseSaying;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

public class App {

    private Scanner sc = new Scanner(System.in);
    private int lastId = 0;

    private List<WiseSaying> wiseSayings = new ArrayList<>();

    public void run() {

        System.out.println("== 명언 앱 ==");

        while (true) {
            System.out.print("명령) ");
            String cmd = sc.nextLine();

            /**명령어(cmd) 분석 수행 클래스 com.ll.wiseSaying.Rq*/
            Rq rq = new Rq(cmd); // cmd 분석 객체

            String action = rq.getAction();

            if (action.equals("종료")) {
                break;
            } else if (action.equals("등록")) {
                actionWrite();
            } else if (action.equals("목록")) {
                actionList();
            } else if (action.startsWith("삭제")) {
                actionDelete(rq);
            } else if (action.startsWith("수정")) {
                actionModify(rq);
            }
        }
    }

    private void actionDelete(Rq rq) {

        int id = rq.getParamAsInt("id", -1);
        if (id == -1) {
            System.out.println("id를 제대로 입력해주세요.");
            return;
        }
        boolean rst = delete(id);

        if (!rst) {
            System.out.println("%d번 명언은 존재하지 않습니다.".formatted(id));
            return;
        }

        System.out.println("%d번 명언이 삭제되었습니다.".formatted(id));
    }

    private void actionModify(Rq rq) {

        int id = rq.getParamAsInt("id", -1);
        if (id == -1) {
            System.out.println("id를 제대로 입력해주세요.");
            return;
        }

        WiseSaying wiseSaying = findById(id);

        if(wiseSaying == null) {
            System.out.println("%d번 명언은 존재하지 않습니다.".formatted(id));
            return;
        }

        System.out.print("명언(기존) : %s\n".formatted(wiseSaying.getContent()));
        System.out.print("명언 : ");
        String content = sc.nextLine();
        System.out.print("작가(기존) : %s\n".formatted(wiseSaying.getAuthor()));
        System.out.print("작가 : ");
        String author = sc.nextLine();

        modify(wiseSaying, content, author);
    }

    private void modify(WiseSaying wiseSaying, String content, String author) {
        wiseSaying.setContent(content);
        wiseSaying.setAuthor(author);
    }

    private WiseSaying findById(int id) {

        int foundedIndex = findIndexById(id);

        if (foundedIndex == -1) {
            return null;
        }

        return wiseSayings.get(foundedIndex);
    }

    private int findIndexById(int id) {
        /**반복문 버전*/
//        for (int i = 0; i < wiseSayings.size(); i++) {
//            com.ll.wiseSaying.WiseSaying foundedWiseSaying = wiseSayings.get(i);
//            if (id == foundedWiseSaying.getId()) {
//                return i;
//            }
//        }
        /**스트림 버전*/
        return IntStream
                .range(0, wiseSayings.size())
                .filter((i) -> wiseSayings.get(i).getId() == id)
                .findFirst()
                .orElse(-1);
    }

    private boolean delete(int deleteTarget) {

//        int foundIndex = findIndexById(deleteTarget);
//        if (foundIndex == -1) return false;
//        wiseSayings.remove(foundIndex);

        // 편리하고 가독성이 더 좋지만, 성능은 좀 부족함.
        return wiseSayings.removeIf((wiseSaying) -> wiseSaying.getId() == deleteTarget);

    }

    private void actionList() {
        System.out.println("번호 / 작가 / 명언");
        System.out.println("----------------------");
        List<WiseSaying> foundedWiseSayings = findList();

        for (WiseSaying wiseSaying : foundedWiseSayings) {
            System.out.printf("%d / %s / %s\n", wiseSaying.getId(), wiseSaying.getAuthor(), wiseSaying.getContent());
        }
    }

    private List<WiseSaying> findList() {

        List<WiseSaying> foundedWiseSayings = new ArrayList<>();

        for(WiseSaying wiseSaying : wiseSayings.reversed()) {
            foundedWiseSayings.add(wiseSaying);
        }

        return foundedWiseSayings;
    }

    private void actionWrite() {
        System.out.print("명언 : ");
        String content = sc.nextLine();
        System.out.print("작가 : ");
        String author = sc.nextLine();

        write(content, author);
        System.out.println(lastId + "번 명언이 등록되었습니다.");
    }

    private void write(String content, String author) {
        WiseSaying wiseSaying = new WiseSaying(++lastId, content, author);
        wiseSayings.add(wiseSaying);
    }
}