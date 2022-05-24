package com.learning.spring.rest;

import com.learning.spring.dao.SubjectDAO;
import com.learning.spring.dao.TaskDAO;
import com.learning.spring.models.Subject;
import com.learning.spring.models.Task;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/task")
public class TaskRestController {

    private final TaskDAO taskDAO;
    private final SubjectDAO subjectDAO;

    private final Logger LOGGER = Logger.getLogger(TaskRestController.class);

    @Autowired
    public TaskRestController(TaskDAO taskDAO, SubjectDAO subjectDAO) {
        this.taskDAO = taskDAO;
        this.subjectDAO = subjectDAO;
    }

    @GetMapping("")
    @PreAuthorize("hasAnyAuthority('users:read','users:check')")
    public List<Task> workingWithTask(@RequestParam("idS") Integer idS) {
        Subject subject = subjectDAO.showAllInfo(idS);

        LOGGER.debug("Show all Task for subject " + subject.toString());
        return subject.getTaskList();
    }

    @RequestMapping(value = "add", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE })
    @PreAuthorize("hasAnyAuthority('users:write','users:check')")
    public void addNewStudent(@ModelAttribute Task task) throws SQLException {
        try {
            taskDAO.save(task);
        } catch (SQLException e) {
            LOGGER.error("Incorrect task to save " + e);
            throw new SQLException("Incorrect task to save ");
        }
        LOGGER.debug("Save new task" + task.toString());
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE })
    @PreAuthorize("hasAnyAuthority('users:write','users:check')")
    public void deleteSubject(@RequestParam("name") String name) {
        LOGGER.debug("Delete subject N" + name);

        taskDAO.delete(name);
    }
}
