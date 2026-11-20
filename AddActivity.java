import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.JOptionPane;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


class AddActivity extends JFrame
{
Container c;
JLabel labName,labRno,labMaths,labPhys,labChem;
JTextField txtRno,txtName,txtMaths,txtPhys,txtChem;
JButton btnSave,btnBack;
AddActivity()
{
c=getContentPane();
c.setLayout(new FlowLayout());
c.setBackground(Color.pink);


labRno=new JLabel("Enter Rollno:");
txtRno=new JTextField(14);
labName=new JLabel("Enter Name:"); 
txtName=new JTextField(14);
labMaths=new JLabel("Enter marks in Maths:"); 
txtMaths=new JTextField(14);
labPhys=new JLabel("Enter marks in Physics:"); 
txtPhys=new JTextField(14);
labChem=new JLabel("Enter marks in Chemistry:"); 
txtChem=new JTextField(14);
btnSave=new JButton("Save");
btnSave.setBackground(Color.white);
btnBack=new JButton("Back");
btnBack.setBackground(Color.white);

JFrame frame=new JFrame();
Font f=new Font("Arial",Font.BOLD,30);

labRno.setFont(f);
txtRno.setFont(f);
labName.setFont(f);
txtName.setFont(f);
labMaths.setFont(f);
txtMaths.setFont(f);
labPhys.setFont(f);
txtPhys.setFont(f);
labChem.setFont(f);
txtChem.setFont(f);
btnSave.setFont(f);
btnBack.setFont(f);


c.add(labRno);
c.add(txtRno);
c.add(labName);
c.add(txtName);
c.add(labMaths);
c.add(txtMaths);
c.add(labPhys);
c.add(txtPhys);
c.add(labChem);
c.add(txtChem);
c.add(btnSave);
c.add(btnBack);

ActionListener a1=(ae)->{
MainActivity a=new MainActivity();
dispose();
};

btnBack.addActionListener(a1);
ActionListener a2=(ae)->{
try
{
DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
String url="jdbc:mysql://localhost:3306/project_sms";
String username="root";
String password="abc123";
Connection con=DriverManager.getConnection(url,username,password);
String sql="insert into Student values(?,?,?,?,?)";
PreparedStatement pst=con.prepareStatement(sql);
String name=txtName.getText();
int Rno = -1;
                try {
                    Rno = Integer.parseInt(txtRno.getText());
                    if (Rno <= 0) {
                        JOptionPane.showMessageDialog(frame, "Rno should contain positive integers only");
                        return;
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(frame, "Rno should contain integers only");
                    return;
                }
String pattern = "^[a-zA-Z]{2,}$";

Pattern r = Pattern.compile(pattern);
Matcher m = r.matcher(name);
while (true) {
    m = r.matcher(name);



if (m.find()) {
    //JOptionPane.showMessageDialog(frame,"Valid name");
    
} else {
   JOptionPane.showMessageDialog(frame,"Error:Name should contain alphabets only");
    break;
}
int Maths = 0;
int Phys = 0;
int Chem = 0;

String MathMarks = txtMaths.getText();
String PhysMarks = txtPhys.getText();
String ChemMarks = txtChem.getText();
try {
     Maths = Integer.parseInt(MathMarks);
     Phys = Integer.parseInt(PhysMarks);
     Chem = Integer.parseInt(ChemMarks);
    if(Maths < 0 || Maths > 100) {
        JOptionPane.showMessageDialog(frame,"Maths marks should be between 0 and 100");
        return;
    }
    if(Phys < 0 || Phys > 100) {
        JOptionPane.showMessageDialog(frame,"Physics marks should be between 0 and 100");
        return;
    }
    if(Chem < 0 || Chem > 100) {
        JOptionPane.showMessageDialog(frame,"Chemistry marks should be between 0 and 100");
        return;
    }
} catch(NumberFormatException e) {
    JOptionPane.showMessageDialog(frame,"Marks should contain integers only");
    return;
}
pst.setInt(1,Rno);
pst.setString(2,name);
pst.setInt(3,Maths);
pst.setInt(4,Phys);
pst.setInt(5,Chem);
pst.executeUpdate();
con.close();
txtName.setText("");
txtRno.setText("");
txtMaths.setText("");
txtPhys.setText("");
txtChem.setText("");
txtRno.requestFocus();
JOptionPane.showMessageDialog(c,"record saved");
break;
}
}
catch(SQLException e)
{
JOptionPane.showMessageDialog(c,"Rno should not be repeated");
}
};

btnSave.addActionListener(a2); 



setTitle("Add Page");
setSize(520,520);
setLocationRelativeTo(null);
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setVisible(true);
}


}