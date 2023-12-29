package bank.managemant.system;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class SignUpSecond implements ActionListener {
	int formNumber;
	JFrame frame;
	JLabel formNo, additionalDetail, religion, category, income, education, occupation, qualification, panNum, aadhar,
			citizen, existAcc;
	JTextField panFeild, aadharFeild;
	JComboBox<String> religionFeild, categoryFeild, incomeFeild, qualificationFeild, occupationFeild;
	JRadioButton citizenYes, citizenNo, exixitingYes, exixitingNo;
	ButtonGroup citizenGroup, exixitingGroup;
	JButton next_2;

	public SignUpSecond(int formNumber) {
		this.formNumber = formNumber;
		frame = new JFrame("Application Form No 2");
		frame.setSize(700, 670);
		frame.setLayout(null);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		
		/* change the icon image of JFrame */
		Toolkit t = Toolkit.getDefaultToolkit();
		Image icon = t.getImage("C:\\Users\\deepanshu\\eclipse-workspace\\ATM Simulartor System\\src\\bank\\managemant\\system\\bank_1.png");
		frame.setIconImage(icon);

		additionalDetail = new JLabel("Page 2 : Additional Details ");
		additionalDetail.setFont(new Font("Arial", Font.BOLD, 16));
		additionalDetail.setBounds(250, 60, 400, 40);
		frame.add(additionalDetail);

		Font textFeildFont = new Font("", Font.BOLD, 16);
		Font comboBoxFont = new Font("Lato", Font.PLAIN, 14);

		religion = new JLabel("Religion :");
		religion.setFont(new Font("", Font.BOLD, 16));
		religion.setBounds(125, 140, 400, 40);
		frame.add(religion);

		String[] lists = { "Hindu", "Mushlim", "Sikh", "Christian", "Other" };
		religionFeild = new JComboBox<String>(lists);
		religionFeild.setBounds(345, 140, 235, 30);
		religionFeild.setFont(comboBoxFont);
		frame.add(religionFeild);

		category = new JLabel("Category :");
		category.setFont(new Font("", Font.BOLD, 16));
		category.setBounds(125, 180, 400, 40);
		frame.add(category);

		String[] categoryList = { "General", "OBC", "SC", "ST", "Other" };
		categoryFeild = new JComboBox<String>(categoryList);
		categoryFeild.setBounds(345, 180, 235, 30);
		categoryFeild.setFont(comboBoxFont);
		frame.add(categoryFeild);

		income = new JLabel("Income :");
		income.setFont(new Font("", Font.BOLD, 16));
		income.setBounds(125, 220, 400, 40);
		frame.add(income);

		String[] incomeList = { "NA", "1-1,50,000", "1,50,000-2,50,000", "2,50,000-5,00,000", "upto 10,00,000" };
		incomeFeild = new JComboBox<String>(incomeList);
		incomeFeild.setBounds(345, 230, 235, 30);
		incomeFeild.setFont(comboBoxFont);
		frame.add(incomeFeild);

		qualification = new JLabel("Qualification :");
		qualification.setFont(new Font("", Font.BOLD, 16));
		qualification.setBounds(125, 260, 400, 40);
		frame.add(qualification);

		String[] qualificationList = { "High School", "Intermediate", "Graduate", "Post-Graduate", "Doctrate", "Other" };
		qualificationFeild = new JComboBox<String>(qualificationList);
		qualificationFeild.setBounds(345, 280, 235, 30);
		frame.add(qualificationFeild);

		occupation = new JLabel("Occupation :");
		occupation.setFont(new Font("", Font.BOLD, 16));
		occupation.setBounds(125, 340, 400, 40);
		frame.add(occupation);

		String occupationList[] = { "Salaried", "Self-employed", "Bussiness", "Student", "Retird", "Others" };
		occupationFeild = new JComboBox<String>(occupationList);
		occupationFeild.setBounds(345, 330, 235, 30);
		frame.add(occupationFeild);

		panNum = new JLabel("PAN Number :");
		panNum.setFont(new Font("", Font.BOLD, 16));
		panNum.setBounds(125, 380, 400, 40);
		frame.add(panNum);

		panFeild = new JTextField();
		panFeild.setBounds(345, 380, 235, 30);
		panFeild.setFont(textFeildFont);
		frame.add(panFeild);

		aadhar = new JLabel("Aadhar Number :");
		aadhar.setFont(new Font("", Font.BOLD, 16));
		aadhar.setBounds(125, 420, 400, 40);
		frame.add(aadhar);

		aadharFeild = new JTextField();
		aadharFeild.setBounds(345, 420, 235, 30);
		aadharFeild.setFont(textFeildFont);
		frame.add(aadharFeild);

		citizen = new JLabel("Senior Citizen :");
		citizen.setFont(new Font("", Font.BOLD, 16));
		citizen.setBounds(125, 460, 400, 40);
		frame.add(citizen);

		citizenYes = new JRadioButton("Yes");
		citizenYes.setBounds(375, 460, 80, 40);
		frame.add(citizenYes);

		citizenNo = new JRadioButton("No");
		citizenNo.setBounds(470, 460, 80, 40);
		frame.add(citizenNo);

		citizenGroup = new ButtonGroup();
		citizenGroup.add(citizenYes);
		citizenGroup.add(citizenNo);

		existAcc = new JLabel("Exisiting Account :");
		existAcc.setFont(new Font("", Font.BOLD, 16));
		existAcc.setBounds(125, 500, 400, 40);
		frame.add(existAcc);

		exixitingYes = new JRadioButton("Yes");
		exixitingYes.setBounds(375, 500, 80, 40);
		frame.add(exixitingYes);

		exixitingNo = new JRadioButton("No");
		exixitingNo.setBounds(470, 500, 80, 40);
		frame.add(exixitingNo);

		exixitingGroup = new ButtonGroup();
		exixitingGroup.add(exixitingYes);
		exixitingGroup.add(exixitingNo);

		next_2 = new JButton("Next");
		next_2.addActionListener(this);
		next_2.setBackground(Color.black);
		next_2.setForeground(Color.white);
		next_2.setBounds(510, 580, 70, 30);
		frame.add(next_2);

		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String religion = (String) religionFeild.getSelectedItem();
		String category = (String) categoryFeild.getSelectedItem();
		String income = (String) incomeFeild.getSelectedItem();
		String qualification = (String) qualificationFeild.getSelectedItem();
		String occupation = (String) occupationFeild.getSelectedItem();
		String panNo = panFeild.getText();
		String aadharNo = aadharFeild.getText();
		String citizen = null;
		if (citizenYes.isSelected()) {
			citizen = "Yes";
		} else if (citizenNo.isSelected()) {
			citizen = "No";
		}
		String existAcc = null;
		if (exixitingYes.isSelected()) {
			existAcc = "Yes";
		} else if (exixitingNo.isSelected()) {
			existAcc = "No";
		}

		if (panNo == null || panNo.isEmpty()) {
			JOptionPane.showMessageDialog(frame, "Pancard is Empty", "Error Message", JOptionPane.ERROR_MESSAGE);
		} else if (panNo.length() < 10 || panNo.length() > 10) {
			JOptionPane.showMessageDialog(frame, "Invalide Pancard detail", "Error Message", JOptionPane.ERROR_MESSAGE);
		} else if (aadharNo.length() < 12 || panNo.length() > 12) {
			JOptionPane.showMessageDialog(frame, "Invalide Aadhar detail", "Error Message", JOptionPane.ERROR_MESSAGE);
		} else if (citizen == null) {
			JOptionPane.showMessageDialog(frame, "Select senior citizen option", "Error Message",
					JOptionPane.ERROR_MESSAGE);
		} else if (existAcc == null) {
			JOptionPane.showMessageDialog(frame, "Select Exisiting Account option", "Error Message",
					JOptionPane.ERROR_MESSAGE);
		} else {
			Long aadhar = Long.parseLong(aadharNo);
			try (Connection con = new MyConnection().getConnection()) {
				PreparedStatement ps = con.prepareStatement("insert into additional_details  values(?,?,?,?,?,?,?,?,?)");
				ps.setString(1, religion);
				ps.setString(2, category);
				ps.setString(3, income);
				ps.setString(4, qualification);
				ps.setString(5, occupation);
				ps.setString(6, panNo.toUpperCase());
				ps.setLong(7, aadhar);
				ps.setString(8, citizen);
				ps.setString(9, existAcc);

				int check = ps.executeUpdate();
				if(check > 0)
					JOptionPane.showMessageDialog(frame,"Details added Successfully","Information",JOptionPane.INFORMATION_MESSAGE);
				else
					JOptionPane.showMessageDialog(frame,"Something went wrong","Error Message",JOptionPane.ERROR_MESSAGE);

				frame.setVisible(false);
				new SignUpThird(formNumber).frame.setVisible(true);

			} catch (Exception ec) {
				System.out.println(ec);
			}
		}
	}
}
