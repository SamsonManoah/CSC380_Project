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
      setLayout(new FlowLayout());//Panel layout

      setBackground (Color.blue);//Panel color

      //Create buttons
      JButton button1 = new JButton("View All Customers");
      JButton button2 = new JButton("View a Customer");
      JButton button3 = new JButton("Insert New Customer");
      JButton button4 = new JButton("Edit a Customer");
      JButton button5 = new JButton("Delete a Customer");

      //Add buttons to panel
      add(button1);
      add(button2);
      add(button3);
      add(button4);
      add(button5);

      //Create new ButtonHandlers
      ButtonHandler1 handler1 = new ButtonHandler1();
      button1.addActionListener(handler1);
      /*ButtonHandler handler2 = new ButtonHandler();
      button2.addActionListener(handler2);
      ButtonHandler handler3 = new ButtonHandler();
      button3.addActionListener(handler3);
      ButtonHandler handler4 = new ButtonHandler();
      button4.addActionListener(handler4);
      ButtonHandler handler5 = new ButtonHandler();
      button5.addActionListener(handler5);*/
   }//End CustomerPanel
}//End class CustomerPanel
/******************************************************************************/