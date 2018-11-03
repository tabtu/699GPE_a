package uow.cs.tv.gpe.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Running Entity
 *
 * @author 	Tab Tu
 * @date	2018-10-22
 * @since	1.0
 *
 */

public class Running extends Entitys implements Serializable {

    private long id;
    private long date;
    private String location;
    private String usid;
    private String rmid;

    public Running() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getUsid() {
        return usid;
    }

    public void setUsid(String usid) {
        this.usid = usid;
    }

    public String getRmid() {
        return rmid;
    }

    public void setRmid(String rmid) {
        this.rmid = rmid;
    }
}
