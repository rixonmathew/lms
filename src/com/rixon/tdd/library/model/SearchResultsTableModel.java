package com.rixon.tdd.library.model;

import com.rixon.tdd.library.domain.SearchResult;
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
    private List<SearchResult> searchResult;

    public SearchResultsTableModel() {
        headerNames = new HashMap<Integer, String>();
        headerNames.put(0,"ID");
        headerNames.put(1,"Title");
        headerNames.put(2,"Author");
        headerNames.put(3,"Description");
        headerNames.put(4,"Date");
        searchResult = new ArrayList<SearchResult>();
    }

    @Override
    public String getColumnName(int column) {
        return headerNames.get(column);
    }

    @Override
    public int getRowCount() {
        return 10;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public int getColumnCount() {
        return headerNames.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return "Testing123";  //To change body of implemented methods use File | Settings | File Templates.
    }
}
