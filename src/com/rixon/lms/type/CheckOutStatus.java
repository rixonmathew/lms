package com.rixon.lms.type;

/**
 * Created by IntelliJ IDEA.
 * User: rixon
 * Date: 17/6/11
 * Time: 11:15 AM
 * To change this template use File | Settings | File Templates.
 */
public enum CheckOutStatus {
    CHECKED_OUT("Checked Out"),
    RETURNED("Returned"),
    EXTENDED("Extended"),
    TRANSFERRED("Transferred"),
    LOST("Lost");

    private String status;

    CheckOutStatus(String status) {
        this.status = status;
    }
}
