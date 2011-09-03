package com.rixon.lms.exception;

/**
 * Created by IntelliJ IDEA.
 * User: rixon
 * Date: 17/6/11
 * Time: 9:35 AM
 * Exception when a library Item is not found
 */
public class InstanceNotAvailableException extends RuntimeException {
    public InstanceNotAvailableException(String s) {
        super(s);
    }
}
