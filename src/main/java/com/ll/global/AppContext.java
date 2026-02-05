package com.ll.global;

import com.ll.system.controller.SystemController;
import com.ll.wiseSaying.controller.WiseSayingController;
import com.ll.wiseSaying.repository.WiseSayingRepository;
import com.ll.wiseSaying.service.WiseSayingService;

public class AppContext {
    /** static이 붙은 자원은 한 개만 존재 가능!!
     * 근데 아직은 싱글톤 패턴이 아님(싱글톤 패턴은 new WiseSayingService()로 새로운 객체 생성못하게 막음)
     * 생성 시점에 맞게 순서를 조정해야함
     * App -> Controller -> Service -> Repository 이므로 이 역순으로 순서 조정!!*/

    public static final WiseSayingRepository wiseSayingRepository = new WiseSayingRepository();
    public static final WiseSayingService wiseSayingService = new WiseSayingService();
    public static final WiseSayingController wiseSayingController = new WiseSayingController();
    public static final SystemController systemController = new SystemController();


}
