package com.ll;

import com.ll.system.controller.SystemController;
import com.ll.wiseSaying.controller.WiseSayingController;
import com.ll.wiseSaying.entity.WiseSaying;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {

    private Scanner sc = new Scanner(System.in);

    private int lastId = 0;

    private List<WiseSaying> wiseSayings = new ArrayList<>();
    private SystemController systemController = new SystemController();
    private WiseSayingController wiseSayingController = new WiseSayingController();

    public void run() {

        System.out.println("== 명언 앱 ==");

        while (true) {
            System.out.print("명령) ");
            String cmd = sc.nextLine();

            /**명령어(cmd) 분석 수행 클래스 com.ll.Rq*/
            Rq rq = new Rq(cmd); // cmd 분석 객체

            String action = rq.getAction();

            if (action.equals("종료")) {
                systemController.exit();
                break;  /** break는 while문 종료, return은 run()함수가 종료*/
            } else if (action.equals("등록")) {
                wiseSayingController.actionWrite();
            } else if (action.equals("목록")) {
                wiseSayingController.actionList();
            } else if (action.startsWith("삭제")) {
                wiseSayingController.actionDelete(rq);
            } else if (action.startsWith("수정")) {
                wiseSayingController.actionModify(rq);
            }
        }
    }


}