package uow.csse.tv.gpe.model;

import java.io.Serializable;
import java.util.Date;

/**
 * VNews Entity
 *
 * @author 	Tab Tu
 * @date	2018-02-23
 * @update  Tab Tu on Feb.23 2018
 * @since	1.0
 *
 */

public class CNews extends Entitys implements Serializable {

    private long id;
    private Club club;
    private String title;
    private String content;
    private Date updatedate;
    private String picture;

    public CNews() { }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Club getClub() {
        return club;
    }

    public void setClub(Club club) {
        this.club = club;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Date getUpdatedate() {
        return updatedate;
    }

    public void setUpdatedate(Date updatedate) {
        this.updatedate = updatedate;
    }
}