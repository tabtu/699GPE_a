package uow.csse.tv.gpe.model;

import java.io.Serializable;
import java.util.Date;

/**
 * VNews Entity
 *
 * @author 	Tab Tu
 * @date	2018-01-30
 * @update  Tab Tu on Jan.30 2018
 * @since	1.0
 *
 */

public class VNews extends Entitys implements Serializable {

    private long id;
    private Venue venue;
    private String title;
    private String content;
    private String picture;
    private Long updatedate;

    public VNews() { }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Venue getVenue() {
        return venue;
    }

    public void setVenue(Venue venue) {
        this.venue = venue;
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

    public Long getUpdateDate() {
        return updatedate;
    }

    public void setUpdateDate(Long updatedate) {
        this.updatedate = updatedate;
    }
}