package AutoSalesApp;
/***************************************************************
 Name        : AccountPanel
 Author      : Samson Manoah
 Date        : 02//2018
 Description : This class allows to view, insert, edit or
 delete  an account in the  database as well as make a payment.
 ***************************************************************/

import java.awt.*;
import javax.swing.*;

public class AccountPanel extends JPanel
{
   public AccountPanel()
   {
      setLayout(new FlowLayout());//Panel layout
      setBackground (Color.blue);//Panel color

      //Create buttons
      JButton button1 = new JButton("View All Accounts");
      JButton button2 = new JButton("View an Account");
      JButton button3 = new JButton("Insert New Account");
      JButton button4 = new JButton("Edit an Account");
      JButton button5 = new JButton("Delete an Account");

      //Add buttons to panel
      add(button1);
      add(button2);
      add(button3);
      add(button4);
      add(button5);
   }//End AccountPanel
}//End class AccountPanel
/******************************************************************************/