/*
 * Copyright (c) 2011. Rixon Mathew (rixonmathew@gmail.com)
 *
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the
 *  following conditions are met:
 * Redistribution of source code must retain the above copyright notice, this list of conditions and the
 *  following disclaimer.
 * Redistribution in binary form must reproduce the above copyright notice, this list of conditions and the following
 *  disclaimer in the documentation and/or other materials provided with the distribution.
 * Neither the name of Rixon Mathew nor the names of its contributors may be used to endorse or promote products
 *  derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES,
 *  INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 *  DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 *  SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 *  SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 *  WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 *  OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package com.rixon.lms.domain;

import java.util.Date;

/**
* Created by Rixon(rixonmathew@gmail.com)
* User: rixon
* Date: 6/8/11
* Time: 9:07 PM
*/
public class ItemReservation
{
    private ILibraryItemInstance libraryItemInstance;
    private LibraryMember libraryMember;
    private Date reservationDate;
    private Date reservationValidUpto;

    private ItemReservation(ILibraryItemInstance libraryItemInstance, LibraryMember libraryMember, Date reservationDate, Date reservationValidUpto) {
        this.libraryItemInstance = libraryItemInstance;
        this.libraryMember = libraryMember;
        this.reservationDate = reservationDate;
        this.reservationValidUpto = reservationValidUpto;
    }

    public ILibraryItemInstance getLibraryItemInstance() {
        return libraryItemInstance;
    }

    public LibraryMember getLibraryMember() {
        return libraryMember;
    }

    public Date getReservationDate() {
        return reservationDate;
    }

    public Date getReservationValidUpto() {
        return reservationValidUpto;
    }

    @Override
    public boolean equals(Object reservationToCompare) {
        if (this == reservationToCompare) return true;
        if (reservationToCompare == null || getClass() != reservationToCompare.getClass()) return false;

        ItemReservation that = (ItemReservation) reservationToCompare;

        if (!libraryItemInstance.equals(that.libraryItemInstance)) return false;
        if (!libraryMember.equals(that.libraryMember)) return false;
        if (!reservationDate.equals(that.reservationDate)) return false;
        return reservationValidUpto.equals(that.reservationValidUpto);

    }

    @Override
    public int hashCode() {
        int result = libraryItemInstance.hashCode();
        result = 31 * result + libraryMember.hashCode();
        result = 31 * result + reservationDate.hashCode();
        result = 31 * result + reservationValidUpto.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "ItemReservation{" +
                "libraryItemInstance=" + libraryItemInstance +
                ", libraryMember=" + libraryMember +
                ", reservationDate=" + reservationDate +
                ", reservationValidUpto=" + reservationValidUpto +
                '}';
    }

    public static class ItemReservationBuilder {
        private ILibraryItemInstance libraryItemInstance;
        private LibraryMember libraryMember;
        private Date reservationDate;
        private Date reservationValidUpto;

        public ItemReservationBuilder setLibraryItemInstance(ILibraryItemInstance libraryItemInstance) {
            this.libraryItemInstance = libraryItemInstance;
            return this;
        }

        public ItemReservationBuilder setLibraryMember(LibraryMember libraryMember){
            this.libraryMember = libraryMember;
            return this;
        }

        public ItemReservationBuilder setReservationDate(Date reservationDate) {
            this.reservationDate = reservationDate;
            return this;
        }

        public ItemReservationBuilder setReservationValidUpto(Date reservationValidUpto) {
            this.reservationValidUpto = reservationValidUpto;
            return this;
        }

        public ItemReservation createItemReservation() {
            return new ItemReservation(libraryItemInstance,libraryMember, reservationDate, reservationValidUpto);
        }
    }
}
