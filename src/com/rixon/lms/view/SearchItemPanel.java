package com.rixon.lms.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;

import com.rixon.lms.business.ItemTypeProvider;
import com.rixon.lms.domain.SearchResult;
import com.rixon.lms.domain.UniqueIdentifier;
import com.rixon.lms.main.LibraryManagementSystem;
import com.rixon.lms.model.SearchResultsTableModel;
import com.rixon.lms.util.ItemTypeConstants;
import com.rixon.lms.util.LMSResourceBundle;
import com.rixon.lms.util.LMSUtil;
import com.rixon.lms.util.ResourceBundleKeys;


@SuppressWarnings("serial")
public class SearchItemPanel extends JPanel {
	
	private JPanel resultsPanel;
    private SearchResultsTableModel tableModel;
    private final LibraryManagementSystem libraryManagementSystem = LibraryManagementSystem.getInstance();
	
    
    public SearchItemPanel() {
		super();
		this.setLayout(new MigLayout("fill", "[fill]rel[grow]", "[grow][pref]"));
		this.add(createCriteriaPanel(),"wrap");
		this.add(createResultsPanel());
	}
    
    private JPanel createCriteriaPanel() {
        JPanel criteriaPanel = new JPanel(new MigLayout("fillx", "[right]rel[grow,fill]"));
        final String CRITERIA_TITLE = LMSResourceBundle.getString(LMSMainView.class, ResourceBundleKeys.CRITERIA_TITLE);
        criteriaPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(CRITERIA_TITLE),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        String searchLabel = LMSResourceBundle.getString(LMSMainView.class, ResourceBundleKeys.SEARCH_LABEL_TEXT);
        criteriaPanel.add(new JLabel(searchLabel));
        final JTextField searchText = new JTextField(10);
        searchText.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                executeSearch(searchText.getText());
            }
        });

        criteriaPanel.add(searchText, "grow, wrap");
        String searchButonText = LMSResourceBundle.getString(LMSMainView.class, ResourceBundleKeys.SEARCH_BUTTON_TEXT);
        JButton searchButton = new JButton(searchButonText);
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                executeSearch(searchText.getText());
            }
        });
        criteriaPanel.add(searchButton, "span");
        return criteriaPanel;
    }

    private void executeSearch(String searchText) {
        //TODO this needs to be executed in a separate thread
        if (tableModel != null) {
            UniqueIdentifier identifier = LMSUtil.createISBN(searchText);
            SearchResult searchResult = libraryManagementSystem.searchForItemByIdentifier
                    (ItemTypeProvider.getItemType(ItemTypeConstants.BOOK), identifier);
            if (searchResult.getAllSearchedItems().size() == 0) {
                //search for all attributes
                searchResult = libraryManagementSystem.searchAllAttributes(ItemTypeProvider.
                        getItemType(ItemTypeConstants.BOOK), searchText);
            }
            if (searchResult.getAllSearchedItems().size() == 0) {
                JOptionPane.showMessageDialog(resultsPanel, "No items found for search text:" + searchText);
            } else {
                tableModel.refreshTableModel(searchResult);
            }
        }
    }

    private JPanel createResultsPanel() {

        //resultsPanel = new JPanel(new MigLayout("fillx", "[right]rel[grow,fill]")); 
    	resultsPanel = new JPanel(new MigLayout("", "[grow][][grow]","[][shrink 0]"));
        final String RESULTS_TITLE = LMSResourceBundle.getString(LMSMainView.class, ResourceBundleKeys.RESULTS_TITLE);
        resultsPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(RESULTS_TITLE),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        tableModel = new SearchResultsTableModel();

        //TODO check if the table cells can be highlighted in a subtle way to indicate which cell matched the query
        JTable resultsTable = new JTable(tableModel);
        final JPopupMenu popUpMenu = getPopUpMenuForItems();
        PopUpListener listener = new PopUpListener(popUpMenu);
        //popupMenu.addMouseListener(listener);
        resultsTable.addMouseListener(listener);
        resultsTable.setAutoCreateRowSorter(true);

        JScrollPane scrollPane = new JScrollPane(resultsTable);
        resultsPanel.add(scrollPane);
        return resultsPanel;
    }

    private JPopupMenu getPopUpMenuForItems() {
        //TODO each of the action items needs an action listener;
        final JPopupMenu popUpMenu = new JPopupMenu();
        final String CHECK_OUT = LMSResourceBundle.getString(LMSMainView.class, ResourceBundleKeys.CHECK_OUT);
        JMenuItem checkOutMenuItem = new JMenuItem(CHECK_OUT);
        checkOutMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO provide implementation for checkOutOfItems
            }
        });
        popUpMenu.add(checkOutMenuItem);
        //TODO introduce resource bundle for all properties
        final String RESERVE = LMSResourceBundle.getString(LMSMainView.class, ResourceBundleKeys.RESERVE);
        popUpMenu.add(new JMenuItem(RESERVE));
        return popUpMenu;
    }

    private class PopUpListener extends MouseAdapter {

        private final JPopupMenu popUp;

        PopUpListener(JPopupMenu popUp) {
            this.popUp = popUp;
        }

        @Override
        public void mousePressed(MouseEvent e) {
            showPopUp(e);
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            showPopUp(e);
        }

        private void showPopUp(MouseEvent e) {
            if (e.isPopupTrigger()) {
                popUp.show(e.getComponent(), e.getX(), e.getY());
            }
        }
    }

}
