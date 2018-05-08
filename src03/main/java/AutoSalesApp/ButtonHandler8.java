package AutoSalesApp;
/****************************************************************
 Name        : ButtonHandler8
 Author      : Samson Manoah
 Date        : 04/29/2018
 Description : This class implements actionListener for insert an 
 account button in account panel.
 ****************************************************************/

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.*;

//Button event handler
public class ButtonHandler8 implements ActionListener
{
	//Global variables
   private static final String sql = "INSERT INTO ACCOUNT(ACCOUNT_NUMBER,CUSTOMER_ID,VIN_NUMBER,START_DATE,END_DATE,`TERM(MONTH)`)VALUES(?,?,?,?,?,?)";
   private static JFrame optionFrame = new JFrame();
   private static JFrame insertAcctFrame = new JFrame("Insert An Account");
   private static JPanel insertAcctPanel = new JPanel();
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
   private static JButton insertButton = new JButton("Insert");

	//Handle button event
	public void actionPerformed(ActionEvent event)
	{
      //Add labels and text fields to panel
      insertAcctPanel.add(acctNumLabel);
      insertAcctPanel.add(acctNumTxtField);
      
      insertAcctPanel.add(custIdLabel);
      insertAcctPanel.add(custIdTxtField);
      
      insertAcctPanel.add(vinNumLabel);
      insertAcctPanel.add(vinNumTxtField);
      
      insertAcctPanel.add(startDateLabel);
      insertAcctPanel.add(startDateTxtField);
      
      insertAcctPanel.add(endDateLabel);
      insertAcctPanel.add(endDateTxtField);
      
      insertAcctPanel.add(termLabel);
      insertAcctPanel.add(termTxtField);      
      
      //Add insert button to panel         
      insertAcctPanel.add(insertButton);
         
      //Add panel to frame and show        
      insertAcctFrame.add(insertAcctPanel, BorderLayout.CENTER);
      insertAcctFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      insertAcctFrame.setSize(550,200);
      insertAcctFrame.setVisible(true);
      
      //Create Button Handler and add Action Listener   
      InsertAcct insertHandler = new InsertAcct();
      insertButton.addActionListener(insertHandler);		
	}//End actionPerformed
   
   private class InsertAcct implements ActionListener
   {
      //Handle button event
	   public void actionPerformed(ActionEvent event)
	   {
         //Connect to Database
         int i,a;
		   try(Connection myConnection = DriverManager.getConnection(
		   "jdbc:mysql://localhost:3306/auto_sales?useSSL=false","root", "Mourwel0*");
	      PreparedStatement s = myConnection.prepareStatement(sql);)
		   {
            //Declare JDBC driver, Set parameters and execute insert into account
            Class.forName("com.mysql.jdbc.Driver");
            s.setString(1,acctNumTxtField.getText());
            acctNumTxtField.setText("");
            s.setString(2,custIdTxtField.getText());
            custIdTxtField.setText("");
            s.setString(3,vinNumTxtField.getText());
            vinNumTxtField.setText("");
            s.setString(4,startDateTxtField.getText());
            startDateTxtField.setText("");
            s.setString(5,endDateTxtField.getText());
            endDateTxtField.setText(""); 
            s.setString(6,termTxtField.getText());
            termTxtField.setText("");      
			   i = s.executeUpdate();
       
            //Check if insert is successful and print out message
			   if(i > 0)
            {
               insertAcctFrame.dispose();
               JOptionPane.showMessageDialog(optionFrame, "Insert Successful!");
            }
            else
            {
               JOptionPane.showMessageDialog(optionFrame, "Insert Unsuccessful!");
            }                    
		   }//End try
		   catch(Exception e)
		   {
			   System.out.println("Failed to get connection");
			   e.printStackTrace();
		   }//End catch
	   }//End actionPerformed
   }//End class InsertCust   
}//End ButtonHandler8
/*****************************************************************************/