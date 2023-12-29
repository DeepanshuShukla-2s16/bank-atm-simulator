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
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class SignUpFirst implements ActionListener{
	int formNumber;
	JFrame frame;
	JLabel formNo,personalDetail,name,fname,dob,gender,email,maritalStatus,address,city,state,pinCode;
	JTextField nameFeild,fnameFeild,emailFeild,addressFeild,cityFeild,stateFeild,pinCodeFeild;
	JComboBox<String> dateBox,monthBox,yearBox;
	JRadioButton male,female,other,married,unmarried,m_other;
	ButtonGroup genderGroup,maritalGroup;	
	JButton next_1;
	
	public SignUpFirst(){
		frame = new JFrame("Application Form No 1");
		frame.setSize(700, 670);
		frame.setLayout(null);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(0);
		frame.setLocationRelativeTo(null);
		
		/* change the icon image of JFrame */
		Toolkit t = Toolkit.getDefaultToolkit();
		Image icon = t.getImage("C:\\Users\\deepanshu\\eclipse-workspace\\ATM Simulartor System\\src\\bank\\managemant\\system\\bank_1.png");
		frame.setIconImage(icon);
		
		Random ran= new Random();
		formNumber = (int) (Math.abs(ran.nextLong()%9000L)+1000L);
		
		formNo = new JLabel("APPLICATION FORM NO. "+formNumber);
		formNo.setFont(new Font("Lato", Font.BOLD, 35));
		formNo.setForeground(Color.red);
		formNo.setBounds(95,15,600,40);
		frame.add(formNo);
		
		personalDetail = new JLabel("Page 1 : Personal Details ");
		personalDetail.setFont(new Font("", Font.BOLD, 16));
		personalDetail.setBounds(250,60,400,40);
		frame.add(personalDetail);
		
		Font textFeildFont = new Font("",Font.BOLD,16);
		
		name = new JLabel("Name :");
		name.setFont(new Font("", Font.BOLD, 16));
		name.setBounds(125,140,400,40);
		frame.add(name);
		
		nameFeild = new JTextField();
		nameFeild.setBounds(345,140,235,30);
		nameFeild.setFont(textFeildFont);
		frame.add(nameFeild);
		
		fname = new JLabel("Father Name :");
		fname.setFont(new Font("", Font.BOLD, 16));
		fname.setBounds(125,180,400,40);
		frame.add(fname);
		
		fnameFeild = new JTextField();
		fnameFeild.setBounds(345,180,235,30);
		fnameFeild.setFont(textFeildFont);
		frame.add(fnameFeild);
		
		dob = new JLabel("Date Of Birth :");
		dob.setFont(new Font("", Font.BOLD, 16));
		dob.setBounds(125,220,400,40);
		frame.add(dob);
		
		String []dates= {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};
		String []months = {"1","2","3","4","5","6","7","8","9","10","11","12"};
		String []years = {"2004","2003","2002","2001","2000","1999","1998","1997","1996","1995","1994","1993","1992","1991","1990","1989","1988","1987","1986","1985","1984","1983","1982","1981","1980"};
		dateBox = new JComboBox<String>(dates);
		dateBox.setBounds(345, 230, 50, 25);
		dateBox.setFont(textFeildFont);
		frame.add(dateBox);
		monthBox = new JComboBox<String>(months);
		monthBox.setBounds(430, 230, 50, 25);
		monthBox.setFont(textFeildFont);
		frame.add(monthBox);
		yearBox = new JComboBox<String>(years);
		yearBox.setBounds(520, 230, 60, 25);
		yearBox.setFont(textFeildFont);
		frame.add(yearBox);
		
		gender = new JLabel("Gender :");
		gender.setFont(new Font("", Font.BOLD, 16));
		gender.setBounds(125,260,400,40);
		frame.add(gender);
		
		male = new JRadioButton("Male");
		male.setBounds(345, 265, 60, 30);
		frame.add(male);
		
		female = new JRadioButton("Female");
		female.setBounds(430, 265, 70, 30);
		frame.add(female);
		
		other = new JRadioButton("Other");
		other.setBounds(525, 265, 70, 30);
		frame.add(other);
		
		genderGroup = new ButtonGroup();
		genderGroup.add(male);
		genderGroup.add(female);
		genderGroup.add(other);
		
		email = new JLabel("Email :");
		email.setFont(new Font("", Font.BOLD, 16));
		email.setBounds(125,300,400,40);
		frame.add(email);
		
		emailFeild = new JTextField();
		emailFeild.setBounds(345,300,235,30);
		emailFeild.setFont(textFeildFont);
		frame.add(emailFeild);
		
		
		maritalStatus = new JLabel("Marital Status :");
		maritalStatus.setFont(new Font("", Font.BOLD, 16));
		maritalStatus.setBounds(125,340,400,40);
		frame.add(maritalStatus);
		
		married = new JRadioButton("Married");
		married.setBounds(345, 345, 80, 30);
		frame.add(married);
		
		unmarried = new JRadioButton("Unmarried");
		unmarried.setBounds(430, 345, 90, 30);
		frame.add(unmarried);
		
		m_other = new JRadioButton("Other");
		m_other.setBounds(525, 345, 70, 30);
		frame.add(m_other);
		
		maritalGroup = new ButtonGroup();
		maritalGroup.add(married);
		maritalGroup.add(unmarried);
		maritalGroup.add(m_other);
		
		address = new JLabel("Address :");
		address.setFont(new Font("", Font.BOLD, 16));
		address.setBounds(125,380,400,40);
		frame.add(address);
		
		addressFeild = new JTextField();
		addressFeild.setBounds(345,380,235,30);
		addressFeild.setFont(textFeildFont);
		frame.add(addressFeild);
		
		city = new JLabel("City :");
		city.setFont(new Font("", Font.BOLD, 16));
		city.setBounds(125,420,400,40);
		frame.add(city);
		
		cityFeild = new JTextField();
		cityFeild.setBounds(345,420,235,30);
		cityFeild.setFont(textFeildFont);		
		frame.add(cityFeild);
		
		state = new JLabel("State :");
		state.setFont(new Font("", Font.BOLD, 16));
		state.setBounds(125,460,400,40);
		frame.add(state);
		
		stateFeild = new JTextField();
		stateFeild.setBounds(345,460,235,30);
		stateFeild.setFont(textFeildFont);
		frame.add(stateFeild);
		
		pinCode = new JLabel("Pin Code :");
		pinCode.setFont(new Font("", Font.BOLD, 16));
		pinCode.setBounds(125,500,400,40);
		frame.add(pinCode);
		
		pinCodeFeild = new JTextField();
		pinCodeFeild.setBounds(345,500,235,30);
		pinCodeFeild.setFont(textFeildFont);
		frame.add(pinCodeFeild);
		
		next_1 = new JButton("Next");
		next_1.setBackground(Color.black);
		next_1.setForeground(Color.white);
		next_1.setBounds(510, 580, 70, 30);
		next_1.addActionListener(this);
		frame.add(next_1);
		
		frame.setVisible(true);	
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		int formNo = formNumber;
		String name = nameFeild.getText();
		String fname = fnameFeild.getText();
		String dob = dateBox.getSelectedItem()+"-"+monthBox.getSelectedItem()+"-"+yearBox.getSelectedItem();
		String gender=null;
		if(male.isSelected()) {
			gender = "male";
		}else if(female.isSelected()) {
			gender = "female";
		}else if(other.isSelected()) {
			gender = "other";
		}
		
		
		String email = emailFeild.getText();
		String maritalStatus=null;
		if(married.isSelected()) {
			maritalStatus = "married";
		}else if(unmarried.isSelected()) {
			maritalStatus = "unmarried";
		}else if(m_other.isSelected()) {
			maritalStatus = "other";
		}
		String address = addressFeild.getText();
		String city = cityFeild.getText();
		String state = stateFeild.getText();
		String pin = pinCodeFeild.getText();
		try {
			
			if(name == null || name.isEmpty()) {
				JOptionPane.showMessageDialog(frame,"Name is Empty","Error Message",JOptionPane.ERROR_MESSAGE);
			}else if(name.length()<3) {
				JOptionPane.showMessageDialog(frame,"Name should be minimum 3 character","Error Message",JOptionPane.ERROR_MESSAGE);
			}else if(fname == null || fname.isEmpty()) {
				JOptionPane.showMessageDialog(frame,"Father Name is Empty","Error Message",JOptionPane.ERROR_MESSAGE);
			}else if(fname.length()<3) {
				JOptionPane.showMessageDialog(frame,"Father Name should be minimum 3 character","Error Message",JOptionPane.ERROR_MESSAGE);
			}else if(gender == null) {
				JOptionPane.showMessageDialog(frame,"Select your gender","Error Message",JOptionPane.ERROR_MESSAGE);
			}else if(email == null || email.isEmpty()) {
				JOptionPane.showMessageDialog(frame,"Email is Empty","Error Message",JOptionPane.ERROR_MESSAGE);
			}else if(maritalStatus == null || maritalStatus.isEmpty()) {
				JOptionPane.showMessageDialog(frame,"Select your Marital Status","Error Message",JOptionPane.ERROR_MESSAGE);
			}else if(address == null || address.isEmpty()) {
				JOptionPane.showMessageDialog(frame,"Address is Empty","Error Message",JOptionPane.ERROR_MESSAGE);
			}else if(city == null || city.isEmpty()) {
				JOptionPane.showMessageDialog(frame,"City is Empty","Error Message",JOptionPane.ERROR_MESSAGE);
			}else if(state == null || state.isEmpty()) {
				JOptionPane.showMessageDialog(frame,"State is Empty","Error Message",JOptionPane.ERROR_MESSAGE);
			}else if(address == null || address.isEmpty()) {
				JOptionPane.showMessageDialog(frame,"Address is Empty","Error Message",JOptionPane.ERROR_MESSAGE);
			}else if(pin == null || pin.isEmpty()) {
				JOptionPane.showMessageDialog(frame,"Pincode is Empty","Error Message",JOptionPane.ERROR_MESSAGE);
			}
			else {
				int pincode = Integer.parseInt(pinCodeFeild.getText());
				Connection con= new MyConnection().getConnection();
				PreparedStatement ps = con.prepareStatement("insert into personal_details  values(?,?,?,?,?,?,?,?,?,?,?)");
					ps.setInt(1, formNo);
					ps.setString(2, name);
					ps.setString(3, fname);
					ps.setString(4, dob);
					ps.setString(5, gender);
					ps.setString(6, email);
					ps.setString(7, maritalStatus);
					ps.setString(8, address);
					ps.setString(9, city);
					ps.setString(10, state);
					ps.setInt(11, pincode);
				
					int check = ps.executeUpdate();
					if(check > 0)
						JOptionPane.showMessageDialog(frame,"Details added Successfully","Information",JOptionPane.INFORMATION_MESSAGE);
					else
						JOptionPane.showMessageDialog(frame,"Something went wrong","Error Message",JOptionPane.ERROR_MESSAGE);

					
					frame.setVisible(false);
					new SignUpSecond(formNumber).frame.setVisible(true);
			}
		}catch(Exception ec){
			System.out.println(ec);
		}
	}
}


