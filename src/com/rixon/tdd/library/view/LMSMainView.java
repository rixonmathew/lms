package com.rixon.tdd.library.view;

import com.rixon.tdd.library.model.SearchResultsTableModel;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ExecutorService;

/**
 * @author: Rixon Mathew(rixonmathew@gmail.com)
 * date   : 8/7/11 - 8:07 PM
 */
public class LMSMainView extends JFrame {

    private JPanel contentPanel;
    private JPanel criteriaPanel;
    private JPanel resultsPanel;

    public LMSMainView() {
        super("Library Management System");
        contentPanel = new JPanel(new MigLayout("wrap", "[]unrel[grow]", "[grow][pref]"));
        contentPanel.add(createCriteriaPanel(),"span, wrap");
        contentPanel.add(createResultsPanel(),"span");
        setContentPane(contentPanel);
        setSize(600, 650);
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
        criteriaPanel.add(new JTextField(10),"wrap");
        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        criteriaPanel.add(searchButton,"span 2");
        return criteriaPanel;
    }

    private JPanel createResultsPanel() {
        resultsPanel = new JPanel();
        resultsPanel.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createTitledBorder("Search Results"),
                        BorderFactory.createEmptyBorder(5,5,5,5)));
        JTable resultsTable = new JTable(new SearchResultsTableModel());
        //resultsTable.setFillsViewportHeight(true);
        JScrollPane scrollPane = new JScrollPane(resultsTable);
        resultsPanel.add(scrollPane);
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
