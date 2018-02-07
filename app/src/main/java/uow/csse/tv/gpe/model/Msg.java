package uow.csse.tv.gpe.model;

import java.io.Serializable;
import java.sql.Time;
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

    private Long sender;
    private Long receiver;
    private Long sendtime;
    private Long receivetime;

    public Msg() {

    }

    public Msg(Long s, Long r, Long st, Long rt) {
        this.sender = s;
        this.receiver = r;
        this.sendtime = st;
        this.receivetime = rt;
    }

    public long getSender() {
        return sender;
    }

    public void setSender(long sender) {
        this.sender = sender;
    }

    public long getReceiver() {
        return receiver;
    }

    public void setReceiver(long receiver) {
        this.receiver = receiver;
    }

    public Long getSendtime() {
        return sendtime;
    }

    public void setSendtime(Long sendtime) {
        this.sendtime = sendtime;
    }

    public Long getReceivetime() {
        return receivetime;
    }

    public void setReceivetime(Long receivetime) {
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

