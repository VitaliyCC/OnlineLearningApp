package com.learning.spring.rest;

import com.learning.spring.dao.AdminDAO;
import com.learning.spring.models.Admin;
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
@RequestMapping("/api/admin")
public class AdminRestController {

    private final AdminDAO adminDAO;
    private final UserDAO userDAO;

    private final Logger LOGGER = Logger.getLogger(AdminRestController.class);

    @Autowired
    public AdminRestController(AdminDAO adminDAO, UserDAO userDAO) {
        this.adminDAO = adminDAO;
        this.userDAO = userDAO;
    }

    @RequestMapping(value = "", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE })
    @PreAuthorize("hasAnyAuthority('users:write')")
    public List<Admin> workingWithAdmin() {
        LOGGER.debug("Show all admins");

        return adminDAO.showAll();
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE })
    @PreAuthorize("hasAnyAuthority('users:write')")
    public void addNewAdmin(@ModelAttribute Admin admin) throws SQLException {
        User user = null;

        LOGGER.debug("Save new admin" + admin.toString());
        try {
            user = new User(admin.getLogin(), admin.getPassword(), Role.ADMIN);
            LOGGER.debug("Save new user" + user.toString());
            adminDAO.save(admin);
            userDAO.save(user);
        } catch (SQLException e) {
            LOGGER.error("Incorrect student to save "+e);
            throw new SQLException("Incorrect student to save");
        }
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE })
    @PreAuthorize("hasAnyAuthority('users:write')")
    public void updateAdmin(@ModelAttribute Admin admin, @RequestParam("id") int id) {
        LOGGER.debug("Update admin" + admin.toString());

        User user = new User();
        user.setLogin(admin.getLogin());
        user.setRoleString(admin.getRole());
        user.setId(adminDAO.showAllInfo(id).getId());

        adminDAO.update(id, admin);
        userDAO.update(user);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE })
    @PreAuthorize("hasAnyAuthority('users:write')")
    public void deleteAdmin(@RequestParam("id") int id) {
        int temp = adminDAO.showAllInfo(id).getId();
        LOGGER.debug("Delete admin with N" + id);

        adminDAO.delete(id);
        userDAO.delete(temp);
    }
}
