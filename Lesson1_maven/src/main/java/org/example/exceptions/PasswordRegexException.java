package org.example.exceptions;

/**
 * Для каждой исключительной ситуации - я описываю свой тип исключения
 */
public class PasswordRegexException extends Exception{
    public PasswordRegexException(String message) {
        super(message);
    }
}
