package org.example.exceptions;

/**
 * Для каждой исключительной ситуации - я описываю свой тип исключения
 */
public class PasswordLengthException extends Exception{
    public PasswordLengthException(String message) {
        super(message);
    }
}
