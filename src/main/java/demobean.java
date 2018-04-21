import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPObject;
import org.junit.jupiter.api.Test;
import primary.EntityTest;
import util.CglibBean;
import util.ExcelRowColformat;

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static primary.Import.readExcelData;
import static util.CglibBean.createBean;
//import static util.CreateEntity.createBean;

/**
 * 类　名: demobean
 * 包　名: PACKAGE_NAME
 * 日　期: 2018/4/20 14:42
 * 版　本: V1.0
 * 创建人：YanYong(zivyan@qq.com)
 * 修改人：无
 */
public class demobean {

    private Object obj;

    @Test
    public <T> void testImportExcelData() throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, NoSuchFieldException {
        EntityTest entity = new EntityTest();
        // Excel列数据和实体字段一一对应
        List<ExcelRowColformat> list = new ArrayList<>();
        //获取从第一行（除去标题的第一行）开始第二列的所有数据
        list.add(new ExcelRowColformat("1","1","name"));
        list.add(new ExcelRowColformat("1","2","sex"));

//        CglibBean bean = createBean(list);
        // 获得bean的实体
//        Object object = bean.getObject();

        T c = (T) Class.forName("primary.EntityTest").newInstance();

        Class<?> cls = Class.forName("primary.EntityTest");
        //使用FieldDemo类的class对象生成 实例
        Object ob = cls.newInstance();

        //提取EXCEL文件信息
        List<Object> objects = readExcelData(ob,"1","4",list,"E:\\2017.xls");

        for (Object o : objects){
            String s = JSON.toJSONString(o);
            JSONObject jso = JSON.parseObject(s);
            String vString=jso.getString("sex");
            System.out.println(vString);
        }

        System.out.println(objects.size()+":...................");
    }


    public static void main(String[] args) throws Exception
    {

        Class<?> c;
        try {
            c = Class.forName("primary.EntityTest");
            @SuppressWarnings("rawtypes")
            Constructor[] constructors = c.getConstructors();
            Object test = constructors[0].newInstance(new Object[] { });//调用无参构造创建实例.
            Method method1=c.getMethod("getA", new Class[] {});
            Object o= method1.invoke(test, new Object[]{});
            System.out.println(o);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }

}
