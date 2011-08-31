package com.rixon.lms.view;

import com.rixon.lms.business.ItemTypeProvider;
import com.rixon.lms.domain.SearchResult;
import com.rixon.lms.domain.UniqueIdentifier;
import com.rixon.lms.main.LibraryManagementSystem;
import com.rixon.lms.model.SearchResultsTableModel;
import com.rixon.lms.util.ItemTypeConstants;
import com.rixon.lms.util.LMSUtil;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author: Rixon Mathew(rixonmathew@gmail.com)
 * date   : 8/7/11 - 8:07 PM
 */
public class LMSMainView extends JFrame {

    private JPanel contentPanel;
    private JPanel criteriaPanel;
    private JPanel resultsPanel;
    private SearchResultsTableModel tableModel;
    private LibraryManagementSystem libraryManagementSystem;

    public LMSMainView() {
        super("Library Management System");
        createContentPanel();
        libraryManagementSystem = new LibraryManagementSystem();
    }

    private void createContentPanel() {
        contentPanel = new JPanel(new MigLayout("wrap", "[]unrel[grow]", "[grow][pref]"));
        contentPanel.add(createCriteriaPanel(),"span, grow, wrap");
        contentPanel.add(createResultsPanel(),"span, grow");
        setContentPane(contentPanel);
        setSize(800, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private JPanel createCriteriaPanel() {
        criteriaPanel = new JPanel(new MigLayout("fillx", "[right]rel[grow,fill]", "[]10[]"));
        criteriaPanel.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createTitledBorder("Enter Search Criteria"),
                        BorderFactory.createEmptyBorder(5,5,5,5)));
        criteriaPanel.add(new JLabel("Select Title"));
        final JTextField searchText = new JTextField(10);
        criteriaPanel.add(searchText,"wrap");
        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               if (tableModel != null) {
                   UniqueIdentifier identifier = LMSUtil.createISBN(searchText.getText());
                   SearchResult searchResult = libraryManagementSystem.searchForItemByIdentifier
                           (ItemTypeProvider.getItemType(ItemTypeConstants.BOOK),identifier);
                   tableModel.refreshTableModel(searchResult);
               }
            }
        });
        criteriaPanel.add(searchButton,"span 2");
        return criteriaPanel;
    }

    private JPanel createResultsPanel() {
        resultsPanel = new JPanel(new MigLayout("fillx", "[right]rel[grow,fill]", "[]10[]"));
        resultsPanel.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createTitledBorder("Search Results"),
                        BorderFactory.createEmptyBorder(5,5,5,5)));
        tableModel = new SearchResultsTableModel();
        JTable resultsTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(resultsTable);
        resultsPanel.add(scrollPane,"span, grow");
        return resultsPanel;
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
