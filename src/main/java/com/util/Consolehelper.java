package com.util;

public class Consolehelper {
    private final static String BOLD = "\033[1m";
    private  final static String RESET = "\033[0m";

    // Prints a line in bold
    public static void println(String message) {
        System.out.println(BOLD + message + RESET);
    }

    // Prints without a newline in bold
    public static void print(String message) {
        System.out.print(BOLD + message + RESET);
    }
	

}
