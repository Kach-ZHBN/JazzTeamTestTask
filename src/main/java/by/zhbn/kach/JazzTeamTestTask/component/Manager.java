package by.zhbn.kach.JazzTeamTestTask.component;

import by.zhbn.kach.JazzTeamTestTask.model.task.Task;
import by.zhbn.kach.JazzTeamTestTask.model.robot.Robot;
import by.zhbn.kach.JazzTeamTestTask.model.robot.RobotFactory;
import by.zhbn.kach.JazzTeamTestTask.model.robot.RobotType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

@Component
public class Manager {
    private final Queue<Task> taskQueue;
    private final List<Robot> allRobots;
    private final List<Robot> freeRobots;
    private final Logger logger;
    private final RobotFactory robotFactory;

    @Autowired
    public Manager(Logger logger) {
        this.logger = logger;
        allRobots = new ArrayList<>();
        freeRobots = new ArrayList<>();
        taskQueue = new LinkedList<>();
        this.robotFactory = new RobotFactory(this, logger);
    }

    public List<Robot> getAllRobots() {
        return allRobots;
    }

    public void addTask(Task task) {
        taskQueue.add(task);
        logger.addLogNote("Менеджер",
                "Получил в общую очередь задание типа " + task.getTaskType() + " | " + task.getDescription());
        doWork();
    }

    public void doWork() {
        while (!taskQueue.isEmpty()) {
            Task taskForExecute = taskQueue.poll();
            Robot robotExecutor = switch (taskForExecute.getTaskType()) {
                case BUILD -> findExecutorByType(RobotType.BUILDER);
                case CLEAN -> findExecutorByType(RobotType.CLEANER);
                case DELIVER -> findExecutorByType(RobotType.COURIER);
                case COOK -> findExecutorByType(RobotType.COOK);
                default -> findExecutorByType(RobotType.COURIER);
            };
            logger.addLogNote("Менеджер",
                    "Передал задание типа " + taskForExecute.getTaskType() + " роботу " + robotExecutor.getRobotName());
            robotExecutor.addTask(taskForExecute);
            freeRobots.remove(robotExecutor);
        }
    }

    private Robot findExecutorByType(RobotType robotType) {
        for (Robot freeRobot : freeRobots) {
            if (freeRobot.getRobotType() == robotType) {
                return freeRobot;
            }
        }
        return robotFactory.createRobot(robotType);
    }

    public void registerFreeRobot(Robot robot) {
        if (!freeRobots.contains(robot)) {
            freeRobots.add(robot);
        }
    }

    public void killRobot(Robot robot) {
        freeRobots.remove(robot);
        allRobots.remove(robot);
    }
}
