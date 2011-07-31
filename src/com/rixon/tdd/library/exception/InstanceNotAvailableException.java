package com.rixon.tdd.library.exception;

/**
 * Created by IntelliJ IDEA.
 * User: rixon
 * Date: 17/6/11
 * Time: 9:35 AM
 * To change this template use File | Settings | File Templates.
 */
public class InstanceNotAvailableException extends RuntimeException {
    public InstanceNotAvailableException(String s) {
        super(s);
    }
}
