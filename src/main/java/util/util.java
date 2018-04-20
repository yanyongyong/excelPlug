package util;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * 类　名: util
 * 包　名: util
 * 版　权: 2018 LXKJ Inc. All rights reserved.
 * 日　期: 2018/4/19 8:37
 * 版　本: V1.0
 * 创建人：YanYong(zivyan@qq.com)
 * 描　述: 工具类
 */
public class util {

    /**
     * @Auther: ＾＿－)≡★ yanyong
     * @Date: 2018/4/19 8:43
     * @Description: 分割字符串
     * @param string 待分割字符串
     * @param splicPoint 分割标记
     * @return  分割后的数组
     */
    public static List<String> splicString(String string,String splicPoint){
        String[] strings = string.split(splicPoint);
        List<String> list = new ArrayList<>();
        for (int i = 0; i < strings.length; i++){
            list.add(strings[i]);
        }
        return list;
    }



    @Test
    public void test(){
//        String[] strings = splicString("abuc",",");
//        System.out.println(strings[0]);
    }
}
