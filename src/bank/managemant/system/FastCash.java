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
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class FastCash implements ActionListener {
	JFrame frame;
	JLabel main, label_1;
	JLabel l1, l2, l3, l4, l5, l6, l7, l8;
	JButton b1, b2, b3, b4, b5, b6, b7, b8;
	Long cardNo;
	int pinNo;

	public FastCash(Long cardNo, int pinNo) {
		this.pinNo = pinNo;
		this.cardNo = cardNo;
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

		label_1 = new JLabel("Please Select Your Amount");
		label_1.setBounds(210, 250, 230, 30);
		label_1.setFont(new Font("", Font.BOLD, 14));
		label_1.setForeground(Color.blue);
		main.add(label_1);

		b1 = new JButton();
		b1.setBounds(125, 318, 25, 16);
		b1.addActionListener(this);

		b2 = new JButton();
		b2.setBounds(125, 338, 25, 16);
		b2.addActionListener(this);

		b3 = new JButton();
		b3.setBounds(125, 358, 25, 16);
		b3.addActionListener(this);

		b4 = new JButton();
		b4.setBounds(125, 378, 25, 16);
		b4.addActionListener(this);

		b5 = new JButton();
		b5.setBounds(441, 318, 25, 16);
		b5.addActionListener(this);

		b6 = new JButton();
		b6.setBounds(441, 338, 25, 16);
		b6.addActionListener(this);

		b7 = new JButton();
		b7.setBounds(441, 358, 25, 16);
		b7.addActionListener(this);

		b8 = new JButton();
		b8.setBounds(441, 378, 25, 16);
		b8.addActionListener(this);

		main.add(b1);
		main.add(b2);
		main.add(b3);
		main.add(b4);
		main.add(b5);
		main.add(b6);
		main.add(b7);
		main.add(b8);

		l1 = new JLabel("100");
		l1.setBounds(170, 310, 100, 30);

		l2 = new JLabel("200");
		l2.setBounds(170, 330, 100, 30);

		l3 = new JLabel("500");
		l3.setBounds(170, 350, 100, 30);

		l4 = new JLabel("1000");
		l4.setBounds(170, 370, 100, 30);

		l5 = new JLabel("5000");
		l5.setBounds(395, 310, 100, 30);

		l6 = new JLabel("10000");
		l6.setBounds(390, 330, 100, 30);

		l7 = new JLabel("Custom");
		l7.setBounds(380, 350, 100, 30);

		l8 = new JLabel("Exit");
		l8.setBounds(395, 370, 100, 30);

		main.add(l1);
		main.add(l2);
		main.add(l3);
		main.add(l4);
		main.add(l5);
		main.add(l6);
		main.add(l7);
		main.add(l8);

		frame.setUndecorated(false);
		frame.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String date = LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MMM-yyyy"));
		String time = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
		
		try (Connection con = new MyConnection().getConnection()) {
			Statement s = con.createStatement();
			String fetchAmount = "select amount from total_amount where cardNo=" + cardNo + "";
			ResultSet rs = s.executeQuery(fetchAmount);
			Long totalAmount = 0L;
			while (rs.next()) {
				totalAmount = rs.getLong(1);
			}

			
			
			if (e.getSource() == b1) {
				int num = 100;
				if (totalAmount >= num) {
					String updateAmount = "update total_amount set amount =" + (totalAmount - num) + "";
					s.executeUpdate(updateAmount);
					String transactionStmt = "insert into bank(cardNo,date,time,type,amount) values('" + cardNo + "','" +date+ "','"+time + "'," + "'Withdraw'" + ",'"
							+ num + "')";
					int check = s.executeUpdate(transactionStmt);
					if (check > 0) {
						JOptionPane.showMessageDialog(frame, "Rs " + num + " Withdraw Successfully");
						frame.setVisible(false);
						new Transactions(cardNo, pinNo).frame.setVisible(true);
					}
				} else {
					JOptionPane.showMessageDialog(frame, "Not sufficient money");
				}
			}
			
			
			if (e.getSource() == b2) {
				int num = 200;
				if (totalAmount > num) {
					String updateAmount = "update total_amount set amount =" + (totalAmount - num) + "";
					s.executeUpdate(updateAmount);
					String transactionStmt = "insert into bank(cardNo,date,time,type,amount) values('" + cardNo + "','" +date+ "','"+time + "'," + "'Withdraw'" + ",'"
							+ num + "')";
					int check = s.executeUpdate(transactionStmt);
					if (check > 0) {
						JOptionPane.showMessageDialog(frame, "Rs " + num + " Withdraw Successfully");
						frame.setVisible(false);
						new Transactions(cardNo, pinNo).frame.setVisible(true);
					}
				} else {
					JOptionPane.showMessageDialog(frame, "Not sufficient money");
				}
			}

			if (e.getSource() == b3) {
				int num = 500;
				if (totalAmount > num) {
					String updateAmount = "update total_amount set amount =" + (totalAmount - num) + "";
					s.executeUpdate(updateAmount);
					String transactionStmt = "insert into bank(cardNo,date,time,type,amount) values('" + cardNo + "','" +date+ "','"+time + "'," + "'Withdraw'" + ",'"
							+ num + "')";
					int check = s.executeUpdate(transactionStmt);
					if (check > 0) {
						JOptionPane.showMessageDialog(frame, "Rs " + num + " Withdraw Successfully");
						frame.setVisible(false);
						new Transactions(cardNo, pinNo).frame.setVisible(true);
					}
				} else {
					JOptionPane.showMessageDialog(frame, "Not sufficient money");
				}
			}
			
			
			if (e.getSource() == b4) {
				int num = 1000;
				if (totalAmount > num) {
					String updateAmount = "update total_amount set amount =" + (totalAmount - num) + "";
					s.executeUpdate(updateAmount);
					String transactionStmt = "insert into bank(cardNo,date,time,type,amount) values('" + cardNo + "','" +date+ "','"+time + "'," + "'Withdraw'" + ",'"
							+ num + "')";
					int check = s.executeUpdate(transactionStmt);
					if (check > 0) {
						JOptionPane.showMessageDialog(frame, "Rs " + num + " Withdraw Successfully");
						frame.setVisible(false);
						new Transactions(cardNo, pinNo).frame.setVisible(true);
					}
				} else {
					JOptionPane.showMessageDialog(frame, "Not sufficient money");
				}
			}
			
			if (e.getSource() == b5) {
				int num = 5000;
				if (totalAmount > num) {
					String updateAmount = "update total_amount set amount =" + (totalAmount - num) + "";
					s.executeUpdate(updateAmount);
					String transactionStmt = "insert into bank(cardNo,date,time,type,amount) values('" + cardNo + "','" +date+ "','"+time + "'," + "'Withdraw'" + ",'"
							+ num + "')";
					int check = s.executeUpdate(transactionStmt);
					if (check > 0) {
						JOptionPane.showMessageDialog(frame, "Rs " + num + " Withdraw Successfully");
						frame.setVisible(false);
						new Transactions(cardNo, pinNo).frame.setVisible(true);
					}
				} else {
					JOptionPane.showMessageDialog(frame, "Not sufficient money");
				}
			}
			
			if (e.getSource() == b6) {
				int num = 10000;
				if (totalAmount > num) {
					String updateAmount = "update total_amount set amount =" + (totalAmount - num) + "";
					s.executeUpdate(updateAmount);
					String transactionStmt = "insert into bank(cardNo,date,time,type,amount) values('" + cardNo + "','" +date+ "','"+time + "'," + "'Withdraw'" + ",'"
							+ num + "')";
					int check = s.executeUpdate(transactionStmt);
					if (check > 0) {
						JOptionPane.showMessageDialog(frame, "Rs " + num + " Withdraw Successfully");
						frame.setVisible(false);
						new Transactions(cardNo, pinNo).frame.setVisible(true);
					}
				} else {
					JOptionPane.showMessageDialog(frame, "Not sufficient money");
				}
			}
			
			
			if (e.getSource() == b7) {
				frame.setVisible(false);
				new Withdraw(totalAmount, pinNo).frame.setVisible(true);
			}
			if (e.getSource() == b8) {
				System.exit(0);
			}

		}catch (Exception e2) {
			System.out.println(e2);
		}
	}

	public static void main(String[] args) {
		new FastCash(89606584827510L, 1245);
	}

}
