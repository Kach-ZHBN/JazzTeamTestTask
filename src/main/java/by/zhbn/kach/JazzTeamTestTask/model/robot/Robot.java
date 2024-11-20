package by.zhbn.kach.JazzTeamTestTask.model.robot;

import by.zhbn.kach.JazzTeamTestTask.component.Logger;
import by.zhbn.kach.JazzTeamTestTask.component.Manager;
import by.zhbn.kach.JazzTeamTestTask.model.task.Task;
import by.zhbn.kach.JazzTeamTestTask.model.task.TaskType;

import java.util.LinkedList;
import java.util.Queue;


public abstract class Robot extends Thread {
    private String robotName;
    private volatile Queue<Task> tasks;
    private RobotType robotType;
    private Manager manager;
    private Logger logger;
    private Thread thread;

    public Robot(String robotName, Manager manager, Logger logger, RobotType robotType) {
        this.robotName = robotName;
        this.manager = manager;
        this.logger = logger;
        this.robotType = robotType;
        tasks = new LinkedList<>();
        start();
    }

    public void performCommonTask(Task task) {
        try {
            logger.addLogNote(robotName, "Начинает выполнять задание типа " + task.getTaskType() + " | " + task.getDescription());
            Thread.sleep((int) (Math.random() * 15000) + 10000);
            logger.addLogNote(robotName, "Закончил выполнять задание типа " + task.getTaskType() + " | " + task.getDescription());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public abstract void performUniqueTask(Task task) throws InterruptedException;

    public void killYourself() {
        System.out.println("Robot " + robotName + " kill yourself");
        logger.addLogNote(robotName, "Убивает сам себя");
        manager.killRobot(this);
    }

    public void addTask(Task task) {
        tasks.add(task);
        logger.addLogNote("" + this.robotName,
                "Получил задание типа " + task.getTaskType() + " | " + task.getDescription());
        if (thread == null || thread.getState() == State.WAITING) {
            synchronized (this) {
                notify();
            }
        }
    }

    @Override
    public void run() {
        synchronized (this) {
            thread = Thread.currentThread();
            while (true) {
                try {
                    while (!tasks.isEmpty()) {
                        Task taskForExecute = tasks.poll();
                        if (taskForExecute.getTaskType() == TaskType.COMMON) {
                            performCommonTask(taskForExecute);
                        } else if (taskForExecute.getTaskType() == TaskType.KILLYOURSELF) {
                            killYourself();
                            return;
                        } else {
                            performUniqueTask(taskForExecute);
                        }
                    }
                    manager.registerFreeRobot(this);
                    wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
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

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public Logger getLogger() {
        return logger;
    }

    public void setLogger(Logger logger) {
        this.logger = logger;
    }
}
