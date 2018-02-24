package uow.csse.tv.gpe.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Log Entity
 *
 * @author 	Tab Tu
 * @date	2018-01-30
 * @update  Tab Tu on Jan.30 2018
 * @since	1.0
 *
 */
public class Log extends Entitys implements Serializable {
    private String logid;
    private User lguser;
    private Date lgtime;
    private String operation;

    public Log() {

    }

    public Log(User u, Date t, String op) {
        this.lguser = u;
        this.lgtime = t;
        this.operation = op;
    }

    public String getLogid() {
        return logid;
    }

    public void setLogid(String logid) {
        this.logid = logid;
    }

    public User getLguser() {
        return lguser;
    }

    public void setLguser(User lguser) {
        this.lguser = lguser;
    }

    public Date getLgtime() {
        return lgtime;
    }

    public void setLgtime(Date lgtime) {
        this.lgtime = lgtime;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }
}