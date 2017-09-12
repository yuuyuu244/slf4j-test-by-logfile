package com.xxx.slf4j_test_by_logfile;

import static org.junit.Assert.*;

import org.junit.Test;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.classic.spi.LoggingEvent;
import ch.qos.logback.core.Appender;
import ch.qos.logback.core.joran.spi.JoranException;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;
import static org.hamcrest.CoreMatchers.is;

import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatcher;
import org.mockito.Mock;

public class LogSampleTest {
    public static final String logbackPath = "logback.xml";

    @Test
    public void test() {
        ch.qos.logback.classic.Logger logger = (ch.qos.logback.classic.Logger) LoggerFactory
                .getLogger(ch.qos.logback.classic.Logger.ROOT_LOGGER_NAME);

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

        // make mock of appender
        @SuppressWarnings("unchecked")
        final Appender<ILoggingEvent> mockAppender = mock(Appender.class);
        // when(mockAppender.getName()).thenReturn("MOCK");
        logger.addAppender(mockAppender);

        LogSample _ = new LogSample();
        _.outputLog("This message is for Test");

        // ArgumentCaptor<Appender> argumentCaptor = ArgumentCaptor.forClass(Appender.class);
        // Mockito.verify(mockAppender).doAppend((ILoggingEvent) argumentCaptor.capture());

        // assertThat(((LoggingEvent)argumentCaptor.getAllValues().get(0)).getMessage(),is("This message is for Test"));
        // 何かしらのログを出力する処理

        // verify(mockAppender).doAppend(argThat(new ArgumentMatcher<LoggingEvent>() {
        // @Override
        // public boolean matches(Object argument) {
        // return ((LoggingEvent)argument).getFormattedMessage().contains("initOption() fail perse.");
        // }
        // }));
        //

        ArgumentMatcher<LoggingEvent> aaa = new ArgumentMatcher<LoggingEvent>() {
            @Override
            public boolean matches(Object argument) {
                return ((LoggingEvent) argument).getFormattedMessage().contains("This message is for Test");
            }
        };

        verify(mockAppender).doAppend(argThat(aaa));

    }

}
