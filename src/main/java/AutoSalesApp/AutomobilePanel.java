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
	      setLayout(new FlowLayout());
	      setBackground (Color.blue);

	      JButton botton1 = new JButton("View All Automobiles");
	      JButton botton2 = new JButton("View an Automobile");
	      JButton botton3 = new JButton("Insert an Automobile");
	      JButton botton4 = new JButton("Edit an Automobile");
	      JButton botton5 = new JButton("Delete an Automobile");

	      add(botton1);
	      add(botton2);
	      add(botton3);
	      add(botton4);
	      add(botton5);
	   }
}