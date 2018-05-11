package com.lxkj.jxls.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lxkj.jxls.entity.EntityOneDb;
import net.sf.cglib.beans.BeanGenerator;
import net.sf.cglib.beans.BeanMap;

import java.lang.reflect.Method;
import java.util.*;

/**
 * @Author: ziv
 * @Date: 2018/4/28 09:19
 * @Description: 动态生成Bean
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
     * @param property 属性名
     * @param value    值
     */
    public void setValue(String property, Object value) {
        beanMap.put(property, value);
    }

    /**
     * 通过属性名得到属性值
     * @param property 属性名
     * @return 值
     */
    public Object getValue(String property) {
        return beanMap.get(property);
    }

    /**
     * 得到该实体bean对象
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


    @SuppressWarnings("unchecked")
    public static CglibBean createTBean(List<EntityOneDb> entityOneDb) throws ClassNotFoundException {
        HashMap propertyMap = new HashMap();
        propertyMap.put("id",Class.forName("java.lang.String"));
        for(EntityOneDb oneDb : entityOneDb) {
            propertyMap.put(oneDb.getEntityName(),Class.forName("java.lang.String"));
        }
        CglibBean bean = new CglibBean(propertyMap);
        return bean;
    }



}