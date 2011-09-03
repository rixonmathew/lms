package com.rixon.lms.view;

import com.rixon.lms.domain.AuthenticationInfo;
import com.rixon.lms.facade.AuthenticationFacade;
import com.rixon.lms.util.LMSResourceBundle;
import com.rixon.lms.util.ResourceBundleKeys;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author: Rixon Mathew(rixonmathew@gmail.com)
 * date   : 9/3/11 - 10:39 AM
 */
public class LoginDialog extends JDialog{

    JTextField userNameField;
    JPasswordField passwordField;
    JComboBox roles;

    public LoginDialog(JFrame owner) {
        super(owner);
        createLoginPanel();
    }

    private void createLoginPanel() {
        setVisible(false);
        setTitle(LMSResourceBundle.getString(LMSMainView.class, ResourceBundleKeys.LOGIN_TITLE));
        JPanel panel = new JPanel(new MigLayout());
        panel.add(new JLabel(LMSResourceBundle.getString(LMSMainView.class,ResourceBundleKeys.USER_NAME)),"gap para");
        userNameField = new JTextField();
        panel.add(userNameField,"span, growx, wrap");
        panel.add(new JLabel(LMSResourceBundle.getString(LMSMainView.class,ResourceBundleKeys.PASSWORD)),"gap para");
        passwordField = new JPasswordField();
        panel.add(passwordField,"span, growx, wrap");
        panel.add(new JLabel(LMSResourceBundle.getString(LMSMainView.class,ResourceBundleKeys.ROLE)),"gap para");
        Object[] items = {"Administrator","Member","Guest"};
        roles = new JComboBox(items);
        panel.add(roles,"span, growx, wrap");

        JButton loginButton = new JButton(LMSResourceBundle.getString(LMSMainView.class, ResourceBundleKeys.LOGIN));
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                authenticate();
            }
        });
        panel.add(loginButton,"span, align center");
        setContentPane(panel);
        setLocationRelativeTo(super.getOwner());
        setSize(210,150);
        setResizable(false);
        setVisible(true);
    }

    private void authenticate() {
        AuthenticationInfo.AuthenticationInfoBuilder authenticationInfoBuilder =
                new AuthenticationInfo.AuthenticationInfoBuilder();
        authenticationInfoBuilder.setUsername(userNameField.getText());
        //TODO Check the impact of converting char[] to String
        authenticationInfoBuilder.setPassword(passwordField.getPassword().toString());
        authenticationInfoBuilder.setRole(roles.getSelectedItem().toString());

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
        roles.setSelectedIndex(0);
    }

    private void setLoginFailureMessage() {
        JOptionPane.showMessageDialog(this,ResourceBundleKeys.AUTHENTICATION_FAILURE,ResourceBundleKeys.AF_DIALOG_TITLE,JOptionPane.ERROR_MESSAGE);
        resetFields();
    }
}
