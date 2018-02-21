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
    private Long sendtime;

    public Msg() {

    }

    public Msg(String s, String r, Long st) {
        this.sender = s;
        this.receiver = r;
        this.sendtime = st;
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

    public Long getSendtime() {
        return sendtime;
    }

    public void setSendtime(Long sendtime) {
        this.sendtime = sendtime;
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
        if(sender == other.sender && receiver == other.receiver && sendtime == other.sendtime) {
            return true;
        } else {
            return false;
        }
    }
}

