package com.learning.spring.controllers;

import com.learning.spring.models.Student;
import com.learning.spring.security.dao.UserDAO;
import com.learning.spring.security.model.Role;
import com.learning.spring.security.model.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.learning.spring.dao.StudentDAO;

import java.sql.SQLException;

@Controller
@RequestMapping("/operation/student")
public class StudentController {

    private final StudentDAO studentDAO;
    private final UserDAO userDAO;
    private final Logger LOGGER = Logger.getLogger(StudentController.class);

    @Autowired
    public StudentController(StudentDAO studentDAO, UserDAO userDAO) {
        this.studentDAO = studentDAO;
        this.userDAO = userDAO;
    }

    @GetMapping("")
    @PreAuthorize("hasAnyAuthority('users:write')")
    public String workingWithStudent(Model model) {
        model.addAttribute("students", studentDAO.showAll());
        LOGGER.debug("show all students");
        return "students/operationsOnStudent";
    }

    @GetMapping("/show")
    @PreAuthorize("hasAnyAuthority('users:check','users:write','users:read')")
    public String showStudentIndex(@RequestParam("id") int id, Model model) {
        Student student = studentDAO.showAllInfo(id);

        model.addAttribute("student", student);
        model.addAttribute("subjects", student.getSubjectList());
        model.addAttribute("reports", student.getReportList());
        model.addAttribute("reviews", student.getReviewMap());

        LOGGER.debug("Show student with " + id);

        return "students/showInfo";
    }

    @GetMapping("/edit")
    @PreAuthorize("hasAnyAuthority('users:write')")
    public String editStudentIndex(@RequestParam("id") int id, Model model) {
        Student student = studentDAO.showAllInfo(id);

        model.addAttribute("student", student);
        LOGGER.debug("Show student  " + student.toString());

        return "students/editStudent";
    }

    @PostMapping("/add")
    @PreAuthorize("hasAnyAuthority('users:write')")
    public String addNewStudent(@RequestParam("name") String name,
                                @RequestParam("surname") String surname,
                                @RequestParam("patronymic") String patronymic,
                                @RequestParam("password") String password,
                                @RequestParam("login") String login) throws SQLException {
        Student student = new Student();
        student.setName(name);
        student.setSurname(surname);
        student.setPatronymic(patronymic);
        student.setPassword(password);

        User user = new User(login, student.getPassword(), Role.STUDENT);
        try {
            studentDAO.save(student);
            userDAO.save(user);

        } catch (SQLException e) {
            LOGGER.error("Incorrect student to save "+e);

            throw new SQLException("Incorrect student to save ");
        }

        LOGGER.debug("Save new student" + student.toString());
        LOGGER.debug("Save new user" + user.toString());

        return "redirect:/operation/student";
    }

    @PostMapping("/edit")
    @PreAuthorize("hasAnyAuthority('users:write')")
    public String updateStudent(@RequestParam("name") String name,
                                @RequestParam("surname") String surname,
                                @RequestParam("patronymic") String patronymic,
                                @RequestParam("login") String login,
                                @RequestParam("role") String role,
                                @RequestParam("id") int id) {
        Student student = new Student();
        student.setName(name);
        student.setSurname(surname);
        student.setPatronymic(patronymic);

        User user = new User();
        user.setLogin(login);
        user.setRoleString(role);
        user.setId(studentDAO.showAllInfo(id).getId());

        LOGGER.debug("Update student" + student.toString());
        LOGGER.debug("Update user" + user.toString());

        studentDAO.update(id, student);
        userDAO.update(user);

        return "redirect:/operation/student";
    }

    @PostMapping("/delete")
    @PreAuthorize("hasAnyAuthority('users:write')")
    public String deleteStudent(@RequestParam("id") int id) {
        int temp = studentDAO.showAllInfo(id).getId();

        LOGGER.debug("Delete student N" + id);
        LOGGER.debug("Delete user N" + id);

        studentDAO.delete(id);
        userDAO.delete(temp);

        return "redirect:/operation/student";
    }
}
