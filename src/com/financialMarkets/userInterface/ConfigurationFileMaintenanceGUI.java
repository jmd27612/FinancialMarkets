/**
 * The ConfigurationFileMaintenanceGUI class provides a user interface to maintain the configuration file. Due to the
 * encryption of the configuration file provided by the Configuration class, it is not possible to edit the file
 * directly. This GUI is the sole maintenance point. 
 * 
 * @author Justin Dudley
 */

package com.financialMarkets.userInterface;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import com.financialMarkets.Utilities;
import javax.swing.JDesktopPane;
import java.awt.Font;
import java.awt.Color;
import java.awt.Insets;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import java.awt.Cursor;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;

public class ConfigurationFileMaintenanceGUI //extends JFrame
{
	//private static final long serialVersionUID = -5694893118879882025L;
	private JFrame mainFrame; 
	private JDesktopPane desktopPane;
	private JPanel mainPanel; 
	private JTextArea headerTextArea; 
	private JPanel currentKeysPanel; 
	private JList<String> keyList; 
	private JPanel optionsPanel; 
	private JButton newKeyButton; 
	private JButton updateButton; 
	private JButton deleteButton; 
	private JButton exitButton; 
	
	public ConfigurationFileMaintenanceGUI()
	{
		mainFrame = new JFrame(); 
		
		mainPanel = new JPanel();
		mainPanel.setBorder(null);
		mainFrame.setContentPane(mainPanel);
		mainPanel.setLayout(null);
		
		desktopPane = new JDesktopPane();
		desktopPane.setBounds(0, 150, 1, 300);
		mainPanel.add(desktopPane);
		
		mainFrame.setLocationRelativeTo(null); 
		mainFrame.setTitle(Utilities.APPLICATION_NAME + " - Configuration File Maintenance"); 
		mainFrame.setSize(700,500); 
		
		//Header text area
		headerTextArea = new JTextArea();
		headerTextArea.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		headerTextArea.setBounds(10, 10, 664, 75);
		headerTextArea.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		headerTextArea.setOpaque(false);
		headerTextArea.setMargin(new Insets(10, 10, 10, 10));
		headerTextArea.setBackground(Color.LIGHT_GRAY);
		headerTextArea.setFont(new Font("Tahoma", Font.BOLD, 12));
		headerTextArea.setWrapStyleWord(true);
		headerTextArea.setLineWrap(true);
		headerTextArea.setEditable(false);
		headerTextArea.setText("Configuration properties for the application can be added, modified, and deleted here. "
				+ "The configuration maintanence system is unable to display any property values by design. "
				+ "Only the property keys will be shown. To add or modify properties, you must supply the class "
				+ "encryption key. Upon submitting changes, the current configuration will be updated and the "
				+ "configuration XML file will be updated.");
		mainPanel.add(headerTextArea);
		
		//Current Keys Panel
		currentKeysPanel = new JPanel();
		currentKeysPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Current Configuration Property Keys", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		currentKeysPanel.setBounds(66, 132, 232, 262);
		mainPanel.add(currentKeysPanel);
		currentKeysPanel.setLayout(null);
		
		//Key List
		keyList = new JList<String>(populatePropertiesList());
		keyList.setBounds(10, 22, 212, 229);
		currentKeysPanel.add(keyList);
		keyList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		//Options Panel
		optionsPanel = new JPanel();
		optionsPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Options", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		optionsPanel.setBounds(373, 132, 232, 262);
		mainPanel.add(optionsPanel);
		optionsPanel.setLayout(null);
		
		//Create New Key Button
		newKeyButton = new JButton("Create New Key");
		newKeyButton.setBounds(10, 25, 211, 23);
		optionsPanel.add(newKeyButton);
		
		newKeyButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				//TODO
				ConfigurationKeyMaintenanceGUI keyMaintGUI = new ConfigurationKeyMaintenanceGUI(mainFrame); 

			}
		});
		
		//Update Selected Key Button
		updateButton = new JButton("Update Selected Key");
		updateButton.setBounds(10, 59, 211, 23);
		optionsPanel.add(updateButton);
		
		updateButton.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				//TODO
				if(keyList.isSelectionEmpty() == false)
				{
					String keyName = keyList.getSelectedValue(); 
					ConfigurationKeyMaintenanceGUI keyMaintGUI = new ConfigurationKeyMaintenanceGUI(keyName); 
					
				}
				else
				{
					JOptionPane.showMessageDialog(null, "You must select a key to use this option.", "Selection Required", JOptionPane.OK_OPTION); 
				}

			}
		});
		
		//Delete Selected Key Button
		deleteButton = new JButton("Delete Selected Key");
		deleteButton.setBounds(10, 93, 211, 23);
		optionsPanel.add(deleteButton);
		deleteButton.setActionCommand("Delete Selected Key");
		
		deleteButton.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				if(keyList.isSelectionEmpty() == false)
				{
					int reply = JOptionPane.showConfirmDialog(null, "Are you sure you want to permanently delete configuration keys?", 
							"Configuration Key Delete Confirmation", JOptionPane.YES_NO_OPTION);
					if(reply == JOptionPane.YES_OPTION)
					{
						String propertyName = keyList.getSelectedValue(); 
						Utilities.config.deleteProperty(propertyName);
						//TODO update list
					}
				}
				else
				{
					JOptionPane.showMessageDialog(null, "You must select a key to use this option.", "Selection Required", JOptionPane.OK_OPTION); 
				}

			}
		});

		//Exit Button
		exitButton = new JButton("Exit");
		exitButton.setBounds(10, 127, 211, 23);
		optionsPanel.add(exitButton);
		
		exitButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				mainFrame.setVisible(false); 
			}
		});
		
		mainFrame.setVisible(true); 
	}
	
	
	
	/**
	 * Utility method: Retrieves a list of property keys from the current running configuration
	 * @return propertiesList	list of current configuration keys
	 */
	private DefaultListModel<String> populatePropertiesList()
	{
		final DefaultListModel<String> L1 = new DefaultListModel<String>(); 
		
		for(String property : Utilities.config.getPropertiesList()) 
		{
			L1.addElement(property);
		}
		
		return L1; 
	}
}
