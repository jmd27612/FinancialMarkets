/**
 * The ConfigurationKeyMaintenanceGUI class provides a simple user interface for entering the
 * key name, key value, and class key for adding and modifying configuration properties.
 * 
 * @author Justin Dudley
 */

package com.financialMarkets.userInterface;

import java.awt.Dialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JWindow;

import com.financialMarkets.Utilities;

public class ConfigurationKeyMaintenanceGUI extends JDialog
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
	private JLabel keyNameLengthLabel; 
	private JLabel keyValueLengthLabel; 
	private JLabel classKeyLengthLabel; 
	private JButton cancelButton; 
	private JButton submitButton; 
	private JFrame parentFrame; 
	
	
	ConfigurationKeyMaintenanceGUI(JFrame parentFrame)
	{
		super(parentFrame); 
		this.parentFrame = parentFrame; 
		createFrame(); 
		setLocationRelativeTo(parentFrame); 
		
	}
	
	ConfigurationKeyMaintenanceGUI(String keyName)
	{
		createFrame(); 
		keyNameField.setText(keyName);
		keyNameField.setEditable(false);
		keyNameLengthLabel.setText(Integer.toString(keyNameField.getText().length()));
		
		//TODO change submit text if an update
	}
	
	private void createFrame()
	{
		mainPanel = new JPanel(); 
		mainPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(), BorderFactory.createLoweredBevelBorder())); 
		setContentPane(mainPanel); 

		setModalityType(Dialog.ModalityType.DOCUMENT_MODAL); 
		mainPanel.setLayout(null);
		
		desktopPane = new JDesktopPane(); 
		desktopPane.setBounds(0, 525, 1, 300);
		mainPanel.add(desktopPane); 
		
		setLocationRelativeTo(parentFrame); 
		//setTitle(Utilities.APPLICATION_NAME + " - Configuration Key Maintenance"); 
		setSize(510, 275); 
		
		//Key Name Label
		keyNameLabel = new JLabel("Key Name: "); 
		keyNameLabel.setBounds(25, 15, 150, 50);
		mainPanel.add(keyNameLabel); 
		
		//Key Name Text Field
		keyNameField = new JTextField(); 
		keyNameField.setBounds(100, 27, 300, 25);
		mainPanel.add(keyNameField); 
		
		//Key Name Length Label
		keyNameLengthLabel = new JLabel("0"); 
		keyNameLengthLabel.setBounds(450, 27, 20, 25);
		mainPanel.add(keyNameLengthLabel); 
		
		keyNameField.addKeyListener(new KeyListener()
		{
			@Override
			public void keyPressed(KeyEvent e)
			{
				//No action to perform
			}

			@Override
			public void keyReleased(KeyEvent e)
			{
				keyNameLengthLabel.setText(Integer.toString(keyNameField.getText().length()));
			}
			
			@Override
			public void keyTyped(KeyEvent arg0)
			{
				//No action to perform
			}
		});
		
		//Key Value Label
		keyValueLabel = new JLabel("Key Value: ");
		keyValueLabel.setBounds(25, 60, 150, 50);
		mainPanel.add(keyValueLabel); 
		
		//Key Value Password Field
		keyValueField = new JPasswordField(); 
		keyValueField.setBounds(100, 72, 300, 25); 
		mainPanel.add(keyValueField); 
		
		//Key Value Length Label
		keyValueLengthLabel = new JLabel("0"); 
		keyValueLengthLabel.setBounds(450, 72, 20, 25);
		mainPanel.add(keyValueLengthLabel); 
		
		keyValueField.addKeyListener(new KeyListener()
		{
			@Override
			public void keyPressed(KeyEvent e)
			{
				//No action to perform
			}

			@Override
			public void keyReleased(KeyEvent e)
			{
				keyValueLengthLabel.setText(Integer.toString(keyValueField.getPassword().length));
			}

			@Override
			public void keyTyped(KeyEvent e)
			{
				//No action to perform
			}
		});
		
		//Class Key Label
		classKeyLabel = new JLabel("Class Key: "); 
		classKeyLabel.setBounds(25, 105, 150, 50); 
		mainPanel.add(classKeyLabel); 
		
		//Class Key Password Field
		classKeyField = new JPasswordField(); 
		classKeyField.setBounds(100, 118, 300, 25); 
		mainPanel.add(classKeyField);
		
		//Class Key Length Label
		classKeyLengthLabel = new JLabel("0"); 
		classKeyLengthLabel.setBounds(450, 118, 20, 25);
		mainPanel.add(classKeyLengthLabel); 
		
		classKeyField.addKeyListener(new KeyListener()
		{
			@Override
			public void keyPressed(KeyEvent e)
			{
				//No action to perform
			}

			@Override
			public void keyReleased(KeyEvent e)
			{
				classKeyLengthLabel.setText(Integer.toString(classKeyField.getPassword().length));
			}

			@Override
			public void keyTyped(KeyEvent e)
			{
				//No action to perform
			}
		});
		
		//Cancel Button
		cancelButton = new JButton("Cancel"); 
		cancelButton.setBounds(50, 175, 175, 25);
		mainPanel.add(cancelButton); 
		
		cancelButton.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				setVisible(false); 
			}
		});
		
		//Submit Button
		submitButton = new JButton("Add Key"); 
		submitButton.setBounds(260, 175, 175, 25);
		mainPanel.add(submitButton); 
		
		
		setVisible(true); 
	}
	
	
}
