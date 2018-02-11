package uow.csse.tv.gpe.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Athlete Entity
 *
 * @author 	Tab Tu
 * @date	2018-01-30
 * @update  Tab Tu on Feb.09 2018
 * @since	1.0
 *
 */
public class Athlete extends Entitys implements Serializable {
    private int athlete_id;
    private boolean gender;
    private int height;
    private int weight;
    private int focusLevel;
    private int teamSpirit;
    private int psychological;
    private int technical;
    private int communication;
    private int physical;
    //private String qualification;
    private List<Club> clubs = new ArrayList<>();

    public Athlete() {

    }

    public int getAthlete_id() {
        return athlete_id;
    }

    public void setAthlete_id(int athlete_id) {
        this.athlete_id = athlete_id;
    }

    public boolean getGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public List<Club> getClubs() {
        return clubs;
    }

    public void setClubs(List<Club> clubs) {
        this.clubs = clubs;
    }

    public int getCommunication() {
        return communication;
    }

    public void setCommunication(int communication) {
        this.communication = communication;
    }

    public int getFocusLevel() {
        return focusLevel;
    }

    public void setFocusLevel(int focusLevel) {
        this.focusLevel = focusLevel;
    }

    public int getPhysical() {
        return physical;
    }

    public void setPhysical(int physical) {
        this.physical = physical;
    }

    public int getPsychological() {
        return psychological;
    }

    public void setPsychological(int psychological) {
        this.psychological = psychological;
    }

    public int getTeamSpirit() {
        return teamSpirit;
    }

    public void setTeamSpirit(int teamSpirit) {
        this.teamSpirit = teamSpirit;
    }

    public int getTechnical() {
        return technical;
    }

    public void setTechnical(int technical) {
        this.technical = technical;
    }

    public int[] getGraphicInts() {
        int[] result = { focusLevel, teamSpirit, psychological, technical, communication, physical };
        return result;
    }
}
