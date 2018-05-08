package AutoSalesApp;
/****************************************************************
 Name        : ButtonHandler12
 Author      : Samson Manoah
 Date        : 04/29/2018
 Description : This class implements actionListener for view an 
 auto button in auto panel.
 ****************************************************************/

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.*;

//Button event handler
public class ButtonHandler12 implements ActionListener
{
	//Global variables
   private static final String sql = "SELECT AUTO.VIN_NUMBER,AUTO.MAKE,AUTO_MODEL.MODEL,"+
   "AUTO.YEAR,AUTO_COLOR.COLOR FROM AUTO,AUTO_MODEL,AUTO_COLOR WHERE AUTO.VIN_NUMBER="+
   "AUTO_MODEL.VIN_NUMBER AND AUTO_MODEL.VIN_NUMBER=AUTO_COLOR.VIN_NUMBER AND AUTO.VIN_NUMBER=?";	
   private static Object rowData[] = null;
   private static DefaultTableModel tableModel = null;
	private static JTable viewAutoTable = null;
   private static JFrame autoFrame = new JFrame("View An Auto");
   private static JFrame findAutoFrame = new JFrame("Find An Auto");
   private static JPanel findAutoPanel = new JPanel();
   private static JLabel vinNumLabel = new JLabel("VIN #:");
   private static JTextField vinNumTxtField = new JTextField(12);
   private static JButton findButton = new JButton("Find");

	//Handle button event
	public void actionPerformed(ActionEvent event)
	{
      //Add label and text field to panel
      findAutoPanel.add(vinNumLabel);
      findAutoPanel.add(vinNumTxtField);
      
      //Add find button to panel         
      findAutoPanel.add(findButton);
         
      //Add panel to frame and show  
      findAutoFrame.add(findAutoPanel, BorderLayout.CENTER);
      findAutoFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      findAutoFrame.setSize(550,200);
      findAutoFrame.setVisible(true);
      
      //Create Button Handler and add Action Listener   
      ViewAuto ViewAutoHandler = new ViewAuto();
      findButton.addActionListener(ViewAutoHandler);		
	}//End actionPerformed
   
   private class ViewAuto implements ActionListener
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
            s.setString(1,vinNumTxtField.getText());
            vinNumTxtField.setText("");
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
			   viewAutoTable = new JTable(tableModel);
            JScrollPane scrollPane = new JScrollPane(viewAutoTable);
            autoFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);       
            autoFrame.add(scrollPane, BorderLayout.CENTER);
            autoFrame.setSize(870,200);
            findAutoFrame.dispose();
            autoFrame.setVisible(true);         
		   }//End try
		   catch(Exception e)
		   {
			   System.out.println("Failed to get connection");
			   e.printStackTrace();
		   }//End catch
	   }//End actionPerformed
   }//End ViewAuto   
}//End ButtonHandler12
/*****************************************************************************/