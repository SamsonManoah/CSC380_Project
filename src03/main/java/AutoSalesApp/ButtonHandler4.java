package AutoSalesApp;
/****************************************************************
 Name        : ButtonHandler4
 Author      : Samson Manoah
 Date        : 04/29/2018
 Description : This class implements actionListener for edit a 
 customer button in customer panel.
 ****************************************************************/

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.*;

//Button event handler
public class ButtonHandler4 implements ActionListener
{
	//Global variables
   private static final String sql = "UPDATE CUSTOMER SET FIRST_NAME=?,LAST_NAME=?,PHONE_NUMBER=? WHERE CUSTOMER_ID=?";
   private static final String sql1 = "UPDATE CUSTOMER_ADDRESS SET STREET=?,CITY=?,STATE=?,ZIP=? WHERE CUSTOMER_ID=?";
   private static JFrame optionFrame = new JFrame();
   private static JFrame updateCustFrame = new JFrame("Update A Customer");
   private static JPanel updateCustPanel = new JPanel();
   private static JTextField idTxtField = new JTextField(2);
   private static JLabel idLabel = new JLabel("Customer ID:");
   private static JTextField fNameTxtField = new JTextField(10);
   private static JLabel fNameLabel = new JLabel("First Name:");
   private static JTextField lNameTxtField = new JTextField(10);
   private static JLabel lNameLabel = new JLabel("Last Name:");
   private static JTextField phNumTxtField = new JTextField(10);
   private static JLabel phNumLabel = new JLabel("Phone #:");
   private static JTextField streetTxtField = new JTextField(10);
   private static JLabel streetLabel = new JLabel("Street:");
   private static JTextField cityTxtField = new JTextField(10);
   private static JLabel cityLabel = new JLabel("City:");
   private static JTextField stateTxtField = new JTextField(2);
   private static JLabel stateLabel = new JLabel("State:");
   private static JTextField zipTxtField = new JTextField(5);
   private static JLabel zipLabel = new JLabel("Zip Code:");
   private static JButton updateButton = new JButton("Update");

	//Handle button event
	public void actionPerformed(ActionEvent event)
	{
      //Add labels and text fields to panel
      updateCustPanel.add(idLabel);
      updateCustPanel.add(idTxtField);
      
      updateCustPanel.add(fNameLabel);
      updateCustPanel.add(fNameTxtField);
      
      updateCustPanel.add(lNameLabel);
      updateCustPanel.add(lNameTxtField);
      
      updateCustPanel.add(phNumLabel);
      updateCustPanel.add(phNumTxtField);
      
      updateCustPanel.add(streetLabel);
      updateCustPanel.add(streetTxtField);
      
      updateCustPanel.add(cityLabel);
      updateCustPanel.add(cityTxtField);
      
      updateCustPanel.add(stateLabel);
      updateCustPanel.add(stateTxtField);
      
      updateCustPanel.add(zipLabel);
      updateCustPanel.add(zipTxtField);
      
      
      //Add update button to panel         
      updateCustPanel.add(updateButton);
         
      //Add panel to frame and show         
      updateCustFrame.add(updateCustPanel, BorderLayout.CENTER);
      updateCustFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      updateCustFrame.setSize(550,200);
      updateCustFrame.setVisible(true);
      
      //Create ButtonsHandler and add Action Listener   
      UpdateCust updateHandler = new UpdateCust();
      updateButton.addActionListener(updateHandler);		
	}//End actionPerformed
   
   private class UpdateCust implements ActionListener
   {
      //Handle button event
	   public void actionPerformed(ActionEvent event)
	   {
         //Connect to Database
         int i,a;
		   try(Connection myConnection = DriverManager.getConnection(
			"jdbc:mysql://localhost:3306/auto_sales?useSSL=false","root", "Mourwel0*");
	      PreparedStatement s = myConnection.prepareStatement(sql);
         PreparedStatement s1 = myConnection.prepareStatement(sql1))
		   {
            //Declare JDBC driver, Set parameters and execute update customer
            Class.forName("com.mysql.jdbc.Driver");
            s.setString(1,fNameTxtField.getText());
            fNameTxtField.setText("");
            s.setString(2,lNameTxtField.getText());
            lNameTxtField.setText("");
            s.setString(3,phNumTxtField.getText());
            phNumTxtField.setText("");
            s.setString(4,idTxtField.getText());
            i = s.executeUpdate();
         
            //Execute update customer address
            s1.setString(1,streetTxtField.getText());
            streetTxtField.setText("");
            s1.setString(2,cityTxtField.getText());
            cityTxtField.setText("");
            s1.setString(3,stateTxtField.getText());
            stateTxtField.setText("");
            s1.setString(4,zipTxtField.getText());
            zipTxtField.setText("");
            s1.setString(5,idTxtField.getText());
            idTxtField.setText("");
        
        	   a = s1.executeUpdate();
       
            //Check if update is successful and print out message
			   if(i > 0 && a > 0)
            {
               updateCustFrame.dispose();
               JOptionPane.showMessageDialog(optionFrame, "Update Successful!");
            }//End if
            else
            {
               JOptionPane.showMessageDialog(optionFrame, "Update Unsuccessful!");
            }//End else         
		   }//End try
		   catch(Exception e)
		   {
			   System.out.println("Failed to get connection");
			   e.printStackTrace();
		   }//End catch
	   }//End actionPerformed
   }//End updateCust  
}//End ButtonHandler4
/*****************************************************************************/