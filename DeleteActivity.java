import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

class DeleteActivity extends JFrame
{
Container c;
   JLabel labRno;
   JTextField txtRno;
   JButton btnDelete,btnBack;

DeleteActivity()
{
c=getContentPane();
c.setLayout(new FlowLayout());
c.setBackground(Color.pink);

labRno=new JLabel("Enter Student Rollno");
txtRno=new JTextField(14);
btnDelete=new JButton("Delete");
btnDelete.setBackground(Color.white);
btnBack=new JButton("Back");
btnBack.setBackground(Color.white);

Font f=new Font("Arial",Font.BOLD,30);
labRno.setFont(f);
txtRno.setFont(f);
btnDelete.setFont(f);
btnBack.setFont(f);

c.add(labRno);
c.add(txtRno);
c.add(btnDelete);
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
String sql="delete from student where Rno=?";
PreparedStatement pst=con.prepareStatement(sql);
int Rno = -1;
                try {
                    Rno = Integer.parseInt(txtRno.getText());
                    if (Rno <= 0) {
                        JOptionPane.showMessageDialog(c, "Rno should contain positive integers only");
                        return;
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(c, "Rno should contain integers only");
                    return;
                }

pst.setInt(1,Rno);
 ResultSet rs = pst.executeQuery("SELECT COUNT(*) FROM student");
            rs.next();
            int count = rs.getInt(1);
            if (count == 0) {
                JOptionPane.showMessageDialog(c,"Table is empty, nothing to delete.");
            } else {
int rowsDeleted = pst.executeUpdate();
         if (rowsDeleted > 0) {
            JOptionPane.showMessageDialog(c,"Roll number deleted successfully!");
         }
         else{
            JOptionPane.showMessageDialog(c,"Rno not found");
         }
       }
con.close();
txtRno.setText(""); 
txtRno.requestFocus();
}

catch(SQLException e)
{
JOptionPane.showMessageDialog(c,"..");
}
};
btnDelete.addActionListener(a2);



setTitle("Delete Page");
setSize(520,520);
setLocationRelativeTo(null);
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setVisible(true);
}


}