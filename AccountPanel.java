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
      setLayout(new FlowLayout());
      setBackground (Color.green);
      
      JButton botton1 = new JButton("View All Accounts");
      JButton botton2 = new JButton("View an Account");
      JButton botton3 = new JButton("Insert New Account");
      JButton botton4 = new JButton("Edit an Account");
      JButton botton5 = new JButton("Delete an Account");
      JButton botton6 = new JButton("Make a Payment");
      
      add(botton1);
      add(botton2);
      add(botton3);
      add(botton4);
      add(botton5);
      add(botton6); 
   }  
}