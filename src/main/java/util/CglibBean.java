package util;

import net.sf.cglib.beans.BeanGenerator;
import net.sf.cglib.beans.BeanMap;

import java.lang.reflect.Method;
import java.util.*;

/**
 * 类　名: util.CglibBean
 * 包　名: PACKAGE_NAME
 * 日　期: 2018/4/16 9:52
 * 版　本: V1.0
 * 创建人：YanYong
 * 修改人：无
 * 描　述: TODO(用一句话描述该文件做什么)
 */
public class CglibBean {

    /**
     * 实体Object
     */
    public Object object = null;

    /**
     * 属性map
     */
    public BeanMap beanMap = null;

    public CglibBean() {
        super();
    }

    @SuppressWarnings("unchecked")
    public CglibBean(Map propertyMap) {
        this.object = generateBean(propertyMap);
        this.beanMap = BeanMap.create(this.object);
    }

    /**
     * 给bean属性赋值
     *
     * @param property 属性名
     * @param value    值
     */
    public void setValue(String property, Object value) {
        beanMap.put(property, value);
    }

    /**
     * 通过属性名得到属性值
     *
     * @param property 属性名
     * @return 值
     */
    public Object getValue(String property) {
        return beanMap.get(property);
    }

    /**
     * 得到该实体bean对象
     *
     * @return
     */
    public Object getObject() {
        return this.object;
    }

    @SuppressWarnings("unchecked")
    private Object generateBean(Map propertyMap) {
        BeanGenerator generator = new BeanGenerator();
        Set keySet = propertyMap.keySet();
        for (Iterator i = keySet.iterator(); i.hasNext(); ) {
            String key = (String) i.next();
            generator.addProperty(key, (Class) propertyMap.get(key));
        }
        return generator.create();
    }








    /**
     * @Auther: ＾＿－)≡★ yanyong
     * @Date: 2018/4/20 16:37
     * @Description: 动态生成实体类（只保存在内存中）
     * @param
     * @return
     */
    @SuppressWarnings("unchecked")
    public static CglibBean createBean(List<ExcelRowColformat> ercf) throws ClassNotFoundException {
        HashMap propertyMap = new HashMap();
        propertyMap.put("id",Class.forName("java.lang.String"));
        for (ExcelRowColformat er: ercf){
            propertyMap.put(er.getEntityFiledName(),"java.lang.String");
        }
        // 生成动态 Bean
        CglibBean bean = new CglibBean(propertyMap);
        return bean;
    }




    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws ClassNotFoundException {
        // 设置类成员属性
        HashMap propertyMap = new HashMap();
        propertyMap.put("id", Class.forName("java.lang.Integer"));
        propertyMap.put("name", Class.forName("java.lang.String"));
        propertyMap.put("address", Class.forName("java.lang.String"));
        // 生成动态 Bean
        CglibBean bean = new CglibBean(propertyMap);
        // 给 Bean 设置值
        bean.setValue("id", new Integer(123));
        bean.setValue("name", "454");
        bean.setValue("address", "789");
        // 从 Bean 中获取值，当然了获得值的类型是 Object
        System.out.println("  >> id      = " + bean.getValue("id"));
        System.out.println("  >> name    = " + bean.getValue("name"));
        System.out.println("  >> address = " + bean.getValue("address"));
        // 获得bean的实体
        Object object = bean.getObject();
        // 通过反射查看所有方法名
        Class clazz = object.getClass();
        Method[] methods = clazz.getDeclaredMethods();
        for (int i = 0; i < methods.length; i++) {
            System.out.println(methods[i].getName());
        }

    }


}