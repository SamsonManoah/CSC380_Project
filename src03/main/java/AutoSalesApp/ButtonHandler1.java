package AutoSalesApp;
/****************************************************************
 Name        : ButtonHandler1
 Author      : Samson Manoah
 Date        : 04/29/2018
 Description : This class implements actionListener for view all
 customers button in customer panel.
 ****************************************************************/

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.*;
import javax.swing.table.AbstractTableModel;

//Button event handler
public class ButtonHandler1 implements ActionListener
{
	//Global variables
	private static final String sql = "SELECT CUSTOMER.CUSTOMER_ID,FIRST_NAME,LAST_NAME,STREET,CITY,STATE,ZIP,"+
   "PHONE_NUMBER FROM CUSTOMER JOIN CUSTOMER_ADDRESS USING(CUSTOMER_ID)";
   private static Object rowData[] = null;
   private static DefaultTableModel tableModel = null;
	private static JTable allCustTable = null;
   private static JFrame allCustFrame = new JFrame("View All Customers");

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
            //fireTableDataChanged();
		   }//End while

			//create a table to view search results
			allCustTable = new JTable();
         allCustTable.setModel(tableModel);
         JScrollPane scrollPane = new JScrollPane(allCustTable);
         allCustFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
         allCustFrame.add(scrollPane, BorderLayout.CENTER);
         allCustFrame.setSize(870,200);
         allCustFrame.revalidate();
         allCustFrame.repaint();
         allCustFrame.setVisible(true);

		}//End try
		catch(Exception e)
		{
			System.out.println("Failed to get connection");
			e.printStackTrace();
		}//End catch
	}//End actionPerformed
}//End ButtonHandler1
/******************************************************************************/