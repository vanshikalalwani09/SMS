import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.JOptionPane;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class UpdateActivity extends JFrame
{
   Container c;
   JLabel labName,labMaths,labPhys,labChem,labRno;
   JTextField txtName,txtMaths,txtPhys,txtChem,txtRno;
   JButton btnUpdate,btnBack;

   UpdateActivity()
   {
      c=getContentPane();
      c.setLayout(new FlowLayout());
      c.setBackground(Color.pink);

      labName=new JLabel("Enter Name");
      txtName=new JTextField(14);
      labMaths=new JLabel("Enter Marks in Maths:");
      txtMaths=new JTextField(14);
      labPhys=new JLabel("Enter Marks in Physics:");
      txtPhys=new JTextField(14); 
      labChem=new JLabel("Enter Marks in Chemistry:");
      txtChem=new JTextField(14);
      labRno=new JLabel("Enter Rollno to be Updated");
      txtRno=new JTextField(14);

      btnUpdate=new JButton("Update");
      btnUpdate.setBackground(Color.white);
      btnBack=new JButton("Back");
      btnBack.setBackground(Color.white);

      JFrame frame=new JFrame();
      Font f=new Font("Arial",Font.BOLD,30);
      labName.setFont(f);
      txtName.setFont(f);
      labMaths.setFont(f);
      txtMaths.setFont(f);
      labPhys.setFont(f);
      txtPhys.setFont(f);
      labChem.setFont(f);
      txtChem.setFont(f);
      labRno.setFont(f);
      txtRno.setFont(f);
      btnUpdate.setFont(f);
      btnBack.setFont(f);

      c.add(labName);
      c.add(txtName);
      c.add(labMaths);
      c.add(txtMaths);
      c.add(labPhys);
      c.add(txtPhys);
      c.add(labChem);
      c.add(txtChem);
      c.add(labRno);
      c.add(txtRno);
      c.add(btnUpdate);
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
      String sql="UPDATE student SET Name = ?, Maths = ?, Physics = ?, Chemistry = ? WHERE Rno = ?";
      PreparedStatement pst=con.prepareStatement(sql);
      String name=txtName.getText();
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


pst.setString(1,name);
pst.setInt(2,Maths);
pst.setInt(3,Phys);
pst.setInt(4,Chem);
pst.setInt(5,Rno);
pst.executeUpdate();

int result = pst.executeUpdate();
      if (result == 0) {
         JOptionPane.showMessageDialog(c,"Record with Rno "+Rno+" not found");
  break;   
} 
else
 {
txtName.setText("");
txtRno.setText("");
txtMaths.setText("");
txtPhys.setText("");
txtChem.setText("");
txtName.requestFocus();
JOptionPane.showMessageDialog(c,"Record with Rno "+Rno+" updated successfully");
 break;
     }
}
con.close();

}
catch(SQLException e) {
      JOptionPane.showMessageDialog(c,"Error: "+e.getMessage());
   }
};
btnUpdate.addActionListener(a2);

    setTitle("Update Page");
    setSize(520,520);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);
    }
}  