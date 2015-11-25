package com.ardublock.ui;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Menu;
import java.io.File;
import java.io.IOException;
import java.util.ResourceBundle;

import javax.swing.AbstractButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JRootPane;
import javax.swing.MenuElement;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import edu.mit.blocks.controller.WorkspaceController;
import edu.mit.blocks.workspace.Workspace;

import com.ardublock.core.Context;
import com.ardublock.ArduBlockTool;
import com.ardublock.ui.listener.ArdublockWorkspaceListener;
import com.ardublock.ui.listener.CompileCodeButtonListener;
import com.ardublock.ui.listener.GenerateCodeButtonListener;
import com.ardublock.ui.listener.NewButtonListener;
import com.ardublock.ui.listener.OpenButtonListener;
import com.ardublock.ui.listener.SaveButtonListener;
import com.ardublock.ui.listener.SaveAsButtonListener;
import com.ardublock.ui.listener.OpenblocksFrameListener;


public class OpenblocksFrame extends JFrame
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2841155965906223806L;

	private Context context;
	private String saveFilePath;
	private String saveFileName;
	private JFileChooser fileChooser;
	private FileFilter ffilter;
	
	private ResourceBundle uiMessageBundle;
	
	public void addListener(OpenblocksFrameListener ofl)
	{
		context.registerOpenblocksFrameListener(ofl);
	}
	
	public String makeFrameTitle()
	{
		uiMessageBundle = ResourceBundle.getBundle("com/ardublock/block/ardublock");
		
		String title = Context.APP_NAME + " v" + uiMessageBundle.getString("ardublock.ui.version") + " " + saveFileName;
		if (context.isWorkspaceChanged())
		{
			title = title + " *";
		}
		return title;
		
	}
	
	public OpenblocksFrame()
	{
		saveFilePath = null;
		saveFileName = "untitled";
		
		context = Context.getContext();
		this.setTitle(makeFrameTitle());
		this.setSize(new Dimension(800, 600));
		this.setLayout(new BorderLayout());
		//put the frame to the center of screen
		this.setLocationRelativeTo(null);
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		uiMessageBundle = ResourceBundle.getBundle("com/ardublock/block/ardublock");
		
		fileChooser = new JFileChooser();
		ffilter = new FileNameExtensionFilter(uiMessageBundle.getString("ardublock.file.suffix"), "abp");
		fileChooser.setFileFilter(ffilter);
		fileChooser.addChoosableFileFilter(ffilter);
		
		
		initOpenBlocks();
	}
	
	public void initOpenBlocks()
	{
		System.out.println("initOpenBlocks 1");
		Context context = Context.getContext();
		
		/*
		WorkspaceController workspaceController = context.getWorkspaceController();
		JComponent workspaceComponent = workspaceController.getWorkspacePanel();
		*/
		
		Workspace workspace = context.getWorkspace();
		
		// WTF I can't add worksapcelistener by workspace contrller
		workspace.addWorkspaceListener(new ArdublockWorkspaceListener(this));
		
		JPanel buttons = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.weightx = 1;
		c.weighty = 1;
		c.fill = GridBagConstraints.BOTH;
		//JPanel buttons = new JPanel();
		//buttons.setLayout(new FlowLayout());
		//JLabel portLabel = new JLabel("Serial port : " + ArduBlockTool.getCOMName());
		JLabel portLabel = new JLabel("<html>Serial port : <font color='blue'>" + ArduBlockTool.getCOMName() + "</font></html>");
		//System.out.println(ArduBlockTool.getCOMName());
		//portLabel.setForeground (Color.red);
		JLabel boardLabel = new JLabel("<html>Board : <font color='blue'>" + ArduBlockTool.getBoardName() + "</font></html>");
		//System.out.println(ArduBlockTool.getBoardName());
		
		JButton newButton = new JButton(uiMessageBundle.getString("ardublock.ui.new"));
		newButton.addActionListener(new NewButtonListener(this));
		JButton saveButton = new JButton(uiMessageBundle.getString("ardublock.ui.save"));
		saveButton.addActionListener(new SaveButtonListener(this));
		JButton saveAsButton = new JButton(uiMessageBundle.getString("ardublock.ui.saveas"));
		saveAsButton.addActionListener(new SaveAsButtonListener(this));
		JButton openButton = new JButton(uiMessageBundle.getString("ardublock.ui.load"));
		openButton.addActionListener(new OpenButtonListener(this));
		JButton compileButton = new JButton(uiMessageBundle.getString("ardublock.ui.compile"));
		compileButton.addActionListener(new CompileCodeButtonListener(this, context));
		JButton generateButton = new JButton(uiMessageBundle.getString("ardublock.ui.upload"));
		generateButton.addActionListener(new GenerateCodeButtonListener(this, context));
		
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(0,10,0,0);  //top padding
		buttons.add(boardLabel, c);
		c.gridx = c.gridx + 1;
		//c.gridwidth = 1;
		//c.gridheight = 1;
		//c.gridy = 0;
		buttons.add(portLabel, c);
		c.insets = new Insets(0,0,0,0);  //top padding
		c.gridx = c.gridx + 1;
		buttons.add(newButton, c);
		c.gridx = c.gridx + 1;
		buttons.add(openButton, c);
		c.gridx = c.gridx + 1;
		buttons.add(saveButton, c);
		c.gridx = c.gridx + 1;
		buttons.add(saveAsButton, c);
		c.insets = new Insets(0,10,0,0);  //top padding
		c.gridx = c.gridx + 1;
		buttons.add(compileButton, c);
		c.insets = new Insets(0,0,0,0);  //top padding
		c.gridx = c.gridx + 1;
		buttons.add(generateButton, c);
			
		this.add(buttons, BorderLayout.NORTH);
		this.add(workspace, BorderLayout.CENTER);
	}
	
	public void doOpenArduBlockFile()
	{
		if (context.isWorkspaceChanged())
		{
			int optionValue = JOptionPane.showOptionDialog(this, uiMessageBundle.getString("message.content.open_unsaved"), uiMessageBundle.getString("message.title.question"), JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, JOptionPane.YES_OPTION);
			if (optionValue == JOptionPane.YES_OPTION)
			{
				doSaveArduBlockFile();
				this.loadFile();
			}
			else
			{
				if (optionValue == JOptionPane.NO_OPTION)
				{
					this.loadFile();
				}
			}
		}
		else
		{
			this.loadFile();
		}
		this.setTitle(makeFrameTitle());
	}
	
	private void loadFile()
	{
		int result = fileChooser.showOpenDialog(this);
		if (result == JFileChooser.APPROVE_OPTION)
		{
			File savedFile = fileChooser.getSelectedFile();
			if (!savedFile.exists())
			{
				JOptionPane.showOptionDialog(this, uiMessageBundle.getString("message.file_not_found"), uiMessageBundle.getString("message.title.error"), JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, null, JOptionPane.OK_OPTION);
				return ;
			}
			
			saveFilePath = savedFile.getAbsolutePath();
			saveFileName = savedFile.getName();
			try
			{
				this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				context.loadArduBlockFile(savedFile);
				context.setWorkspaceChanged(false);
			}
			catch (IOException e)
			{
				JOptionPane.showOptionDialog(this, uiMessageBundle.getString("message.file_not_found"), uiMessageBundle.getString("message.title.error"), JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, null, JOptionPane.OK_OPTION);
				e.printStackTrace();
			}
			finally
			{
				this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		}
	}
	
	public void doSaveArduBlockFile()
	{
		if (context.isWorkspaceChanged())
		{
			try
			{
				WorkspaceController workspaceController = context.getWorkspaceController();
				String saveString = workspaceController.getSaveString();
				
				if (saveFilePath == null)
				{
					int chooseResult;
					chooseResult = fileChooser.showSaveDialog(this);
					if (chooseResult == JFileChooser.APPROVE_OPTION)
					{
						File saveFile = fileChooser.getSelectedFile();
						saveFile = checkFileSuffix(saveFile);
						if (saveFile != null)
						{
							if (saveFile.exists())
							{
								int optionValue = JOptionPane.showOptionDialog(this, uiMessageBundle.getString("message.content.overwrite"), uiMessageBundle.getString("message.title.question"), JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, JOptionPane.YES_OPTION);
								if (optionValue != JOptionPane.YES_OPTION)
								{
									return ;
								}
							}
							context.saveArduBlockFile(saveFile, saveString);
							saveFilePath = saveFile.getAbsolutePath();
							saveFileName = saveFile.getName();
							context.setWorkspaceChanged(false);
							this.setTitle(this.makeFrameTitle());
							
						}
					}
				}
				else
				{
					File saveFile = new File(saveFilePath);
					context.saveArduBlockFile(saveFile, saveString);
					saveFilePath = saveFile.getAbsolutePath();
					saveFileName = saveFile.getName();
					context.setWorkspaceChanged(false);
					this.setTitle(this.makeFrameTitle());
					
				}
			}
			catch (Exception ex)
			{
				ex.printStackTrace();
			}
		}
	}
	
	public void doSaveAsArduBlockFile()
	{
		if (context.isWorkspaceChanged())
		{
			try
			{
				WorkspaceController workspaceController = context.getWorkspaceController();
				String saveString = workspaceController.getSaveString();
				
				int chooseResult;
				chooseResult = fileChooser.showSaveDialog(this);
				if (chooseResult == JFileChooser.APPROVE_OPTION)
				{
					File saveFile = fileChooser.getSelectedFile();
					saveFile = checkFileSuffix(saveFile);
					if (saveFile != null)
					{
						if (saveFile.exists())
						{
							int optionValue = JOptionPane.showOptionDialog(this, uiMessageBundle.getString("message.content.overwrite"), uiMessageBundle.getString("message.title.question"), JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, JOptionPane.YES_OPTION);
							if (optionValue != JOptionPane.YES_OPTION)
							{
								return ;
							}
						}
						context.saveArduBlockFile(saveFile, saveString);
						saveFilePath = saveFile.getAbsolutePath();
						saveFileName = saveFile.getName();
						context.setWorkspaceChanged(false);
						this.setTitle(this.makeFrameTitle());
						
					}
				}
			}
			catch (Exception ex)
			{
				ex.printStackTrace();
			}
		}
	}
	
	public void doNewArduBlockFile()
	{
		if (context.isWorkspaceChanged())
		{
			int optionValue = JOptionPane.showOptionDialog(this, uiMessageBundle.getString("message.question.newfile_on_workspace_changed"), uiMessageBundle.getString("message.title.question"), JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, JOptionPane.YES_OPTION);
			if (optionValue != JOptionPane.YES_OPTION)
			{
				return ;
			}
		}
		this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		context.resetWorksapce();

		context.setWorkspaceChanged(false);
		this.setTitle(this.makeFrameTitle());
		this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		
		//changeBoardName("titi", "tutu");
	}
	
	private File checkFileSuffix(File saveFile)
	{
		String filePath = saveFile.getAbsolutePath();
		if (filePath.endsWith(".abp"))
		{
			return saveFile;
		}
		else
		{
			return new File(filePath + ".abp");
		}
	}
	
	public void changeBoardName(String boardName, String portName)
	{
		//System.out.println(boardName);
		//System.out.println(portName);
		
		Component[] elementsPane = this.getLayeredPane().getComponents();
		if (elementsPane[0].getComponentAt(0, getDefaultCloseOperation()) instanceof JPanel) {
			JPanel panel = (JPanel) elementsPane[0].getComponentAt(0, getDefaultCloseOperation());
			JLabel portLabel = new JLabel("<html>port : <font color='blue'>" + portName + "</font></html>");
			JLabel boardLabel = new JLabel("<html>Board : <font color='red'>" + boardName + "</font></html>");
			
			panel.remove(0);
			panel.remove(0);
			GridBagConstraints c = new GridBagConstraints();
			c.weightx = 1;
			c.weighty = 1;
			c.fill = GridBagConstraints.BOTH;
			c.gridx = 0;
			c.gridy = 0;
			c.insets = new Insets(0,10,0,0);  //top padding
			panel.add(boardLabel, c, 0);
			c.gridx = c.gridx + 1;
			panel.add(portLabel, c, 1);
			panel.repaint();
			
			this.add(panel, BorderLayout.NORTH);
			this.revalidate();
			this.repaint();
		}
		
		
		//context.setWorkspaceChanged(false);
	}
}



