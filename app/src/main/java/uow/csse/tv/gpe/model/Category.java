package uow.csse.tv.gpe.model;

import java.io.Serializable;

/**
 * Category Entity
 *
 * @author 	Tab Tu
 * @date	2018-01-30
 * @update  Tab Tu on Feb.03 2018
 * @since	1.0
 *
 */
public class Category extends Entitys implements Serializable {
    private int cate_id;
    private String name;
    private int sort;

    public Category() {

    }

    public Category(int cate_id) {
        this.cate_id = cate_id;
    }

    public int getCate_id() {
        return cate_id;
    }

    public void setCate_id(int cate_id) {
        this.cate_id = cate_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }
}