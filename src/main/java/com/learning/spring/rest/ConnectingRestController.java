package com.learning.spring.rest;

import com.learning.spring.dao.ConnectingStudentDAO;
import com.learning.spring.dao.ConnectingTeacherDAO;
import com.learning.spring.models.ConnectingStudent;
import com.learning.spring.models.ConnectingTeacher;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;


@RestController
@RequestMapping("/api/connectingPerson")
public class ConnectingRestController {

    private final ConnectingStudentDAO connectingStudentDAO;
    private final ConnectingTeacherDAO connectingTeacherDAO;

    private final Logger LOGGER = Logger.getLogger(ConnectingRestController.class);

    @Autowired
    public ConnectingRestController(ConnectingStudentDAO connectingStudentDAO, ConnectingTeacherDAO connectingTeacherDAO) {
        this.connectingStudentDAO = connectingStudentDAO;
        this.connectingTeacherDAO = connectingTeacherDAO;
    }


    @RequestMapping(value = "/S", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE })
    @PreAuthorize("hasAnyAuthority('users:write')")
    public List<ConnectingStudent> workingWithConnectingStudent() {
        LOGGER.debug("Show all connectingStudent");

        return connectingStudentDAO.showAll();
    }

    @RequestMapping(value = "/T", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE })
    @PreAuthorize("hasAnyAuthority('users:write')")
    public List<ConnectingTeacher> workingWithConnectingTeacher() {
        LOGGER.debug("Show all connectingStudent");

        return connectingTeacherDAO.showAll();
    }

    @RequestMapping(value = "/S/add", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE })
    @PreAuthorize("hasAnyAuthority('users:write')")
    public void addNewConnectingStudent(@ModelAttribute ConnectingStudent connectingStudent) throws SQLException {
        try {
            connectingStudentDAO.save(connectingStudent);
        } catch (SQLException e) {
            LOGGER.error("Incorrect connecting student to save "+e);
            throw new SQLException("Incorrect connecting student to save");
        }

        LOGGER.debug("Save new connectingStudent" + connectingStudent.toString());
    }

    @RequestMapping(value = "/T/add", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE })
    @PreAuthorize("hasAnyAuthority('users:write')")
    public void addNewConnectingTeacher(@ModelAttribute ConnectingTeacher connectingTeacher) throws SQLException {
        try {
            connectingTeacherDAO.save(connectingTeacher);
        } catch (SQLException e) {
            LOGGER.error("Incorrect connecting teacher to save "+e);
            throw new SQLException("Incorrect connecting student to save ");
        }

        LOGGER.debug("Save new connectingTeacher" + connectingTeacher.toString());
    }

    @RequestMapping(value = "/S/update", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE })
    @PreAuthorize("hasAnyAuthority('users:write')")
    public void updateConnectingStudent(@ModelAttribute ConnectingStudent connectingStudent, @RequestParam("id") int id) {
        LOGGER.debug("Update connectingStudent" + connectingStudent.toString());

        connectingStudentDAO.update(id, connectingStudent);
    }

    @RequestMapping(value = "/T/update", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE })
    @PreAuthorize("hasAnyAuthority('users:write')")
    public void updateConnectingTeacher(@ModelAttribute ConnectingTeacher connectingTeacher, @RequestParam("id") int id) {
        LOGGER.debug("Update connectingStudent" + connectingTeacher.toString());

        connectingTeacherDAO.update(id, connectingTeacher);
    }

    @RequestMapping(value = "/S/delete", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE })
    @PreAuthorize("hasAnyAuthority('users:write')")
    public void deleteConnectingStudent(@ModelAttribute("id") int id) {
        LOGGER.debug("Delete connectingStudent N" + id);

        connectingStudentDAO.delete(id);
    }

    @RequestMapping(value = "/T/delete", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE })
    @PreAuthorize("hasAnyAuthority('users:write')")
    public void deleteConnectingTeacher(@ModelAttribute("id") int id) {
        LOGGER.debug("Delete connectingTeacher N" + id);

        connectingTeacherDAO.delete(id);
    }
}
