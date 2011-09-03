package com.rixon.lms.type;

/**
 * Created by IntelliJ IDEA.
 * User: rixon
 * Date: 17/6/11
 * Time: 11:15 AM
 * Enum representing a checkout status
 */
public enum CheckOutStatus {
    CHECKED_OUT("Checked Out"),
    RETURNED("Returned"),
    EXTENDED("Extended"),
    TRANSFERRED("Transferred"),
    LOST("Lost");

    CheckOutStatus(String status) {
    }
}
