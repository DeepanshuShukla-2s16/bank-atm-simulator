package bank.managemant.system;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Random;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;

public class SignUpThird implements ActionListener {
	JButton submit;
	JFrame frame;
	JLabel accountDetail, accType, cardNo, card, pinNo, pin, cardDetail, pinDetail, services;
	JCheckBox c1, c2, c3, c4, c5, c6, accept;
	JRadioButton saving, fdAccount, current, recurring;
	ButtonGroup accountType;
	long cardNumber;
	int formNumber;

	SignUpThird(int formNumber) {
		this.formNumber = formNumber;
		frame = new JFrame("Application Forn No 3");
		frame.setSize(700, 670);
		frame.setLayout(null);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(0);
		frame.setLocationRelativeTo(null);
		
		/* change the icon image of JFrame */
		Toolkit t = Toolkit.getDefaultToolkit();
		Image icon = t.getImage("C:\\Users\\deepanshu\\eclipse-workspace\\ATM Simulartor System\\src\\bank\\managemant\\system\\bank_1.png");
		frame.setIconImage(icon);

		Random ran = new Random();
		cardNumber = Math.abs(ran.nextLong() % 90000000L) + 89606534000000L;
		int cardFour = (int) (cardNumber % 10000);

		accountDetail = new JLabel("Page 3 : Account Details ");
		accountDetail.setFont(new Font("", Font.BOLD, 16));
		accountDetail.setBounds(250, 60, 400, 40);
		frame.add(accountDetail);

		accType = new JLabel("Account Type :");
		accType.setFont(new Font("", Font.BOLD, 16));
		accType.setBounds(105, 140, 200, 40);
		frame.add(accType);

		saving = new JRadioButton("Saving Account");
		saving.setBounds(125, 180, 170, 40);
		frame.add(saving);

		fdAccount = new JRadioButton("Fixed Deposit Account");
		fdAccount.setBounds(315, 180, 170, 40);
		frame.add(fdAccount);

		current = new JRadioButton("Current Account");
		current.setBounds(125, 230, 170, 40);
		frame.add(current);

		recurring = new JRadioButton("Recurring Deposit Account");
		recurring.setBounds(315, 230, 200, 40);
		frame.add(recurring);

		accountType = new ButtonGroup();
		accountType.add(saving);
		accountType.add(current);
		accountType.add(fdAccount);
		accountType.add(recurring);

		cardNo = new JLabel("Card Number : ");
		cardNo.setBounds(105, 280, 200, 40);
		cardNo.setFont(new Font("", Font.BOLD, 16));
		frame.add(cardNo);
		cardDetail = new JLabel("Your 16 digits Card Number");
		cardDetail.setBounds(105, 300, 300, 40);
		frame.add(cardDetail);

		card = new JLabel("XXXX-XXXX-XXXX-" + cardFour);
		card.setBounds(300, 280, 200, 40);
		card.setFont(new Font("", Font.BOLD, 16));
		frame.add(card);

		pinNo = new JLabel("PIN : ");
		pinNo.setBounds(105, 340, 200, 40);
		pinNo.setFont(new Font("", Font.BOLD, 16));
		frame.add(pinNo);

		pinDetail = new JLabel("Your 4 digit Card Pin Number");
		pinDetail.setBounds(105, 360, 300, 40);
		frame.add(pinDetail);

		pin = new JLabel("XXXX");
		pin.setBounds(300, 340, 200, 40);
		pin.setFont(new Font("", Font.BOLD, 16));
		frame.add(pin);

		services = new JLabel("Services Required :");
		services.setBounds(105, 400, 200, 40);
		services.setFont(new Font("", Font.BOLD, 16));
		frame.add(services);

		c1 = new JCheckBox("ATM Card");
		c1.setBounds(125, 450, 170, 40);
		c1.setFont(new Font("", Font.BOLD, 16));
		frame.add(c1);

		c2 = new JCheckBox("Internet Banking");
		c2.setBounds(315, 450, 200, 40);
		c2.setFont(new Font("", Font.BOLD, 16));
		frame.add(c2);

		c3 = new JCheckBox("Mobile Banking");
		c3.setBounds(125, 500, 170, 40);
		c3.setFont(new Font("", Font.BOLD, 16));
		frame.add(c3);

		c4 = new JCheckBox("E-mail & SMS Alert");
		c4.setBounds(315, 500, 170, 40);
		c4.setFont(new Font("", Font.BOLD, 16));
		frame.add(c4);

		c5 = new JCheckBox("Cheque Book");
		c5.setBounds(125, 550, 170, 40);
		c5.setFont(new Font("", Font.BOLD, 16));
		frame.add(c5);

		c6 = new JCheckBox("E-statement");
		c6.setBounds(315, 550, 170, 40);
		c6.setFont(new Font("", Font.BOLD, 16));
		frame.add(c6);

		accept = new JCheckBox("I hereby declares that the above entered details are correct to the best of my knowledge");
		accept.setBounds(125, 600, 600, 30);
		accept.setFont(new Font("", Font.BOLD, 12));
		frame.add(accept);

		submit = new JButton("Submit");
		submit.setBackground(Color.black);
		submit.setForeground(Color.white);
		submit.setBounds(515, 550, 80, 30);
		submit.addActionListener(this);
		frame.add(submit);

		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String accountType = null;
		Random random = new Random();
		int pinNo = (int) (Math.abs(random.nextLong() % 9000L) + 1000L);
		String services = "";

		if (e.getSource() == submit) {
			if (saving.isSelected()) {
				accountType = "Saving Account";
			} else if (current.isSelected()) {
				accountType = "Current Account";
			} else if (fdAccount.isSelected()) {
				accountType = "Fixed Deposit Account";
			} else if (recurring.isSelected()) {
				accountType = "Recurring Account";
			}

			if (c1.isSelected()) {
				services = services + ", ATM Card";
			}
			if (c2.isSelected()) {
				services = services + ", InternetBanking";
			}
			if (c3.isSelected()) {
				services = services + ", Mobile Banking";
			}
			if (c4.isSelected()) {
				services = services + ", E-mail & SMS Alert";
			}
			if (c5.isSelected()) {
				services = services + ", Cheque book";
			}
			if (c6.isSelected()) {
				services = services + ", E-Statement";
			}

			if (accountType == null || accountType.isEmpty()) {
				JOptionPane.showMessageDialog(frame, "Account Type is Empty", "Error Message",JOptionPane.ERROR_MESSAGE);
			} else if (accept.isSelected()) {
				try (Connection con = new MyConnection().getConnection()) {
						PreparedStatement ps = con.prepareStatement("insert into account_details values(?,?)");
						ps.setString(1, accountType);
						ps.setString(2, services);
						int check = ps.executeUpdate();

						PreparedStatement ps1 = con.prepareStatement("insert into card_details values(?,?)");
						ps1.setLong(1, cardNumber);
						ps1.setInt(2, pinNo);
						int check1 = ps1.executeUpdate();

						if (check > 0 && check1 > 0) {
							JOptionPane.showMessageDialog(frame, "Card No :" + cardNumber + "\n Pin NO : " + pinNo,"Please Remember this Info", 2);
							frame.setVisible(false);
							new Deposit(cardNumber,pinNo).frame.setVisible(true);
						} else {
							JOptionPane.showMessageDialog(frame,"Something went wrong","Error Message",JOptionPane.ERROR_MESSAGE);
						}
				} catch (Exception e2) {
					System.out.println(e);
				}
			} else {
				JOptionPane.showMessageDialog(frame, "Please Accept terms and condition", "Error Message",	JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}
