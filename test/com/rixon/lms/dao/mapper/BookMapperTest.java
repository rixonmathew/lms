package com.rixon.lms.dao.mapper;

import com.rixon.lms.business.PropertyProvider;
import com.rixon.lms.dao.resultset.ItemPropertyRS;
import com.rixon.lms.dao.resultset.ItemRS;
import com.rixon.lms.dao.resultset.PropertyRS;
import com.rixon.lms.domain.Book;
import com.rixon.lms.util.LMSUtil;
import com.rixon.lms.util.PropertyConstants;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

/**
 * @author: Rixon Mathew(rixonmathew@gmail.com)
 * date   : 8/28/11 - 8:01 PM
 */
public class BookMapperTest {

    private ItemRS mockItemRSForBook;

    @Test
    public void testMapItemToBook()
    {
        String bookName = "An Introduction";
        String bookAuthor = "Rixon Mathew";
        String bookIsbn = "978-112-112323";
        String bookPublisher = "Harper Collins";
        String bookPublishedDate = "01/07/2011";

        ItemRS itemRS = getMockItemForBook(bookName, bookAuthor, bookIsbn, bookPublisher, bookPublishedDate);
        assertNotNull(itemRS);
        //List<ItemPropertyRS> itemPropertyList = itemRS.getProperties();
        Book book = BookMapper.mapToBook(itemRS);
        assertNotNull(book);
        assertEquals("Title does not match",bookName,book.getTitle());
        assertEquals("Author does not match",bookAuthor,book.getAuthor());
        assertEquals("Publisher does not match",bookPublisher,book.getPublisher());
        assertEquals("PublishedDate does not match", LMSUtil.getFormattedDate(bookPublishedDate),book.getPublishedDate());
    }


    private static ItemRS getMockItemForBook(String bookName,String bookAuthor,String bookIsbn,
                                           String bookPublisher,String bookPublishedDate) {
        ItemRS itemRS = new ItemRS();
        int itemID = 101;
        itemRS.setId(itemID);
        itemRS.setName(bookName);
        Map<PropertyRS,ItemPropertyRS> itemPropertyMap = new HashMap<PropertyRS, ItemPropertyRS>();
        ItemPropertyRS author = new ItemPropertyRS();
        author.setId(1);
        PropertyRS propertyRSTypeAuthor = PropertyMapper.mapToPropertyRS(PropertyProvider
                .getProperty(PropertyConstants.AUTHOR));
        author.setPropertyRS(propertyRSTypeAuthor);
        author.setValue(bookAuthor);
        author.setItem_id(itemID);
        itemPropertyMap.put(propertyRSTypeAuthor,author);

        ItemPropertyRS isbn = new ItemPropertyRS();
        isbn.setId(2);
        PropertyRS propertyRSTypeISBN = PropertyMapper.mapToPropertyRS(PropertyProvider
                .getProperty(PropertyConstants.ISBN));
        isbn.setPropertyRS(propertyRSTypeISBN);
        isbn.setValue(bookIsbn);
        isbn.setItem_id(itemID);
        itemPropertyMap.put(propertyRSTypeISBN,isbn);

        ItemPropertyRS publisher = new ItemPropertyRS();
        publisher.setId(2);
        PropertyRS propertyRSTypePublisher = PropertyMapper.mapToPropertyRS(PropertyProvider
                .getProperty(PropertyConstants.PUBLISHER));
        isbn.setPropertyRS(propertyRSTypePublisher);
        isbn.setValue(bookPublisher);
        isbn.setItem_id(itemID);
        itemPropertyMap.put(propertyRSTypePublisher,isbn);

        ItemPropertyRS publishedDate = new ItemPropertyRS();
        publishedDate.setId(3);
        PropertyRS propertyRSForPublishedDate = PropertyMapper.mapToPropertyRS(PropertyProvider
                .getProperty(PropertyConstants.PUBLISHED_DATE));
        publishedDate.setPropertyRS(propertyRSForPublishedDate);
        publishedDate.setValue(bookPublishedDate);
        publishedDate.setItem_id(itemID);
        itemPropertyMap.put(propertyRSForPublishedDate,publishedDate);
        itemRS.setProperties(itemPropertyMap);
        return itemRS;
    }
}