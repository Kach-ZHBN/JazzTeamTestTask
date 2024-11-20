package by.zhbn.kach.JazzTeamTestTask.DTO;

import by.zhbn.kach.JazzTeamTestTask.model.robot.RobotType;
import by.zhbn.kach.JazzTeamTestTask.model.task.Task;

import java.util.Queue;

public class RobotDTO {
    private String robotName;
    private Queue<Task> tasks;
    private RobotType robotType;

    public RobotDTO(String robotName, Queue<Task> tasks, RobotType robotType) {
        this.robotName = robotName;
        this.tasks = tasks;
        this.robotType = robotType;
    }

    public String getRobotName() {
        return robotName;
    }

    public void setRobotName(String robotName) {
        this.robotName = robotName;
    }

    public Queue<Task> getTasks() {
        return tasks;
    }

    public void setTasks(Queue<Task> tasks) {
        this.tasks = tasks;
    }

    public RobotType getRobotType() {
        return robotType;
    }

    public void setRobotType(RobotType robotType) {
        this.robotType = robotType;
    }
}
