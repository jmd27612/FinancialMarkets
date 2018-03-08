/**
 * The ConfigKeyMaintGUI class provides a simple user interface for entering the
 * key name, key value, and class key for adding and modifying configuration properties.
 * 
 * @author Justin Dudley
 */

package com.financialMarkets.userInterface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.logging.Logger;
import java.awt.Dialog;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import com.financialMarkets.Utilities;

public class ConfigKeyMaintGUI extends JDialog
{
	private static final long serialVersionUID = 8263805047853289347L;
	private Logger localLogger; 
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
	boolean isEdit; 
	String keyName; 
	
	//Constructor for adding a new key
	ConfigKeyMaintGUI(JFrame parentFrame)
	{
		super(parentFrame); 
		isEdit = false; 
		this.parentFrame = parentFrame; 
		createFrame(); 
		setLocationRelativeTo(parentFrame); 
	}
	
	//Constructor for editing an existing key
	ConfigKeyMaintGUI(String keyName, JFrame parentFrame)
	{
		super(parentFrame); 
		isEdit = true; 
		this.keyName = keyName; 
		this.parentFrame = parentFrame; 
		setLocationRelativeTo(parentFrame); 
		createFrame(); 
	}
	
	private void createFrame()
	{
		//Initialize LOGGER
		localLogger = Logger.getLogger("com.financialMarkets.userInterface.ConfigKeyMaintGUI"); 
		
		mainPanel = new JPanel(); 
		mainPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(), BorderFactory.createLoweredBevelBorder())); 
		setContentPane(mainPanel); 

		//Document Modality locks the parent window until current window is closed
		setModalityType(Dialog.ModalityType.DOCUMENT_MODAL); 
		mainPanel.setLayout(null);
		
		desktopPane = new JDesktopPane(); 
		desktopPane.setBounds(0, 525, 1, 300);
		mainPanel.add(desktopPane); 
		
		//Location relative to parent keeps the window from opening in the top corner of the screen
		setLocationRelativeTo(parentFrame); 
		setTitle(Utilities.APPLICATION_NAME + " - Configuration Key Maintenance"); 
		setSize(510, 275); 
		
		//Key Name Label
		keyNameLabel = new JLabel("Key Name: "); 
		keyNameLabel.setBounds(25, 15, 150, 50);
		mainPanel.add(keyNameLabel); 
		
		//Key Name Text Field
		keyNameField = new JTextField(); 
		keyNameField.setBounds(100, 27, 300, 25);
		mainPanel.add(keyNameField); 
		
		//If an edit, fill in the key name automatically. Set to not allow editing 
		if(isEdit)
		{
			keyNameField.setText(keyName);
			keyNameField.setEditable(false);
		}
		
		//Key Name Length Label
		keyNameLengthLabel = new JLabel("0"); 
		keyNameLengthLabel.setBounds(450, 27, 50, 25);
		mainPanel.add(keyNameLengthLabel); 
		
		//Key Listener for Key Name Length Field
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
				lengthVerification(); 
			}
			
			@Override
			public void keyTyped(KeyEvent arg0)
			{
				//No action to perform
			}
		});
		
		//If an edit, manually update the length field
		if(isEdit)
		{
			keyNameLengthLabel.setText(Integer.toString(keyName.length()));
		}
		
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
		keyValueLengthLabel.setBounds(450, 72, 50, 25);
		mainPanel.add(keyValueLengthLabel); 
		
		//Key Listener for Key Value Field
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
				lengthVerification(); 
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
		classKeyLengthLabel.setBounds(450, 118, 50, 25);
		mainPanel.add(classKeyLengthLabel); 
		
		//Key Listener for Class Key Field
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
				lengthVerification(); 
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
		
		//Action Listener for Cancel Button
		cancelButton.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				localLogger.info("Cancel Button clicked"); 
				setVisible(false); 
			}
		});
		
		//Submit Button
		submitButton = new JButton("Add Key"); 
		submitButton.setBounds(260, 175, 175, 25);
		submitButton.setEnabled(false);
		mainPanel.add(submitButton); 
		
		//If this is an edit, the submit button says "Change Key" instead. 
		if(isEdit)
		{
			submitButton.setText("Change Key");
		}
		
		//Action Listener for Submit Button
		submitButton.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				localLogger.info("Submit Button clicked");
				Utilities.CONFIG.setProperty(keyNameField.getText(), keyValueField.getPassword(), classKeyField.getPassword());
				setVisible(false); 
			}
		});
		
		
		setVisible(true); 
	}
	
	/**
	 * Utility method: Enables and disables the submit key based on the length of the input fields. 
	 */
	private void lengthVerification()
	{
		if(classKeyField.getPassword().length == 512 && keyNameField.getText().length() != 0
				&& keyValueField.getPassword().length > 1)
		{
			submitButton.setEnabled(true);
		}
		else
		{
			submitButton.setEnabled(false);
		}
	}
	
	
}
