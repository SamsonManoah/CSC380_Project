package AutoSalesApp;
/****************************************************************
 Name        : ButtonHandler14
 Author      : Samson Manoah
 Date        : 04/29/2018
 Description : This class implements actionListener for edit an 
 auto button in auto panel.
 ****************************************************************/

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.*;

//Button event handler
public class ButtonHandler14 implements ActionListener
{
	//Global variables
   //private static final String sql = "UPDATE ACCOUNT SET CUSTOMER_ID=?,VIN_NUMBER=?,START_DATE=?,END_DATE=?,"+
   //"`TERM(MONTH)`=? WHERE ACCOUNT_NUMBER=?";
   private static final String sql = "UPDATE AUTO SET VIN_NUMBER=?,MAKE=?,YEAR=? WHERE VIN_NUMBER=?";
   private static final String sql1 = "UPDATE AUTO_MODEL SET MODEL_NUMBER=?,VIN_NUMBER=?,MODEL=? WHERE VIN_NUMBER=?";
   private static final String sql2 = "UPDATE AUTO_COLOR SET COLOR_SECTION=?,VIN_NUMBER=?,COLOR=? WHERE VIN_NUMBER=?";
   private static JFrame optionFrame = new JFrame();
   private static JFrame updateAutoFrame = new JFrame("Update An Auto");
   private static JPanel updateAutoPanel = new JPanel();
   private static JTextField vinNumTxtField = new JTextField(12);
   private static JLabel vinNumLabel = new JLabel("VIN #");   
   private static JTextField makeTxtField = new JTextField(16);
   private static JLabel makeLabel = new JLabel("Make:");
   private static JTextField yearTxtField = new JTextField(4);
   private static JLabel yearLabel = new JLabel("Year:");
   private static JTextField modelNumTxtField = new JTextField(4);
   private static JLabel modelNumLabel = new JLabel("Model #:");
   private static JTextField modelTxtField = new JTextField(16);
   private static JLabel modelLabel = new JLabel("Model:");
   private static JTextField colorSectTxtField = new JTextField(8);
   private static JLabel colorSectLabel = new JLabel("Color Section:");
   private static JTextField colorTxtField = new JTextField(16);
   private static JLabel colorLabel = new JLabel("Color:");
   private static JButton updateButton = new JButton("Update");

	//Handle button event
	public void actionPerformed(ActionEvent event)
	{
      //Add labels and text fields to panel
      updateAutoPanel.add(vinNumLabel);
      updateAutoPanel.add(vinNumTxtField);
      
      updateAutoPanel.add(makeLabel);
      updateAutoPanel.add(makeTxtField);
      
      updateAutoPanel.add(yearLabel);
      updateAutoPanel.add(yearTxtField);
      
      updateAutoPanel.add(modelNumLabel);
      updateAutoPanel.add(modelNumTxtField);
      
      updateAutoPanel.add(modelLabel);
      updateAutoPanel.add(modelTxtField);
      
      updateAutoPanel.add(colorSectLabel);
      updateAutoPanel.add(colorSectTxtField); 
      
      updateAutoPanel.add(colorLabel);
      updateAutoPanel.add(colorTxtField);      
      
      //Add update button to panel         
      updateAutoPanel.add(updateButton);
         
      //Add panel to frame and show        
      updateAutoFrame.add(updateAutoPanel, BorderLayout.CENTER);
      updateAutoFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      updateAutoFrame.setSize(550,200);
      updateAutoFrame.setVisible(true);
      
      //Create Button Handler and add Action Listener   
      UpdateAcct updateHandler = new UpdateAcct();
      updateButton.addActionListener(updateHandler);		
	}//End actionPerformed
   
   private class UpdateAcct implements ActionListener
   {
      //Handle button event
	   public void actionPerformed(ActionEvent event)
	   {
         //Connect to Database
         int i,a,o;
		   try(Connection myConnection = DriverManager.getConnection(
			"jdbc:mysql://localhost:3306/auto_sales?useSSL=false","root", "Mourwel0*");
	      PreparedStatement s = myConnection.prepareStatement(sql);
         PreparedStatement s1 = myConnection.prepareStatement(sql1);
         PreparedStatement s2 = myConnection.prepareStatement(sql2))
		   {
            //Declare JDBC driver, Set parameters and execute insert into auto
            Class.forName("com.mysql.jdbc.Driver");
            s.setString(1,vinNumTxtField.getText());
            s.setString(2,makeTxtField.getText());
            makeTxtField.setText("");
            s.setString(3,yearTxtField.getText());
            yearTxtField.setText("");
            s.setString(4,vinNumTxtField.getText());
            i = s.executeUpdate();
                       
            s1.setString(1,modelNumTxtField.getText());
            modelNumTxtField.setText("");
            s1.setString(2,vinNumTxtField.getText()); 
            s1.setString(3,modelTxtField.getText());
            modelTxtField.setText("");
            s1.setString(4,vinNumTxtField.getText());      
			   a = s1.executeUpdate();
                        
            s2.setString(1,colorSectTxtField.getText());
            colorSectTxtField.setText("");
            s2.setString(2,vinNumTxtField.getText()); 
            s2.setString(3,colorTxtField.getText());
            colorTxtField.setText("");
            s2.setString(4,vinNumTxtField.getText());
            vinNumTxtField.setText("");      
			   o = s2.executeUpdate();
       
            //Check if insert is successful and print out message
			   if(i > 0 && a > 0 && o > 0)
            {
               updateAutoFrame.dispose();
               JOptionPane.showMessageDialog(optionFrame, "Update Successful!");
            }
            else
            {
               JOptionPane.showMessageDialog(optionFrame, "Update Unsuccessful!");
            }         
		   }//End try
		   catch(Exception e)
		   {
			   System.out.println("Failed to get connection");
			   e.printStackTrace();
		   }//End catch
	   }//End actionPerformed
   }//End updateCust  
}//End ButtonHandler14
/*****************************************************************************/