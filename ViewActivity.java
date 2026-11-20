import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

class ViewActivity extends JFrame
{
 Container c;
 TextArea taData;
 JButton btnBack;

 ViewActivity()
 {
   c=getContentPane();
   c.setLayout(new FlowLayout());
   c.setBackground(Color.pink);

   taData=new TextArea(8,20);
   btnBack=new JButton("back");
   btnBack.setBackground(Color.white);

   Font f=new Font("Arial",Font.BOLD,30);
   taData.setFont(f);
   btnBack.setFont(f);

   c.add(taData);
   c.add(btnBack);

   ActionListener a1=(ae)->{
      MainActivity a=new MainActivity();
       dispose();
  };
  btnBack.addActionListener(a1);
  taData.setEditable(false);
  try
  {
    DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
    String url="jdbc:mysql://localhost:3306/project_sms";
    String username="root";
    String password="abc123";
    Connection con=DriverManager.getConnection(url,username,password);
    String sql="select* from student";
    PreparedStatement pst=con.prepareStatement(sql);
    ResultSet rs=pst.executeQuery();
    StringBuffer info=new StringBuffer();
    while(rs.next())
    info.append("Rollno: " + rs.getInt(1) + "\tName: " + rs.getString(2) + "\tMaths:" + rs.getInt(3) + "\tPhys:" + rs.getInt(4) + "\tChem:" + rs.getInt(5) + "\n");
    taData.setText(info.toString());
    con.close();
  }
  catch(SQLException e)
  {
    JOptionPane.showMessageDialog(c,"issue"+e);
  }
  setTitle("View Page");
  setSize(520,520);
  setLocationRelativeTo(null);
  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  setVisible(true);
}
}