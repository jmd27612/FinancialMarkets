package com.financialMarkets.userInterface;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.financialMarkets.Utilities;

public class ConfigurationKeyMaintenanceGUI extends JFrame
{
	private static final long serialVersionUID = 8263805047853289347L;
	private JDesktopPane desktopPane; 
	private JPanel mainPanel; 
	private JLabel keyNameLabel; 
	private JLabel keyValueLabel; 
	private JLabel classKeyLabel; 
	private JTextField keyNameField; 
	private JPasswordField keyValueField; 
	private JPasswordField classKeyField; 
	
	
	ConfigurationKeyMaintenanceGUI()
	{
		createFrame(); 
	}
	
	ConfigurationKeyMaintenanceGUI(String keyName)
	{
		createFrame(); 
		keyNameField.setText(keyName);
		keyNameField.setEditable(false);
	}
	
	private void createFrame()
	{
		mainPanel = new JPanel(); 
		mainPanel.setBorder(null); 
		setContentPane(mainPanel); 
		mainPanel.setLayout(null);
		
		desktopPane = new JDesktopPane(); 
		desktopPane.setBounds(0, 525, 1, 300);
		mainPanel.add(desktopPane); 
		
		setTitle(Utilities.APPLICATION_NAME + " - Configuration Key Maintenance"); 
		setSize(525, 300); 
		
		//Key Name Label
		keyNameLabel = new JLabel("Key Name: "); 
		keyNameLabel.setBounds(25, 15, 150, 50);
		mainPanel.add(keyNameLabel); 
		
		//Key Name Text Field
		keyNameField = new JTextField(); 
		keyNameField.setBounds(100, 27, 300, 25);
		mainPanel.add(keyNameField); 
		
		//Key Value Label
		keyValueLabel = new JLabel("Key Value: ");
		keyValueLabel.setBounds(25, 60, 150, 50);
		mainPanel.add(keyValueLabel); 
		
		//Key Value Password Field
		keyValueField = new JPasswordField(); 
		keyValueField.setBounds(100, 72, 300, 25); 
		mainPanel.add(keyValueField); 
		
		//Class Key Label
		classKeyLabel = new JLabel("Class Key: "); 
		classKeyLabel.setBounds(25, 105, 150, 50); 
		mainPanel.add(classKeyLabel); 
		
		//Class Key Password Field
		classKeyField = new JPasswordField(); 
		classKeyField.setBounds(100, 118, 300, 25); 
		mainPanel.add(classKeyField);
		
		
		
		setVisible(true); 
	}
	
	
}
