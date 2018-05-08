package AutoSalesApp;
/****************************************************************
 Name        : ButtonHandler10
 Author      : Samson Manoah
 Date        : 02//2018
 Description : This class implements an Action Listener for delete
 an account button in account panel.
 ****************************************************************/

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.*;

//Button event handler
public class ButtonHandler10 implements ActionListener
{
	//Global variables
   private static final String sql = "DELETE FROM ACCOUNT WHERE ACCOUNT_NUMBER=?";
   private static JFrame optionFrame = new JFrame();
   private static JFrame deleteAcctFrame = new JFrame("Delete An Account");
   private static JPanel deleteAcctPanel = new JPanel();
   private static JTextField acctNumTxtField = new JTextField(2);
   private static JLabel acctNumLabel = new JLabel("Account #:");
   private static JButton deleteButton = new JButton("Delete");

	//Handle button event
	public void actionPerformed(ActionEvent event)
	{
      //Add label and text field to panel
      deleteAcctPanel.add(acctNumLabel);
      deleteAcctPanel.add(acctNumTxtField);
      
      //Add delete button to panel         
      deleteAcctPanel.add(deleteButton);
         
      //Add panel to frame and show         
      deleteAcctFrame.add(deleteAcctPanel,BorderLayout.CENTER);
      deleteAcctFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      deleteAcctFrame.setSize(550,200);
      deleteAcctFrame.setVisible(true);
      
      //Create Button Handler and add Action Listener   
      deleteAcct deleteHandler = new deleteAcct();
      deleteButton.addActionListener(deleteHandler);
      		
	}//End actionPerformed
   
   private class deleteAcct implements ActionListener
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
            //Declare JDBC driver, Set parameters and execute delete customer
            Class.forName("com.mysql.jdbc.Driver");
            s.setString(1,acctNumTxtField.getText());
            //acctNumTxtField.setText("");
			   i = s.executeUpdate();
         
            //Check if delete is successful and print out message
			   if(i > 0)
            {
               deleteAcctFrame.dispose();
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
   }//End class deleteAcct  
}//End ButtonHandler10
/*****************************************************************************/