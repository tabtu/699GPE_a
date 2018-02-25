package uow.csse.tv.gpe.model;

import java.util.Date;

/**
 * Activity User Orders Entity
 *
 * @author 	Tab Tu
 * @date	2018-02-23
 * @update  Tab Tu on Feb.23 2018
 * @since	1.0
 *
 */

public class ActOds {

    private String id;
    private Activity activity;
    private User user;
    private Date date;
    private int count;
    private boolean ispaid;
    private Date pdate;

    public ActOds() {

    }

    public ActOds(Activity a, User u, Date d, int c) {
        this.activity = a;
        this.user = u;
        this.date = d;
        this.count = c;
        this.ispaid = false;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean getIspaid() {
        return ispaid;
    }

    public void setIspaid(boolean ispaid) {
        this.ispaid = ispaid;
    }

    public Date getPdate() {
        return pdate;
    }

    public void setPdate(Date pdate) {
        this.pdate = pdate;
    }
}
