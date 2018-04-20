import org.junit.jupiter.api.Test;
import primary.EntityTest;
import util.CglibBean;
import util.ExcelRowColformat;

import java.util.ArrayList;
import java.util.List;

import static primary.Import.readExcelData;
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

    @Test
    public void testImportExcelData() throws ClassNotFoundException {
//        EntityTest entity = new EntityTest();
        // Excel列数据和实体字段一一对应
        List<ExcelRowColformat> list = new ArrayList<>();
        //获取从第一行（除去标题的第一行）开始第二列的所有数据
        list.add(new ExcelRowColformat("1","1","name"));
        list.add(new ExcelRowColformat("1","2","sex"));
//        CglibBean bean = createBean(list);
        // 获得bean的实体
//        Object object = bean.getObject();

        //提取EXCEL文件信息
//        List<EntityTest> entityList = readExcelData(entity,"1","4",list,"D:\\workInformation\\2017.xls");
//        for (EntityTest e: entityList){
//            System.out.println(e.getName());
//            System.out.println(e.getSex()+"后面的。。。。。。");
//        }
//        System.out.println(entityList.size()+":...................");
    }

}
