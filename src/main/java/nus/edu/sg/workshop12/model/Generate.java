package nus.edu.sg.workshop12.model;

import java.io.Serializable;

// import third party library for logging
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Generate implements Serializable {
    private static final Logger logger = LoggerFactory.getLogger(Generate.class);
    private Integer numberVal;

    public void setNumberVal(Integer numberVal) {
        this.numberVal = numberVal;
        logger.info("Setting numberVal to {}", numberVal);
    }

    public Integer getNumberVal() {
        return this.numberVal;
    }
}
