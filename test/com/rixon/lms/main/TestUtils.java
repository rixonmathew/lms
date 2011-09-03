package com.rixon.lms.main;

import com.rixon.lms.domain.UniqueIdentifier;
import com.rixon.lms.util.PropertyConstants;

/**
 * @author: Rixon Mathew(rixonmathew@gmail.com)
 * date   : 9/1/11 - 7:59 PM
 */
public class TestUtils {
    public static UniqueIdentifier getUniqueIdForBook(String bookISBN) {
        return new UniqueIdentifier.UniqueIdentifierBuilder().setType(PropertyConstants.ISBN).
                                                       setValue(bookISBN).createUniqueIdentifier();
    }

    public static UniqueIdentifier getUniqueIdForMovie(String barCode) {
        return new UniqueIdentifier.UniqueIdentifierBuilder().setType(PropertyConstants.BARCODE).
                                                       setValue(barCode).createUniqueIdentifier();
    }
}
