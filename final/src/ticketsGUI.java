
/*
 * Final Project - Trouble Ticket System
 * ITMD 411
 * Programmed by Guzel Nasybullina
 * 12.09.2017
 * ticketsGUI.java manipulates tickets in the system
 * 
 */
//import needed libraries
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

@SuppressWarnings("serial")
// classs ticketsGUI extends JFrame
public class ticketsGUI extends JFrame implements ActionListener {
	private JFrame frameMain;
	private JMenu file = new JMenu("File");
	private JMenu tickets = new JMenu("Tickets");
	int idTicket;
	JScrollPane scroll = null;
	private JLabel statusLabel;
	// otions to manipulate tickets
	JMenuItem logout;
	JMenuItem newTicket;
	JMenuItem view;
	JMenuItem del;
	JMenuItem aView;
	JMenuItem adminEnd;
	JMenuItem adminChange;
	JMenuItem update;

	public ticketsGUI(final String name) {
		// calling methods
		tableNew();
		menu(name);
		gui();
	}

	// create new table - GNasybullina1
	private void tableNew() {
		final String createTable = "CREATE TABLE GNasybullina1(ticket_id INT AUTO_INCREMENT PRIMARY KEY, ticket_name VARCHAR(30), ticket_description VARCHAR(200), start_date VARCHAR(200), end_date VARCHAR(200), ticket_status VARCHAR(10))";
		Connection connect = null;
		Statement statement = null;
		// connect to DB
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connect = DriverManager
					.getConnection("jdbc:mysql://www.papademas.net:3306/tickets?user=fp411&password=411&useSSL=false");
			statement = connect.createStatement();
			// create a table
			statement.executeUpdate(createTable);
			System.out.println("Table created");
			// close connection
			statement.close();
			connect.close();
			// catch exception
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	// menu method
	private void menu(final String name) {
		file.setMnemonic('F');
		// logout, exit application
		logout = new JMenuItem("Log out");
		logout.setMnemonic('x');
		file.add(logout);
		// create a new ticket
		newTicket = new JMenuItem("New ticket");
		newTicket.setMnemonic('x');
		tickets.add(newTicket);
		// view all tickets
		view = new JMenuItem("View tickets");
		view.setMnemonic('x');
		tickets.add(view);
		// delete ticket
		del = new JMenuItem("Delete ticket");
		del.setMnemonic('x');
		tickets.add(del);

		update = new JMenuItem("Update ticket");
		update.setMnemonic('x');
		tickets.add(update);

		// opltion for admins only: change status of a ticket
		adminChange = new JMenuItem("Admin only: change status of the ticket");
		adminChange.setMnemonic('x');
		tickets.add(adminChange);
		// option for admins only: end ticket
		adminEnd = new JMenuItem("Admin only: end ticket");
		adminEnd.setMnemonic('x');
		tickets.add(adminEnd);

		// listeners added
		logout.addActionListener(this);
		newTicket.addActionListener(this);
		view.addActionListener(this);
		del.addActionListener(this);
		update.addActionListener(this);
		adminEnd.addActionListener(this);
		adminChange.addActionListener(this);
		// options for admin only
		if (name.equalsIgnoreCase("admin")) {
			logout.setVisible(true);
			newTicket.setVisible(false);
			view.setVisible(true);
			del.setVisible(true);
			update.setVisible(true);
			adminEnd.setVisible(true);
			adminChange.setVisible(true);
			// options for all other users
		} else {
			logout.setVisible(true);
			newTicket.setVisible(true);
			view.setVisible(false);
			del.setVisible(false);
			update.setVisible(true);
			adminEnd.setVisible(false);
			adminChange.setVisible(false);
		}
		// new status label
		statusLabel = new JLabel();
		// show username
		statusLabel.setText("Hello, " + name);
	}

	// gui method
	private void gui() {
		// new frame object
		frameMain = new JFrame("Trouble Ticket System");
		frameMain.add(statusLabel);
		// create menu
		JMenuBar menuBar = new JMenuBar();
		// add file and tickets options
		menuBar.add(file);
		menuBar.add(tickets);
		// add menu bar component to frame
		frameMain.setJMenuBar(menuBar);
		// set size
		frameMain.setSize(1400, 1400);
		frameMain.setVisible(true);
	}

	public void actionPerformed(ActionEvent event) {
		// TODO Auto-generated method stub

		// when user presses logout button, exit the system
		if (event.getSource() == logout) {
			System.exit(0);
			// if ctreated new ticket
		} else if (event.getSource() == newTicket) {
			// ask for name and describe ticket
			try {
				String nameTicket;
				String description;
				// add new ticket info
				do {
					nameTicket = JOptionPane.showInputDialog(null, "Enter your name: ");
				} while (nameTicket.equalsIgnoreCase(""));
				do {
					description = JOptionPane.showInputDialog(null, "Enter your problem: ");
				} while (nameTicket.equalsIgnoreCase(""));
				Class.forName("com.mysql.jdbc.Driver").newInstance();
				// connect to DB
				Connection connection = DriverManager.getConnection(
						"jdbc:mysql://www.papademas.net:3306/tickets?user=fp411&password=411&useSSL=false");

				System.out.println("Connected!");
				Statement statement = connection.createStatement();
				Random r = new Random();
				idTicket = r.nextInt(100000);
				// show initial status of a ticket
				String statusInit = "Open";
				// date when created
				java.util.Date cretedDate = new java.util.Date();
				long t = cretedDate.getTime();
				java.sql.Date d = new java.sql.Date(t);
				java.sql.Time t2 = new java.sql.Time(t);
				java.sql.Timestamp stamp = new java.sql.Timestamp(t);
				// update table values
				int res = statement.executeUpdate(
						"Insert into GNasybullina1(ticket_id, ticket_name, ticket_description, start_date, ticket_status) values('"
								+ idTicket + "','" + nameTicket + "','" + description + "','" + stamp + "','"
								+ statusInit + "')");
				// show if ticket is created or not
				if (res != 0) {
					System.out.println("Ticket is created now");
				} else {
					System.out.println("Cannot create a ticket");
				}
				// show ticket ID
				JOptionPane.showMessageDialog(null, "Ticket ID: " + idTicket);
				// catch exception
			} catch (SQLException exception) {
				// TODO Auto-generated catch block
				exception.printStackTrace();
			} catch (InstantiationException esception1) {
				// TODO Auto-generated catch block
				esception1.printStackTrace();
			} catch (IllegalAccessException exception1) {
				// TODO Auto-generated catch block
				exception1.printStackTrace();
			} catch (ClassNotFoundException exception1) {
				// TODO Auto-generated catch block
				exception1.printStackTrace();
			}
		} else if (event.getSource() == view) {
			// show ticket information
			try {
				Class.forName("com.mysql.jdbc.Driver").newInstance();
				// connect to DB
				Connection connection = DriverManager.getConnection(
						"jdbc:mysql://www.papademas.net:3306/tickets?user=fp411&password=411&useSSL=false");
				Statement statement = connection.createStatement();
				ResultSet results = statement.executeQuery("SELECT * FROM GNasybullina1");
				// display table model
				JTable jt = new JTable(ticketsJTable.buildTableModel(results));

				jt.setBounds(600, 600, 1100, 1900);
				scroll = new JScrollPane(jt);
				frameMain.add(scroll);
				frameMain.setVisible(true);
				statement.close();
				// close connection
				connection.close();
				// catch exceptions
			} catch (InstantiationException exception) {
				// TODO Auto-generated catch block
				exception.printStackTrace();
			} catch (IllegalAccessException exception1) {
				// TODO Auto-generated catch block
				exception1.printStackTrace();
			} catch (ClassNotFoundException exception1) {
				// TODO Auto-generated catch block
				exception1.printStackTrace();
			} catch (SQLException exception1) {
				// TODO Auto-generated catch block
				exception1.printStackTrace();
			}
		}

		// when user presses delete button...
		else if (event.getSource() == del) {
			try {

				String ticketDel;
				String conf;
				// ask for ticket ID
				do {
					ticketDel = JOptionPane.showInputDialog(null, "Enter ID of a ticket that you want to delete: ");
				} while (ticketDel.equalsIgnoreCase(""));
				// make sure user want to delete ticket
				conf = JOptionPane.showInputDialog(null, "Are you sure you want to delete Ticket# " + ticketDel + "?"
						+ "\n\n Type 'OK' to delete this ticket.");
				// when OK:
				if (conf.equalsIgnoreCase("OK")) {
					Class.forName("com.mysql.jdbc.Driver").newInstance();
					// connect to DB
					Connection connection = DriverManager.getConnection(
							"jdbc:mysql://www.papademas.net:3306/tickets?user=fp411&password=411&useSSL=false");
					System.out.println("Connected");
					Statement statement = connection.createStatement();
					// delete from table
					int res = statement.executeUpdate("DELETE FROM GNasybullina1 WHERE ticket_id = " + ticketDel);
					// show message to indicate if ticket was deleted or not
					if (res != 0) {
						System.out.println("Ticket is deleted now");
					} else {
						System.out.println("Ticket is not deleted");
					}

					// show new status of a ticket
					JOptionPane.showMessageDialog(null, "You deleted Ticket# " + ticketDel);
				}
				// if entered not - OK
				else {
					JOptionPane.showMessageDialog(null, "Please try again, enter OK to delete a ticket");
				}
				// catch exceptions
			} catch (SQLException exception) {
				// TODO Auto-generated catch block
				exception.printStackTrace();
			} catch (InstantiationException exception1) {
				// TODO Auto-generated catch block
				exception1.printStackTrace();
			} catch (IllegalAccessException exception1) {
				// TODO Auto-generated catch block
				exception1.printStackTrace();
			} catch (ClassNotFoundException exception1) {
				// TODO Auto-generated catch block
				exception1.printStackTrace();
			}

		}
		// if user wants to update ticket description
		else if (event.getSource() == update) {
			try {
				String ticketUpd;
				String conf;
				// ask for ticket ID
				do {
					ticketUpd = JOptionPane.showInputDialog(null, "Enter ID of a ticket that you want to update: ");
				} while (ticketUpd.equalsIgnoreCase(""));
				// make sure user want to update a ticket
				conf = JOptionPane.showInputDialog(null, "Are you sure you want to update Ticket# " + ticketUpd + "?"
						+ "\n\n Type 'OK' to update this ticket.");
				// when OK:
				if (conf.equalsIgnoreCase("OK")) {
					Class.forName("com.mysql.jdbc.Driver").newInstance();
					// connect to DB
					Connection connection = DriverManager.getConnection(
							"jdbc:mysql://www.papademas.net:3306/tickets?user=fp411&password=411&useSSL=false");
					System.out.println("Connected");
					Statement statement = connection.createStatement();

					String updStatus = JOptionPane.showInputDialog(null, "Update your problem: ");

					// update table
					int res = statement.executeUpdate("UPDATE GNasybullina1 SET ticket_description = '" + updStatus
							+ "' WHERE ticket_id = '" + ticketUpd + "'");

					// show message to indicate if ticket was updated or not
					if (res != 0) {
						System.out.println("Ticket is updated now");
					} else {
						System.out.println("Ticket was not updated");
					}

					// show new status of a ticket
					JOptionPane.showMessageDialog(null,
							"You updated Ticket# " + ticketUpd + "\nNew description: " + updStatus);
				}
				// if entered not - OK
				else {
					JOptionPane.showMessageDialog(null, "Please try again, enter OK to update a ticket");
				}
				// catch exceptions
			} catch (SQLException exception) {
				// TODO Auto-generated catch block
				exception.printStackTrace();
			} catch (InstantiationException exception1) {
				// TODO Auto-generated catch block
				exception1.printStackTrace();
			} catch (IllegalAccessException exception1) {
				// TODO Auto-generated catch block
				exception1.printStackTrace();
			} catch (ClassNotFoundException exception1) {
				// TODO Auto-generated catch block
				exception1.printStackTrace();
			}
		}

		// if admin wants to end a ticket
		else if (event.getSource() == adminEnd) {
			// get ticket ID and confirm
			try {
				String endTicketID;
				String conf;
				// enter ticket ID
				do {
					endTicketID = JOptionPane.showInputDialog(null, "Admin only: enter ID of a ticket you want to end");
				} while (endTicketID.equalsIgnoreCase(""));
				// confirm - type OK
				conf = JOptionPane.showInputDialog(null, "Are you sure you want to end Ticket# " + endTicketID + "?"
						+ "\n\n Type 'OK' to delete this ticket.");
				if (conf.equalsIgnoreCase("OK")) {
					Class.forName("com.mysql.jdbc.Driver").newInstance();
					// connect to DB
					Connection connection = DriverManager.getConnection(
							"jdbc:mysql://www.papademas.net:3306/tickets?user=fp411&password=411&useSSL=false");

					System.out.println("Connected");
					Statement statement = connection.createStatement();
					String statusF = "Closed";
					java.util.Date d = new java.util.Date();
					long t = d.getTime();
					java.sql.Date day = new java.sql.Date(t);
					java.sql.Time t2 = new java.sql.Time(t);
					java.sql.Timestamp timestamp = new java.sql.Timestamp(t);
					// update ticket status
					int res = statement.executeUpdate("UPDATE GNasybullina1 SET end_date = '" + timestamp
							+ "' WHERE ticket_id = '" + endTicketID + "'");
					int res2 = statement.executeUpdate("UPDATE GNasybullina1 SET ticket_status = '" + statusF
							+ "' WHERE ticket_id = '" + endTicketID + "'");
					if (res != 0) {
						System.out.println("Ticket is deleted now");
					} else {
						System.out.println("Cannot delete a ticket");
					}
					if (res2 != 0) {
						System.out.println("Ticket updated");
					} else {
						System.out.println("Cannot update a ticket");
					}
					// show message to indicate that ticket was ended
					JOptionPane.showMessageDialog(null, "Ticket# " + endTicketID + " Ended");
				}

				// try again
				else {
					JOptionPane.showMessageDialog(null, "Please try again, enter OK");
				}
			} catch (SQLException exception) {
				// TODO Auto-generated catch block
				exception.printStackTrace();
			} catch (InstantiationException exception1) {
				// TODO Auto-generated catch block
				exception1.printStackTrace();
			} catch (IllegalAccessException exception1) {
				// TODO Auto-generated catch block
				exception1.printStackTrace();
			} catch (ClassNotFoundException exception1) {
				// TODO Auto-generated catch block
				exception1.printStackTrace();
			}
		}
		// if admin wants to change ticket
		else if (event.getSource() == adminChange) {

			try {

				String changeTicketID;
				String statusTo;
				do {
					changeTicketID = JOptionPane.showInputDialog(null,
							"Admin only: please enter ID of a ticket you want to change: ");
				} while (changeTicketID.equalsIgnoreCase(""));
				// shoose otion to review, open, close or delete
				do {
					statusTo = JOptionPane.showInputDialog(null,
							"Admin only: choose any option below: \n\n " + "Review, Open, Closed, or Deleted");
				} while (statusTo.equalsIgnoreCase(""));
				if (statusTo.equalsIgnoreCase("Review") || statusTo.equalsIgnoreCase("Open")
						|| statusTo.equalsIgnoreCase("Closed") || statusTo.equalsIgnoreCase("Deleted")) {
					Class.forName("com.mysql.jdbc.Driver").newInstance();
					// connect to DB
					Connection connection = DriverManager.getConnection(
							"jdbc:mysql://www.papademas.net:3306/tickets?user=fp411&password=411&useSSL=false");

					System.out.println("Connected");
					Statement st = connection.createStatement();
					// update table
					int res = st.executeUpdate("UPDATE GNasybullina1 SET ticket_status = '" + statusTo
							+ "' WHERE ticket_id = '" + changeTicketID + "'");
					// show message
					if (res != 0) {
						System.out.println("Status of a ticket is changed now");
					} else {
						System.out.println("Cannot change status of a ticket");
					}
					// show message what status ticket was changed to
					JOptionPane.showMessageDialog(null,
							"Ticket# " + changeTicketID + " Stauts Changed to: " + statusTo);
				} else {
					JOptionPane.showMessageDialog(null, "Try again!");
				}
				// catch errors
			} catch (SQLException exception) {
				// TODO Auto-generated catch block
				exception.printStackTrace();
			} catch (InstantiationException exception1) {
				// TODO Auto-generated catch block
				exception1.printStackTrace();
			} catch (IllegalAccessException exception1) {
				// TODO Auto-generated catch block
				exception1.printStackTrace();
			} catch (ClassNotFoundException exception1) {
				// TODO Auto-generated catch block
				exception1.printStackTrace();
			}
		}

	}

}
