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

import com.rixon.lms.type.OwnerType;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Rixon Mathew(rixonmathew@gmail.com)
 * date   : 6/11/11 - 2:25 PM
 */
public class ItemOwnerInformation {

    private final OwnerType ownerType;
    private final List<String> owners;

    private ItemOwnerInformation(OwnerType ownerType, List<String> owners) {
        this.ownerType = ownerType;
        this.owners = owners;
    }

    public static ItemOwnerInformation createWithMultipleOwners(OwnerType ownerType, List<String> owners) {
        return new ItemOwnerInformation(ownerType, owners);
    }

    public static ItemOwnerInformation createWithSingleOwner(OwnerType ownerType,String owner) {
        List<String> owners = new ArrayList<String>();
        owners.add(owner);
        return new ItemOwnerInformation(ownerType,owners);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ItemOwnerInformation that = (ItemOwnerInformation) o;

        if (ownerType != that.ownerType) return false;
        return owners.equals(that.owners);

    }

    @Override
    public int hashCode() {
        int result = ownerType.hashCode();
        result = 31 * result + owners.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "ItemOwnerInformation{" +
                "ownerType=" + ownerType +
                ", owners=" + owners +
                '}';
    }
}
