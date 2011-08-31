package com.rixon.lms.dao.mapper;

import com.rixon.lms.business.PropertyProvider;
import com.rixon.lms.dao.resultset.ItemRS;
import com.rixon.lms.domain.Book;
import com.rixon.lms.util.LMSUtil;
import com.rixon.lms.util.PropertyConstants;

/**
 * @author: Rixon Mathew(rixonmathew@gmail.com)
 * date   : 8/28/11 - 8:00 PM
 */
public class BookMapper {

    public static Book mapToBook(ItemRS itemRS) {
        Book.BookBuilder bookBuilder = new Book.BookBuilder();
        bookBuilder.setTitle(itemRS.getName());
        String author = itemRS.getProperties().get(PropertyProvider.getProperty(PropertyConstants.AUTHOR)).getValue();
        bookBuilder.setAuthor(author);
        String isbn = itemRS.getProperties().get(PropertyProvider.getProperty(PropertyConstants.ISBN)).getValue();
        bookBuilder.setIsbn(LMSUtil.createISBN(isbn));
        String publisher = itemRS.getProperties().get(PropertyProvider.getProperty(PropertyConstants.PUBLISHER)).getValue();
        bookBuilder.setPublisher(publisher);
        String publishedDate = itemRS.getProperties().get(PropertyProvider.getProperty(PropertyConstants.PUBLISHED_DATE))
                                    .getValue();
        bookBuilder.setPublishedDate(LMSUtil.getFormattedDate(publishedDate));
        return bookBuilder.createBook();
    }
}
