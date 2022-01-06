package nus.edu.sg.workshop12;

// import third party library for logging
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Workshop12Application {
	private static final Logger logger = LoggerFactory.getLogger(Workshop12Application.class);

	public static void main(String[] args) {
		logger.info("Workshop 12 Started");
		SpringApplication app = new SpringApplication(Workshop12Application.class);


		app.run(args);
	}

}
