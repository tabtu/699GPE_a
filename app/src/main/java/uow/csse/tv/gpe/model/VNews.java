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

    private long vnews_id;
    private Venue venue;
    private String title;
    private String content;
    private String picture;
    private Long updateDate;

    public VNews() { }

    public long getVnews_id() {
        return vnews_id;
    }

    public void setVnews_id(long vnews_id) {
        this.vnews_id = vnews_id;
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
        return updateDate;
    }

    public void setUpdateDate(Long updateDate) {
        this.updateDate = updateDate;
    }
}
