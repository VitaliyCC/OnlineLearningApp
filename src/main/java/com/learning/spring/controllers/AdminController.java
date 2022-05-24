package com.learning.spring.controllers;

import com.learning.spring.dao.AdminDAO;
import com.learning.spring.models.Admin;
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
@RequestMapping("/operation/admin")
public class AdminController {

    private final AdminDAO adminDAO;
    private final UserDAO userDAO;

    private final Logger LOGGER = Logger.getLogger(AdminController.class);

    @Autowired
    public AdminController(AdminDAO adminDAO, UserDAO userDAO) {
        this.adminDAO = adminDAO;
        this.userDAO = userDAO;
    }

    @GetMapping("")
    @PreAuthorize("hasAnyAuthority('users:write')")
    public String workingWithAdmin(Model model) {
        model.addAttribute("admins", adminDAO.showAll());
        model.addAttribute("newAdmin", new Admin());

        LOGGER.debug("Show all admins");

        return "admins/operationsAdmin";
    }

    @GetMapping("/edit")
    @PreAuthorize("hasAnyAuthority('users:write')")
    public String editAdmin(@RequestParam("id") int id, Model model) {
        Admin admin = adminDAO.showAllInfo(id);
        LOGGER.debug("Show admin to edit  " + admin.toString());
        model.addAttribute("admin", admin);

        return "admins/editAdmin";
    }

    @PostMapping("/add")
    @PreAuthorize("hasAnyAuthority('users:write')")
    public String addNewAdmin(@ModelAttribute("newAdmin") Admin admin) throws SQLException {
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


        return "redirect:/operation/admin";
    }

    @PostMapping("/update")
    @PreAuthorize("hasAnyAuthority('users:write')")
    public String updateAdmin(@ModelAttribute("admin") Admin admin, @RequestParam("id") int id) {
        LOGGER.debug("Update admin" + admin.toString());
        User user = new User();
        user.setLogin(admin.getLogin());
        user.setRoleString(admin.getRole());
        user.setId(adminDAO.showAllInfo(id).getId());

        adminDAO.update(id, admin);
        userDAO.update(user);
        return "redirect:/operation/admin";
    }

    @PostMapping("/delete")
    @PreAuthorize("hasAnyAuthority('users:write')")
    public String deleteAdmin(@RequestParam("id") int id) {
        int temp = adminDAO.showAllInfo(id).getId();
        LOGGER.debug("Delete admin with N" + id);

        adminDAO.delete(id);
        userDAO.delete(temp);
        return "redirect:/operation/admin";
    }
}
