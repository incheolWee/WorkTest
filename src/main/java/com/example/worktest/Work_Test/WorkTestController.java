package com.example.worktest.Work_Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/work") // Base path is /work
public class WorkTestController {

    @Autowired
    private WorkTestService workService;

    @GetMapping
    public String getAllWorks(Model model) {
        List<WorkTestDTO> workList = workService.getAllWorks();
        model.addAttribute("works", workList);
        return "work-list"; // This should match the name of the Thymeleaf template
    }

    @GetMapping("/add")
    public String showAddWorkForm(Model model) {
        model.addAttribute("work", new WorkTestDTO());
        return "work-form"; // Go to work form page
    }

    @PostMapping("/save")
    public String saveWork(@ModelAttribute("work") WorkTestDTO workDTO) {
        workService.createWork(workDTO);
        return "redirect:/work"; // Redirect to the work list page
    }

    @GetMapping("/edit/{id}")
    public String showEditWorkForm(@PathVariable Long id, Model model) {
        Optional<WorkTestDTO> workDTO = workService.getWorkById(id);
        if (workDTO.isPresent()) {
            model.addAttribute("work", workDTO.get());
            return "work-form"; // Use the same form for editing
        } else {
            return "redirect:/work"; // Redirect if not found
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteWork(@PathVariable Long id) {
        workService.deleteWork(id);
        return "redirect:/work"; // Redirect to the work list page
    }
}
