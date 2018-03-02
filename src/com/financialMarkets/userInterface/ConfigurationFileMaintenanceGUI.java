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

public class ConfigurationFileMaintenanceGUI extends JFrame
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5694893118879882025L;
	private JDesktopPane desktopPane;
	
	public ConfigurationFileMaintenanceGUI()
	{
		JPanel mainPanel = new JPanel();
		mainPanel.setBorder(null);
		setContentPane(mainPanel);
		mainPanel.setLayout(null);
		
		desktopPane = new JDesktopPane();
		desktopPane.setBounds(0, 150, 1, 311);
		mainPanel.add(desktopPane);
		
		JTextArea txtrConfigurationPropertiesFor = new JTextArea();
		txtrConfigurationPropertiesFor.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		txtrConfigurationPropertiesFor.setBounds(10, 11, 664, 74);
		txtrConfigurationPropertiesFor.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		txtrConfigurationPropertiesFor.setOpaque(false);
		txtrConfigurationPropertiesFor.setMargin(new Insets(10, 10, 10, 10));
		txtrConfigurationPropertiesFor.setBackground(Color.LIGHT_GRAY);
		txtrConfigurationPropertiesFor.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtrConfigurationPropertiesFor.setWrapStyleWord(true);
		txtrConfigurationPropertiesFor.setLineWrap(true);
		txtrConfigurationPropertiesFor.setEditable(false);
		txtrConfigurationPropertiesFor.setText("Configuration properties for the application can be added, modified, and deleted here. "
				+ "The configuration maintanence system is unable to display any property values by design. "
				+ "Only the property keys will be shown. To add or modify properties, you must supply the class "
				+ "encryption key. Upon submitting changes, the current configuration will be updated and the "
				+ "configuration XML file will be updated.");
		mainPanel.add(txtrConfigurationPropertiesFor);
		
		populatePropertiesList();
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Options", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(373, 132, 232, 262);
		mainPanel.add(panel_1);
		panel_1.setLayout(null);
		
		JButton btnDeleteKey = new JButton("Delete Selected Key");
		btnDeleteKey.setBounds(10, 93, 211, 23);
		panel_1.add(btnDeleteKey);
		btnDeleteKey.setActionCommand("Delete Selected Key");
		
		JButton btnNewButton = new JButton("Update Selected Key");
		btnNewButton.setBounds(10, 59, 211, 23);
		panel_1.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Create New Key");
		btnNewButton_1.setBounds(10, 25, 211, 23);
		panel_1.add(btnNewButton_1);
		
		JButton btnExit = new JButton("Exit");
		btnExit.setBounds(10, 127, 211, 23);
		panel_1.add(btnExit);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Current Configuration Property Keys", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(66, 132, 232, 262);
		mainPanel.add(panel);
		panel.setLayout(null);
		
		JList<String> list = new JList<String>(populatePropertiesList());
		list.setBounds(10, 22, 212, 229);
		panel.add(list);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		

		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnDeleteKey.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				if(list.isSelectionEmpty() == false)
				{
					int reply = JOptionPane.showConfirmDialog(null, "Are you sure you want to permanently delete configuration keys?", 
							"Configuration Key Delete Confirmation", JOptionPane.YES_NO_OPTION);
					if(reply == JOptionPane.YES_OPTION)
					{
						String propertyName = list.getSelectedValue(); 
						Utilities.config.deleteProperty(propertyName);
						//TODO update list
					}
				}

			}
		});
		

		
		setTitle(Utilities.APPLICATION_NAME + " - Configuration File Maintenance"); 
		setSize(700,500); 
		
		
		

		
		setVisible(true); 
	}
	

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
