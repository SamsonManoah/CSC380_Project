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
      JButton button3 = new JButton("Insert an Automobile");
      JButton button4 = new JButton("Edit an Automobile");
      JButton button5 = new JButton("Delete an Automobile");

      //Add buttons to panel
      add(button1);
      add(button2);
      add(button3);
      add(button4);
      add(button5);
   }//End AutomobilePanel
}//End class AutomobilePanel
/******************************************************************************/