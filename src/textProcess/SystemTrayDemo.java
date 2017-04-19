package textProcess;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import javax.swing.JOptionPane;

public class SystemTrayDemo{
	
	public static String currentPaper = "« ENHANCING QUALITY OF SERVICE IN SOFTWARE-DEFINED NETWORKS » ";

//start of main method
public static void main(String []args) throws FileNotFoundException{
	
	System.setOut(new PrintStream(new FileOutputStream("text_process_log.txt")));
	
	System.out.println("Begin > ");
	
    //checking for support
    if(!SystemTray.isSupported()){
        System.out.println("System tray is not supported !!! ");
        return ;
    }
    
    //get the systemTray of the system
    SystemTray systemTray = SystemTray.getSystemTray();

    //get default toolkit
    //Toolkit toolkit = Toolkit.getDefaultToolkit();
    //get image 
    //Toolkit.getDefaultToolkit().getImage("src/resources/busylogo.jpg");
    Image image = Toolkit.getDefaultToolkit().getImage("icos.png");

    //popupmenu
    PopupMenu trayPopupMenu = new PopupMenu();

    //1t menuitem for popupmenu
    MenuItem action = new MenuItem("Text");
    action.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
        	setClipBoard(processText(getClipBoard(), false));
        	System.out.println("clipset!!");
        }
    });     
    trayPopupMenu.add(action);

    //2nd menuitem for popupmenu
    MenuItem actionWithEnd = new MenuItem("Text + Ref");
    actionWithEnd.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
        	setClipBoard(processText(getClipBoard(), true));
        	System.out.println("action With End !!");
        }
    });     
    trayPopupMenu.add(actionWithEnd);
    
    //3rd menuitem for popupmenu
    MenuItem changePaper = new MenuItem("set Paper Bib link");
    changePaper.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
        	setClipBoard(processText(getClipBoard(), true));
        	System.out.println("set Paper Bib link !!");
        }
    });     
    trayPopupMenu.add(changePaper);
    
    //4th menuitem of popupmenu
    MenuItem close = new MenuItem("Close");
    close.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);             
        }
    });
    trayPopupMenu.add(close);

    //setting tray icon
    TrayIcon trayIcon = new TrayIcon(image, "SystemTray Demo", trayPopupMenu);
    //adjust to default size as per system recommendation 
    trayIcon.setImageAutoSize(true);

    try{
        systemTray.add(trayIcon);
    }catch(AWTException awtException){
        awtException.printStackTrace();
    } 

}//end of main


public static String processText(String txt , boolean add){
	
	String[] output = txt.split("\n");
	
	String result = "" ;
	
	int a = 0 ;
	
	for(int i=0; i < output.length ; i++){
		output[i] = output[i].replace('\r', ' ');
		output[i] = output[i].trim();
		
		
		if(!output[i].equals("")){
			
	    System.out.println(a + " ----" + output[i]+"-----");
		if ( (output[i].charAt(output[i].length()-1) == '.') || (output[i].charAt(output[i].length()-1) == ':') ) result = result + output[i] + '\r' + '\n';
		else result = result + output[i] + ' ';
		
		a++;
		
		}
	}
	
	if (add) return result + "\r\n________________________________________________________________\r\n" + currentPaper ;
	else return result ;
}

public static String getClipBoard(){
	
    try {
        return (String)Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor);
    } catch (HeadlessException e) {
        e.printStackTrace();            
    } catch (UnsupportedFlavorException e) {
        e.printStackTrace();            
    } catch (IOException e) {
        e.printStackTrace();
    }
    
    return "";
}

public static void setClipBoard(String txt){
	StringSelection selection = new StringSelection(txt);
    Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();  
    clipboard.setContents(selection, selection);
}

}//end of class
