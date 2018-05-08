package AutoSalesApp;
/****************************************************************
 Name        : ButtonHandler6
 Author      : Samson Manoah
 Date        : 04/29/2018
 Description : This class implements actionListener for view all 
 accounts button in account panel.
 ****************************************************************/

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.*;

//Button event handler
public class ButtonHandler6 implements ActionListener
{
	//Global variables
	private static final String sql = "SELECT * FROM ACCOUNT";
   private static Object rowData[] = null;
   private static DefaultTableModel tableModel = null;
	private static JTable allAcctTable = null;
   private static JFrame allAcctFrame = new JFrame("View All Accounts");

	//Handle button event
	public void actionPerformed(ActionEvent event)
	{
		//Connect to Database
		try(Connection myConnection = DriverManager.getConnection(
		"jdbc:mysql://localhost:3306/auto_sales?useSSL=false","root", "Mourwel0*");
	   PreparedStatement s = myConnection.prepareStatement(sql);
	   ResultSet rs = s.executeQuery())
		{
         //Declare JDBC driver
			Class.forName("com.mysql.jdbc.Driver");
			
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
			allAcctTable = new JTable(tableModel);
         JScrollPane scrollPane = new JScrollPane(allAcctTable); 
         allAcctFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);        
         allAcctFrame.add(scrollPane, BorderLayout.CENTER);
         allAcctFrame.setSize(870,200);
         allAcctFrame.setVisible(true);      
         allAcctFrame.revalidate();

		}//End try
		catch(Exception e)
		{
			System.out.println("Failed to get connection");
			e.printStackTrace();
		}//End catch
	}//End actionPerformed
}//End ButtonHandler6
/******************************************************************************/