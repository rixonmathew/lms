package com.rixon.lms.dao.resultset;

import javax.persistence.*;

/**
 * @author: Rixon Mathew(rixonmathew@gmail.com)
 * date   : 10/3/11 - 12:04 AM
 */
@Entity()
@Table(name="MEMBER")
@NamedQuery(name=MemberRS.FIND_MEMBER,query = "select x from MemberRS x where x.emailId = :emailId and x.password = :password")
public class MemberRS {

    public final static String FIND_MEMBER ="findMember";

    private int id;
    private String firstName;
    private String lastName;
    private String emailId;
    private String mobileNumber;
    private String postalAddress;
    private String password;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "member_id_gen")
    @SequenceGenerator(name="member_id_gen",sequenceName = "member_id_seq",allocationSize = 1)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name="FIRST_NAME")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name="LAST_NAME")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name="EMAIL_ID")
    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    @Column(name="MOBILE_NUMBER")
    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    @Column(name="POSTAL_ADDRESS")
    public String getPostalAddress() {
        return postalAddress;
    }

    public void setPostalAddress(String postalAddress) {
        this.postalAddress = postalAddress;
    }

    @Column(name="PASSWORD")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MemberRS memberRS = (MemberRS) o;

        if (id != memberRS.id) return false;
        if (emailId != null ? !emailId.equals(memberRS.emailId) : memberRS.emailId != null) return false;
        if (firstName != null ? !firstName.equals(memberRS.firstName) : memberRS.firstName != null) return false;
        if (lastName != null ? !lastName.equals(memberRS.lastName) : memberRS.lastName != null) return false;
        if (mobileNumber != null ? !mobileNumber.equals(memberRS.mobileNumber) : memberRS.mobileNumber != null)
            return false;
        if (password != null ? !password.equals(memberRS.password) : memberRS.password != null) return false;
        if (postalAddress != null ? !postalAddress.equals(memberRS.postalAddress) : memberRS.postalAddress != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (emailId != null ? emailId.hashCode() : 0);
        result = 31 * result + (mobileNumber != null ? mobileNumber.hashCode() : 0);
        result = 31 * result + (postalAddress != null ? postalAddress.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "MemberRS{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", emailId='" + emailId + '\'' +
                ", mobileNumber='" + mobileNumber + '\'' +
                ", postalAddress='" + postalAddress + '\'' +
                '}';
    }
}
