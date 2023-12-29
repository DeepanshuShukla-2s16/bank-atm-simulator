package bank.managemant.system;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class LastTransaction extends MouseAdapter{
	JFrame frame;
	JLabel namelabel;
	Long cardNo;
	JLabel addlabel, dateLabel,timeLabel,atmIdLabel,cardLabel,statusLabel,LastLabel,informationLAbel;
	JLabel dateLabel1,timeLabel1,atmIdLabel1,cardLabel1,statusLabel1,LastLabel1,informationLAbel1;
	String date,time,type;
	Long amount;
	int card =0;
	
	public LastTransaction(Long cardNo) {
		this.cardNo = cardNo;
		card = (int) (cardNo%10000);
		frame = new JFrame("Transaction Recipt");
		frame.setSize(400, 500);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(3);
		frame.setResizable(false);
		frame.setLayout(null);
		frame.addMouseListener(this);
		
		/* change the icon image of JFrame */
		Toolkit t = Toolkit.getDefaultToolkit();
		Image icon = t.getImage("C:\\Users\\deepanshu\\eclipse-workspace\\ATM Simulartor System\\src\\bank\\managemant\\system\\bank_1.png");
		frame.setIconImage(icon);
		
		try(Connection con = new MyConnection().getConnection()) {
			String fetchQuery = "select date,time,type,amount from bank where sno = (select max(sno) from bank where cardNo="+cardNo+")";
			PreparedStatement pstmt = con.prepareStatement(fetchQuery);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			date = rs.getString(1);
			time = rs.getString(2);
			type = rs.getNString(3);
			amount = rs.getLong(4);
		} catch (Exception e) {
			System.out.println(e);
		}
		namelabel = new JLabel("HITACHI ATM");
		namelabel.setForeground(Color.red);
		namelabel.setBounds(90, 40, 400, 30);
		namelabel.setFont(new Font("Osword", Font.BOLD, 32));
		frame.add(namelabel);
		
		addlabel = new JLabel("Khora Colony Noida");
		addlabel.setBounds(115, 80, 300, 30);
		addlabel.setFont(new Font("Osword", Font.BOLD, 16));
		frame.add(addlabel);

		dateLabel = new JLabel("Date");
		dateLabel1 = new JLabel(date);
		dateLabel.setBounds(80, 130, 60, 30);
		dateLabel1.setBounds(70, 155, 90, 30);
		frame.add(dateLabel);frame.add(dateLabel1);

		timeLabel = new JLabel("Time");
		timeLabel.setBounds(175, 130, 60, 30);
		frame.add(timeLabel);
		timeLabel1 = new JLabel(time);
		timeLabel1.setBounds(170, 155, 60, 30);
		frame.add(timeLabel1);
		
		atmIdLabel = new JLabel("ATM Id");
		atmIdLabel.setBounds(265, 130, 60, 30);
		frame.add(atmIdLabel);
		atmIdLabel1 = new JLabel("MPB01757");
		atmIdLabel1.setBounds(260, 155, 60, 30);
		frame.add(atmIdLabel1);
		
		if(type.equals("Enquiry")) {
			statusLabel = new JLabel("Balance "+type+"-- Current Balance : "+amount+" Rs.");
			statusLabel.setBounds(70, 195, 300, 30);
			frame.add(statusLabel);
		}
		else if(type.equals("Withdraw")) {
			statusLabel = new JLabel(type+" -- Amount : "+amount+" Rs.");
			statusLabel.setBounds(70, 195, 300, 30);
			frame.add(statusLabel);
		}
		else if(type.equals("Deposit")) {
			statusLabel = new JLabel(type+" -- Amount : "+amount+" Rs.");
			statusLabel.setBounds(70, 195, 300, 30);
			frame.add(statusLabel);
		}	
		cardLabel = new JLabel("Card Number : XXXXXXXXXX-"+card);
		cardLabel.setBounds(70, 230, 300, 30);
		frame.add(cardLabel);
		
		LastLabel = new JLabel("Thank you for using Hitachi Money Spot ATM");
		LastLabel.setBounds(60, 280, 300, 30);
		frame.add(LastLabel);
		
		LastLabel1 = new JLabel("Powered by Hitachi Payment");
		LastLabel1.setBounds(110, 320, 300, 30);
		frame.add(LastLabel1);
		
		informationLAbel = new JLabel("Note : Never write your PIN anywhere");
		informationLAbel.setFont(new Font("Osword", Font.BOLD, 16));
		informationLAbel.setBounds(50, 370, 300, 30);
		frame.add(informationLAbel);
		
		informationLAbel1 = new JLabel("(Thank You)");
		informationLAbel1.setBounds(300, 420, 300, 30);
		frame.add(informationLAbel1);
		
		frame.setUndecorated(true);
		frame.setVisible(true);
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getClickCount()==2) {
			frame.dispose();
		}
	}



}
