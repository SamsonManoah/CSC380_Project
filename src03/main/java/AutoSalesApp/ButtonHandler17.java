package AutoSalesApp;
/****************************************************************
 Name        : ButtonHandler17
 Author      : Samson Manoah
 Date        : 04/29/2018
 Description : This class implements actionListener for view a 
 balance button in payment panel.
 ****************************************************************/

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.*;

//Button event handler
public class ButtonHandler17 implements ActionListener
{
	//Global variables
   private static final String sql = "SELECT ACCOUNT.CUSTOMER_ID,ACCOUNT_BALANCE."+
   "ACCOUNT_NUMBER,ACCOUNT_BALANCE.PRINCIPAL_BALANCE,ACCOUNT_BALANCE.AMOUNT_DUE,"+
   "ACCOUNT_BALANCE.PAYMENT_DUE_DATE,PAYMENT.PAYMENT_NUMBER,PAYMENT.PAYMENT_AMOUNT"+
   ",PAYMENT.PAYMENT_DATE FROM ACCOUNT,ACCOUNT_BALANCE,PAYMENT WHERE ACCOUNT.ACCOUNT"+
   "_NUMBER=ACCOUNT_BALANCE.ACCOUNT_NUMBER AND ACCOUNT_BALANCE.ACCOUNT_NUMBER=PAYMENT.ACCOUNT_NUMBER AND ACCOUNT.ACCOUNT_NUMBER=?";	
   private static Object rowData[] = null;
   private static DefaultTableModel tableModel = null;
	private static JTable viewBalanceTable = null;
   private static JFrame balanceFrame = new JFrame("View A Balance");
   private static JFrame findBalanceFrame = new JFrame("Find A Balance");
   private static JPanel findBalancePanel = new JPanel();
   private static JLabel acctNumLabel = new JLabel("Account #:");
   private static JTextField acctNumTxtField = new JTextField(2);
   private static JButton findButton = new JButton("Find");

	//Handle button event
	public void actionPerformed(ActionEvent event)
	{
      //Add label and text field to panel
      findBalancePanel.add(acctNumLabel);
      findBalancePanel.add(acctNumTxtField);
      
      //Add find button to panel         
      findBalancePanel.add(findButton);
         
      //Add panel to frame and show  
      findBalanceFrame.add(findBalancePanel, BorderLayout.CENTER);
      findBalanceFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      findBalanceFrame.setSize(550,200);
      findBalanceFrame.setVisible(true);
      
      //Create Button Handler and add Action Listener   
      ViewBalance ViewBalanceHandler = new ViewBalance();
      findButton.addActionListener(ViewBalanceHandler);		
	}//End actionPerformed
   
   private class ViewBalance implements ActionListener
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
            s.setString(1,acctNumTxtField.getText());
            acctNumTxtField.setText("");
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
		      tableModel = new DefaultTableModel(null,col);

		      //Populate rowData array with data
		      while (rs.next())
            {
				   for (int i = 0; i < rowData.length; ++i)
				   {
					   rowData[i] = rs.getObject(i+1);
		         }//End for
            
               //Add rowData to tableModel
		         tableModel.addRow(rowData);               
		      }//End while
         
			   //create a table to view search results
			   viewBalanceTable = new JTable(tableModel);
            JScrollPane scrollPane = new JScrollPane(viewBalanceTable);
            balanceFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);       
            balanceFrame.add(scrollPane, BorderLayout.CENTER);
            balanceFrame.setSize(870,200);
            findBalanceFrame.dispose();
            balanceFrame.setVisible(true);         
		   }//End try
		   catch(Exception e)
		   {
			   System.out.println("Failed to get connection");
			   e.printStackTrace();
		   }//End catch
	   }//End actionPerformed
   }//End ViewBalance   
}//End ButtonHandler17
/*****************************************************************************/