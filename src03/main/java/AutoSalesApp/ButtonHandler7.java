package AutoSalesApp;
/****************************************************************
 Name        : ButtonHandler7
 Author      : Samson Manoah
 Date        : 04/29/2018
 Description : This class implements actionListener for view an 
 account button in account panel.
 ****************************************************************/

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.*;

//Button event handler
public class ButtonHandler7 implements ActionListener
{
	//Global variables
   private static final String sql = "SELECT * FROM ACCOUNT WHERE ACCOUNT_NUMBER = ?";	
   private static Object rowData[] = null;
   private static DefaultTableModel tableModel = null;
	private static JTable viewAcctTable = null;
   private static JFrame acctFrame = new JFrame("View An Account");
   private static JFrame findAcctFrame = new JFrame("Find An Account");
   private static JPanel findAcctPanel = new JPanel();
   private static JLabel acctNumLabel = new JLabel("Account #:");
   private static JTextField acctNumTxtField = new JTextField(2);
   private static JButton findButton = new JButton("Find");

	//Handle button event
	public void actionPerformed(ActionEvent event)
	{
      //Add label and text field to panel
      findAcctPanel.add(acctNumLabel);
      findAcctPanel.add(acctNumTxtField);
      
      //Add find button to panel         
      findAcctPanel.add(findButton);
         
      //Add panel to frame and show  
      findAcctFrame.add(findAcctPanel, BorderLayout.CENTER);
      findAcctFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      findAcctFrame.setSize(550,200);
      findAcctFrame.setVisible(true);
      
      //Create Button Handler and add Action Listener   
      ViewAcct ViewAcctHandler = new ViewAcct();
      findButton.addActionListener(ViewAcctHandler);		
	}//End actionPerformed
   
   private class ViewAcct implements ActionListener
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
			   viewAcctTable = new JTable(tableModel);
            JScrollPane scrollPane = new JScrollPane(viewAcctTable);
            acctFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);       
            acctFrame.add(scrollPane, BorderLayout.CENTER);
            acctFrame.setSize(870,200);
            findAcctFrame.dispose();
            acctFrame.setVisible(true);         
		   }//End try
		   catch(Exception e)
		   {
			   System.out.println("Failed to get connection");
			   e.printStackTrace();
		   }//End catch
	   }//End actionPerformed
   }//End ViewAcct   
}//End ButtonHandler7
/*****************************************************************************/