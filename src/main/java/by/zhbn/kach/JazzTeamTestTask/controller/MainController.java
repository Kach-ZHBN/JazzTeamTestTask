package by.zhbn.kach.JazzTeamTestTask.controller;

import by.zhbn.kach.JazzTeamTestTask.component.Logger;
import by.zhbn.kach.JazzTeamTestTask.component.Manager;
import by.zhbn.kach.JazzTeamTestTask.model.task.Task;
import by.zhbn.kach.JazzTeamTestTask.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {

    private final TaskService taskService;
    private final Manager manager;
    private final Logger logger;

    @Autowired
    public MainController(TaskService taskService, Manager manager, Logger logger) {
        this.taskService = taskService;
        this.manager = manager;
        this.logger = logger;
    }

    @GetMapping("/")
    public String homePage(Model model) {
        model.addAttribute("logList", logger.getLogList());
        return "main-page";
    }

    @GetMapping("/new")
    public String getNewTaskPage(Model model) {
        model.addAttribute("robots", manager.getAllRobots());
        model.addAttribute("taskTypes", taskService.getTypes());
        model.addAttribute("newTask", new Task());
        return "new-task-page";
    }

    @PostMapping("/new")
    public String newTask(@ModelAttribute("newTask") Task newTask,
                          @ModelAttribute("robotName") String robotName) {
        taskService.saveNewTask(newTask, robotName);
        return "redirect:/";
    }
}
