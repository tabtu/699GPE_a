package uow.csse.tv.gpe.model;

import java.io.Serializable;

/**
 * Log Entity
 *
 * @author 	Tab Tu
 * @date	2018-01-30
 * @update  Tab Tu on Feb.03 2018
 * @since	1.0
 *
 */

public class Category extends Entitys implements Serializable {

    private short cate_id;
    private String name;
    private short sort;

    public Category() {

    }

    public short getCate_id() {
        return cate_id;
    }

    public void setCate_id(short cate_id) {
        this.cate_id = cate_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public short getSort() {
        return sort;
    }

    public void setSort(short sort) {
        this.sort = sort;
    }
}
