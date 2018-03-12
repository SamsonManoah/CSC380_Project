/**********************************************************
 Name        : MenuPanel 
 Author      : Samson Manoah
 Date        : 02//2018
 Description : This is the introduction panel for the GUI.
 **********************************************************/
 
import java.awt.*;
import javax.swing.*;
//import java.util.*; 

public class MenuPanel extends JPanel
{
   public MenuPanel()
   {
      setBackground (Color.green);
      
      System.out.println("\n");//Blank line
      JLabel label_1 = new JLabel("Choose a tab to start a transcation");
      
      add (label_1);
   }  
}