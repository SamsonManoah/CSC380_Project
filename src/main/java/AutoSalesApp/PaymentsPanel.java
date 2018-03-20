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

public class PaymentsPanel extends JPanel
{
   public PaymentsPanel()
   {
      setLayout(new FlowLayout());
      setBackground (Color.blue);

      JButton botton1 = new JButton("View an Account Balance");
      JButton botton2 = new JButton("View All Accounts Balances");
      JButton botton3 = new JButton("Make a Payment");

      add(botton1);
      add(botton2);
      add(botton3);
   }
}