package nus.edu.sg.workshop12.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

// import third party library for logging
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class CustomErrorController implements ErrorController {
    Logger logger = LoggerFactory.getLogger(CustomErrorController.class);

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, HttpServletResponse response) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        if (status != null) {
            String statusString = status.toString();
            logger.info(statusString);
            Integer statusCode = Integer.valueOf(statusString);

            if (statusCode == HttpStatus.NOT_FOUND.value()) {
                return "error-404";
            }
        }
        return "error";
    }
}
