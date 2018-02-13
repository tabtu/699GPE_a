package uow.csse.tv.gpe.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Sport list
 *
 * @author 	Tab Tu
 * @date	2018-01-30
 * @update  Tab Tu on Jan.30 2018
 * @since	1.0
 *
 */
public class News extends Entitys implements Serializable {
    private long id;
    private Long updatedate;
    private String title;
    private String text;

    public News() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Long getUpdateDate() {
        return updatedate;
    }

    public void setUpdateDate(Long updatedate) {
        this.updatedate = updatedate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}