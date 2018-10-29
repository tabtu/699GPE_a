//package uow.csse.tv.gpe.model;
//
//import com.fasterxml.jackson.annotation.JsonManagedReference;
//import org.hibernate.annotations.NaturalId;
//
//import javax.persistence.*;
//import java.io.Serializable;
//import java.util.Set;
//
///**
// * Interest Entity
// *
// * @author 	Tab Tu
// * @date	2018-01-30
// * @update  Tab Tu on Feb.03 2018
// * @since	1.0
// *
// */
//
//@Entity(name = "Interest")
//@Table(name = "tv_interest")
//public class Interest extends Entitys implements Serializable {
//
//    @Id
//    @ManyToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name="interest_user")
//    @JsonManagedReference
//    private User user;
//    @Id
//    @ManyToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name="interest_sport")
//    @JsonManagedReference
//    private Sport sport;
//    private int rate;
//
//    public Interest() {
//
//    }
//
//    public Interest(User u, Sport s, int r) {
//        user = u;
//        sport = s;
//        rate = r;
//    }
//
//    public Sport getSport() {
//        return sport;
//    }
//
//    public void setSport(Sport sport) {
//        this.sport = sport;
//    }
//
//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }
//
//    public int getRate() {
//        return rate;
//    }
//
//    public void setRate(int rate) {
//        this.rate = rate;
//    }
//}
