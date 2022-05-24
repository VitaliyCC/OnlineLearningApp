package com.learning.spring.rest;

import com.learning.spring.dao.StudentDAO;
import com.learning.spring.models.Student;
import com.learning.spring.security.dao.UserDAO;
import com.learning.spring.security.model.Role;
import com.learning.spring.security.model.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/student")
public class StudentRestController {

    private final StudentDAO studentDAO;
    private final UserDAO userDAO;
    private final Logger LOGGER = Logger.getLogger(StudentRestController.class);

    @Autowired
    public StudentRestController(StudentDAO studentDAO, UserDAO userDAO) {
        this.studentDAO = studentDAO;
        this.userDAO = userDAO;
    }

    @RequestMapping(value = "", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE })
    @PreAuthorize("hasAnyAuthority('users:write')")
    public List<Student> workingWithStudent() {
        LOGGER.debug("show all students");

        return studentDAO.showAll();
    }

    @RequestMapping(value = "add", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE })
    @PreAuthorize("hasAnyAuthority('users:write')")
    public void addNewStudent(@ModelAttribute Student student) throws SQLException {
        User user = new User(student.getLogin(), student.getPassword(), Role.STUDENT);
        try {
            studentDAO.save(student);
            userDAO.save(user);

        } catch (SQLException e) {
            LOGGER.error("Incorrect student to save "+e);

            throw new SQLException("Incorrect student to save ");
        }

        LOGGER.debug("Save new student" + student.toString());
        LOGGER.debug("Save new user" + user.toString());
    }

    @RequestMapping(value = "update", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE })
    @PreAuthorize("hasAnyAuthority('users:write')")
    public void updateStudent(@ModelAttribute Student student,
                                @RequestParam("id") int id) {
        User user = new User();
        user.setLogin(student.getLogin());
        user.setRoleString(student.getRole());
        user.setId(studentDAO.showAllInfo(id).getId());

        LOGGER.debug("Update student" + student.toString());
        LOGGER.debug("Update user" + user.toString());

        studentDAO.update(id, student);
        userDAO.update(user);
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE })
    @PreAuthorize("hasAnyAuthority('users:write')")
    public void deleteStudent(@RequestParam("id") int id) {
        int temp = studentDAO.showAllInfo(id).getId();

        LOGGER.debug("Delete student N" + id);
        LOGGER.debug("Delete user N" + id);

        studentDAO.delete(id);
        userDAO.delete(temp);
    }
}
