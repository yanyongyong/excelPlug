package primary;

/**
 * All rights Reserved, Designed By LXKJ
 * 类　名: primary.Entity
 * 包　名: PACKAGE_NAME
 * 所　属: 路讯科技
 * 版　权: 2018 LXKJ Inc. All rights reserved.
 * 日　期: 2018/4/16 11:22
 * 版　本: V1.0
 * 创建人：YanYong(zivyan@qq.com)
 * 修改人：无
 * 注　意：本内容仅限于路讯科技内部传阅，禁止外泄以及用于其他的商业目的
 * 描　述: TODO(用一句话描述该文件做什么)
 */
public class Entity {

    private String id;

    private String name;

    private String sex;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Entity(String id, String name, String sex) {
        this.id = id;
        this.name = name;
        this.sex = sex;
    }

    public Entity(String name, String sex) {
        this.name = name;
        this.sex = sex;
    }

    public Entity(){

    }
}
