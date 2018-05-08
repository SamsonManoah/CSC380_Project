package AutoSalesApp;
/****************************************************************
 Name        : ButtonHandler2
 Author      : Samson Manoah
 Date        : 04/29/2018
 Description : This class implements actionListener for view a 
 customer button in customer panel.
 ****************************************************************/

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.*;

//Button event handler
public class ButtonHandler2 implements ActionListener
{
	//Global variables
   private static final String sql = "SELECT CUSTOMER.CUSTOMER_ID,FIRST_NAME,LAST_NAME,STREET,CITY,STATE,ZIP,"+
   "PHONE_NUMBER FROM CUSTOMER JOIN CUSTOMER_ADDRESS USING(CUSTOMER_ID) WHERE LAST_NAME = ?";	
   private static Object rowData[] = null;
   private static DefaultTableModel tblModel = null;
	private static JTable viewCusttbl = null;
   private static JFrame custFrame = new JFrame("View A Customer");
   private static JFrame findCustFrame = new JFrame("Find a Customer");
   private static JPanel findCustPanel = new JPanel();
   private static JLabel lNameLabel = new JLabel("Last name:");
   private static JTextField lNameTxtField = new JTextField(10);
   private static JButton findButton = new JButton("Find");

	//Handle button event
	public void actionPerformed(ActionEvent event)
	{
      //Add label and text field to panel
      findCustPanel.add(lNameLabel);
      findCustPanel.add(lNameTxtField);
      
      //Add find button to panel         
      findCustPanel.add(findButton);
         
      //Add panel to frame and show  
      findCustFrame.add(findCustPanel, BorderLayout.CENTER);
      findCustFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      findCustFrame.setSize(550,200);
      findCustFrame.setVisible(true);
      
      //Create Button Handler and add Action Listener   
      ViewCust ViewCustHandler = new ViewCust();
      findButton.addActionListener(ViewCustHandler);		
	}//End actionPerformed
   
   private class ViewCust implements ActionListener
   {
      //Handle button event
	   public void actionPerformed(ActionEvent event)
	   {
         //Connect to Database
		   try(Connection myConnection = DriverManager.getConnection(
		   "jdbc:mysql://localhost:3306/auto_sales?useSSL=false","root", "Mourwel0*");
	      PreparedStatement s = myConnection.prepareStatement(sql))      
		   {
            //Declare JDBC driver, Set parameters and execute query
            Class.forName("com.mysql.jdbc.Driver");
            s.setString(1,lNameTxtField.getText());
            lNameTxtField.setText("");
            ResultSet rs = s.executeQuery();
			
			   //Get metaData
            ResultSetMetaData metaData = rs.getMetaData();
			   int numOfCols = metaData.getColumnCount();
            rowData = new Object[numOfCols];

		      //create string array to hold column names
		      String[] col = new String[numOfCols];

		      //read column names into string array
		      for(int count = 0; count < numOfCols; count++)
		      {
			    col[count] = metaData.getColumnLabel(count + 1);
		      }//End for

		      //create table model
		      tblModel = new DefaultTableModel(null,col);

		      //Populate rowData array with data
		      while (rs.next())
            {
				   for (int i = 0; i < rowData.length; ++i)
				   {
					   rowData[i] = rs.getObject(i+1);
		         }//End for
            
               //Add rowData to tableModel
		         tblModel.addRow(rowData);               
		      }//End while
         
			   //create a table to view search results
			   viewCusttbl = new JTable();
            viewCusttbl.setModel(tblModel);
            JScrollPane scrollPane = new JScrollPane(viewCusttbl);
            custFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);       
            custFrame.add(scrollPane, BorderLayout.CENTER);
            custFrame.setSize(870,200);
            findCustFrame.dispose();
            custFrame.revalidate();
            custFrame.repaint();
            custFrame.setVisible(true);         
		   }//End try
		   catch(Exception e)
		   {
			   System.out.println("Failed to get connection");
			   e.printStackTrace();
		   }//End catch
	   }//End actionPerformed
   }//End ViewCust   
}//End ButtonHandler2
/*****************************************************************************/