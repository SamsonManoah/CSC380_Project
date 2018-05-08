package AutoSalesApp;
/****************************************************************
 Name        : AutomobilePanel
 Author      : Samson Manoah
 Date        : 02//2018
 Description : This class allows to view, insert, edit or delete
 an automobile from the database
 ****************************************************************/

import java.awt.*;
import javax.swing.*;

public class AutomobilePanel extends JPanel
{
   public AutomobilePanel()
   {
      setLayout(new FlowLayout());//Panel layout
      setBackground (Color.blue);//Panel color

      //Create buttons
      JButton button1 = new JButton("View All Automobiles");
      JButton button2 = new JButton("View an Automobile");
      JButton button3 = new JButton("Insert New Automobile");
      JButton button4 = new JButton("Edit an Automobile");
      JButton button5 = new JButton("Delete an Automobile");

      //Add buttons to panel
      add(button1);
      add(button2);
      add(button3);
      add(button4);
      add(button5);

      //Create Button Handlers and add Action Listeners
      ButtonHandler11 handler11 = new ButtonHandler11();
      button1.addActionListener(handler11);
      ButtonHandler12 handler12 = new ButtonHandler12();
      button2.addActionListener(handler12);
      ButtonHandler13 handler13 = new ButtonHandler13();
      button3.addActionListener(handler13);
      ButtonHandler14 handler14 = new ButtonHandler14();
      button4.addActionListener(handler14);
      ButtonHandler15 handler15 = new ButtonHandler15();
      button5.addActionListener(handler15);
   }//End AutomobilePanel
}//End class AutomobilePanel
/******************************************************************************/