package uow.csse.tv.gpe.model;

import java.io.Serializable;

/**
 * Message Entity
 *
 * @author 	Tab Tu
 * @date	2018-01-30
 * @update  Tab Tu on Feb.01 2018
 * @since	1.0
 *
 */
public class Message extends Entitys implements Serializable {
    private Msg msgid;
    private boolean isread;
    private String text;
    private Long logtime;

    public Message() {

    }

    public Message(Msg msd, String txt) {
        this.msgid = msd;
        this.text = txt;
    }

    public Msg getMsgid() {
        return msgid;
    }

    public void setMsgid(Msg msgid) {
        this.msgid = msgid;
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