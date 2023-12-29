package bank.managemant.system;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Login implements ActionListener {
	JFrame frame;
	JLabel label;
	ImageIcon icon;
	JButton signIn, signUp, clear;
	JTextField cardNo;
	JPasswordField pin;
	JLabel heading, cardLabel, pinLabel;

	Login() throws IOException {
		frame = new JFrame("AUTOMATED TELLER MACHINE");
		frame.setLayout(null);
		frame.setSize(700, 500);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(3);
		frame.setResizable(false);

		/* change the icon image of JFrame */
		Toolkit t = Toolkit.getDefaultToolkit();
		Image icon = t.getImage("C:\\Users\\deepanshu\\eclipse-workspace\\ATM Simulartor System\\src\\bank\\managemant\\system\\bank_1.png");
		frame.setIconImage(icon);

		/* set the image in JFrame body */
		BufferedImage bufferedImage = ImageIO.read(new File("C:\\Users\\deepanshu\\eclipse-workspace\\ATM Simulartor System\\src\\bank\\managemant\\system\\bank_1.png"));
		Image img = bufferedImage.getScaledInstance(80, 80, Image.SCALE_DEFAULT);

		/* convert Image to ImageIcon, JLabel not accepted Image as argument; */
		ImageIcon imgIcon = new ImageIcon(img);
		label = new JLabel(imgIcon);
		label.setBounds(100, 20, 100, 100);

		heading = new JLabel("Welcone to ATM");
		heading.setFont(new Font("Osword", Font.BOLD, 38));
		heading.setBounds(250, 40, 300, 70);

		cardLabel = new JLabel("Card no.");
		cardLabel.setFont(new Font("Osword", Font.BOLD, 22));
		cardLabel.setBounds(130, 160, 300, 40);

		pinLabel = new JLabel("PIN");
		pinLabel.setFont(new Font("Osword", Font.BOLD, 22));
		pinLabel.setBounds(130, 220, 300, 40);

		cardNo = new JTextField();
		cardNo.setBounds(300, 166, 220, 28);
		cardNo.setFont(new Font("Arial", Font.PLAIN, 18));

		pin = new JPasswordField();
		pin.setEchoChar('*');
		pin.setBounds(300, 226, 220, 28);
		pin.setFont(new Font("Arial", Font.PLAIN, 18));

		signIn = new JButton("Sign In");
		signIn.setBounds(210, 300, 80, 35);
		signIn.setBackground(Color.BLACK);
		signIn.setForeground(Color.WHITE);
		signIn.addActionListener(this);

		signUp = new JButton("Sign Up");
		signUp.setBounds(350, 300, 80, 35);
		signUp.setBackground(Color.BLACK);
		signUp.setForeground(Color.WHITE);
		signUp.addActionListener(this);

		clear = new JButton("Clear");
		clear.setBounds(260, 360, 120, 35);
		clear.setBackground(Color.BLACK);
		clear.setForeground(Color.WHITE);
		clear.addActionListener(this);

		frame.add(label);
		frame.add(heading);
		frame.add(cardLabel);
		frame.add(pinLabel);
		frame.add(cardNo);
		frame.add(pin);
		frame.add(signIn);
		frame.add(signUp);
		frame.add(clear);
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == signIn) {
			Connection con = new MyConnection().getConnection();
			int pinNo = Integer.parseInt(this.pin.getText());
			Long cardNo = Long.parseLong(this.cardNo.getText());
			try {
				String query = "select * from card_details where cardNo=" + cardNo + " and pinNo=" + pinNo+" ";
				Statement s = con.createStatement();
				ResultSet rs = s.executeQuery(query);
				if (rs.next()) {
					frame.setVisible(false);
					Transactions t = new Transactions(cardNo, pinNo);
					t.frame.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(frame, "Invalid Information");
				}
			} catch (Exception es) {
				System.out.println(es);
			}
		}
		if (e.getSource() == signUp) {
			frame.setVisible(false);
			new SignUpFirst().frame.setVisible(true);
		}
		if (e.getSource() == clear) {
			cardNo.setText("");
			pin.setText("");
		}
	}
	
	/* main method */
	public static void main(String[] args) {
		try {
			new Login();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
}
