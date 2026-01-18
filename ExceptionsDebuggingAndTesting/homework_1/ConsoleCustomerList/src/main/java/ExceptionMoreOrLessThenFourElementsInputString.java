package main.java;

public class ExceptionMoreOrLessThenFourElementsInputString extends RuntimeException{
    ExceptionMoreOrLessThenFourElementsInputString(String message) {
        super(message);
    }
    ExceptionMoreOrLessThenFourElementsInputString(String message, Throwable cause) {
        super(message, cause);
    }

    }