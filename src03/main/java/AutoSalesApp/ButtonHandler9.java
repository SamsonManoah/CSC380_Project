package AutoSalesApp;
/****************************************************************
 Name        : ButtonHandler9
 Author      : Samson Manoah
 Date        : 04/29/2018
 Description : This class implements actionListener for edit an
 account button in account panel.
 ****************************************************************/

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.*;

//Button event handler
public class ButtonHandler9 implements ActionListener
{
	//Global variables
   private static final String sql = "UPDATE ACCOUNT SET CUSTOMER_ID=?,VIN_NUMBER=?,START_DATE=?,END_DATE=?,"+
   "`TERM(MONTH)`=? WHERE ACCOUNT_NUMBER=?";
   private static JFrame optionFrame = new JFrame();
   private static JFrame updateAcctFrame = new JFrame("Update An Account");
   private static JPanel updateAcctPanel = new JPanel();
   private static JTextField acctNumTxtField = new JTextField(2);
   private static JLabel acctNumLabel = new JLabel("Account #");   
   private static JTextField custIdTxtField = new JTextField(2);
   private static JLabel custIdLabel = new JLabel("Customer ID:");
   private static JTextField vinNumTxtField = new JTextField(12);
   private static JLabel vinNumLabel = new JLabel("VIN #:");
   private static JTextField startDateTxtField = new JTextField(10);
   private static JLabel startDateLabel = new JLabel("Start Date:");
   private static JTextField endDateTxtField = new JTextField(10);
   private static JLabel endDateLabel = new JLabel("End Date:");
   private static JTextField termTxtField = new JTextField(2);
   private static JLabel termLabel = new JLabel("Term:");
   private static JButton updateButton = new JButton("Update");

	//Handle button event
	public void actionPerformed(ActionEvent event)
	{
      //Add labels and text fields to panel
      updateAcctPanel.add(acctNumLabel);
      updateAcctPanel.add(acctNumTxtField);
      
      updateAcctPanel.add(custIdLabel);
      updateAcctPanel.add(custIdTxtField);
      
      updateAcctPanel.add(vinNumLabel);
      updateAcctPanel.add(vinNumTxtField);
      
      updateAcctPanel.add(startDateLabel);
      updateAcctPanel.add(startDateTxtField);
      
      updateAcctPanel.add(endDateLabel);
      updateAcctPanel.add(endDateTxtField);
      
      updateAcctPanel.add(termLabel);
      updateAcctPanel.add(termTxtField);      
      
      //Add insert button to panel         
      updateAcctPanel.add(updateButton);
         
      //Add panel to frame and show        
      updateAcctFrame.add(updateAcctPanel, BorderLayout.CENTER);
      updateAcctFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      updateAcctFrame.setSize(550,200);
      updateAcctFrame.setVisible(true);
      
      //Create Button Handler and add Action Listener   
      UpdateAcct updateHandler = new UpdateAcct();
      updateButton.addActionListener(updateHandler);		
	}//End actionPerformed
   
   private class UpdateAcct implements ActionListener
   {
      //Handle button event
	   public void actionPerformed(ActionEvent event)
	   {
         //Connect to Database
         int i;
		   try(Connection myConnection = DriverManager.getConnection(
			"jdbc:mysql://localhost:3306/auto_sales?useSSL=false","root", "Mourwel0*");
	      PreparedStatement s = myConnection.prepareStatement(sql))
		   {
            //Declare JDBC driver, Set parameters and execute update account
            Class.forName("com.mysql.jdbc.Driver");
            s.setString(1,custIdTxtField.getText());
            custIdTxtField.setText("");
            s.setString(2,vinNumTxtField.getText());
            vinNumTxtField.setText("");
            s.setString(3,startDateTxtField.getText());
            startDateTxtField.setText("");
            s.setString(4,endDateTxtField.getText());
            endDateTxtField.setText(""); 
            s.setString(5,termTxtField.getText());
            termTxtField.setText("");
            s.setString(6,acctNumTxtField.getText());
            acctNumTxtField.setText("");      
			   i = s.executeUpdate();
       
            //Check if insert is successful and print out message
			   if(i > 0)
            {
               updateAcctFrame.dispose();
               JOptionPane.showMessageDialog(optionFrame, "Update Successful!");
            }
            else
            {
               JOptionPane.showMessageDialog(optionFrame, "Update Unsuccessful!");
            }         
		   }//End try
		   catch(Exception e)
		   {
			   System.out.println("Failed to get connection");
			   e.printStackTrace();
		   }//End catch
	   }//End actionPerformed
   }//End updateCust  
}//End ButtonHandler9
/*****************************************************************************/