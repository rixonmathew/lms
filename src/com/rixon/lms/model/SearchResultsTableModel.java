package com.rixon.lms.model;

import com.rixon.lms.business.PropertyProvider;
import com.rixon.lms.domain.ILibraryItem;
import com.rixon.lms.domain.SearchResult;
import com.rixon.lms.domain.SearchResultDetail;
import com.rixon.lms.util.LMSUtil;
import com.rixon.lms.util.PropertyConstants;
import org.eclipse.persistence.internal.jpa.weaving.MethodWeaver;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: Rixon Mathew(rixonmathew@gmail.com)
 * date   : 8/7/11 - 9:17 PM
 */
public class SearchResultsTableModel extends AbstractTableModel{

    private Map<Integer,String> headerNames;
    private SearchResult searchResult;

    public SearchResultsTableModel() {
        initHeader();
    }

    public SearchResultsTableModel(SearchResult searchResult) {
        initHeader();
        this.searchResult = searchResult;
    }

    private void initHeader() {
        headerNames = new HashMap<Integer, String>();
        headerNames.put(0,"ID");
        headerNames.put(1,"Type");
        headerNames.put(2,"Title");
        headerNames.put(3,"Author");
        headerNames.put(4,"Description");
        headerNames.put(5,"Published Date");
    }

    @Override
    public String getColumnName(int column) {
        return headerNames.get(column);
    }

    @Override
    public int getRowCount() {
        if (searchResult == null) {
            return 0;
        }
        return searchResult.getAllSearchedItems().size();
    }

    @Override
    public int getColumnCount() {
        return headerNames.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (searchResult == null) {
            return null;
        }
        SearchResultDetail tableRow = searchResult.getAllSearchedItems().get(rowIndex);
        ILibraryItem libraryItem = tableRow.getLibraryItem();
        //TODO Search Result is not the correct interface
        switch(columnIndex){
            case 0:
                return libraryItem.getUniqueId().getValue();
            case 1:
                return libraryItem.getItemType().getType();
            case 2:
                return libraryItem.getName();
            case 3:
                return libraryItem.getItemPropertyValue(PropertyProvider.getProperty(PropertyConstants.AUTHOR)).getPropertyValue();
            case 4:
                return libraryItem.getDescription();
            case 5:
                return libraryItem.getItemPropertyValue(PropertyProvider.
                        getProperty(PropertyConstants.PUBLISHED_DATE)).getPropertyValue();
            default:
                throw new IllegalArgumentException("Column not expected :"+columnIndex);
        }
    }

    public void refreshTableModel(SearchResult searchResult) {
        this.searchResult = searchResult;
        fireTableDataChanged();
    }
}
