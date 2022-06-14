package com.learning.spring.controllers;

import com.learning.spring.dao.ReportDAO;
import com.learning.spring.models.Report;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

@Controller
@RequestMapping("/operation/report")
public class ReportController {

    private final ReportDAO reportDAO;

    private final Logger LOGGER = Logger.getLogger(ReportController.class);
    @Autowired
    public ReportController(ReportDAO reportDAO) {
        this.reportDAO = reportDAO;
    }


    @GetMapping("/show")
    @PreAuthorize("hasAnyAuthority('users:read')")
    public String showReportIndex(@RequestParam("id") int id, @RequestParam("nameT") String name, Model model) {
        Report report = new Report();
        report.setStudentId(id);
        report.setTaskName(name);
        report.setSendTime(Date.valueOf(LocalDate.now()));

        model.addAttribute("report", report);
        LOGGER.debug("Show report with " + id);

        return "tasks/newReport";
    }

    @PostMapping("/add")
    @PreAuthorize("hasAnyAuthority('users:read')")
    public String addNewReport(@ModelAttribute("report") Report report) throws SQLException {
        try {
            reportDAO.save(report);
        } catch (SQLException e) {
            LOGGER.error("Incorrect report to save "+e);
            throw new SQLException("Incorrect report to save ");
        }

        LOGGER.debug("Save new report" + report.toString());

        return "redirect:/";
    }
}
