package com.xxx.slf4j_test_by_logfile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;

public class LogSample {
    public static final String logbackPath = "logback.xml";
    /** getting logger. */
    Logger logger = LoggerFactory.getLogger(LogSample.class);
    
    public static void main(String... args) {
        // reloading logback configuration file.
        LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
        JoranConfigurator configurator = new JoranConfigurator();
        configurator.setContext(lc);
        lc.reset();
        try {
            configurator.doConfigure(logbackPath);
        } catch (JoranException ex) {
            ex.printStackTrace();
        }
        LogSample _ = new LogSample();
        _.outputLog("This message is for Test");
    }
    
    /**
     * this method is for Testing.
     */
    public void outputLog (String msg) {
        logger.trace(msg);
        logger.error(msg);
    }

}
