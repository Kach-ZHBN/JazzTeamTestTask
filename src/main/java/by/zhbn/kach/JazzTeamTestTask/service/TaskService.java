package by.zhbn.kach.JazzTeamTestTask.service;

import by.zhbn.kach.JazzTeamTestTask.DTO.TaskDTO;
import by.zhbn.kach.JazzTeamTestTask.component.Manager;
import by.zhbn.kach.JazzTeamTestTask.model.task.Task;
import by.zhbn.kach.JazzTeamTestTask.model.task.TaskType;
import by.zhbn.kach.JazzTeamTestTask.component.Logger;
import by.zhbn.kach.JazzTeamTestTask.model.robot.Robot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    private final Logger logger;
    private final Manager manager;

    @Autowired
    public TaskService(Logger logger, Manager manager) {
        this.logger = logger;
        this.manager = manager;
    }

    public List<TaskType> getTypes() {
        return Arrays.stream(TaskType.values()).toList();
    }

    public void saveNewTask(Task task, String robotName) {
        if (robotName.equals("commonTask")) {
            manager.addTask(task);
        } else if (robotName.equals("broadcastTask")) {
            doBroadcastTask(task);
        } else {
            Robot robotForTask = manager.getAllRobots().stream()
                    .filter(robot -> robot.getRobotName().equals(robotName))
                    .findFirst().get();
            robotForTask.addTask(task);
        }
    }

    private void doBroadcastTask(Task task) {
        List<Robot> robotsForBroadcastTask = manager.getAllRobots();
        if (!robotsForBroadcastTask.isEmpty()) {
            logger.addLogNote("BROADCAST", "Выдает задание всем роботам");
            for (Robot robot : robotsForBroadcastTask) {
                robot.addTask(task);
            }
        } else {
            logger.addLogNote("BROADCAST", "ERROR. Роботов не существует");
        }
    }

    public void saveNewTaskFromREST(TaskDTO taskDTO) {
        Optional<Robot> robotExecutor = manager.getAllRobots().stream()
                .filter(robot -> robot.getRobotName().equals(taskDTO.getRobotExecutorName()))
                .findFirst();
        Task task = new Task(taskDTO.getTaskType(), taskDTO.getDescription());
        if (robotExecutor.isPresent()) {
            robotExecutor.get().addTask(task);
        } else {
            manager.addTask(task);
        }
    }
}
