package com.learning.spring.rest;

import com.learning.spring.dao.TeacherDAO;
import com.learning.spring.models.Teacher;
import com.learning.spring.security.dao.UserDAO;
import com.learning.spring.security.model.Role;
import com.learning.spring.security.model.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/teacher")
public class TeacherRestController {

    private final TeacherDAO teacherDAO;
    private final Logger LOGGER = Logger.getLogger(TeacherRestController.class);
    private final UserDAO userDAO;

    @Autowired
    public TeacherRestController(TeacherDAO teacherDAO, UserDAO userDAO) {
        this.teacherDAO = teacherDAO;
        this.userDAO = userDAO;
    }

    @RequestMapping(value = "", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE })
    @PreAuthorize("hasAnyAuthority('users:write')")
    public List<Teacher> workingWithTeacher(Model model) {
        LOGGER.debug("Show all teachers");

        return teacherDAO.showAll();
    }

    @RequestMapping(value = "add", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE })
    @PreAuthorize("hasAnyAuthority('users:write')")
    public void addNewTeacher(@ModelAttribute Teacher teacher) throws SQLException {
        User user = null;

        try {
            user = new User(teacher.getLogin(), teacher.getPassword(), Role.TEACHER);
            teacherDAO.save(teacher);
            userDAO.save(user);
        } catch (SQLException e) {
            LOGGER.error("Incorrect student to save "+e);
            throw new SQLException("Incorrect student to save ");
        }

        LOGGER.debug("Save new teacher" + teacher.toString());
        LOGGER.debug("Save new user" + user.toString());
    }

    @RequestMapping(value = "update", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE })
    @PreAuthorize("hasAnyAuthority('users:write')")
    public void updateTeacher(@ModelAttribute Teacher teacher, @RequestParam("id") int id) {
        Teacher temp = teacherDAO.showAllInfo(id);

        User user = new User();

        user.setId(temp.getId());
        user.setLogin(teacher.getLogin());
        user.setRoleString(teacher.getRole());
        user.setId(teacherDAO.showAllInfo(id).getId());

        LOGGER.debug("Update teacher" + teacher.toString());
        LOGGER.debug("Update user" + user.toString());

        teacherDAO.update(id, teacher);
        userDAO.update(user);
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE })
    @PreAuthorize("hasAnyAuthority('users:write')")
    public void deleteTeacher(@RequestParam("id") int id) {
        int temp = teacherDAO.showAllInfo(id).getId();

        LOGGER.debug("Delete teacher N" + id);
        LOGGER.debug("Delete user N" + id);

        teacherDAO.delete(id);
        userDAO.delete(temp);
    }
}
