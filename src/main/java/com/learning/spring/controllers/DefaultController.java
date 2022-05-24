package com.learning.spring.controllers;

import com.learning.spring.dao.StudentDAO;
import com.learning.spring.dao.TeacherDAO;
import com.learning.spring.models.Student;
import com.learning.spring.models.Subject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.learning.spring.dao.AdminDAO;
import com.learning.spring.security.dao.UserDAO;
import com.learning.spring.security.model.Role;
import com.learning.spring.security.model.User;


@Controller
public class DefaultController {
    private final UserDAO userDAO;
    private final AdminDAO adminDAO;
    private final TeacherDAO teacherDAO;
    private final StudentDAO studentDAO;
    private static final Logger LOGGER = Logger.getLogger(DefaultController.class);

    @Autowired
    public DefaultController(UserDAO userDAO, AdminDAO adminDAO, TeacherDAO teacherDAO, StudentDAO studentDAO) {
        this.userDAO = userDAO;
        this.adminDAO = adminDAO;
        this.teacherDAO = teacherDAO;
        this.studentDAO = studentDAO;
    }

    @GetMapping("/")
    @PreAuthorize(value = "hasAnyAuthority('users:write','users:read','users:check')")
    public String index(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        User user = userDAO.findByUsername(currentPrincipalName);

        if (user.getRole().equals(Role.STUDENT)) {
            Student student = studentDAO.showAllInfo(user.findStudent(studentDAO.showAll()));

            for (Subject subject : student.getSubjectList()) {
                subject.setProgress(studentDAO.calculationSubjectProgress(student.getStudentId(), subject.getSubjectID()));
            }

            model.addAttribute("student", student);

            LOGGER.debug("Student logged in" + student.toString());

            return "/hello/students";
        } else if (user.getRole().equals(Role.TEACHER)) {
            model.addAttribute("teacher", teacherDAO.showAllInfo(user.findTeacher(teacherDAO.showAll())));

            LOGGER.debug("Teacher logged in " + user.toString());

            return "/hello/teachers";
        } else {
            LOGGER.debug("Admin logged in " + user.toString());

            return "hello/admins";
        }

    }
}
