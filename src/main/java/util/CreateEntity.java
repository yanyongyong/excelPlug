package util;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * 类　名: CreateEntity
 * 包　名: util
 * 版　权: 2018 LXKJ Inc. All rights reserved.
 * 日　期: 2018/4/20 16:32
 * 版　本: V1.0
 * 创建人：YanYong(zivyan@qq.com)
 * 修改人：无
 */
public class CreateEntity {

    //指定实体生成所在包的路径
    private String packageOutPath = "primary";
    //作者名字
    private String authorName = "严勇";
    //表名
    private String tablename = "cfj_user";
    // 列名数组
    private String[] colnames;
    //列名类型数组
    private String[] colTypes;
    //列名大小数组
    private int[] colSizes;
    // 是否需要导入包java.util.*
    private boolean f_util = false;
    // 是否需要导入包java.sql.*
    private boolean f_sql = false;



    /**
     * 功能：生成实体类主体代码
     * @return
     */
    private String parse() {
        StringBuffer sb = new StringBuffer();
        sb.append("package " + this.packageOutPath + ";\r\n");
        //判断是否导入工具包
        if(f_util){
            sb.append("import java.util.Date;\r\n");
        }
        if(f_sql){
            sb.append("import java.sql.*;\r\n");
        }
        sb.append("\r\n");
        //注释部分
        sb.append("   /**\r\n");
        sb.append("    * "+tablename+" 实体类\r\n");
        sb.append("    * "+new Date()+" "+this.authorName+"\r\n");
        sb.append("    */ \r\n");
        //实体部分
        sb.append("\r\n\r\npublic class " + initcap(tablename) + "{\r\n");
        //属性
        processAllAttrs(sb);
        //get set方法
        processAllMethod(sb);
        sb.append("}\r\n");
        return sb.toString();
    }

    /**
     * 功能：生成所有属性
     * @param sb
     */
    private void processAllAttrs(StringBuffer sb) {
        for (int i = 0; i < colnames.length; i++) {
            sb.append("\tprivate String " + colnames[i] + ";\r\n");
        }

    }

    /**
     * 功能：生成所有方法
     * @param sb
     */
    private void processAllMethod(StringBuffer sb) {
        for (int i = 0; i < colnames.length; i++) {
            sb.append("\tpublic void set" + initcap(colnames[i]) + "( String " +
                    colnames[i] + "){\r\n");
            sb.append("\tthis." + colnames[i] + "=" + colnames[i] + ";\r\n");
            sb.append("\t}\r\n");
            sb.append("\tpublic String get" + initcap(colnames[i]) + "(){\r\n");
            sb.append("\t\treturn " + colnames[i] + ";\r\n");
            sb.append("\t}\r\n");
        }
    }

    /**
     * 功能：将输入字符串的首字母改成大写
     * @param str
     * @return
     */
    private String initcap(String str) {
        char[] ch = str.toCharArray();
        if(ch[0] >= 'a' && ch[0] <= 'z'){
            ch[0] = (char)(ch[0] - 32);
        }
        return new String(ch);
    }


}
