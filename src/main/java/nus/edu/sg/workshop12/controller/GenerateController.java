package nus.edu.sg.workshop12.controller;

import java.util.Collections;
import java.util.List;
// import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

// import third party library for logging
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.server.ResponseStatusException;

import nus.edu.sg.workshop12.exception.RandomNumberException;
import nus.edu.sg.workshop12.model.Generate;

@Controller
public class GenerateController {
    private static final int IMAGE_NUMBERS = 30;
    private static final String BAD_REQUEST_CODE = "400";
    private static final Logger logger = LoggerFactory.getLogger(GenerateController.class);
    // private static final Random random = new Random();


    @GetMapping("/")
    public String showGenerateForm(Model model) {
        Generate generate = new Generate();
        model.addAttribute("generate", generate);
        return "generate";
    }

    @PostMapping("/generate")
    public String generateNumbers(@ModelAttribute Generate generate, Model model) {
        logger.info("User submitted: {}", generate.getNumberVal());
        try {
            if (generate.getNumberVal() == null) {
                throw new RandomNumberException();
            }
            int numberLimit = generate.getNumberVal();
            if (numberLimit > 30 || numberLimit < 0) {
                throw new RandomNumberException();
            }

            // Set<Integer> uniqueRandomNumbersSet = new LinkedHashSet<>();
            // while (uniqueRandomNumbersSet.size() < numberLimit) {
            //     int randInt = random.nextInt(IMAGE_NUMBERS + 1);
            //     uniqueRandomNumbersSet.add(randInt);
            // }
            List<Integer> numberRange = IntStream.rangeClosed(1, IMAGE_NUMBERS).boxed().collect(Collectors.toList());
            Collections.shuffle(numberRange);

            List<String> selectedImg = numberRange.stream()
                .limit(numberLimit)
                .map(num -> "number" + num + ".jpg")
                .toList();

            logger.info("Images to display: {}", selectedImg);
            model.addAttribute("randNumberResult", selectedImg);
            model.addAttribute("numberLimit", numberLimit);
        } catch (RandomNumberException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Number should be between 1 and 30", e);
        }
        return "result";
    }
}
