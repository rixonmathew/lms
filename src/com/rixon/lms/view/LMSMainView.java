package com.rixon.lms.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.swing.DefaultListModel;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import net.miginfocom.swing.MigLayout;

import com.rixon.lms.domain.Role;
import com.rixon.lms.main.LibraryManagementSystem;
import com.rixon.lms.util.LMSResourceBundle;
import com.rixon.lms.util.ResourceBundleKeys;

/**
 * @author: Rixon Mathew(rixonmathew@gmail.com)
 * date   : 8/7/11 - 8:07 PM
 */
@SuppressWarnings("serial")
public class LMSMainView extends JFrame {

    //TODO Check if the constants are really required and can they be refactored into constant strings
    final private JPanel contentPanel = new JPanel(new MigLayout("wrap", "[]unrel[grow]", "[grow][pref]"));
    final private JPanel detailPanel = new JPanel();
    private JTabbedPane navigationPane;
    private JList menuList;

	
    final String FORM_TITLE = LMSResourceBundle.getString(LMSMainView.class, ResourceBundleKeys.FORM_TITLE);

    private LMSMainView() {
        super.setTitle(FORM_TITLE);
        Set<Role> roles = LibraryManagementSystem.getInstance().getAllRoles();
        LoginDialog loginDialog = new LoginDialog(this,roles);
        //TODO do you need to wait till login is successful to build the dialog
        createContentPanel();        
        //TODO add dialog to show data loading in progress to create a more user friendly application
    }

    private void createContentPanel() {
        //TODO this is inconsistent with approach used in LoginDialog. Refactor
        contentPanel.add(createNavigationPanel(),"spany,grow");
        contentPanel.add(detailPanel,"spany,grow");
        setContentPane(contentPanel);
        setSize(800, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private JComponent createNavigationPanel() {
        navigationPane = new JTabbedPane();
        String menuHeader = LMSResourceBundle.getString(LMSMainView.class, ResourceBundleKeys.NAVIGATION_PANE_HEADER) ;
        menuList = new JList(new DefaultListModel());
        populateMenuList((DefaultListModel)menuList.getModel(),getMenuEntries());
        menuList.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				String menu = (String)menuList.getSelectedValue();
				JPanel panel = ViewProvider.getView(menu);
				detailPanel.removeAll();
				detailPanel.add(panel);
				contentPanel.revalidate();
			}
		});
        navigationPane.addTab(menuHeader, menuList);
		return navigationPane;
	}

	private List<String> getMenuEntries() {
		List<String> entries = new ArrayList<String>();
		//TODO get this List based on the role from the DB
		entries.add("Search Entry");
		entries.add("Add/Edit Member");
		entries.add("Add/Edit Item");
		entries.add("View Reports");
		entries.add("Edit Preferences");
		
		return entries;
	}

	private void populateMenuList(DefaultListModel model,List<String> menuEntries) {
		// TODO Auto-generated method stub
		for (String entry:menuEntries) {
			model.addElement(entry);
		}
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
