package com.learning.spring.rest;

import com.learning.spring.dao.ReviewDAO;
import com.learning.spring.dao.StudentDAO;
import com.learning.spring.models.Review;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/adi/review")
public class ReviewRestController {

    private final ReviewDAO reviewDAO;
    private final StudentDAO studentDAO;

    private final Logger LOGGER = Logger.getLogger(ReviewRestController.class);

    @Autowired
    public ReviewRestController(ReviewDAO reviewDAO, StudentDAO studentDAO) {
        this.reviewDAO = reviewDAO;
        this.studentDAO = studentDAO;
    }

    @RequestMapping(value = "", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE })
    @PreAuthorize("hasAnyAuthority('users:read','users:write','users:check')")
    public HashMap<Integer, List<Review>> workingWithReview(@RequestParam("id") Integer id) {
        LOGGER.debug("Show all review for student "+id);

        return studentDAO.showAllInfo(id).getReviewMap();
    }

    @RequestMapping(value = "add", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE })
    @PreAuthorize("hasAnyAuthority('users:write','users:check')")
    public void addNewReview(@ModelAttribute Review review) throws SQLException {
        try {
            reviewDAO.save(review);
        } catch (SQLException e) {
            LOGGER.error("Incorrect review to save "+e);
            throw new SQLException("Incorrect review to save ");
        }

        LOGGER.debug("Save new review" + review.toString());
    }
}
