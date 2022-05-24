package com.learning.spring.controllers;

import com.learning.spring.dao.ConnectingStudentDAO;
import com.learning.spring.dao.ConnectingTeacherDAO;
import com.learning.spring.models.ConnectingStudent;
import com.learning.spring.models.ConnectingTeacher;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;


@Controller
@RequestMapping("/operation/connectingPerson")
public class ConnectingController {

    private final ConnectingStudentDAO connectingStudentDAO;
    private final ConnectingTeacherDAO connectingTeacherDAO;

    private final Logger LOGGER = Logger.getLogger(ConnectingController.class);

    @Autowired
    public ConnectingController(ConnectingStudentDAO connectingStudentDAO, ConnectingTeacherDAO connectingTeacherDAO) {
        this.connectingStudentDAO = connectingStudentDAO;
        this.connectingTeacherDAO = connectingTeacherDAO;
    }


    @GetMapping("")
    @PreAuthorize("hasAnyAuthority('users:write')")
    public String workingWithConnectingStudent(Model model) {
        model.addAttribute("connectingS", connectingStudentDAO.showAll());
        model.addAttribute("connectingT", connectingTeacherDAO.showAll());

        LOGGER.debug("Show all connectingStudent");

        return "connectingPerson/operationsOnConnectingPerson";
    }

    @GetMapping("/S/edit")
    @PreAuthorize("hasAnyAuthority('users:write')")
    public String editConnectingStudentIndex(@RequestParam("id") int id, Model model) {
        ConnectingStudent connectingStudent = connectingStudentDAO.showAllInfo(id);
        ConnectingTeacher connectingTeacher = connectingTeacherDAO.showAllInfo(id);

        model.addAttribute("connectingS", connectingStudent);
        model.addAttribute("connectingT", connectingTeacher);

        LOGGER.debug("Show connectingStudent  " + connectingStudent.toString());
        LOGGER.debug("Show connectingStudent  " + connectingTeacher.toString());

        return "connectingPerson/editConnectingStudent";
    }

    @GetMapping("/T/edit")
    @PreAuthorize("hasAnyAuthority('users:write')")
    public String editConnectingTeacherIndex(@RequestParam("id") int id, Model model) {
        ConnectingTeacher connectingTeacher = connectingTeacherDAO.showAllInfo(id);

        model.addAttribute("connectingT", connectingTeacher);

        LOGGER.debug("Show connectingTeacher  " + connectingTeacher.toString());

        return "connectingPerson/editConnectingTeacher";
    }

    @PostMapping("/S/add")
    @PreAuthorize("hasAnyAuthority('users:write')")
    public String addNewConnectingStudent(@ModelAttribute("connectingS") ConnectingStudent connectingStudent) throws SQLException {

        try {
            connectingStudentDAO.save(connectingStudent);
        } catch (SQLException e) {
            LOGGER.error("Incorrect connecting student to save "+e);
            throw new SQLException("Incorrect connecting student to save");
        }

        LOGGER.debug("Save new connectingStudent" + connectingStudent.toString());

        return "redirect:/operation/connectingPerson";
    }
    @PostMapping("/T/add")
    @PreAuthorize("hasAnyAuthority('users:write')")
    public String addNewConnectingTeacher(@ModelAttribute("connectingT") ConnectingTeacher connectingTeacher) throws SQLException {
        try {
            connectingTeacherDAO.save(connectingTeacher);
        } catch (SQLException e) {
            LOGGER.error("Incorrect connecting teacher to save "+e);
            throw new SQLException("Incorrect connecting student to save ");
        }

        LOGGER.debug("Save new connectingTeacher" + connectingTeacher.toString());

        return "redirect:/operation/connectingPerson";
    }

    @PostMapping("/S/update")
    @PreAuthorize("hasAnyAuthority('users:write')")
    public String updateConnectingStudent(@ModelAttribute("connectingS") ConnectingStudent connectingStudent, @RequestParam("id") int id) {
        LOGGER.debug("Update connectingStudent" + connectingStudent.toString());

        connectingStudentDAO.update(id, connectingStudent);

        return "redirect:/operation/connectingPerson";
    }
    @PostMapping("/T/update")
    @PreAuthorize("hasAnyAuthority('users:write')")
    public String updateConnectingTeacher(@ModelAttribute("connectingT") ConnectingTeacher connectingTeacher, @RequestParam("id") int id) {
        LOGGER.debug("Update connectingStudent" + connectingTeacher.toString());

        connectingTeacherDAO.update(id, connectingTeacher);

        return "redirect:/operation/connectingPerson";
    }

    @PostMapping("/S/delete")
    @PreAuthorize("hasAnyAuthority('users:write')")
    public String deleteConnectingStudent(@RequestParam("id") int id) {
        LOGGER.debug("Delete connectingStudent N" + id);

        connectingStudentDAO.delete(id);

        return "redirect:/operation/connectingPerson";
    }

    @PostMapping("/T/delete")
    @PreAuthorize("hasAnyAuthority('users:write')")
    public String deleteConnectingTeacher(@RequestParam("id") int id) {
        LOGGER.debug("Delete connectingTeacher N" + id);

        connectingTeacherDAO.delete(id);

        return "redirect:/operation/connectingPerson";
    }
}
