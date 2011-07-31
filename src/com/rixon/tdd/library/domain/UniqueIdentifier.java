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

package com.rixon.tdd.library.domain;

import java.util.UUID;

/**
 * @author: Rixon Mathew(rixonmathew@gmail.com)
 * date   : 6/12/11 - 10:28 AM
 */
public class UniqueIdentifier {

    private String type;
    private String value;

    private UniqueIdentifier(String type, String value) {
        this.type = type;
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public String getValue() {
        return value;
    }

    //todo add static method to get unique id
    @Override
    public boolean equals(Object uiToCompare) {
        if (this == uiToCompare) return true;
        if (uiToCompare == null || getClass() != uiToCompare.getClass()) return false;

        UniqueIdentifier that = (UniqueIdentifier) uiToCompare;

        if (!value.equals(that.value)) return false;
        if (!type.equals(that.type)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = type.hashCode();
        result = 31 * result + value.hashCode();
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("UniqueIdentifier{").append("type=>").append(type).append("|")
           .append("value=>").append(value).append("}");
        return sb.toString();
    }

    public static class UniqueIdentifierBuilder {
        private String type;
        private String value;

        public UniqueIdentifierBuilder setType(String type) {
            this.type = type;
            return this;
        }

        public UniqueIdentifierBuilder setValue(String value) {
            this.value = value;
            return this;
        }

        public UniqueIdentifierBuilder generateUniqueValue() {
            this.value = UUID.randomUUID().toString();
            return this;
        }
        public UniqueIdentifier createUniqueIdentifier() {
            return new UniqueIdentifier(type, value);
        }
    }
}
