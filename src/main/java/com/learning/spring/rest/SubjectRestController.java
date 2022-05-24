package com.learning.spring.rest;

import com.learning.spring.dao.SubjectDAO;
import com.learning.spring.models.Subject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/subject")
public class SubjectRestController {

    private final SubjectDAO subjectDAO;

    private final Logger LOGGER = Logger.getLogger(SubjectRestController.class);

    @Autowired
    public SubjectRestController(SubjectDAO subjectDAO) {
        this.subjectDAO = subjectDAO;
    }

    @RequestMapping(value = "", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE })
    @PreAuthorize("hasAnyAuthority('users:write')")
    public List<Subject> workingWithSubject() {
        LOGGER.debug("Show all subject");

        return subjectDAO.showAll();
    }

    @RequestMapping(value = "add", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE })
    @PreAuthorize("hasAnyAuthority('users:write')")
    public void addNewSubject(@ModelAttribute Subject subject) throws SQLException {
        try {
            subjectDAO.save(subject);
        } catch (SQLException e) {
            LOGGER.error("Incorrect student to save "+ e);
            throw new SQLException("Incorrect student to save ");
        }

        LOGGER.debug("Save new subject" + subject.toString());
    }

    @RequestMapping(value = "update", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE })
    @PreAuthorize("hasAnyAuthority('users:write')")
    public void updateSubject(@ModelAttribute Subject subject, @RequestParam("id") int id) {
        LOGGER.debug("Update subject" + subject.toString());

        subjectDAO.update(id, subject);
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE })
    @PreAuthorize("hasAnyAuthority('users:write')")
    public void deleteSubject(@RequestParam("id") int id) {
        LOGGER.debug("Delete subject N" + id);

        subjectDAO.delete(id);
    }
}
