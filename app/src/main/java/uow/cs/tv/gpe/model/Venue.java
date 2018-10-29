package uow.cs.tv.gpe.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Venue Entity
 *
 * @author 	Tab Tu
 * @date	2018-01-30
 * @update  Tab Tu on Jan.30 2018
 * @since	1.0
 *
 */

public class Venue extends Entitys implements Serializable {
    private int id;
    private String name;
    private String tel;
    private District district;
    private String address;
    private Long createdate;
    private String picture;
    private List<Sport> sports = new ArrayList<>();


    public Venue() { }

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

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getCreateDate() {
        return createdate;
    }

    public void setCreateDate(Long createdate) {
        this.createdate = createdate;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public List<Sport> getSports() {
        return sports;
    }

    public void setSports(List<Sport> sports) {
        this.sports = sports;
    }

    public String getSportsName() {
        String result = "";
        if (sports != null) {
            if (sports.size() > 0) {
                for (int i = 0; i < sports.size(); i++) {
                    result += (sports.get(i).getName() + ", ");
                }
                return result;
            } else {
                return "empty";
            }
        } else {
            return "empty";
        }
    }
}