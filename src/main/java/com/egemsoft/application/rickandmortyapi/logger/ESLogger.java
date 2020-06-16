package com.egemsoft.application.rickandmortyapi.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.springframework.web.util.HtmlUtils;

import java.util.Arrays;
import java.util.EnumSet;

/**
 * Digital platform safe logger
 */
public class ESLogger {

    private final Logger logger;

    public static final String OPERATION = "OPERATION";
    public static final String USER_IP = "USER_IP";
    public static final String USER_AGENT = "USER_AGENT";
    public static final String REQUEST_ID = "REQUEST_ID";
    public static final String CUSTOMER_ID = "CUSTOMER_ID";
    public static final String USER_ID = "USER_ID";

    private ESLogger(Logger logger) {
        this.logger = logger;
    }

    public static ESLogger getLogger(Class<?> clazz) {
        Logger logger = LoggerFactory.getLogger(clazz);
        return new ESLogger(logger);
    }

    private Object[] filterValues(Object... argArray) {
        return Arrays.stream(argArray)
                .map(a -> a == null ? null : filterValue(a)).toArray();
    }

    private Object filterValue(Object a) {
        return a instanceof Throwable ? a : HtmlUtils.htmlEscape(
                EnumSet.allOf(FilterPattern.class)
                        .stream()
                        .reduce(a,
                                (o, f) -> o.toString().replaceAll(f.getRegexp(), f.getMask()),
                                (s1, s2) -> s1.toString() + s2.toString())
                        .toString())
                .replace("\r", "&cr;")
                .replace("\n", "&lf;");
    }

    public String getName() {
        return logger.getName();
    }

    public void trace(String s, Object... args) {
        if (logger.isTraceEnabled()) {
            logger.trace(s, filterValues(args));
        }
    }

    public void trace(Marker marker, String s, Object... args) {
        if (logger.isTraceEnabled(marker)) {
            logger.trace(marker, s, filterValues(args));
        }
    }

    public void debug(String s, Object... args) {
        if (logger.isDebugEnabled()) {
            logger.debug(s, filterValues(args));
        }
    }

    public void debug(Marker marker, String s, Object... args) {
        if (logger.isDebugEnabled(marker)) {
            logger.debug(marker, s, filterValues(args));
        }
    }

    public void info(String s, Object... args) {
        if (logger.isInfoEnabled()) {
            logger.info(s, filterValues(args));
        }
    }

    public void info(Marker marker, String s, Object... args) {
        if (logger.isInfoEnabled(marker)) {
            logger.info(marker, s, filterValues(args));
        }
    }

    public void warn(String s, Object... args) {
        if (logger.isWarnEnabled()) {
            logger.warn(s, filterValues(args));
        }
    }

    public void warn(Marker marker, String s, Object... args) {
        if (logger.isWarnEnabled(marker)) {
            logger.warn(marker, s, filterValues(args));
        }
    }

    public void error(String s, Object... args) {
        if (logger.isErrorEnabled()) {
            logger.error(s, filterValues(args));
        }
    }

    public void error(Marker marker, String s, Object... args) {
        if (logger.isErrorEnabled(marker)) {
            logger.error(marker, s, filterValues(args));
        }
    }

    private enum FilterPattern {
        NAME_SURNAME("[A-Z]+[ ]+[A-Z]+[ ]?+[A-Z]?", "**** *******"),
        MOBILE_NUMBER("\\b(\\+?\\d{1,3}[- ]?)?\\d{9,10}\\b", "**********"),
        TIN("\\b\\d{10}\\b", "**********"),
        FIN("\\b([A-Z]+\\d[A-Z\\d]+)|(\\d+[A-Z][A-Z\\S]+)\\b", "*******"),
        EMAIL("\\b([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})\\b", "*@*.*"),
        AMOUNT_AND_IP("\\b[0-9]+\\.[0-9]+\\b", "*.*"),
        CARD_NUMBER("\\b\\d{16}\\b", "****************");

        private String regexp;
        private String mask;

        FilterPattern(String regexp, String mask) {
            this.regexp = regexp;
            this.mask = mask;
        }

        private String getRegexp() {
            return regexp;
        }

        private String getMask() {
            return mask;
        }
    }
}
