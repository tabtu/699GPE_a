package uow.cs.tv.gpe.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Coach Entity
 *
 * @author 	Tab Tu
 * @date	2018-01-30
 * @update  Tab Tu on Feb.09 2018
 * @since	1.0
 *
 */

public class Coach extends Entitys implements Serializable {
    private int id;
    private List<Club> clubs = new ArrayList<>();

    public Coach() {

    }

    public int getId() {
        return id;
    }

    public void setId(int coach_id) {
        this.id = id;
    }

    public List<Club> getClubs() {
        return clubs;
    }

    public void setClubs(List<Club> clubs) {
        this.clubs = clubs;
    }
}
