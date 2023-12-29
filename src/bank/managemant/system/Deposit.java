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

public class Deposit implements ActionListener {
	JFrame frame;
	JLabel main, label_1, depositLabel, backLable;
	JButton deposit, back;
	JTextField amount;
	Long cardNo;
	int pinNo;

	Deposit(Long cardNo, int pinNo) {
		this.pinNo = pinNo;
		this.cardNo = cardNo;
		frame = new JFrame("Deposit");
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

		label_1 = new JLabel("Enter the amount you want to deposit");
		label_1.setBounds(170, 250, 270, 30);
		label_1.setFont(new Font("", Font.BOLD, 14));
		label_1.setForeground(Color.blue);
		main.add(label_1);

		amount = new JTextField("0");
		amount.setBounds(185, 290, 200, 30);
		amount.setFont(new Font("", Font.BOLD, 14));
		amount.setForeground(Color.blue);
		main.add(amount);

		deposit = new JButton();
		deposit.setBounds(441, 358, 25, 16);
		deposit.addActionListener(this);

		back = new JButton();
		back.setBounds(441, 378, 25, 16);
		back.addActionListener(this);

		main.add(deposit);
		main.add(back);

		depositLabel = new JLabel("Deposit");
		depositLabel.setBounds(370, 350, 100, 30);

		backLable = new JLabel("Back");
		backLable.setBounds(380, 370, 100, 30);

		main.add(depositLabel);
		main.add(backLable);

		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent es) {
		if (es.getSource().equals(deposit)) {
			Long num = Long.parseLong(amount.getText());
			if (num < 1) {
				JOptionPane.showMessageDialog(frame, "Please Enter amount");
			} else {
				try (Connection con = new MyConnection().getConnection();) {
					Statement s = con.createStatement();
					String fetchAmount = "select amount from total_amount where cardNo =" + cardNo + "";
					ResultSet rs = s.executeQuery(fetchAmount);
				
					int flag=0;
					Long totalAmount = num;
					while (rs.next()) {
						flag++;
						totalAmount = totalAmount + rs.getLong(1);
					}
					if(flag==0) {
						String insertNewAmount = "insert into total_amount values(" + cardNo + " , " + totalAmount+ ")";
						s.executeUpdate(insertNewAmount);
					}else {
						String updateAmount = "update total_amount set amount =" + totalAmount + " where cardNo = " + cardNo+ "";
						s.executeUpdate(updateAmount);
					}
					
					String date = LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MMM-yyyy"));
					String time = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
					String transactionStmt = "insert into bank(cardNo,date,time,type,amount) values('" + cardNo + "','" +date+ "','"+time + "'," + "'Deposit'" + ",'"+ num + "')";
					
					int check = s.executeUpdate(transactionStmt);
					if (check > 0) {
						JOptionPane.showMessageDialog(frame, "Rs " + num + " Deposited Successfully");
						frame.setVisible(false);
						Transactions t = new Transactions(cardNo, pinNo);
						t.frame.setVisible(true);
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
	public static void main(String[] args) {
		new Deposit(89606584827510L, 1234);
	}
}
