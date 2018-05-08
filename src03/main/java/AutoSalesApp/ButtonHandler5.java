package AutoSalesApp;
/****************************************************************
 Name        : ButtonHandler5
 Author      : Samson Manoah
 Date        : 02//2018
 Description : This class implements an Action Listener for delete a 
 customer button in customer panel.
 ****************************************************************/

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.*;

//Button event handler
public class ButtonHandler5 implements ActionListener
{
	//Global variables
   private static final String sql = "DELETE FROM CUSTOMER WHERE CUSTOMER_ID=?";
   private static JFrame optionFrame = new JFrame();
   private static JFrame deleteCustFrame = new JFrame("Delete A Customer");
   private static JPanel deleteCustPanel = new JPanel();
   private static JTextField idTxtField = new JTextField(2);
   private static JLabel idLabel = new JLabel("Customer ID:");
   private static JButton deleteButton = new JButton("Delete");

	//Handle button event
	public void actionPerformed(ActionEvent event)
	{
      //Add label and text field to panel
      deleteCustPanel.add(idLabel);
      deleteCustPanel.add(idTxtField);
      
      //Add delete button to panel         
      deleteCustPanel.add(deleteButton);
         
      //Add panel to frame and show         
      deleteCustFrame.add(deleteCustPanel,BorderLayout.CENTER);
      deleteCustFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      deleteCustFrame.setSize(550,200);
      deleteCustFrame.setVisible(true);
      
      //Create Button Handler and add Action Listener   
      DeleteCust deleteHandler = new DeleteCust();
      deleteButton.addActionListener(deleteHandler);
      		
	}//End actionPerformed
   
   private class DeleteCust implements ActionListener
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
            s.setString(1,idTxtField.getText());
			   i = s.executeUpdate();
         
            //Check if delete is successful and print out message
			   if(i > 0)
            {
               deleteCustFrame.dispose();
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
   }//End class DeleteCust  
}//End ButtonHandler5
/*****************************************************************************/