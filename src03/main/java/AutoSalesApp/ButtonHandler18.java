package AutoSalesApp;
/****************************************************************
 Name        : ButtonHandler18
 Author      : Samson Manoah
 Date        : 04/29/2018
 Description : This class implements actionListener for make a 
 payment button in customer panel.
 ****************************************************************/

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.*;

//Button event handler
public class ButtonHandler18 implements ActionListener
{
	//Global variables
   private static final String sql = "INSERT INTO PAYMENT(PAYMENT_NUMBER,ACCOUNT_NUMBER,PAYMENT_AMOUNT"+
   ",PAYMENT_DATE)VALUES(?,?,?,?)";
   private static JFrame optionFrame = new JFrame();
   private static JFrame paymentFrame = new JFrame("Make A Payment");
   private static JPanel paymentPanel = new JPanel();
   private static JTextField payNumTxtField = new JTextField(2);
   private static JLabel payNumLabel = new JLabel("Payment #:");
   private static JTextField acctNumTxtField = new JTextField(2);
   private static JLabel acctNumLabel = new JLabel("Account #:");
   private static JTextField payAmntTxtField = new JTextField(10);
   private static JLabel payAmntLabel = new JLabel("Payment Amount:");
   private static JTextField payDateTxtField = new JTextField(10);
   private static JLabel payDateLabel = new JLabel("Payment Date:");
   private static JButton payButton = new JButton("Pay");

	//Handle button event
	public void actionPerformed(ActionEvent event)
	{
      //Add labels and text fields to panel
      paymentPanel.add(payNumLabel);
      paymentPanel.add(payNumTxtField);
      
      paymentPanel.add(acctNumLabel);
      paymentPanel.add(acctNumTxtField);
      
      paymentPanel.add(payAmntLabel);
      paymentPanel.add(payAmntTxtField);
      
      paymentPanel.add(payDateLabel);
      paymentPanel.add(payDateTxtField);      
      
      //Add insert button to panel         
      paymentPanel.add(payButton);
         
      //Add panel to frame and show        
      paymentFrame.add(paymentPanel, BorderLayout.CENTER);
      paymentFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      paymentFrame.setSize(550,200);
      paymentFrame.setVisible(true);
      
      //Create Button Handler and add Action Listener   
      MakePymnt makePymnt = new MakePymnt();
      payButton.addActionListener(makePymnt);		
	}//End actionPerformed
   
   private class MakePymnt implements ActionListener
   {
      //Handle button event
	   public void actionPerformed(ActionEvent event)
	   {
         //Connect to Database
         int i;
		   try(Connection myConnection = DriverManager.getConnection(
		   "jdbc:mysql://localhost:3306/auto_sales?useSSL=false","root", "Mourwel0*");
	      PreparedStatement s = myConnection.prepareStatement(sql);)
		   {
            //Declare JDBC driver, Set parameters and execute insert into customer
            Class.forName("com.mysql.jdbc.Driver");
            s.setString(1,payNumTxtField.getText());
            payNumTxtField.setText("");
            s.setString(2,acctNumTxtField.getText());
            acctNumTxtField.setText("");
            s.setString(3,payAmntTxtField.getText());
            payAmntTxtField.setText("");
            s.setString(4,payDateTxtField.getText());
            payDateTxtField.setText("");
			   i = s.executeUpdate();
       
            //Check if insert is successful and print out message
			   if(i > 0)
            {
               paymentFrame.dispose();
               JOptionPane.showMessageDialog(optionFrame, "Payment Successful!");               
            }
            else
            {
               JOptionPane.showMessageDialog(optionFrame, "Payment Unsuccessful!");
            }
         
		   }//End try
		   catch(Exception e)
		   {
			   System.out.println("Failed to get connection");
			   e.printStackTrace();
		   }//End catch
	   }//End actionPerformed
   }//End class MakePymnt   
}//End ButtonHandler18
/*****************************************************************************/