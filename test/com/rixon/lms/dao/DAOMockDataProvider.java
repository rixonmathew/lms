package com.rixon.lms.dao;

import com.rixon.lms.dao.resultset.MemberRS;

/**
 * @author: Rixon Mathew(rixonmathew@gmail.com)
 * date   : 10/3/11 - 12:37 AM
 */
public class DAOMockDataProvider {
    public static MemberRS getMemberRecord(String firstName, String lastName, String email, String mobileNumber, String postalAddress, String password) {
        MemberRS memberRS = new MemberRS();
        memberRS.setFirstName(firstName);
        memberRS.setLastName(lastName);
        memberRS.setEmailId(email);
        memberRS.setMobileNumber(mobileNumber);
        memberRS.setPostalAddress(postalAddress);
        memberRS.setPassword(password);
        return memberRS;
    }
}
