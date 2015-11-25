package com.ardublock;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import com.ardublock.core.Context;

import processing.app.tools.Tool;
import processing.app.Base;
import processing.app.Editor;
import processing.app.Preferences;

import com.ardublock.ui.ArduBlockToolFrame;
import com.ardublock.ui.listener.OpenblocksFrameListener;


public class ArduBlockTool implements Tool, OpenblocksFrameListener, MenuListener
{
	static Editor editor;
	static ArduBlockToolFrame openblocksFrame;
	public static JMenu serialMenu;
	
	static Base base;

	static private JMenu menuTools;
	static private String selectedPort = ArduBlockTool.getCOMName();
	static private String selectedBoard = ArduBlockTool.getBoardName();
	static public String menuDeroulant = "";
	
	private static JFileChooser chooser = new JFileChooser();
	public static List<String> libraryList = new LinkedList<String>();
	
	class SerialMenuListener implements ActionListener {
		private final String serialPort;
		
		public SerialMenuListener(String serialPort) {
			this.serialPort = serialPort;
		}
		
		public void actionPerformed(ActionEvent e) {
			//System.out.println(serialPort);
			selectedPort = serialPort;
	    	ArduBlockTool.openblocksFrame.changeBoardName(selectedBoard, selectedPort);
		}
	}
	
	public void init(Editor editor) {
		if (ArduBlockTool.editor == null ) {
			ArduBlockTool.editor = editor;
			ArduBlockTool.openblocksFrame = new ArduBlockToolFrame();
			ArduBlockTool.openblocksFrame.addListener(this);
			Context context = Context.getContext();
			String arduinoVersion = ArduBlockTool.getArduinoVersion();
			context.setInArduino(true);
			context.setArduinoVersionString(arduinoVersion);
			context.setEditor(editor);
			//System.out.println("Arduino Version: " + arduinoVersion);
			
		}
	}

	public void run() {
		try {
			ArduBlockTool.editor.toFront();
			ArduBlockTool.openblocksFrame.setVisible(true);
			ArduBlockTool.openblocksFrame.toFront();
			
			menuTools = editor.getJMenuBar().getMenu(3);
			File libraryDir = Base.getContentFile("libraries");
			listDir(libraryDir);
			
			
//			for(String model : libraryList) {
//	            System.out.println(model);
//	        }
			/*int nbreComponent = editor.getRootPane().getComponentCount();
			System.out.println(nbreComponent);
			for (Component c : editor.getRootPane().getComponents()) {
				System.out.println(c.getName());
			}
			
			nbreComponent = editor.getRootPane().getLayeredPane().getComponentCount();
			System.out.println(nbreComponent);
			for (Component c : editor.getRootPane().getLayeredPane().getComponents()) {
				System.out.println(c.getName());
				System.out.println(c.toString());
			}
			nbreComponent = editor.getRootPane().getContentPane().getComponentCount();
			System.out.println(nbreComponent);
			for (Component c : editor.getRootPane().getContentPane().getComponents()) {
				System.out.println(c.getName());
				System.out.println(c.toString());
			}
			
			JPanel panel = (JPanel) editor.getRootPane().getContentPane().getComponent(0);
			nbreComponent = panel.getComponentCount();
			System.out.println(nbreComponent);
			for (Component c : panel.getComponents()) {
				System.out.println(c.getName());
				System.out.println(c.toString());
			}
			
			Box box = (Box) panel.getComponent(0);
			nbreComponent = box.getComponentCount();
			System.out.println(nbreComponent);
			for (Component c : box.getComponents()) {
				System.out.println(c.getName());
				System.out.println(c.toString());
			}
			
			JSplitPane splitPane = (JSplitPane) box.getComponent(0);
			nbreComponent = splitPane.getComponentCount();
			System.out.println(nbreComponent);
			for (Component c : splitPane.getComponents()) {
				System.out.println(c.getName());
				System.out.println(c.toString());
			}
			
			Box box2 = (Box) splitPane.getComponent(0);
			nbreComponent = box2.getComponentCount();
			System.out.println(nbreComponent);
			for (Component c : box2.getComponents()) {
				System.out.println(c.getName());
				System.out.println(c.toString());
			}
			
			JPanel panel2 = (JPanel) splitPane.getComponent(1);
			nbreComponent = panel2.getComponentCount();
			System.out.println(nbreComponent);
			for (Component c : panel2.getComponents()) {
				System.out.println(c.getName());
				System.out.println(c.toString());
			}
			
			EditorLineStatus linestatus = (EditorLineStatus) panel2.getComponent(2);
			//JComponent linestatusComponent = (JComponent) panel2.getComponent(2);
			
			//linestatus.addInputMethodListener(this);
			//linestatus.addComponentListener(this);
			//linestatusComponent.addAncestorListener(this);
			//linestatus.addFocusListener(this);
			//linestatusComponent.addContainerListener(this);
			//linestatus.addPropertyChangeListener(this);
			System.out.println(linestatus.getToolkit().toString());
			//linestatusComponent.addVetoableChangeListener(this);
			System.out.println(linestatus.getName());
			System.out.println(linestatus.toString());
			Graphics tyty = linestatus.getGraphics();
			System.out.println("linestatus.toString = " + tyty.toString());
			/*AccessibleContext accContext = linestatus.getAccessibleContext();//.addPropertyChangeListener(this);
			System.out.println(accContext.getAccessibleText().getSelectedText());
			System.out.println(accContext.getAccessibleDescription().toString());
			System.out.println(accContext.getAccessibleName().toString());
			System.out.println(accContext.toString());
			System.out.println(accContext.getAccessibleEditableText().getSelectedText());
			System.out.println(accContext.getAccessibleValue().getCurrentAccessibleValue());
			System.out.println(accContext.getAccessibleComponent().toString());
			nbreComponent = linestatus.getComponentCount();
			System.out.println(nbreComponent);
			for (Component c : linestatus.getComponents()) {
				System.out.println(c.getName());
				System.out.println(c.toString());
			}
			
					
			// donne la valeur � droite avec 
			//layout.layoutContainer(RIGHT_ALIGNMENT);
			
			
			//Class<?> toto = editor.getClass();
			*/

			//menuTools.addMenuListener(this);
			for (Component c : menuTools.getMenuComponents()) {
				if (c instanceof JMenu && c.isVisible()) {
					JMenu menu = (JMenu)c;
					menu.addMenuListener(this);
					//menu.addActionListener(this);//.addMouseListener(this);
					int count = menu.getItemCount();
					//System.out.println(count);
					for (int i=0; i < count; i++) {
						JMenuItem item = menu.getItem(i);
						/*if (item instanceof JMenuItem) {

							//item.addMouseListener(this);
							//item.addChangeListener(this);
							//item.addActionListener(this);
							//item.addItemListener(this);
							System.out.println("JMenuItem " + item.getLabel());
							System.out.println("JMenuItem " + item.isSelected());
							menuDeroulant = menuDeroulant + item.getLabel();
							menuDeroulant = menuDeroulant + item.isSelected();		
						}*/
						if (item instanceof JRadioButtonMenuItem) {
							final JRadioButtonMenuItem itemRB = (JRadioButtonMenuItem)item;
							itemRB.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent e) {
									selectedBoard = itemRB.getText();
							    	ArduBlockTool.openblocksFrame.changeBoardName(selectedBoard, selectedPort);
								}
							});
						}
						if ((item instanceof JCheckBoxMenuItem)) {
							JCheckBoxMenuItem itemCB = (JCheckBoxMenuItem)item;
							itemCB.addActionListener(new SerialMenuListener(itemCB.getText()));
							
							/*for (ItemListener il : itemCB.getItemListeners()) {
								System.out.println("ItemListener " + il.toString());
							}
							for (ActionListener il : itemCB.getActionListeners()) {
								System.out.println("ActionListener " + il.getClass());
								//itemCB.addActionListener(il);
								System.out.println("ActionListener " + il.toString());
							}
							for (ChangeListener il : itemCB.getChangeListeners()) {
								System.out.println("ChangeListener " + il.toString());
							}*/
						}
						/*for (ItemListener il : item.getItemListeners()) {
							System.out.println(il.toString());
						}
						for (ActionListener il : item.getActionListeners()) {
							System.out.println(il.toString());
						}
						for (ChangeListener il : item.getChangeListeners()) {
							System.out.println(il.toString());
						}*/
					}
					
				}
			}
			
		} catch (Exception e) {
			
		}
	}
	


	public String getMenuTitle() {
		return Context.APP_NAME;
	}

	public void didSave() {
		System.out.println("saved");
	}

	public void didSaveAs()	{
		
	}
	
	public void didNew()	{
		
	}
	
	public void didLoad() {
		
	}
	
	public void didCompile(String source) {
		ArduBlockTool.editor.setText(source);
	}
	
	public void didGenerate(String source) {
		ArduBlockTool.editor.setText(source);
		ArduBlockTool.editor.handleExport(false);
	}
	
	public static String getBoardName() {
		try {
			String bName = Preferences.get("board");
			if (bName == null) {
				bName = "uno";
			}
			return bName;
			
		} catch (Exception e) {
			return null;
		}
		
	}

	public static String getCOMName() {
		try {
			String comName = Preferences.get("serial.port");
			if (comName == null) {
				comName = "COM1";
			}
			return comName;
			
		} catch (Exception e) {
			return null;
		}
	}
	
	public static String getArduinoVersion()
	{
		Context context = Context.getContext();
		File versionFile = context.getArduinoFile("lib/version.txt");
		if (versionFile.exists())
		{
			try
			{
				InputStream is = new FileInputStream(versionFile);
				BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
				String line = reader.readLine();
				reader.close();
				if (line == null)
				{
					return Context.ARDUINO_VERSION_UNKNOWN;
				}
				line = line.trim();
				if (line.length() == 0)
				{
					return Context.ARDUINO_VERSION_UNKNOWN;
				}
				return line;
				
			}
			catch (FileNotFoundException e)
			{
				return Context.ARDUINO_VERSION_UNKNOWN;
			}
			catch (UnsupportedEncodingException e)
			{
				return Context.ARDUINO_VERSION_UNKNOWN;
			}
			catch (IOException e)
			{
				e.printStackTrace();
				return Context.ARDUINO_VERSION_UNKNOWN;
			}
		}
		else
		{
			return Context.ARDUINO_VERSION_UNKNOWN;
		}
		
	}
	
	public static void listDir(File dir) {
        //if (chooser.getTypeDescription(dir).equals("C compiler header file")) {
		// be careful : this way of doing is no longer use
		// header of C file don't have the same description at anytime
		// "C compiler header file" on XP ; "H file" on WIN 7
		if (getExtension(dir.getPath()).equals("h")) {
        	libraryList.add(dir.getName());
        }
        if ( dir.isDirectory ( ) ) {
        	File[] list = dir.listFiles();
        	if (list != null){
        		for ( int i = 0; i < list.length; i++) {
        			// Appel r�cursif sur les sous-r�pertoires
        			listDir( list[i]);
        		} 
        	} else {
        		System.err.println(dir + " : Erreur de lecture.");
        	}
        } 
	} 

	public void menuCanceled(MenuEvent arg0) {
		// TODO Auto-generated method stub
	}

	public void menuDeselected(MenuEvent arg0) {
		// TODO Auto-generated method stub
	}

	public void menuSelected(MenuEvent arg0) {
		// TODO Auto-generated method stub
		for (Component c : menuTools.getMenuComponents()) {
			if (c instanceof JMenu && c.isVisible()) {
				JMenu menu = (JMenu)c;
				int count = menu.getItemCount();
				for (int i=0; i < count; i++) {
					JMenuItem item = menu.getItem(i);
					if ((item instanceof JCheckBoxMenuItem)) {
						JCheckBoxMenuItem itemCB = (JCheckBoxMenuItem)item;
						for (ActionListener il : itemCB.getActionListeners()) {
							if (il.toString().contains("com.ardublock.ArduBlockTool$SerialMenuListener")) {
								itemCB.removeActionListener(il);
							}	
						}
						itemCB.addActionListener(new SerialMenuListener(itemCB.getText()));
					}
				}
			}
		}
	}
	
	public static String getExtension(String filename) {
        if (filename == null) {
            return null;
        }
        int extensionPos = filename.lastIndexOf('.');
        int lastUnixPos = filename.lastIndexOf('/');
        int lastWindowsPos = filename.lastIndexOf('\\');
        int lastSeparator = Math.max(lastUnixPos, lastWindowsPos);
 
        int index = lastSeparator > extensionPos ? -1 : extensionPos;
        if (index == -1) {
            return "";
        } else {
            return filename.substring(index + 1);
        }
    }


}