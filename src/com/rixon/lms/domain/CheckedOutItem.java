package com.rixon.lms.domain;

import com.rixon.lms.type.CheckOutStatus;

import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: rixon
 * Date: 16/6/11
 * Time: 11:21 AM
 * Domain class representing a CheckedOut Item
 */
public class CheckedOutItem {

    private final ILibraryItemInstance libraryItemInstance;
    private final LibraryMember member;
    private final Date checkedOutDate;
    private final Date requiredReturnDate;
    private CheckOutStatus checkOutStatus = CheckOutStatus.CHECKED_OUT;
    private Date actualReturnDate;
    private Money fees;

    public ILibraryItemInstance getLibraryItemInstance() {
        return libraryItemInstance;
    }

    public LibraryMember getMember() {
        return member;
    }

    public Date getCheckedOutDate() {
        return checkedOutDate;
    }

    public Date getRequiredReturnDate() {
        return requiredReturnDate;
    }

    public CheckOutStatus getCheckOutStatus() {
        return checkOutStatus;
    }

    public Date getActualReturnDate() {
        return actualReturnDate;
    }

    public Money getFees() {
        return fees;
    }

    public void setCheckOutStatus(CheckOutStatus checkOutStatus) {
        this.checkOutStatus = checkOutStatus;
    }

    public void setActualReturnDate(Date actualReturnDate) {
        this.actualReturnDate = actualReturnDate;
    }

    public void setFees(Money fees) {
        this.fees = fees;
    }

    private CheckedOutItem(ILibraryItemInstance libraryItemInstance, LibraryMember member, Date checkedOutDate, Date requiredReturnDate) {
        this.libraryItemInstance = libraryItemInstance;
        this.member = member;
        this.checkedOutDate = checkedOutDate;
        this.requiredReturnDate = requiredReturnDate;
    }

    @Override
    public String toString() {
        return "CheckedOutItem{" +
                "libraryItemInstance=" + libraryItemInstance +
                ", member=" + member +
                ", checkedOutDate=" + checkedOutDate +
                ", requiredReturnDate=" + requiredReturnDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CheckedOutItem that = (CheckedOutItem) o;

        if (checkOutStatus != that.checkOutStatus) return false;
        if (!checkedOutDate.equals(that.checkedOutDate)) return false;
        if (!libraryItemInstance.equals(that.libraryItemInstance)) return false;
        if (!member.equals(that.member)) return false;
        return requiredReturnDate.equals(that.requiredReturnDate);

    }

    @Override
    public int hashCode() {
        int result = libraryItemInstance.hashCode();
        result = 31 * result + member.hashCode();
        result = 31 * result + checkedOutDate.hashCode();
        result = 31 * result + requiredReturnDate.hashCode();
        result = 31 * result + checkOutStatus.hashCode();
        return result;
    }

    public static class CheckedOutItemBuilder {
        private ILibraryItemInstance libraryItemInstance;
        private LibraryMember member;
        private Date checkedOutDate;
        private Date returnDate;

        public CheckedOutItemBuilder setLibraryItemInstance(ILibraryItemInstance libraryItemInstance) {
            this.libraryItemInstance = libraryItemInstance;
            return this;
        }

        public CheckedOutItemBuilder setMember(LibraryMember member) {
            this.member = member;
            return this;
        }

        public CheckedOutItemBuilder setCheckedOutDate(Date checkedOutDate) {
            this.checkedOutDate = checkedOutDate;
            return this;
        }

        public CheckedOutItemBuilder setReturnDate(Date returnDate) {
            this.returnDate = returnDate;
            return this;
        }

        public CheckedOutItem createItemCheckedOut() {
            return new CheckedOutItem(libraryItemInstance, member, checkedOutDate, returnDate);
        }

    }
}
