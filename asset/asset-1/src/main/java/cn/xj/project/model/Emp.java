package cn.xj.project.model;

import cn.xj.common.annotation.Meta;
import cn.xj.common.annotation.Model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Welink on 2017/4/26.
 */
@Entity
@Table(name = "emp")
@Model(table = "emp")
public class Emp extends BaseModel implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "id")
    @Meta(pKey = true)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "dept_no")
    @Meta(column = "dept_no")
    private String deptNo;

    @Column(name = "age")
    @Meta(defaultValue = "20")
    private Integer age;

    @Column(name = "email")
    @Meta(defaultValue = "123@abc.com")
    private String email;

    @Column(name = "address")
    private String address;

    @Column(name = "date")
    @Meta(format = "date_format(@,'%Y-%m-%d')")
    private Date date;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDeptNo() {
        return deptNo;
    }

    public void setDeptNo(String deptNo) {
        this.deptNo = deptNo;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Emp{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", deptNo='" + deptNo + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", date=" + date +
                '}';
    }

}
