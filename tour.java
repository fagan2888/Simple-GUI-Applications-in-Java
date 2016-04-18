import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class tour {
private JFrame frame;
private JPanel entry;
private JLabel top;
private JLabel bottom;
private JTextField name1;
private JTextField bookdate;
private JTextField tourdate;
private JTextField adultno;
private JTextField childno;
private JLabel name2;
private JLabel bookdate1;
private JLabel tourdate1;
private JLabel adultno1;
private JLabel childno1;
private JPanel form;
private JPanel controlPanel;
JRadioButton rad1 = new JRadioButton("General");
JRadioButton rad2 = new JRadioButton("Business");
JRadioButton rad3 = new JRadioButton("Economy");

public tour(){
prepareGUI();
}
private void prepareGUI(){
frame=new JFrame("Tour Billing System");
frame.setSize(800,500);
entry=new JPanel();
entry.setSize(600,500);
entry.setLayout(new GridLayout(4,2));

top = new JLabel("" , JLabel.CENTER);
bottom = new JLabel("" , JLabel.CENTER);
form = new JPanel();
form.setLayout(new GridLayout(5,2));
		
name2 = new JLabel("Name" , JLabel.LEFT);
bookdate1 = new JLabel("Booking Date" , JLabel.LEFT);
tourdate1 = new JLabel("Tour Date" , JLabel.LEFT);		
		adultno1 = new JLabel("No of Adults" , JLabel.LEFT);		
		childno1 = new JLabel("No of Children",JLabel.LEFT);
		name1 = new JTextField();
		bookdate = new JTextField();
		tourdate= new JTextField();
		adultno= new JTextField();
		childno= new JTextField();
		form.add(name2);
		form.add(name1);
		form.add(bookdate1);
		form.add(bookdate);
		form.add(tourdate1);
		form.add(tourdate);
		form.add(adultno1);
		form.add(adultno);
		form.add(childno1);
		form.add(childno);
		frame.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent windowEvent)
			{
				System.exit(0);
			}
		});
		controlPanel = new JPanel();
		controlPanel.setLayout(new GridLayout(5,3));
		
		entry.add(top);
		entry.add(form);


}

private void show(){
top.setText("Fill Up the form");
JButton Submit=new JButton("Submit");
JButton exit= new JButton("Exit");
Submit.setActionCommand("ok");
exit.setActionCommand("exit");
Submit.addActionListener(new ButtonClickListener()); 
exit.addActionListener(new ButtonClickListener());
ButtonGroup group=new ButtonGroup();
rad1.setAlignmentX(Component.CENTER_ALIGNMENT);
rad2.setAlignmentX(Component.CENTER_ALIGNMENT);
rad3.setAlignmentX(Component.CENTER_ALIGNMENT);
group.add(rad1);
group.add(rad2);
group.add(rad3);
controlPanel.add(rad1);
controlPanel.add(rad2);
controlPanel.add(rad3);
controlPanel.add(Submit);
controlPanel.add(exit);
entry.add(controlPanel);
entry.add(bottom);
frame.add(entry);
frame.setVisible(true);

}
private class ButtonClickListener implements ActionListener{
tour_details T=new tour_details("","","",0,0,0.0);
public void clear(){
name1.setText("");
bookdate.setText("");
tourdate.setText("");
adultno.setText("");
childno.setText("");
}
public void actionPerformed(ActionEvent e){
String command=e.getActionCommand();
if(command.equals("exit")){
System.exit(0);
}
else if(command.equals("ok"))
{
String iname=name1.getText();
String date1=bookdate.getText();
String date2=tourdate.getText();
int count1=Integer.parseInt(adultno.getText());
int count2=Integer.parseInt(childno.getText());

if(rad1.isSelected()==true){
T.general(count1,count2);
}
else if(rad2.isSelected()==true){
T.business(count1,count2);
}
else if(rad3.isSelected()==true){
T.economy(count1,count2);
}
double total1=T.total;
T=new tour_details(iname,date1,date2,count1,count2,total1);
new secondFrame(T);
}
}

}

private class secondFrame{
JFrame j=new JFrame();
JPanel p=new JPanel();
Random r= new Random();
int val=r.nextInt(123456);
secondFrame(tour_details t){
j.setSize(400,400);
p.setSize(300,400);
JLabel a1,a2,a3,a4,a5,a6,a7,a8,a9,a10;
p.setLayout(new GridLayout(10,1));
a1=new JLabel("",JLabel.CENTER);
a2=new JLabel("",JLabel.CENTER);
a3=new JLabel("",JLabel.CENTER);
a4=new JLabel("",JLabel.CENTER);
a5=new JLabel("",JLabel.CENTER);
a6=new JLabel("",JLabel.CENTER);
a7=new JLabel("",JLabel.CENTER );
a8=new JLabel("",JLabel.CENTER);
p.add(a1);
p.add(a2);
p.add(a3);
p.add(a4);
p.add(a5);
p.add(a6);
p.add(a7);
p.add(a8);

a1.setText("Billing Information of "+t.name);
a2.setText("Billing No : "+val);
a3.setText("Billing Date :" + t.bookingdate);
a4.setText("Tour Date :" + t.tourdate);
a5.setText("Number of Adults:"+t.adults);
a6.setText("Number of Children : "+t.children);
a7.setText("Total Amount Payable : Rs"+ t.total);
a8.setText("HAPPY TRAVELLING");
j.add(p);
j.setVisible(true);


}
}
public static void main(String[] args){
tour t=new tour();
t.show();
}

}
class tour_details{
public int booking_no;
public String name;
public String bookingdate;
public String tourdate;
public int baseprice;
public double addcharges;
public int adults,children;
public double total;
tour_details(String name,String bookingdat,String tourdate,int adults,int children,double total){
this.name=name;
this.bookingdate=bookingdat;
this.tourdate=tourdate;
this.adults=adults;
this.children=children;
this.total=total;
}
public void general(int a,int c){
addcharges=0;
baseprice=1000;
total=2*baseprice*a+baseprice*c;
}
public void business(int a,int c){
baseprice=2500;
addcharges=625;
total=4*baseprice*a+baseprice*2*c +addcharges;
}
public void economy(int a ,int c){
baseprice=5000;
total=4*baseprice*a+baseprice*2*c+addcharges;
}

}
