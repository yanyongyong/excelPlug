package primary;

/**
 * 类　名: primary.EntityTest
 * 包　名: PACKAGE_NAME
 * 版　权: 2018 LXKJ Inc. All rights reserved.
 * 日　期: 2018/4/16 11:22
 * 版　本: V1.0
 * 创建人：YanYong(zivyan@qq.com)
 */
public class EntityTest {

    private String id;

    private String name;

    private String sex;

    private String school;

    private String city;

    private String time;

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

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public EntityTest(String id, String name, String sex, String school, String city, String time) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.school = school;
        this.city = city;
        this.time = time;
    }

    public EntityTest(String name, String sex) {
        this.name = name;
        this.sex = sex;
    }

    public EntityTest(){

    }
}
