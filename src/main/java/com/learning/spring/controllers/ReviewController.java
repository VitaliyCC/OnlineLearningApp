package com.learning.spring.controllers;

import com.learning.spring.dao.ReviewDAO;
import com.learning.spring.dao.StudentDAO;
import com.learning.spring.models.Review;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

@Controller
@RequestMapping("/operation/review")
public class ReviewController {

    private final ReviewDAO reviewDAO;
    private final StudentDAO studentDAO;

    private final Logger LOGGER = Logger.getLogger(ReviewController.class);

    @Autowired
    public ReviewController(ReviewDAO reviewDAO, StudentDAO studentDAO) {
        this.reviewDAO = reviewDAO;
        this.studentDAO = studentDAO;
    }

    @GetMapping("")
    @PreAuthorize("hasAnyAuthority('users:read','users:write','users:check')")
    public String workingWithReview(@RequestParam("id") Integer id, Model model) {
        model.addAttribute("student", studentDAO.showAllInfo(id));

        LOGGER.debug("Show all review for student "+id);

        return "tasks/student`sReview";
    }

    @GetMapping("/show")
    @PreAuthorize("hasAnyAuthority('users:write','users:check')")
    public String showReviewIndex(Model model,
                                  @RequestParam("id") int id,
                                  @RequestParam("idT")int idT) {
        Review review = new Review();

        review.setTimeReview(Date.valueOf(LocalDate.now()));
        review.setReportId(id);
        review.setTeacherId(idT);

        model.addAttribute("review",review);
        LOGGER.debug("Show review with " + id);

        return "tasks/newReview";
    }

    @PostMapping("/add")
    @PreAuthorize("hasAnyAuthority('users:write','users:check')")
    public String addNewReview(@ModelAttribute("review") Review review) throws SQLException {
        try {
            reviewDAO.save(review);
        } catch (SQLException e) {
            LOGGER.error("Incorrect review to save "+e);
            throw new SQLException("Incorrect review to save ");
        }

        LOGGER.debug("Save new review" + review.toString());

        return "redirect:/index";
    }
}
