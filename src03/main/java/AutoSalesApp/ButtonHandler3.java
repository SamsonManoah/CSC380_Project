package AutoSalesApp;
/****************************************************************
 Name        : ButtonHandler3
 Author      : Samson Manoah
 Date        : 04/29/2018
 Description : This class implements actionListener for insert a 
 customer button in customer panel.
 ****************************************************************/

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.*;

//Button event handler
public class ButtonHandler3 implements ActionListener
{
	//Global variables
   private static final String sql = "INSERT INTO CUSTOMER(CUSTOMER_ID,FIRST_NAME,LAST_NAME,PHONE_NUMBER)VALUES(?,?,?,?)";
   private static final String sql1 = "INSERT INTO CUSTOMER_ADDRESS(CUSTOMER_ID,STREET,CITY,STATE,ZIP)VALUES(?,?,?,?,?)";
   private static JFrame optionFrame = new JFrame();
   private static JFrame insertCustFrame = new JFrame("Insert A Customer");
   private static JPanel insertCustPanel = new JPanel();
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
   private static JButton insertButton = new JButton("Insert");

	//Handle button event
	public void actionPerformed(ActionEvent event)
	{
      //Add labels and text fields to panel
      insertCustPanel.add(idLabel);
      insertCustPanel.add(idTxtField);
      
      insertCustPanel.add(fNameLabel);
      insertCustPanel.add(fNameTxtField);
      
      insertCustPanel.add(lNameLabel);
      insertCustPanel.add(lNameTxtField);
      
      insertCustPanel.add(phNumLabel);
      insertCustPanel.add(phNumTxtField);
      
      insertCustPanel.add(streetLabel);
      insertCustPanel.add(streetTxtField);
      
      insertCustPanel.add(cityLabel);
      insertCustPanel.add(cityTxtField);
      
      insertCustPanel.add(stateLabel);
      insertCustPanel.add(stateTxtField);
      
      insertCustPanel.add(zipLabel);
      insertCustPanel.add(zipTxtField);
      
      
      //Add insert button to panel         
      insertCustPanel.add(insertButton);
         
      //Add panel to frame and show        
      insertCustFrame.add(insertCustPanel, BorderLayout.CENTER);
      insertCustFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      insertCustFrame.setSize(550,200);
      insertCustFrame.setVisible(true);
      
      //Create Button Handler and add Action Listener   
      InsertCust insertHandler = new InsertCust();
      insertButton.addActionListener(insertHandler);		
	}//End actionPerformed
   
   private class InsertCust implements ActionListener
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
            //Declare JDBC driver, Set parameters and execute insert into customer
            Class.forName("com.mysql.jdbc.Driver");
            s.setString(1,idTxtField.getText());
            s.setString(2,fNameTxtField.getText());
            fNameTxtField.setText("");
            s.setString(3,lNameTxtField.getText());
            lNameTxtField.setText("");
            s.setString(4,phNumTxtField.getText());
            phNumTxtField.setText("");
			   i = s.executeUpdate();
            
            //Execute insert into customer address
            s1.setString(1,idTxtField.getText());
            idTxtField.setText("");
            s1.setString(2,streetTxtField.getText());
            streetTxtField.setText("");
            s1.setString(3,cityTxtField.getText());
            cityTxtField.setText("");
            s1.setString(4,stateTxtField.getText());
            stateTxtField.setText("");
            s1.setString(5,zipTxtField.getText());
            zipTxtField.setText("");
        
        	   a = s1.executeUpdate();
       
            //Check if insert is successful and print out message
			   if(i > 0 && a > 0)
            {
               insertCustFrame.dispose();
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
}//End ButtonHandler3
/*****************************************************************************/