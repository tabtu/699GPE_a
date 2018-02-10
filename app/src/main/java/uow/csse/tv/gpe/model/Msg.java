package uow.csse.tv.gpe.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Message Entity
 *
 * @author 	Tab Tu
 * @date	2018-01-31
 * @update  Tab Tu on Feb.01 2018
 * @since	1.0
 *
 */

public class Msg implements Serializable {

    private String sender;
    private String receiver;
    private Date sendtime;
    private Date receivetime;

    public Msg() {

    }

    public Msg(String s, String r, Date st, Date rt) {
        this.sender = s;
        this.receiver = r;
        this.sendtime = st;
        this.receivetime = rt;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public Date getSendtime() {
        return sendtime;
    }

    public void setSendtime(Date sendtime) {
        this.sendtime = sendtime;
    }

    public Date getReceivetime() {
        return receivetime;
    }

    public void setReceivetime(Date receivetime) {
        this.receivetime = receivetime;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Msg other = (Msg) obj;
        if(sender == other.sender && receiver == other.receiver &&
                sendtime == other.sendtime && receivetime == other.receivetime) {
            return true;
        } else {
            return false;
        }
    }
}

