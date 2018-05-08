package AutoSalesApp;
/****************************************************************
 Name        : ButtonHandler13
 Author      : Samson Manoah
 Date        : 04/29/2018
 Description : This class implements actionListener for insert an 
 auto button in auto panel.
 ****************************************************************/

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.*;

//Button event handler
public class ButtonHandler13 implements ActionListener
{
	//Global variables
   private static final String sql = "INSERT INTO AUTO(VIN_NUMBER,MAKE,YEAR)VALUES(?,?,?)";
   private static final String sql1 = "INSERT INTO AUTO_MODEL(MODEL_NUMBER,VIN_NUMBER,MODEL)VALUES(?,?,?)";
   private static final String sql2 = "INSERT INTO AUTO_COLOR(COLOR_SECTION,VIN_NUMBER,COLOR)VALUES(?,?,?)";
   private static JFrame optionFrame = new JFrame();
   private static JFrame insertAutoFrame = new JFrame("Insert An Auto");
   private static JPanel insertAutoPanel = new JPanel();
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
   private static JButton insertButton = new JButton("Insert");

	//Handle button event
	public void actionPerformed(ActionEvent event)
	{
      //Add labels and text fields to panel
      insertAutoPanel.add(vinNumLabel);
      insertAutoPanel.add(vinNumTxtField);
      
      insertAutoPanel.add(makeLabel);
      insertAutoPanel.add(makeTxtField);
      
      insertAutoPanel.add(yearLabel);
      insertAutoPanel.add(yearTxtField);
      
      insertAutoPanel.add(modelNumLabel);
      insertAutoPanel.add(modelNumTxtField);
      
      insertAutoPanel.add(modelLabel);
      insertAutoPanel.add(modelTxtField);
      
      insertAutoPanel.add(colorSectLabel);
      insertAutoPanel.add(colorSectTxtField); 
      
      insertAutoPanel.add(colorLabel);
      insertAutoPanel.add(colorTxtField);     
      
      //Add insert button to panel         
      insertAutoPanel.add(insertButton);
         
      //Add panel to frame and show        
      insertAutoFrame.add(insertAutoPanel, BorderLayout.CENTER);
      insertAutoFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      insertAutoFrame.setSize(550,200);
      insertAutoFrame.setVisible(true);
      
      //Create Button Handler and add Action Listener   
      InsertAuto insertHandler = new InsertAuto();
      insertButton.addActionListener(insertHandler);		
	}//End actionPerformed
   
   private class InsertAuto implements ActionListener
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
            i = s.executeUpdate();
                       
            s1.setString(1,modelNumTxtField.getText());
            modelNumTxtField.setText("");
            s1.setString(2,vinNumTxtField.getText()); 
            s1.setString(3,modelTxtField.getText());
            modelTxtField.setText("");      
			   a = s1.executeUpdate();
                        
            s2.setString(1,colorSectTxtField.getText());
            colorSectTxtField.setText("");
            s2.setString(2,vinNumTxtField.getText());
            vinNumTxtField.setText(""); 
            s2.setString(3,colorTxtField.getText());
            colorTxtField.setText("");      
			   o = s2.executeUpdate();
       
            //Check if insert is successful and print out message
			   if(i > 0 && a > 0 && o > 0)
            {
               insertAutoFrame.dispose();
               JOptionPane.showMessageDialog(optionFrame, "Insert Successful!");
            }
            else
            {
               JOptionPane.showMessageDialog(optionFrame, "Insert Unsuccessful!");
            }                    
		   }//End try
		   catch(Exception e)
		   {
			   System.out.println("Failed to get connection");
			   e.printStackTrace();
		   }//End catch
	   }//End actionPerformed
   }//End class InsertCust   
}//End ButtonHandler13
/*****************************************************************************/