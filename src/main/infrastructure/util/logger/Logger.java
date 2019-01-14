package main.infrastructure.util.logger;

import java.util.Date;

public class Logger {

    private final String classContext;
    private static final String ANSI_BACK_TO_NORMAL = "\u001B[0m";
    private static final String ANSI_GREEN = "\033[1;32m";  // GREEN
    public static final String ANSI_RED = "\033[1;31m";    // RED
    public static final String ANSI_YELLOW = "\033[1;33m"; // YELLOW


    public Logger(String classContext){
        this.classContext = classContext;
    }

    public void info(Object toLog){
        System.out.println(ANSI_GREEN + new Date() + ": [" + Thread.currentThread().getName() + "]: [" + classContext + "]: " + toLog + ANSI_BACK_TO_NORMAL);
    }

    public void warn(Object toLog){
        System.out.println(ANSI_YELLOW + new Date() + ": [" + Thread.currentThread().getName() + "]: [" + classContext + "]: " + toLog + ANSI_BACK_TO_NORMAL);
    }

    public void error(Object errorToLog){
        System.out.println(ANSI_RED + new Date() + ": [" + Thread.currentThread().getName() + "]: [" + classContext + "]: " + errorToLog + ANSI_BACK_TO_NORMAL);
    }
}
