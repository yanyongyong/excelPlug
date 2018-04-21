import primary.EntityTest;
import org.junit.jupiter.api.Test;
import util.ExcelRowColformat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static primary.Import.readExcelData;
import static primary.Out.outDataToExcel;

/**
 * 类　名: demo
 * 包　名: PACKAGE_NAME
 * 日　期: 2018/4/17 15:18
 * 版　本: V1.0
 * 创建人：YanYong(zivyan@qq.com)
 * 描　述: Excel导入导出demo
 */
public class demo {





    /**
     * @Auther: ＾＿－)≡★ yanyong
     * @Date: 2018/4/18 17:24
     * @Description:从Excel导入数据
     */
    @Test
    public void testImportExcelData(){
        EntityTest entity = new EntityTest();
        // Excel列数据和实体字段一一对应
        List<ExcelRowColformat> list = new ArrayList<>();
         //获取从第一行（除去标题的第一行）开始第二列的所有数据
        list.add(new ExcelRowColformat("1","1","name"));
        list.add(new ExcelRowColformat("1","2","sex"));
        //提取EXCEL文件信息
        List<EntityTest> entityList = readExcelData(entity,"1","4",list,"D:\\workInformation\\2017.xls");
        for (EntityTest e: entityList){
            System.out.println(e.getName());
            System.out.println(e.getSex()+"后面的。。。。。。");
        }
        System.out.println(entityList.size()+":...................");
    }


    /**
     * @Auther: ＾＿－)≡★ yanyong
     * @Date: 2018/4/18 17:24
     * @Description:导出数据到Excel
     */
    @Test
    public void testOutDataToExcel(){
        try {
            //列表标题一定要一一对应（如姓名对应name,按实体类顺序排列）
            List<String> titleList = Arrays.asList("姓名","学校","城市","入学时间");
            //第一个参数导出实体类的List数据（如List<EntityTest>），第二个参数设置Excel表sheet的名称
            // 第三个参数实体类不需要导出数据的字段（如 id,两个字段以上用逗号隔开：id,name）,第四个参数保存Excel的地址
            outDataToExcel(titleList,generateSampleEmployeeData(),"sheet","id,sex","导出数据","D:\\8.xls");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 测试数据
     * @return
     */
    public static List<EntityTest> generateSampleEmployeeData(){
        List<EntityTest> employees = new ArrayList<EntityTest>();
        employees.add( new EntityTest("1", "沿革", "你好","东软","成都","2017-8-10") );
        employees.add( new EntityTest("2", "沿革", "你好","东软","成都","2017-8-10") );
        employees.add( new EntityTest("3", "沿革", "你好","东软","成都","2017-8-10") );
        employees.add( new EntityTest("4", "沿革", "你好","东软","成都","2017-8-10") );
        employees.add( new EntityTest("5", "沿革", "你好","东软","成都","2017-8-10") );
        return employees;
    }

}
