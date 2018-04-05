package AutoSalesApp;
/****************************************************************
 Name        : ButtonHandler1
 Author      : Samson Manoah
 Date        : 02//2018
 Description : This class implements actionListener for view all
 customers button in customer panel.
 ****************************************************************/

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.*;

//Button event handler
public class ButtonHandler1 implements ActionListener
{

	private static final String sql = "SELECT * FROM CUSTOMER";
	private static Connection myConnection = null;
	private static PreparedStatement s = null;
	private static ResultSet rs = null;
   private static Object rowData[] = null;
   private static DefaultTableModel tableModel = null;
	private static JTable table = null;
   private static JFrame frame1 = new JFrame();

	//Handle button event
	public void actionPerformed(ActionEvent event)
	{
		//Connect to Database
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			myConnection = DriverManager.getConnection(
			"jdbc:mysql://localhost:3306/auto_sales?useSSL=false","root", "Mourwel0");
			s = myConnection.prepareStatement(sql);
			rs = s.executeQuery();

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
		   }

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
			table = new JTable(tableModel);
         JScrollPane scrollPane = new JScrollPane(table);
         frame1.add(scrollPane, BorderLayout.CENTER);
         frame1.setSize(550,200);
         frame1.setVisible(true);

		}//End try
		catch(Exception e)
		{
			System.out.println("Failed to get connection");
			e.printStackTrace();
		}//End catch

		//Close reasultSet, Statement and connection
		finally
		{
			try
			{
				rs.close();
				s.close();
				myConnection.close();
			}//End try
			catch(Exception e)
			{
				e.printStackTrace();
			}//End catch
		}//End finally
	}//End actionPerformed
}//End handler1
/******************************************************************************/