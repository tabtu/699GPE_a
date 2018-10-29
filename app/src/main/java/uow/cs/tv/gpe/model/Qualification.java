package uow.cs.tv.gpe.model;

import java.io.Serializable;

/**
 * Sport list
 *
 * @author 	Tab Tu
 * @date	2018-01-30
 * @update  Tab Tu on Feb.09 2018
 * @since	1.0
 *
 */
public class Qualification extends Entitys implements Serializable {
    private int id;
    private String name;
    private String picture;

    public Qualification() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}