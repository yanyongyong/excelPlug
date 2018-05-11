package com.lxkj.jxls.test;

import com.lxkj.jxls.core.ImportImp;
import com.lxkj.jxls.entity.EntityOneDb;
import com.lxkj.jxls.entity.ImportAttibute;
import com.lxkj.jxls.entity.MessageCode;
import com.lxkj.jxls.entity.PathEnum;
import org.junit.jupiter.api.Test;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

import static com.lxkj.jxls.entity.PathEnum.CONFIG_NAME;
import static com.lxkj.jxls.util.ConfigFile.savaExcelData;

/**
 * @Author: ziv
 * @Date: 2018/5/2 16:02
 * @Description: 导入测试(先保存Excel配置文件，再导入Excel数据)
 */
public class ImportDemo {

    static org.slf4j.Logger logger = LoggerFactory.getLogger(ImportDemo.class);

    @Test
    public void saveConfig(){
        //保存Excel相关配置信息
        List<EntityOneDb> entityOneDbs = new ArrayList<>();
        entityOneDbs.add(new EntityOneDb("2","城市","en_arrive","db_city"));
        entityOneDbs.add(new EntityOneDb("3","学校","en_start","db_school"));
        ImportAttibute data = new ImportAttibute("table_test2","铁路表格","铁路数据","1","1","3",entityOneDbs);
        if (savaExcelData(data,CONFIG_NAME.getPath())){
            logger.info(MessageCode.getByCode(100).getCode());
        }
        logger.info(MessageCode.getByCode(200).getCode());
    }

    @Test
    public void imports(){
        String code = new ImportImp().handler("table_test1","D:\\workInformation\\2017.xlsx");
        logger.info(code);
    }

}
