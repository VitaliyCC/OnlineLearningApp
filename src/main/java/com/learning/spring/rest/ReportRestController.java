package com.learning.spring.rest;

import com.learning.spring.dao.ReportDAO;
import com.learning.spring.models.Report;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
@RequestMapping("/api/report")
public class ReportRestController {

    private final ReportDAO reportDAO;

    private final Logger LOGGER = Logger.getLogger(ReportRestController.class);
    @Autowired
    public ReportRestController(ReportDAO reportDAO) {
        this.reportDAO = reportDAO;
    }

    @RequestMapping(value = "add", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE })
    @PreAuthorize("hasAnyAuthority('users:read')")
    public void addNewReport(@ModelAttribute Report report) throws SQLException {
        try {
            reportDAO.save(report);
        } catch (SQLException e) {
            LOGGER.error("Incorrect report to save "+e);
            throw new SQLException("Incorrect report to save ");
        }

        LOGGER.debug("Save new report" + report.toString());
    }
}
