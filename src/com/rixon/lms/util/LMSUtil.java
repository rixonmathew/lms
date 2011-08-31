package com.rixon.lms.util;

import com.rixon.lms.domain.UniqueIdentifier;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author: Rixon Mathew(rixonmathew@gmail.com)
 * date   : 8/28/11 - 9:05 PM
 */
public class LMSUtil {

    private static DateFormat dateFormat = SimpleDateFormat.getDateInstance(SimpleDateFormat.SHORT);
    public static UniqueIdentifier createISBN(String isbn) {
        UniqueIdentifier identifier = new UniqueIdentifier.UniqueIdentifierBuilder().setType(PropertyConstants.ISBN)
                                          .setValue(isbn).createUniqueIdentifier();
        return identifier;
    }

    public static Date getFormattedDate(String dateString)
    {
        try {
            return dateFormat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        throw new IllegalArgumentException("Invalid date string "+dateString);
    }
}
