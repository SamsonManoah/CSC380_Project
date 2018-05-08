package AutoSalesApp;
/****************************************************************
 Name        : PaymentsPanel
 Author      : Samson Manoah
 Date        : 02//2018
 Description : This class allows to view
 ****************************************************************/

import java.awt.*;
import javax.swing.*;

public class PaymentsPanel extends JPanel
{
   public PaymentsPanel()
   {
      setLayout(new FlowLayout());//Panel layout
      setBackground (Color.blue);//Panel color

      //Create buttons
      JButton button1 = new JButton("View All Balances");
      JButton button2 = new JButton("View A Balance");
      JButton button3 = new JButton("Make a Payment");

      //Add buttons to panel
      add(button1);
      add(button2);
      add(button3);

      //Create Button Handlers and add Action Listeners
      ButtonHandler16 handler16 = new ButtonHandler16();
      button1.addActionListener(handler16);
      ButtonHandler17 handler17 = new ButtonHandler17();
      button2.addActionListener(handler17);
      ButtonHandler18 handler18 = new ButtonHandler18();
      button3.addActionListener(handler18);
   }//End PaymentsPanel
}//End class PaymentsPane
/******************************************************************************/