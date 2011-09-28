package com.rixon.lms.view;

import com.rixon.lms.domain.AuthenticationInfo;
import com.rixon.lms.domain.Role;
import com.rixon.lms.facade.AuthenticationFacade;
import com.rixon.lms.model.RoleComboBoxModel;
import com.rixon.lms.util.LMSResourceBundle;
import com.rixon.lms.util.ResourceBundleKeys;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;

/**
 * @author: Rixon Mathew(rixonmathew@gmail.com)
 * date   : 9/3/11 - 10:39 AM
 */

@SuppressWarnings("serial")
public class LoginDialog extends JDialog{

	private Set<Role> roleEntries;

	//UI elements
	private JTextField userNameField;
    private JPasswordField passwordField;
    private JComboBox rolesComboBox;  

    public LoginDialog(JFrame owner,Set<Role> roleEntries) {
        super(owner);
        this.roleEntries = roleEntries;
        createLoginPanel();
    }

    private void createLoginPanel() {
        JPanel panel = initializeMainPanel();
        initializeRoleComboBox(panel);        
        initializeUserNameField(panel);
        initializePasswordField(panel);
        initializeLoginButton(panel);
        
        //TODO add listener for combox box selection 
        // 1) Disable fields if role is guest.
        // 2) Disable login if role is not guest and username or pwd is blank
        //    Think about using Strategy pattern to enable disable view based on the role.
        setContentPane(panel);
        setLocationRelativeTo(super.getOwner());
        //TODO magic numbers needs to be extracted
        setSize(210,150);
        setResizable(false);
        setVisible(true);
    }

	private void initializeLoginButton(JPanel panel) {
		JButton loginButton = new JButton(LMSResourceBundle.getString(LMSMainView.class, ResourceBundleKeys.LOGIN));
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                authenticate();
            }
        });
        panel.add(loginButton,"span, align center");
	}

	private JPanel initializeMainPanel() {
		setVisible(false);
        setTitle(LMSResourceBundle.getString(LMSMainView.class, ResourceBundleKeys.LOGIN_TITLE));
        JPanel panel = new JPanel(new MigLayout());
		return panel;
	}

	private void initializePasswordField(JPanel panel) {
		panel.add(new JLabel(LMSResourceBundle.getString(LMSMainView.class,ResourceBundleKeys.PASSWORD)),"gap para");
        passwordField = new JPasswordField();
        panel.add(passwordField,"span, growx, wrap");
	}

	private void initializeUserNameField(JPanel panel) {
		panel.add(new JLabel(LMSResourceBundle.getString(LMSMainView.class,ResourceBundleKeys.USER_NAME)),"gap para");
        userNameField = new JTextField();
        panel.add(userNameField,"span, growx, wrap");
	}

	private void initializeRoleComboBox(JPanel panel) {
		panel.add(new JLabel(LMSResourceBundle.getString(LMSMainView.class,ResourceBundleKeys.ROLE)),"gap para");
        rolesComboBox = new JComboBox(new RoleComboBoxModel(roleEntries));
        rolesComboBox.setSelectedIndex(0);        
        panel.add(rolesComboBox,"span, growx, wrap");
	}

    private void authenticate() {
        AuthenticationInfo.AuthenticationInfoBuilder authenticationInfoBuilder =
                new AuthenticationInfo.AuthenticationInfoBuilder();
        authenticationInfoBuilder.setUsername(userNameField.getText());
        //TODO Check the impact of converting char[] to String
        authenticationInfoBuilder.setPassword(passwordField.getPassword().toString());
        authenticationInfoBuilder.setRole(rolesComboBox.getSelectedItem().toString());

        if (AuthenticationFacade.authenticateUser(authenticationInfoBuilder.createAuthenticationInfo())) {
          setVisible(false);
          this.getOwner().setVisible(true);
        }
        else{
            setLoginFailureMessage();
        }
    }

    private void resetFields() {
        userNameField.setText(null);
        passwordField.setText(null);
        rolesComboBox.setSelectedIndex(0);
    }

    private void setLoginFailureMessage() {
        String dialogMessage = LMSResourceBundle.getString(LMSMainView.class,ResourceBundleKeys.AUTHENTICATION_FAILURE);
        String dialogTitle = LMSResourceBundle.getString(LMSMainView.class,ResourceBundleKeys.AF_DIALOG_TITLE);
    	JOptionPane.showMessageDialog(this,dialogMessage,dialogTitle,JOptionPane.ERROR_MESSAGE);
        resetFields();
    }
}
