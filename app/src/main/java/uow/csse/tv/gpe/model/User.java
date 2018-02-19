package uow.csse.tv.gpe.model;

import java.io.Serializable;
import java.util.Date;
import uow.csse.tv.gpe.model.Coach;
/**
 * User Entity
 *
 * @author 	Tab Tu
 * @date	2018-1-30
 * @update  Tab Tu on Jan.30 2018
 * @since	1.0
 *
 */

public class User extends Entitys implements Serializable {
    private String id;
    private String username;
    private String telephone;
    private String password;
    private String email;
    private boolean enabled;
    private String picture;
    private String introduction;
    private Long createtime;
    private Long updatetime;
    private String name;
    private Long birth;
    private boolean gender;
    private Athlete athlete;
    private Coach coach;
    private Referee referee;

    public User() { super(); }

    public User(String userName, String passWord) {
        super();
        this.username = userName;
        this.password = passWord;
    }

    public User(String userName, String passWord, String email) {
        super();
        this.email = email;
        this.password = passWord;
        this.username = userName;
        this.enabled = true;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public Long getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Long createtime) {
        this.createtime = createtime;
    }

    public Long getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Long updatetime) {
        this.updatetime = updatetime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getBirth() {
        return birth;
    }

    public void setBirth(Long birth) {
        this.birth = birth;
    }

    public boolean getGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public Athlete getAthlete() {
        return athlete;
    }

    public void setAthlete(Athlete athlete) {
        this.athlete = athlete;
    }

    public Coach getCoach() {
        return coach;
    }

    public void setCoach(Coach coach) {
        this.coach = coach;
    }

    public Referee getReferee() {
        return referee;
    }

    public void setReferee(Referee referee) {
        this.referee = referee;
    }
}