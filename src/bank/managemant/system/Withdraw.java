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
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Withdraw implements ActionListener {
	JFrame frame;
	JLabel main, label_1, withdrawLabel, backLable;
	JButton withdraw, back;
	JTextField amount;
	Long cardNo;
	int pinNo;

	Withdraw(Long cardNo, int pinNo2) {
		this.cardNo = cardNo;
		this.pinNo = pinNo2;
		frame = new JFrame("set title");
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

		label_1 = new JLabel("Enter the amount you want to Withdraw");
		label_1.setBounds(170, 250, 270, 30);
		label_1.setFont(new Font("", Font.BOLD, 14));
		label_1.setForeground(Color.blue);
		main.add(label_1);

		amount = new JTextField("0");
		amount.setBounds(185, 290, 200, 30);
		amount.setFont(new Font("", Font.BOLD, 14));
		amount.setForeground(Color.blue);
		main.add(amount);

		withdraw = new JButton();
		withdraw.setBounds(441, 358, 25, 16);
		withdraw.addActionListener(this);

		back = new JButton();
		back.setBounds(441, 378, 25, 16);
		back.addActionListener(this);

		main.add(withdraw);
		main.add(back);

		withdrawLabel = new JLabel("Withdraw");
		withdrawLabel.setBounds(370, 350, 100, 30);

		backLable = new JLabel("Back");
		backLable.setBounds(380, 370, 100, 30);

		main.add(withdrawLabel);
		main.add(backLable);

		frame.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent es) {
		if (es.getSource().equals(withdraw)) {
			Long num = Long.parseLong(amount.getText());

			if (num < 1) {
				JOptionPane.showMessageDialog(frame, "Please Enter amount");
			} else {
				try (Connection con = new MyConnection().getConnection()) {
					Statement s = con.createStatement();
					String fetchAmount = "select amount from total_amount where cardNo=" + cardNo + "";
					ResultSet rs = s.executeQuery(fetchAmount);
					Long totalAmount = 0L;
					while (rs.next()) {
						totalAmount = rs.getLong(1);
					}
					if (totalAmount > num) {
						String updateAmount ="update total_amount set amount ="+(totalAmount-num)+" where cardNo="+cardNo+"";
						s.executeUpdate(updateAmount);
						String date = LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MMM-yyyy"));
						String time = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
						String transactionStmt = "insert into bank(cardNo,date,time,type,amount) values('" + cardNo + "','" +date+ "','"+time + "'," + "'Withdraw'" + ",'"
								+ num + "')";
						int check = s.executeUpdate(transactionStmt);
						if (check > 0) {
							JOptionPane.showMessageDialog(frame, "Rs " + num + " Withdraw Successfully");
							frame.setVisible(false);
							Transactions t = new Transactions(cardNo, pinNo);
							t.frame.setVisible(true);
						}
					}else {
						JOptionPane.showMessageDialog(frame, "Not sufficient money");
					}
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
}
