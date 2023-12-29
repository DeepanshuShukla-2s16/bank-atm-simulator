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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

public class BalanceEnquiry implements ActionListener {
	JFrame frame;
	JLabel main, label_1, balanceEnquiryLabel, backLable;
	JButton balanceEnquiry, back;
	JPasswordField pin;
	Long cardNo;
	int pinNo;

	BalanceEnquiry(Long cardNo, int pinNo2) {
		this.cardNo = cardNo;
		this.pinNo = pinNo2;
		frame = new JFrame("Balance Enquiry");
		frame.setSize(700, 680);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(3);
		frame.setResizable(false);
		frame.setLayout(null);
		
		/* change the icon image of JFrame */
		Toolkit t = Toolkit.getDefaultToolkit();
		Image icon = t.getImage("C:\\Users\\deepanshu\\eclipse-workspace\\ATM Simulartor System\\src\\bank\\managemant\\system\\bank_1.png");
		frame.setIconImage(icon);

		BufferedImage bufferedImage = null;
		try {
			bufferedImage = ImageIO.read(new File("C:\\Users\\deepanshu\\eclipse-workspace\\ATM Simulartor System\\src\\bank\\managemant\\system\\atm_1.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Image img = bufferedImage.getScaledInstance(700, 700, Image.SCALE_DEFAULT);

		/* convert Image to ImageIcon, Jlabel not accepted Image as args; */
		ImageIcon imgIcon = new ImageIcon(img);
		main = new JLabel(imgIcon);
		main.setBounds(0, 0, 700, 700);
		frame.add(main);

		label_1 = new JLabel("Please enter your PIN : ");
		label_1.setBounds(170, 270, 270, 30);
		label_1.setFont(new Font("", Font.BOLD, 14));
		label_1.setForeground(Color.blue);
		main.add(label_1);

		pin = new JPasswordField("");
		pin.setEchoChar('*');
		pin.setBounds(330, 273, 90, 30);
		pin.setFont(new Font("", Font.BOLD, 14));
		pin.setForeground(Color.blue);
		main.add(pin);

		balanceEnquiry = new JButton();
		balanceEnquiry.setBounds(441, 358, 25, 16);
		balanceEnquiry.addActionListener(this);

		back = new JButton();
		back.setBounds(441, 378, 25, 16);
		back.addActionListener(this);

		main.add(balanceEnquiry);
		main.add(back);

		balanceEnquiryLabel = new JLabel("Confirm");
		balanceEnquiryLabel.setBounds(380, 350, 100, 30);

		backLable = new JLabel("Back");
		backLable.setBounds(380, 370, 100, 30);

		main.add(balanceEnquiryLabel);
		main.add(backLable);

		frame.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent es) {
		if (es.getSource().equals(balanceEnquiry)) {
			int num =Integer.parseInt(pin.getText());

			if (num != pinNo) {
				JOptionPane.showMessageDialog(frame, "Please enter Valid PIN");
			} else {
				try (Connection con = new MyConnection().getConnection()) {
					String fetchAmount = "select amount from total_amount where cardNo=" + cardNo + "";
					PreparedStatement s = con.prepareStatement(fetchAmount);
					ResultSet rs = s.executeQuery();
					Long totalAmount = 0L;
					while (rs.next()) {
						totalAmount = rs.getLong(1);
					}
					String date = LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MMM-yyyy"));
					String time = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
					String transactionStmt = "insert into bank(cardNo,date,time,type,amount) values('" + cardNo + "','" +date+ "','"+time + "'," + "'Enquiry'" + ",'"+ num + "')";
					s.executeUpdate(transactionStmt);
					
					JOptionPane.showMessageDialog(frame, "Total Balance : "+totalAmount);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		if (es.getSource() == back) {
			frame.setVisible(false);
			new Transactions(cardNo, pinNo).frame.setVisible(true);
		}
	}
	public static void main(String[] args) {
		new BalanceEnquiry(89606584827510L,1245);
	}
}
