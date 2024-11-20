package by.zhbn.kach.JazzTeamTestTask.controller;

import by.zhbn.kach.JazzTeamTestTask.DTO.RobotDTO;
import by.zhbn.kach.JazzTeamTestTask.DTO.TaskDTO;
import by.zhbn.kach.JazzTeamTestTask.component.Logger;
import by.zhbn.kach.JazzTeamTestTask.model.LogNote;
import by.zhbn.kach.JazzTeamTestTask.service.RobotService;
import by.zhbn.kach.JazzTeamTestTask.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RESTApiController {

    private final Logger logger;
    private final RobotService robotService;
    private final TaskService taskService;

    @Autowired
    public RESTApiController(Logger logger, RobotService robotService, TaskService taskService) {
        this.logger = logger;
        this.robotService = robotService;
        this.taskService = taskService;
    }

    @GetMapping("/log")
    public ResponseEntity<List<LogNote>> getLogList() {
        List<LogNote> logList = logger.getLogList();
        if (logList.isEmpty()) {
            return (ResponseEntity<List<LogNote>>) ResponseEntity.notFound();
        } else {
            return ResponseEntity.ok(logList);
        }
    }

    @GetMapping("/robots")
    public ResponseEntity<List<RobotDTO>> getAllRobots() {
        List<RobotDTO> allRobots = robotService.getAllRobots();
        if (allRobots.isEmpty()) {
            return (ResponseEntity<List<RobotDTO>>) ResponseEntity.notFound();
        } else {
            return ResponseEntity.ok(allRobots);
        }
    }

    @GetMapping("/robots/{robotName}")
    public ResponseEntity<RobotDTO> getRobot(@PathVariable("robotName") String robotName) {
        RobotDTO robot = robotService.getRobotByName(robotName);
        if (robot == null) {
            return (ResponseEntity<RobotDTO>) ResponseEntity.notFound();
        } else {
            return ResponseEntity.ok(robot);
        }
    }

    @PostMapping("/task/new")
    public void createNewTask(@RequestBody TaskDTO taskDTO) {
        taskService.saveNewTaskFromREST(taskDTO);
    }
}
