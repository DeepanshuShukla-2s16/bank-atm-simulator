package bank.managemant.system;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class PinChange implements ActionListener {
	JFrame frame;
	JLabel main,description,label_1,label_2,changeLabel;
	JTextField newPin,oldPin;
	JButton change;
	
	Long cardNo;
	int pinNo;
	
	PinChange(Long cardNo, int pinNo){
		this.cardNo = cardNo;
		this.pinNo = pinNo;
		frame = new JFrame("Pin Change");
		frame.setSize(700, 680);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(3);
		frame.setResizable(false);
		frame.setLayout(null);
		
		BufferedImage bufferedImage=null;
		try {
			bufferedImage = ImageIO.read(new File("C:\\Users\\deepanshu\\eclipse-workspace\\ATM Simulartor System\\src\\bank\\managemant\\system\\atm_1.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Image 	img = bufferedImage.getScaledInstance(700, 700, Image.SCALE_DEFAULT);
		
		 /* convert Image to ImageIcon, Jlabel not accepted Image as args;*/
		ImageIcon imgIcon = new ImageIcon(img);
		main = new JLabel(imgIcon);
		main.setBounds(0,0, 700, 700);
		frame.add(main);
		
		description= new JLabel("CHANE YOUR PIN");
		description.setBounds(240,250,270,30);
		description.setFont(new Font("",Font.BOLD,14));
		description.setForeground(Color.blue);
		main.add(description);
		
		label_1= new JLabel("Enter Old PIN :");
		label_1.setBounds(180,288,270,30);
		label_1.setFont(new Font("",Font.BOLD,14));
		main.add(label_1);
		
		oldPin = new JTextField();
		oldPin.setBounds(290,290,130,30);
		oldPin.setFont(new Font("",Font.BOLD,14));
		oldPin.setBackground(Color.gray);
		main.add(oldPin);
		
		label_2= new JLabel("Enter New PIN :");
		label_2.setBounds(180,328,270,30);
		label_2.setFont(new Font("",Font.BOLD,14));
		main.add(label_2);
		
		newPin = new JTextField();
		newPin.setBounds(290,330,130,30);
		newPin.setFont(new Font("",Font.BOLD,14));
		newPin.setBackground(Color.gray);
		main.add(newPin);
		
		changeLabel = new JLabel("Change");
		changeLabel.setBounds(370, 369, 70, 30);
		changeLabel.setFont(new Font("",Font.BOLD,14));
		main.add(changeLabel);
		
		change = new JButton();
		change.setBounds(441, 378, 25, 16);
		change.setBackground(Color.cyan);
		change.addActionListener(this);
		main.add(change);
		
		frame.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		try(Connection con = new MyConnection().getConnection()) {
			Statement stmt = con.createStatement();
			int old_pin = Integer.parseInt(oldPin.getText());
			int new_pin = Integer.parseInt(newPin.getText());
			
			if(pinNo==old_pin) {
				String updatePin = "update card_details set pinNo='"+new_pin+"' where cardNo='"+cardNo+"'";				
				int check = stmt.executeUpdate(updatePin);
				if(check>0) {
					JOptionPane.showMessageDialog(frame, "Your new PIN is :"+new_pin);					
				}else {
					JOptionPane.showMessageDialog(frame, "Not change your pin ");
				}
			}else {
				JOptionPane.showMessageDialog(frame, "Please enter correct pin");
			}	
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
}
