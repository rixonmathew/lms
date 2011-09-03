package com.rixon.lms.view;

import com.rixon.lms.business.ItemTypeProvider;
import com.rixon.lms.domain.SearchResult;
import com.rixon.lms.domain.UniqueIdentifier;
import com.rixon.lms.main.LibraryManagementSystem;
import com.rixon.lms.model.SearchResultsTableModel;
import com.rixon.lms.util.ItemTypeConstants;
import com.rixon.lms.util.LMSResourceBundle;
import com.rixon.lms.util.LMSUtil;
import com.rixon.lms.util.ResourceBundleKeys;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @author: Rixon Mathew(rixonmathew@gmail.com)
 * date   : 8/7/11 - 8:07 PM
 */
public class LMSMainView extends JFrame {

    private JMenuBar menuBar;
    private JPanel resultsPanel;
    private SearchResultsTableModel tableModel;
    private final LibraryManagementSystem libraryManagementSystem;
    final String FORM_TITLE = LMSResourceBundle.getString(LMSMainView.class, ResourceBundleKeys.FORM_TITLE);

    private LMSMainView() {
        super.setTitle(FORM_TITLE);
        LoginDialog loginDialog = new LoginDialog(this);
        createContentPanel();
        libraryManagementSystem = new LibraryManagementSystem();
        //TODO add dialog to show data loading in progress to create a more user friendly application
    }

    private void createContentPanel() {
        //TODO Check if the constants are really required and can they be refactored into constant strings
        JPanel contentPanel = new JPanel(new MigLayout("wrap", "[]unrel[grow]", "[grow][pref]"));
        contentPanel.add(createCriteriaPanel(), "span, grow, wrap");
        contentPanel.add(createResultsPanel(), "span, grow");
        initializeMenuBar();
        setContentPane(contentPanel);
        setSize(800, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setVisible(true);
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

        criteriaPanel.add(searchText, "wrap");
        String searchButonText  = LMSResourceBundle.getString(LMSMainView.class, ResourceBundleKeys.SEARCH_BUTTON_TEXT);
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
                    (ItemTypeProvider.getItemType(ItemTypeConstants.BOOK),identifier);
            if (searchResult.getAllSearchedItems().size() == 0 ){
                //search for all attributes
                searchResult = libraryManagementSystem.searchAllAttributes(ItemTypeProvider.
                        getItemType(ItemTypeConstants.BOOK),searchText);
            }
            if (searchResult.getAllSearchedItems().size()==0){
                JOptionPane.showMessageDialog(resultsPanel, "No items found for search text:"+searchText);
            } else {
                tableModel.refreshTableModel(searchResult);
            }
        }
    }

    private JPanel createResultsPanel() {


        resultsPanel = new JPanel(new MigLayout("fillx", "[right]rel[grow,fill]", "[]10[]"));
        final String RESULTS_TITLE = LMSResourceBundle.getString(LMSMainView.class, ResourceBundleKeys.RESULTS_TITLE);
        resultsPanel.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createTitledBorder(RESULTS_TITLE),
                        BorderFactory.createEmptyBorder(5,5,5,5)));
        tableModel = new SearchResultsTableModel();
        //TODO check if the table cells can be highlighted in a subtle way to indicate which cell matched the query
        JTable resultsTable = new JTable(tableModel);
        final JPopupMenu popUpMenu = getPopUpMenuForItems();
        PopUpListener listener = new PopUpListener(popUpMenu);
        //popupMenu.addMouseListener(listener);
        resultsTable.addMouseListener(listener);
        resultsTable.setAutoCreateRowSorter(true);

        JScrollPane scrollPane = new JScrollPane(resultsTable);
        resultsPanel.add(scrollPane,"span, grow");
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

        private void showPopUp(MouseEvent e){
            if (e.isPopupTrigger()){
                popUp.show(e.getComponent(),e.getX(),e.getY());
            }
        }
    }

    private void initializeMenuBar() {
        menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        fileMenu.add(new JMenuItem("Open"));
        fileMenu.add(new JMenuItem("Save"));
        JMenu editMenu = new JMenu("Edit");
        editMenu.add(new JMenuItem("Cut"));
        editMenu.add(new JMenuItem("Copy"));
        editMenu.add(new JMenuItem("Paste"));
        editMenu.addSeparator();
        editMenu.add(new JMenuItem("Search"));
        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        super.setJMenuBar(menuBar);
    }

    public static void main(String[] args) {

        final String laf = UIManager.getSystemLookAndFeelClassName();
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    UIManager.setLookAndFeel(laf);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                new LMSMainView();
            }
        });
    }
}
