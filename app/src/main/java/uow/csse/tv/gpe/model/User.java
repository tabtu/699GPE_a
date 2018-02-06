package uow.csse.tv.gpe.model;

import java.io.Serializable;
import java.util.Date;

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

    private Long user_id;
    private String username;
    private String password;
    private String email;
    private boolean enabled;
    private String profilePicture;
    private String introduction;
    private Date createTime;
    private Date lastModifyTime;


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

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastModifyTime() {
        return lastModifyTime;
    }

    public void setLastModifyTime(Date lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }
}
