package AutoSalesApp;
/****************************************************************
 Name        : ButtonHandler11
 Author      : Samson Manoah
 Date        : 04/29/2018
 Description : This class implements actionListener for view all 
 autos button in auto panel.
 ****************************************************************/

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.*;

//Button event handler
public class ButtonHandler11 implements ActionListener
{
	//Global variables
	private static final String sql = "SELECT AUTO.VIN_NUMBER,AUTO.MAKE,AUTO_MODEL.MODEL,"+
   "AUTO.YEAR,AUTO_COLOR.COLOR FROM AUTO,AUTO_MODEL,AUTO_COLOR WHERE AUTO.VIN_NUMBER="+
   "AUTO_MODEL.VIN_NUMBER AND AUTO_MODEL.VIN_NUMBER=AUTO_COLOR.VIN_NUMBER";
   private static Object rowData[] = null;
   private static DefaultTableModel tableModel = null;
	private static JTable allAutoTable = null;
   private static JFrame allAutoFrame = new JFrame("View All Autos");

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
			allAutoTable = new JTable(tableModel);
         JScrollPane scrollPane = new JScrollPane(allAutoTable); 
         allAutoFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);        
         allAutoFrame.add(scrollPane, BorderLayout.CENTER);
         allAutoFrame.setSize(870,200);
         allAutoFrame.setVisible(true);

		}//End try
		catch(Exception e)
		{
			System.out.println("Failed to get connection");
			e.printStackTrace();
		}//End catch
	}//End actionPerformed
}//End ButtonHandler11
/******************************************************************************/