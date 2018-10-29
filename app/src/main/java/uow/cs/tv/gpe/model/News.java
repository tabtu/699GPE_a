package uow.cs.tv.gpe.model;

import java.io.Serializable;

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
    private City city;
    private String background;
    private String author;
    private boolean home;

    public News() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Long getUpdatedate() {
        return updatedate;
    }

    public void setUpdatedate(Long updatedate) {
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

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public boolean getHome() {
        return home;
    }

    public void setHome(boolean home) {
        this.home = home;
    }
}