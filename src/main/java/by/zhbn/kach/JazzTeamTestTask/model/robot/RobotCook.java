package by.zhbn.kach.JazzTeamTestTask.model.robot;

import by.zhbn.kach.JazzTeamTestTask.component.Logger;
import by.zhbn.kach.JazzTeamTestTask.component.Manager;
import by.zhbn.kach.JazzTeamTestTask.model.task.Task;
import by.zhbn.kach.JazzTeamTestTask.model.task.TaskType;

public class RobotCook extends Robot {

    public RobotCook(String name, Manager manager, Logger logger) {
        super(name, manager, logger, RobotType.COOK);
    }

    @Override
    public void performUniqueTask(Task task) throws InterruptedException {
        if (task.getTaskType() == TaskType.COOK) {
            super.getLogger().addLogNote(super.getRobotName(),
                    "Начинает выполнять задание типа " + task.getTaskType() + " | " + task.getDescription());
            Thread.sleep((int) (Math.random() * 15000) + 10000);
            super.getLogger().addLogNote(super.getRobotName(),
                    "Закончил выполнять задание типа " + task.getTaskType() + " | " + task.getDescription());
        } else {
            super.getLogger().addLogNote(super.getRobotName(),
                    "Не может выполнить задание типа " + task.getTaskType()
                            + " и передает в общую очередь | " + task.getDescription());
            super.getManager().addTask(task);
        }
    }
}
