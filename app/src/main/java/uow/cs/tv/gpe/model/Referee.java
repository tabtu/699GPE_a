package uow.cs.tv.gpe.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Referee Entity
 *
 * @author 	Tab Tu
 * @date	2018-01-30
 * @update  Tab Tu on Jan.30 2018
 * @since	1.0
 *
 */
public class Referee extends Entitys implements Serializable {
    private int id;
    private List<Qualification> qualifications;
    private List<Club> clubs = new ArrayList<>();

    public Referee() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Club> getClubs() {
        return clubs;
    }

    public void setClubs(List<Club> clubs) {
        this.clubs = clubs;
    }

    public List<Qualification> getQualifications() {
        return qualifications;
    }

    public void setQualifications(List<Qualification> qualifications) {
        this.qualifications = qualifications;
    }
}