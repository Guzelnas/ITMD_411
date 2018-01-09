
/*
 * Final Project - Trouble Ticket System
 * ITMD 411
 * Programmed by Guzel Nasybullina
 * 12.09.2017
 * Login.java authenticates users
 * 
 */
//import needed libraries:
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.*;

//login class
public class Login {
	// declare variables
	private JFrame frameMain;
	private JLabel labelHeader;
	private JLabel labelStatus;
	private JPanel panelControl;
	int id;
	String firstName;
	String lastName;
	String password;

	public Login() {
		// call gUI method
		gUI();
	}

	// main method
	public static void main(String[] args) {
		createTable();
		Login login = new Login();
		login.showTextField();
	}

	private static void createTable() {
		// create table
		final String createTable = "CREATE TABLE GNasybullina(name VARCHAR(30), username VARCHAR(30), password VARCHAR(200), date_created VARCHAR(200))";
		Connection connect = null;
		Statement statement = null;
		try {
			// load mysql driver
			Class.forName("com.mysql.jdbc.Driver");
			// connect to database
			connect = DriverManager
					.getConnection("jdbc:mysql://www.papademas.net:3306/tickets?user=fp411&password=411&useSSL=false");
			statement = connect.createStatement();
			// create table
			statement.executeUpdate(createTable);
			System.out.println("Created new table");
			// close connection
			statement.close();
			connect.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private void gUI() {
		// set parameters for GUI
		frameMain = new JFrame("Trouble Ticket System");
		frameMain.setSize(400, 400);
		frameMain.setLayout(new GridLayout(3, 2));
		frameMain.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent) {
				System.exit(0);
			}
		});
		labelHeader = new JLabel("", JLabel.CENTER);
		labelStatus = new JLabel("", JLabel.CENTER);
		labelStatus.setSize(350, 100);
		panelControl = new JPanel();
		panelControl.setLayout(new FlowLayout());
		frameMain.add(labelHeader);
		frameMain.add(panelControl);
		frameMain.add(labelStatus);
		frameMain.setVisible(true);
	}

	private void showTextField() {
		labelHeader.setText("Access account");
		JLabel nameL = new JLabel("ID: ", JLabel.RIGHT);
		JLabel passwordL = new JLabel("Password: ", JLabel.CENTER);
		final JTextField text = new JTextField(6);
		final JPasswordField passwordT = new JPasswordField(6);
		final JButton loginB = new JButton("Login");
		final JButton createAB = new JButton("Create Account");

		loginB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// username, password hardcoded for user and admin
				String correctU = lastName;
				String correctP = password;

				String admin = "admin";
				String adminPassword = "admin1";
				// get input
				String usernameNew = text.getText();
				String passwordNew = passwordT.getText();
				if (usernameNew.equals(correctU) && passwordNew.equals(correctP)) {
					// open main window if username and password are correct
					frameMain.dispose();
					new ticketsGUI(firstName);
				} else if (usernameNew.equals(admin) && passwordNew.equals(adminPassword)) {
					// open main window if username and password are correct for admin
					frameMain.dispose();
					firstName = "admin";
					new ticketsGUI(firstName);
				}
				// show error message id username or password is incorrect
				else {
					JOptionPane.showMessageDialog(null, "Incorrect password or username", "Error!",
							JOptionPane.PLAIN_MESSAGE);
				}
			}
		});
		// add action listener
		createAB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					do {
						firstName = JOptionPane.showInputDialog(null, "Enter your first name: ");
					} while (firstName.equals(""));

					do {
						lastName = JOptionPane.showInputDialog(null, "Enter your last name: ");
					} while (lastName.equals(""));

					do {
						password = JOptionPane.showInputDialog(null, "Enter a password: ");
					} while (password.equals(""));
					// greet new user, show his/her creditentials
					JOptionPane.showMessageDialog(null,
							"Hi, " + firstName + "\n\nYour username is: " + lastName + "\nYour password is: " + password
									+ "\n\nPlease use your new user name and password to log in",
							"Login Information", JOptionPane.PLAIN_MESSAGE);
					// use current date as start ticket date
					java.util.Date date = new java.util.Date();
					long time = date.getTime();
					java.sql.Date day = new java.sql.Date(time);
					java.sql.Time time2 = new java.sql.Time(time);
					java.sql.Timestamp timeStamp = new java.sql.Timestamp(time);

					Class.forName("com.mysql.jdbc.Driver").newInstance();
					// connect to DB
					Connection connection = DriverManager.getConnection(
							"jdbc:mysql://www.papademas.net:3306/tickets?user=fp411&password=411&useSSL=false");
					// establish connection
					System.out.println("Connected");
					Statement statement = connection.createStatement();
					// insert values to GNasybullina table
					statement.executeUpdate("Insert into GNasybullina (name, username, password, date_created) values('"
							+ firstName + "','" + lastName + "','" + password + "','" + timeStamp + "')");
					// catch exceptions
				} catch (SQLException exception) {
					// TODO Auto-generated catch block
					exception.printStackTrace();
				} catch (InstantiationException except) {
					// TODO Auto-generated catch block
					except.printStackTrace();
				} catch (IllegalAccessException except) {
					// TODO Auto-generated catch block
					except.printStackTrace();
				} catch (ClassNotFoundException execpt) {
					// TODO Auto-generated catch block
					execpt.printStackTrace();
				}
			}
		});
		panelControl.add(nameL);
		panelControl.add(text);
		panelControl.add(passwordL);
		panelControl.add(passwordT);
		panelControl.add(loginB);
		panelControl.add(createAB);
		panelControl.setBackground(Color.PINK);
		frameMain.setVisible(true);
	}
}