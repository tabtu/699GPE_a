package uow.cs.tv.gpe.model;

import java.io.Serializable;

/**
 * Message Entity
 *
 * @author 	Tab Tu
 * @date	2018-01-31
 * @update  Tab Tu on Feb.01 2018
 * @since	1.0
 *
 */

public class Msgs implements Serializable {

    private String sender;
    private String receiver;
    private Long sendtime;
    private boolean isread;
    private String text;
    private Long logtime;

    public Msgs() {

    }

    public Msgs(String s, String r, Long st, String text, boolean isread) {
        this.sender = s;
        this.receiver = r;
        this.sendtime = st;
        this.logtime = st;
        this.text = text;
        this.isread = isread;
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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean getIsread() {
        return isread;
    }

    public void setIsread(boolean isread) {
        this.isread = isread;
    }

    public Long getLogtime() {
        return logtime;
    }

    public void setLogtime(Long logtime) {
        this.logtime = logtime;
    }
}

