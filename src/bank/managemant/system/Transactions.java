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

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Transactions implements ActionListener {
	JFrame frame;
	JLabel main, label_1;
	JLabel l1, l2, l3, l4, l5, l6, l7, l8;
	JButton b1, b2, b3, b4, b5, b6, b7, b8;
	Long cardNo;
	int pinNo;

	public Transactions(Long cardNo,int pinNo) {
		this.pinNo = pinNo;
		this.cardNo = cardNo;
		frame = new JFrame("Welcome to Hitachi ATM");
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

		label_1 = new JLabel("Please Select Your Transaction");
		label_1.setBounds(190, 250, 230, 30);
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

		l1 = new JLabel("Deposit");
		l1.setBounds(170, 310, 100, 30);

		l2 = new JLabel("Fast Cash");
		l2.setBounds(170, 330, 100, 30);

		l3 = new JLabel("Pin Change");
		l3.setBounds(170, 350, 100, 30);

		l5 = new JLabel("Cash Withdraw");
		l5.setBounds(330, 310, 100, 30);

		l6 = new JLabel("Mini Statement");
		l6.setBounds(330, 330, 100, 30);

		l7 = new JLabel("Balance Enquiry");
		l7.setBounds(330, 350, 100, 30);

		l8 = new JLabel("Exit");
		l8.setBounds(360, 370, 100, 30);

		main.add(l1);
		main.add(l2);
		main.add(l3);
		main.add(l5);
		main.add(l6);
		main.add(l7);
		main.add(l8);

		frame.setUndecorated(false);
		frame.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == b1) {
			frame.setVisible(false);
			Deposit d = new Deposit(cardNo, pinNo);
			d.frame.setVisible(true);
		}

		if (e.getSource() == b2) {// fast cash
			frame.setVisible(false);
			new FastCash(cardNo, pinNo).frame.setVisible(true);
		}

		if (e.getSource() == b3) {
			frame.setVisible(false);
			new PinChange(cardNo,pinNo).frame.setVisible(true);
		}
		
		if (e.getSource() == b5) {
			frame.setVisible(false);
			new Withdraw(cardNo,pinNo).frame.setVisible(true);

		}

		if (e.getSource() == b6) {// mini statement
			frame.setVisible(false);
			new LastTransaction(cardNo).frame.setVisible(true);
		}

		if (e.getSource() == b7) {
			frame.setVisible(false);
			new BalanceEnquiry(cardNo, pinNo).frame.setVisible(true);
		}

		if (e.getSource() == b8) {
			System.exit(0);
		}
	}
}
