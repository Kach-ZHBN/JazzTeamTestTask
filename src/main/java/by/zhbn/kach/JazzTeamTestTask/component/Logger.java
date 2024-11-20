package by.zhbn.kach.JazzTeamTestTask.component;

import by.zhbn.kach.JazzTeamTestTask.model.LogNote;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.*;

@Component
public class Logger {
    private final List<LogNote> logList;

    public Logger() {
        this.logList = new ArrayList<>();
    }

    public List<LogNote> getLogList() {
        return logList;
    }

    public void addLogNote(String initiator, String message) {
        logList.add(0, new LogNote(new Timestamp(System.currentTimeMillis()),
                initiator, message));
    }
}
