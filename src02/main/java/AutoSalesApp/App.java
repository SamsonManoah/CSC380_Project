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

 public class App
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

		//Display frame
        frame.getContentPane().add(tabbedPane);
        frame.pack();
        frame.setSize(550,600);
        frame.setVisible(true);

    }//End Main
 }//End Class
 /******************************************************************************/