package util;

/**
 * All rights Reserved, Designed By LXKJ
 * 类　名: util.Genericity
 * 包　名: PACKAGE_NAME
 * 所　属: 路讯科技
 * 版　权: 2018 LXKJ Inc. All rights reserved.
 * 日　期: 2018/4/16 11:37
 * 版　本: V1.0
 * 创建人：YanYong(zivyan@qq.com)
 * 修改人：无
 * 注　意：本内容仅限于路讯科技内部传阅，禁止外泄以及用于其他的商业目的
 * 描　述:泛型类
 */

public class Genericity<T> {

    private T t;

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }
}
