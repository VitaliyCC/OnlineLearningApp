package com.learning.spring.controllers;

import com.learning.spring.service.ScheduleService;
import org.apache.log4j.Logger;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Controller
@RequestMapping("service")
public class ScheduleController {
    private final ScheduleService scheduleService;
    private final Logger LOGGER = Logger.getLogger(TaskController.class);

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @GetMapping("")
    @PreAuthorize("hasAnyAuthority('users:check','users:write','users:read')")
    public String showFormForGroupInfo(Model model) throws IOException {
        model.addAttribute("departments", scheduleService.getAllDepartments());

        return "service/form";
    }

    @GetMapping("/groups")
    @PreAuthorize("hasAnyAuthority('users:check','users:write','users:read')")
    public String findGroupByDepartment(@RequestParam int deptId,Model model) throws IOException {
        model.addAttribute("groups",scheduleService.getAllGroupsByDepartment(deptId));

        return "service/groups";
    }

    @PostMapping("/schedule")
    @PreAuthorize("hasAnyAuthority('users:check','users:write','users:read')")
    public String findScheduleByGroupInfo(
            @RequestParam int deptId,
            @RequestParam int course,
            @RequestParam int stream,
            @RequestParam int groupCode,
            @RequestParam int subGroupCode,Model model) throws IOException {
        model.addAttribute("schedule",scheduleService.findScheduleByGroup(deptId, course, stream, groupCode, subGroupCode));

        return "service/scheduleInfo";
    }
}
