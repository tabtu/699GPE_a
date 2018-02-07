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

public class City extends Entitys implements Serializable {

    private short city_id;
    private String name;
    private short sort;

    public City() {

    }

    public short getCity_id() {
        return city_id;
    }

    public void setCity_id(short city_id) {
        this.city_id = city_id;
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
