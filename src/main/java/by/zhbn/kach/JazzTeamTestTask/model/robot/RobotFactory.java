package by.zhbn.kach.JazzTeamTestTask.model.robot;

import by.zhbn.kach.JazzTeamTestTask.component.Logger;
import by.zhbn.kach.JazzTeamTestTask.component.Manager;

public class RobotFactory {
    private static int robotNumber = 1;
    private final Manager manager;
    private final Logger logger;

    public RobotFactory(Manager manager, Logger logger) {
        this.manager = manager;
        this.logger = logger;
    }

    public Robot createRobot(RobotType robotType) {
        Robot robot = switch (robotType) {
            case BUILDER -> new RobotBuilder("Robot " + robotNumber++ + ": BUILDER", manager, logger);
            case CLEANER -> new RobotCleaner("Robot " + robotNumber++ + ": CLEANER", manager, logger);
            case COURIER -> new RobotCourier("Robot " + robotNumber++ + ": COURIER", manager, logger);
            case COOK -> new RobotCook("Robot " + robotNumber++ + ": COOK", manager, logger);
        };

        logger.addLogNote(robot.getRobotName(), "Создан");
        System.out.println("Created robot " + robot.getRobotName());
        manager.getAllRobots().add(robot);
        return robot;
    }
}
