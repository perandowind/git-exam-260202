package com.ll.wiseSaying;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Rq {

    private String action;
    private Map<String, String> paramMap;

    public Rq(String cmd) {
        paramMap = new HashMap<>();

        String[] cmdBits = cmd.split("\\?");
        action = cmdBits[0];
        String params = cmdBits.length > 1 ? cmdBits[1] : ""; // 목록 or 목록?page=1 의 형태인지

        String[] paramsBits = params.split("&");
//        for (String param : paramsBits) {
//            String[] paramBits = param.split("=");
//            String key = paramBits[0];
//            String value = paramBits.length > 1 ? paramBits[1] : "";
//            paramMap.put(key, value);
//        }
        paramMap = Arrays.stream(paramsBits) // ["id=1", "name=aaa", "age=20"] 이 넘어옴
                .map(param -> param.split("=")) // "id=1" 가 넘어옴
                .filter((paramBits) -> paramBits.length == 2
                        && paramBits[0] != null && paramBits[1] != null) // ["id", "1"]
                .collect(Collectors.toMap(
                        bits -> bits[0],
                        bits -> bits[1]
                ));

    }
//git tag -a step2-complete -m "step2 complete"
    public String getAction() {
        return action;
    }
    /**이후 목록?~ 에서 사용됨*/
    public String getParam (String key, String defaultValue) {
        if (paramMap.containsKey(key)) {
            return paramMap.get(key);
        }
        return defaultValue;
    }

    public int getParamAsInt(String key, int defaultValue) {
        try{
            return Integer.parseInt(paramMap.get(key));
        }catch (NumberFormatException e){
            return defaultValue;
        }
    }
}
