import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class MainActivity extends JFrame
{
   Container c;
   JButton btnAdd,btnView,btnUpdate,btnDelete;

   MainActivity()
   {
      c=getContentPane();
      c.setLayout(new FlowLayout());
      c.setBackground(Color.pink);

      btnAdd=new JButton("Add");
      btnAdd.setBackground(Color.white);
      btnView=new JButton("View");
      btnView.setBackground(Color.white);
      btnUpdate=new JButton("Update");
      btnUpdate.setBackground(Color.white);
      btnDelete=new JButton("Delete");
      btnDelete.setBackground(Color.white);

      Font f=new Font("Arial",Font.BOLD,30);
      btnAdd.setFont(f);
      btnView.setFont(f);
      btnUpdate.setFont(f);
      btnDelete.setFont(f);

     c.add(btnAdd);
     c.add(btnView);
     c.add(btnUpdate);
     c.add(btnDelete);

    ActionListener a1=(ae)->{
              AddActivity a=new AddActivity();
              dispose();
            };
    btnAdd.addActionListener(a1);

    ActionListener a2=(ae)->{
             ViewActivity a=new ViewActivity();
               dispose();
          };
    btnView.addActionListener(a2);

    ActionListener a3=(ae)->{
             UpdateActivity a=new UpdateActivity();
               dispose();
          };
    btnUpdate.addActionListener(a3);

    ActionListener a4=(ae)->{
             DeleteActivity a=new DeleteActivity();
               dispose();
          };
    btnDelete.addActionListener(a4);

    setTitle("Student Management System");
    setSize(520,520);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);
    }
}