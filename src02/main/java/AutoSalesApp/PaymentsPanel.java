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
      JButton button1 = new JButton("View an Account Balance");
      JButton button2 = new JButton("View All Accounts Balances");
      JButton button3 = new JButton("Make a Payment");

      //Add buttons to panel
      add(button1);
      add(button2);
      add(button3);
   }//End PaymentsPanel
}//End class PaymentsPane
/******************************************************************************/