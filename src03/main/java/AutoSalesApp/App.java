package AutoSalesApp;
/**********************************************************************
 Name        : Auto Sales Application
 Author      : Samson Manoah
 Date        : 02//2018
 Description : This program is a GUI interface for a database that
 allows an  auto  shop organize, document and track its car sales
 business. The user will be able to  view, enter, update or delete
 information about inventory, customers  and accounts information
 housed in a MySQL database. JDBC is used to connect to  the database.
 **********************************************************************/

 import javax.swing.*;
 import javax.swing.event.*;
 //import javax.swing.event.ChangeListener;

 public class AutoSales
 {
    public static void main (String[] args)
    {
		//Create frame
      JFrame frame = new JFrame ("Auto Sales Application");
      frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);

		//Create JTabbedPane and add panels
      JTabbedPane tabbedPane = new JTabbedPane( ) ;
      tabbedPane.addTab ("Menu", new MenuPanel());
      tabbedPane.addTab ("Customer", new CustomerPanel());
      tabbedPane.addTab ("Account", new AccountPanel());
      tabbedPane.addTab ("Automobile", new AutomobilePanel());
      tabbedPane.addTab ("Payments", new PaymentsPanel());
      tabbedPane.addTab ("Refresh", new JPanel());

      /*/Add the Listener
      tabbedPane.addChangeListener(new ChangeListener()
      {
         public void stateChanged(ChangeEvent e)
         {
            if(tabbedPane.getSelectedIndex()==6)
            {
               //Refresh
               frame.revalidate();
               frame.repaint();
            }//
        }//
      });/*/