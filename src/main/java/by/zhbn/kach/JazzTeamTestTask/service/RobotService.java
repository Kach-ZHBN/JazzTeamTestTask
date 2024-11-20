package by.zhbn.kach.JazzTeamTestTask.service;

import by.zhbn.kach.JazzTeamTestTask.DTO.RobotDTO;
import by.zhbn.kach.JazzTeamTestTask.component.Manager;
import by.zhbn.kach.JazzTeamTestTask.model.robot.Robot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RobotService {
    private final Manager manager;

    @Autowired
    public RobotService(Manager manager) {
        this.manager = manager;
    }

    public List<RobotDTO> getAllRobots() {
        List<RobotDTO> robotsDTO = new ArrayList<>();
        for (Robot robot : manager.getAllRobots()) {
            robotsDTO.add(new RobotDTO(robot.getRobotName(),
                    robot.getTasks(),
                    robot.getRobotType()));
        }
        return robotsDTO;
    }

    public RobotDTO getRobotByName(String name) {
        Optional<Robot> robotFromList = manager.getAllRobots().stream()
                .filter(robot -> robot.getRobotName().equals(name))
                .findFirst();
        if (robotFromList.isPresent()) {
            return new RobotDTO(robotFromList.get().getRobotName(),
                    robotFromList.get().getTasks(),
                    robotFromList.get().getRobotType());
        } else {
            return null;
        }
    }
}
