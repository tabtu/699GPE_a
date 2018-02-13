package uow.csse.tv.gpe.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
    private int id;
    private String name;

    private String cname;
    private String tablename;
    private Category category;
    private int sort;
    private List<Venue> venues = new ArrayList<>();

//    @ManyToMany(mappedBy = "schools", cascade = CascadeType.ALL)
//    private List<School> myfav;

    public Sport() { }

    public Sport(String name) { this.name = name; }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
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

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }
}
