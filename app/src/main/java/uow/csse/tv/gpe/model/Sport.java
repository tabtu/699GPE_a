package uow.csse.tv.gpe.model;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * Sport list
 *
 * @author 	Tab Tu
 * @date	2018-01-30
 * @update  Tab Tu on Jan.30 2018
 * @since	1.0
 *
 */

public class Sport extends Entitys implements Serializable {

    private short sport_id;
    private String name;
    private String cname;
    private String tablename;
    private Category category;
    private short sort;

    public Sport() { }

    public Sport(String name) { this.name = name; }

    public short getSport_id() { return sport_id; }
    public void setSport_id(short id) { this.sport_id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getTablename() { return tablename; }
    public void setTablename(String tablename) { this.tablename = tablename; }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public short getSort() {
        return sort;
    }

    public void setSort(short sort) {
        this.sort = sort;
    }
}
