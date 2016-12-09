package com.springer.paint.component;

/**
 * Supported commands in drawing application
 *
 * @author Abhilash Ghosh
 * @since 1.2
 * @version 1.2
 */
public enum Command {

    CANVAS("C"),
    LINE("L"),
    RECTANGLE("R"),
    FILL("B"),
    EXIT("Q");

    String acronyms;

    Command(String acronyms){
        this.acronyms = acronyms;
    }

    public String getAcronyms() {
        return this.acronyms;
    }

    public static Command findCommandByAcronyms(String acronyms) {
        if (acronyms != null) {
            for (Command cmd : Command.values()) {
                if (acronyms.equalsIgnoreCase(cmd.acronyms)) {
                    return cmd;
                }
            }
        }
        return null;
    }
}
