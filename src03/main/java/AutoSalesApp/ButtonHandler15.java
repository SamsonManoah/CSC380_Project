package AutoSalesApp;
/****************************************************************
 Name        : ButtonHandler15
 Author      : Samson Manoah
 Date        : 02//2018
 Description : This class implements an Action Listener for delete an 
 auto button in auto panel.
 ****************************************************************/

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.*;

//Button event handler
public class ButtonHandler15 implements ActionListener
{
	//Global variables
   private static final String sql = "DELETE FROM AUTO WHERE VIN_NUMBER=?";
   private static JFrame optionFrame = new JFrame();
   private static JFrame deleteAutoFrame = new JFrame("Delete An Auto");
   private static JPanel deleteAutoPanel = new JPanel();
   private static JTextField vinNumTxtField = new JTextField(12);
   private static JLabel vinNumLabel = new JLabel("VIN #:");
   private static JButton deleteButton = new JButton("Delete");

	//Handle button event
	public void actionPerformed(ActionEvent event)
	{
      //Add label and text field to panel
      deleteAutoPanel.add(vinNumLabel);
      deleteAutoPanel.add(vinNumTxtField);
      
      //Add delete button to panel         
      deleteAutoPanel.add(deleteButton);
         
      //Add panel to frame and show         
      deleteAutoFrame.add(deleteAutoPanel,BorderLayout.CENTER);
      deleteAutoFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      deleteAutoFrame.setSize(550,200);
      deleteAutoFrame.setVisible(true);
      
      //Create Button Handler and add Action Listener   
      deleteAuto deleteHandler = new deleteAuto();
      deleteButton.addActionListener(deleteHandler);
      		
	}//End actionPerformed
   
   private class deleteAuto implements ActionListener
   {
      //Handle button event
	   public void actionPerformed(ActionEvent event)
	   {      
		   //Connect to Database
         int i;
		   try(Connection myConnection =  DriverManager.getConnection(
		   "jdbc:mysql://localhost:3306/auto_sales?useSSL=false","root", "Mourwel0*");
	      PreparedStatement s = myConnection.prepareStatement(sql);)
		   {
            //Declare JDBC driver, Set parameters and execute delete auto
            Class.forName("com.mysql.jdbc.Driver");
            s.setString(1,vinNumTxtField.getText());
            //vinNumTxtField.setText("");
			   i = s.executeUpdate();
         
            //Check if delete is successful and print out message
			   if(i > 0)
            {
               JOptionPane.showMessageDialog(optionFrame, "Delete Successful!");
            }
            else
            {
               JOptionPane.showMessageDialog(optionFrame, "Delete Unsuccessful!");
            }

         
		   }//End try
		   catch(Exception e)
		   {
			   System.out.println("Failed to get connection");
			   e.printStackTrace();
		   }//End catch
	   }//End actionPerformed
   }//End class deleteAuto  
}//End ButtonHandler15
/*****************************************************************************/