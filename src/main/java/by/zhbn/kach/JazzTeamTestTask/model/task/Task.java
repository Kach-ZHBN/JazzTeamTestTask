package by.zhbn.kach.JazzTeamTestTask.model.task;

public class Task {
    private TaskType taskType;
    private String description;

    public Task() {
    }

    public Task(TaskType taskType, String description) {
        this.taskType = taskType;
        this.description = description;
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
}
