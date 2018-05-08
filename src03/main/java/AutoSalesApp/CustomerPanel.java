package AutoSalesApp;
/****************************************************************
 Name        : CustomerPanel
 Author      : Samson Manoah
 Date        : 02//2018
 Description : This class allows to view, insert, edit or delete
 a customer in  the database
 ****************************************************************/

import java.awt.*;
import javax.swing.*;

public class CustomerPanel extends JPanel
{
   public CustomerPanel()
   {
      JPanel custmerPanel = new JPanel();

      setLayout(new FlowLayout());//Panel layout

      setBackground (Color.blue);//Panel color

      //Create buttons
      JButton button1 = new JButton("View All Customers");
      JButton button2 = new JButton("View a Customer");
      JButton button3 = new JButton("Insert New Customer");
      JButton button4 = new JButton("Edit a Customer");
      JButton button5 = new JButton("Delete a Customer");
      JButton button6 = new JButton("Refresh");

      //Add buttons to panel
      add(button1);
      add(button2);
      add(button3);
      add(button4);
      add(button5);
      //add(button6);

      //Create Button Handlers and add Action Listeners
      ButtonHandler1 handler1 = new ButtonHandler1();
      button1.addActionListener(handler1);
      ButtonHandler2 handler2 = new ButtonHandler2();
      button2.addActionListener(handler2);
      ButtonHandler3 handler3 = new ButtonHandler3();
      button3.addActionListener(handler3);
      ButtonHandler4 handler4 = new ButtonHandler4();
      button4.addActionListener(handler4);
      ButtonHandler5 handler5 = new ButtonHandler5();
      button5.addActionListener(handler5);
      /*Refresh handler6 = new Refresh();
      button6.addActionListener(handler6);*/
      custmerPanel.revalidate();
      custmerPanel.repaint();
   }//End CustomerPanel
}//End class CustomerPanel
/******************************************************************************/