package com.javarush.controller;

import com.javarush.dto.TaskDTO;
import com.javarush.entity.TaskEntity;
import com.javarush.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.IntStream;

import static java.util.Objects.isNull;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getTasks(Model model,
                                     @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                     @RequestParam(value = "limit", required = false, defaultValue = "10") int limit) {

        List<TaskEntity> tasks = taskService.getAll((page - 1) * limit, limit);
        model.addAttribute("tasks", tasks);
        model.addAttribute("currentPage", page);
        int totalPages = (int) Math.ceil(1.0 * taskService.getAllCount() / limit);
        if (totalPages > 1) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().toList();
            model.addAttribute("page_numbers", pageNumbers);
        }
        return "tasks";
    }

    @PostMapping("/{id}")
    public String edit(Model model,
                     @PathVariable Long id,
                     @RequestBody TaskDTO taskDTO) {
        if (isNull(id) || id <=0 ) {
            throw new RuntimeException("Invalid id");
        }

        taskService.edit(id, taskDTO.getDescription(), taskDTO.getStatus());
        return getTasks(model, 1, 10);
    }

    @PostMapping("/")
    public String add(Model model,
                    @RequestBody TaskDTO taskDTO) {
        taskService.create(taskDTO.getDescription(), taskDTO.getStatus());
        return getTasks(model, 1, 10);
    }

    @DeleteMapping("/{id}")
    public String delete(Model model,
                         @PathVariable Long id) {
        if (isNull(id) || id <=0 ) {
            throw new RuntimeException("Invalid id");
        }

        taskService.delete(id);
        return getTasks(model, 1, 10);
    }
}
