package by.zhbn.kach.JazzTeamTestTask.DTO;

import by.zhbn.kach.JazzTeamTestTask.model.task.TaskType;

public class TaskDTO {
    private TaskType taskType;
    private String description;
    private String robotExecutorName;

    public TaskDTO(TaskType taskType, String description, String robotExecutorName) {
        this.taskType = taskType;
        this.description = description;
        this.robotExecutorName = robotExecutorName;
    }

    public TaskType getTaskType() {
        return taskType;
    }

    public void setTaskType(TaskType taskType) {
        this.taskType = taskType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRobotExecutorName() {
        return robotExecutorName;
    }

    public void setRobotExecutorName(String robotExecutorName) {
        this.robotExecutorName = robotExecutorName;
    }
}
