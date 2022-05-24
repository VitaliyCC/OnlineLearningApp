package com.learning.spring.controllers;

import com.learning.spring.dao.TeacherDAO;
import com.learning.spring.models.Teacher;
import com.learning.spring.security.dao.UserDAO;
import com.learning.spring.security.model.Role;
import com.learning.spring.security.model.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@Controller
@RequestMapping("/operation/teacher")
public class TeacherController {

    private final TeacherDAO teacherDAO;
    private final Logger LOGGER = Logger.getLogger(TeacherController.class);
    private final UserDAO userDAO;

    @Autowired
    public TeacherController(TeacherDAO teacherDAO, UserDAO userDAO) {
        this.teacherDAO = teacherDAO;
        this.userDAO = userDAO;
    }


    @GetMapping("")
    @PreAuthorize("hasAnyAuthority('users:write')")
    public String workingWithTeacher(Model model) {
        model.addAttribute("teachers", teacherDAO.showAll());

        LOGGER.debug("Show all teachers");

        return "teachers/operationsOnTeacher";
    }

    @GetMapping("/show")
    @PreAuthorize("hasAnyAuthority('users:write','users:read','users:check')")
    public String showTeacherIndex(@RequestParam("id") int id, Model model) {
        Teacher teacher = teacherDAO.showAllInfo(id);

        model.addAttribute("teacher",teacher);

        LOGGER.debug("Show teacher " + teacher.toString());

        return "teachers/showInfo";
    }

    @GetMapping("/edit")
    @PreAuthorize("hasAnyAuthority('users:write')")
    public String editTeacher(@RequestParam("id") int id, Model model) {
        Teacher teacher = teacherDAO.showAllInfo(id);

        model.addAttribute("teacher", teacher);

        LOGGER.debug("Show teacher  " + teacher.toString());

        return "teachers/editTeacher";
    }

    @PostMapping("/add")
    @PreAuthorize("hasAnyAuthority('users:write')")
    public String addNewTeacher(@ModelAttribute("teacher") Teacher teacher) throws SQLException {
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

        return "redirect:/operation/teacher";
    }

    @PostMapping("/update")
    @PreAuthorize("hasAnyAuthority('users:write')")
    public String updateTeacher(@ModelAttribute("teacher") Teacher teacher, @RequestParam("id") int id) {
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

        return "redirect:/operation/teacher";
    }

    @PostMapping("/delete")
    @PreAuthorize("hasAnyAuthority('users:write')")
    public String deleteTeacher(@RequestParam("id") int id) {
        int temp = teacherDAO.showAllInfo(id).getId();

        LOGGER.debug("Delete teacher N" + id);
        LOGGER.debug("Delete user N" + id);

        teacherDAO.delete(id);
        userDAO.delete(temp);

        return "redirect:/operation/teacher";
    }
}
