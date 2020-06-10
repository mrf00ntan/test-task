package kz.eserzhanov.testtask.testtask.exception;

import java.util.Map;

public class SelfException extends Exception {
    private Map<String, String> errorMap;

    public SelfException(String message, Map<String, String> map) {
        super(message);
        this.errorMap = map;
    }

    public SelfException(String message) {
        super(message);
    }

    public Map<String, String> getErrorMap() {
        return errorMap;
    }
}
