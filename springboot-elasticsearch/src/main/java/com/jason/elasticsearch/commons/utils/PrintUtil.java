package com.jason.elasticsearch.commons.utils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;

/**
 * @author WongChenHoll
 * @date 2022-5-15 星期日 16:11
 **/
public class PrintUtil {

    private PrintUtil() {
    }

    public static void printMap(Map<String, Object> map, String... filterKeys) {
        if (map == null || map.size() == 0) {
            return;
        }
        Set<String> keySet = map.keySet();
        ArrayList<String> keys = new ArrayList<>(Arrays.asList(filterKeys));
        System.out.print("{");
        for (String key : keySet) {

            if (!keys.contains(key)) {
                if (key.equals("acct_balance")) {
                    System.out.print("\"acct_balance\":\"" + new BigDecimal(map.get(key).toString()) + "\", ");
                } else {
                    System.out.print("\"" + key + "\":\"" + map.get(key) + "\", ");
                }
            }
        }
        System.out.println("}");
    }
}
