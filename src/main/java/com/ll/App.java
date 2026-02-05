package com.ll;

import com.ll.global.AppContext;
import com.ll.global.Rq;
import com.ll.system.controller.SystemController;
import com.ll.wiseSaying.controller.WiseSayingController;

import java.util.Scanner;

public class App {

    private Scanner sc = new Scanner(System.in);
    private SystemController systemController = AppContext.systemController;
    private WiseSayingController wiseSayingController = AppContext.wiseSayingController;

    public void run() {

        System.out.println("== 명언 앱 ==");

        while (true) {
            System.out.print("명령) ");
            String cmd = sc.nextLine();

            /**명령어(cmd) 분석 수행 클래스 com.ll.global.Rq*/
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