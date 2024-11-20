package by.zhbn.kach.JazzTeamTestTask.model;

import java.sql.Timestamp;

public class LogNote {
    private Timestamp time;
    private String initiator;
    private String message;

    public LogNote() {
    }

    public LogNote(Timestamp time, String initiator, String message) {
        this.time = time;
        this.initiator = initiator;
        this.message = message;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public String getInitiator() {
        return initiator;
    }

    public void setInitiator(String initiator) {
        this.initiator = initiator;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "| " + time + " | " + initiator + " | " + message + " |";
    }
}
