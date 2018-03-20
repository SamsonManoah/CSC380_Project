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
      setLayout(new FlowLayout());

      setBackground (Color.blue);

      JButton botton1 = new JButton("View All Customers");
      JButton botton2 = new JButton("View a Customer");
      JButton botton3 = new JButton("Insert New Customer");
      JButton botton4 = new JButton("Edit a Customer");
      JButton botton5 = new JButton("Delete a Customer");

      add(botton1);
      add(botton2);
      add(botton3);
      add(botton4);
      add(botton5);
   }
}