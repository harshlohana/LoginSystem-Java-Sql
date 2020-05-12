import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


class Login extends JFrame implements ActionListener {
	JLabel username=new JLabel("Username : ");
	JLabel password=new JLabel("Password : ");
	JLabel message=new JLabel("   ");
	JTextField usertext=new JTextField();
	JPasswordField userpass=new JPasswordField();
	JButton signin=new JButton("SignIn");
	JButton signup=new JButton("SignUp");
	JButton clear=new JButton("Clear");
	ImageIcon icon=new ImageIcon("profile.png");
	JLabel profile=new JLabel(icon);
	Container c;


	
	public Login() {
		
		c=this.getContentPane();
		c.setLayout(null);
		
		username.setBounds(30,5,100,60);
		password.setBounds(30,45,90,70);
		usertext.setBounds(130,25,150,25);
		userpass.setBounds(130,65,150,25);
		signin.setBounds(20,140,80,25);
		signup.setBounds(110,140,80,25);
		clear.setBounds(200,140,80,25);
		message.setBounds(20,145,200,100);
		profile.setBounds(350,50,100,100);


		signin.addActionListener(this);
		signup.addActionListener(this);
		clear.addActionListener(this);
		
		c.add(username);
		c.add(password);
		c.add(usertext);
		c.add(userpass);
		c.add(signin);
		c.add(signup);
		c.add(clear);
		c.add(message);
		c.add(profile);
		


	} //login constructor closed

	public void actionPerformed(ActionEvent e){
		if(e.getSource()==signin){
			String name=usertext.getText();
			String pass=userpass.getText();
			System.out.println(name +" "+pass);
			

			try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(
                "jdbc:mysql://127.0.0.1:3306/student", "root", "12345");
				System.out.println("Connected to Database");
				String sql="SELECT * FROM login WHERE usertext=? AND userpass=? ;";
				PreparedStatement pstmt =conn.prepareStatement(sql);
				pstmt.setString(1,name);
				pstmt.setString(2,pass);
				


				ResultSet rs = pstmt.executeQuery();
				//JOptionPane.showInputBox(null,"Add Records Successfully");
				System.out.println(rs.next());

				if(rs.next()){
				message.setText("User Loggedin");
				message.setForeground(Color.green);
				usertext.setText("");
				userpass.setText("");

				} //if closed
				else{
				message.setText("Wrong Username or Password");
				message.setForeground(Color.red);
				usertext.setText("");
				userpass.setText("");
				} //else closed
				conn.close();
				
				} //try closed
				 catch(Exception e2) {
				e2.printStackTrace();

				} //catch closed


			} // if closed

		if(e.getSource()==signup){



			try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(
                "jdbc:mysql://127.0.0.1:3306/student", "root", "12345");
				System.out.println("Connected to Database");
				String sql="INSERT INTO login VALUES (?,?)";
				PreparedStatement pstmt =conn.prepareStatement(sql);
				pstmt.setString(1,usertext.getText());
				pstmt.setString(2,userpass.getText());
				


				pstmt.executeUpdate();
				//JOptionPane.showInputBox(null,"Add Records Successfully");
				conn.close();
				message.setText("User Added");
				message.setForeground(Color.orange);
				usertext.setText("");
				userpass.setText("");

			} //try closed
			catch(Exception e2) {
			e2.printStackTrace();

			} //catch closed


		} //try closed

		 if(e.getSource()==clear) {
			usertext.setText("");
			userpass.setText("");
			message.setText("");
			
		} //if closed

	} //actionPerformed closed
	
}  //class Login closed  	 

       

class LoginMain {
	public static void main(String[] args){
		Login login=new Login();
		login.setTitle("Employee Login");
		login.setBounds(100,100,500,250);
		login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		login.setVisible(true);

	} //main Closed
}//Login Closed


//set classpath=C:\Program Files (x86)\MySQL\Connector J 8.0\mysql-connector-java-8.0.19.jar;.; (plz execute this command to set classpath to your jdbc connection driver)