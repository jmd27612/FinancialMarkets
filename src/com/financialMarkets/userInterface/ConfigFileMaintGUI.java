/**
 * The ConfigFileMaintGUI class provides a user interface to maintain the configuration file. Due to the
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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import java.util.logging.Logger;

public class ConfigFileMaintGUI
{
	private Logger localLogger; 
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
	
	public ConfigFileMaintGUI()
	{
		Utilities.logger.entering("ConfigFileMaintGUI", "ConfigFileMaintGUI");
		
		//Initialize logger
		localLogger = Logger.getLogger("com.financialMarkets.userInterface.ConfigFileMaintGUI"); 
		
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
		
		//Action listener for New Key Button
		//Calls Configuration Key Maintenance GUI
		newKeyButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				localLogger.info("New Key Button clicked");
				new ConfigKeyMaintGUI(mainFrame); 
			}
		});
		
		//Update Selected Key Button
		updateButton = new JButton("Update Selected Key");
		updateButton.setBounds(10, 59, 211, 23);
		optionsPanel.add(updateButton);
		
		//Action listener for Update Key Button
		//Calls Configuration Key Maintenance GUI
		updateButton.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				localLogger.info("Update Key Button clicked"); 
				if(keyList.isSelectionEmpty() == false)
				{
					String keyName = keyList.getSelectedValue(); 
					new ConfigKeyMaintGUI(keyName, mainFrame); 
					
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
		
		//Action listener for Delete Key Button
		deleteButton.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				localLogger.info("Delete Key Button clicked"); 
				if(keyList.isSelectionEmpty() == false)
				{
					int reply = JOptionPane.showConfirmDialog(null, "Are you sure you want to permanently delete configuration keys?", 
							"Configuration Key Delete Confirmation", JOptionPane.YES_NO_OPTION);
					if(reply == JOptionPane.YES_OPTION)
					{
						String propertyName = keyList.getSelectedValue(); 
						localLogger.warning("User requests delete configuration key - " + propertyName);
						Utilities.config.deleteProperty(propertyName);
						updateKeyList(); 
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
		
		//Action listener for Exit Button
		exitButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				localLogger.info("Exit Button clicked");
				mainFrame.setVisible(false); 
			}
		});
		
		//List Updater
		mainFrame.addWindowListener(new WindowAdapter()
		{
			@Override
			public void windowActivated(WindowEvent e)
			{
				localLogger.info("GUI regained focus, keys may be out of date. Requesting refresh.");
				updateKeyList(); 
			}
		});
		
		
		mainFrame.setVisible(true); 
	}
	
	/**
	 * Utility method: Updates the key list on the GUI
	 */
	private void updateKeyList()
	{
		localLogger.entering("ConfigFileMaintGUI", "updateKeyList");
		localLogger.info("Updating Key List");
		keyList.setModel(populatePropertiesList());
	}
	
	
	/**
	 * Utility method: Retrieves a list of property keys from the current running configuration
	 * @return propertiesList	list of current configuration keys
	 */
	private DefaultListModel<String> populatePropertiesList()
	{
		localLogger.entering("ConfigFileMaintGUI", "populatePropertiesList");
		
		final DefaultListModel<String> L1 = new DefaultListModel<String>(); 
		
		for(String property : Utilities.config.getPropertiesList()) 
		{
			L1.addElement(property);
		}
		
		return L1; 
	}
}
