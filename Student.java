import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
public class Student {
	StudentDetails[] list = new StudentDetails[60];	
	
	private JFrame mainFrame;
	private JPanel entry;
	
	private JLabel top;
	private JLabel bottom;
	private JPanel controlPanel;
	
	private JPanel form;
	private JTextField roll;
	private JTextField name;
	private JTextField dept;
	private JTextField date;
	private JTextField marks;
	private JLabel nameL;
	private JLabel rollL;		
	private JLabel deptL;
	private JLabel dateL;
	private JLabel Marks;
	private JTextField marks1;
	
	
	
	
	
	public Student()
	{
		prepareGUI();
	}
	private void prepareGUI()
	{
		//creating mainframe//

		mainFrame = new JFrame("Student Management System");
		mainFrame.setSize(800,400);
		mainFrame.setLayout(new GridLayout(1,2));
		mainFrame.getContentPane().setBackground(new Color(70,240,170));
		
		entry = new JPanel();
		entry.setSize(500,400);
		entry.setLayout(new GridLayout(4,2));
		
				top = new JLabel("" , JLabel.CENTER);
		bottom = new JLabel("" , JLabel.CENTER);
		
		form = new JPanel();
		form.setLayout(new GridLayout(5,2));
		
		nameL = new JLabel("Name" , JLabel.LEFT);
		rollL = new JLabel("Roll No." , JLabel.LEFT);
		deptL = new JLabel("Department" , JLabel.LEFT);		
		dateL = new JLabel("Date-of-admission(dd mm yyyy)" , JLabel.LEFT);		
		Marks = new JLabel("Marks Obtained(separated by spaces)",JLabel.LEFT);
		roll = new JTextField();
		name = new JTextField();
		dept = new JTextField();
		date = new JTextField();
		marks1= new JTextField();
		form.add(nameL);
		form.add(name);
		form.add(rollL);
		form.add(roll);
		form.add(deptL);
		form.add(dept);
		form.add(dateL);
		form.add(date);
		form.add(Marks);
		form.add(marks1);
		mainFrame.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent windowEvent)
			{
				System.exit(0);
			}
		});
		controlPanel = new JPanel();
		controlPanel.setLayout(new GridLayout(2,2));
		
		entry.add(top);
		entry.add(form);
	}
	private void showEvent()
	{
		top.setText("Enter credentials");
		
		JButton okButton = new JButton("Add Student");
		JButton sub = new JButton("Show Details");
		marks = new JTextField();
		JButton marksB = new JButton("Change Marks");
		
		okButton.setActionCommand("add");
		sub.setActionCommand("show");
		marksB.setActionCommand("marks");
		
		okButton.addActionListener(new ButtonClickListener()); 
		sub.addActionListener(new ButtonClickListener()); 
		marksB.addActionListener(new ButtonClickListener()); 

		controlPanel.add(okButton);
		controlPanel.add(sub);
		controlPanel.add(marks);
		controlPanel.add(marksB);   
		
		entry.add(controlPanel);
		
		entry.add(bottom);
				mainFrame.add(entry);
		
		
		mainFrame.setVisible(true);
	}
	private class ButtonClickListener implements ActionListener{
		public void clear()
		{
			roll.setText("");
			name.setText("");
			dept.setText("");
			date.setText("");
			marks.setText("");
			marks1.setText("");
		}
		public void actionPerformed(ActionEvent e)
		{
			StudentDetails s1 = new StudentDetails(0,"","",0,0,0);
			String command = e.getActionCommand();
			if(command.equals("add"))
			{
				String Iname = name.getText();
				int Iroll  = 0,dd=0,mm=0,yy=0;
				if(!(roll.getText().equals("")))
					Iroll = Integer.parseInt(roll.getText());
				String Idept = dept.getText();
				String[] dt = date.getText().split(" ");
				if(dt.length >= 3)
				{
					dd = Integer.parseInt(dt[0]);
					mm = Integer.parseInt(dt[1]);
					yy = Integer.parseInt(dt[2]);										
				}
				
				String r = s1.validate(Iname,Iroll,Idept,dd,mm,yy);
				if(r.equals("ok"))
				{
					if( list[Iroll] == null){
						s1 = new StudentDetails(Iroll,Iname,Idept,dd,mm,yy);
						list[Iroll] = s1;
						s1.updateMarks(marks1.getText());
						clear();
					}
					else
						r = "Roll no. already exists";
				}
				
				bottom.setText(r);
			}
			else if(command.equals("show"))
			
{
				if((roll.getText().equals("")))
					bottom.setText("Provide roll no");
				else{
					int Iproll = Integer.parseInt(roll.getText());
					if(list[Iproll] == null)
						bottom.setText("No Student Enrolled Yet with the Given Roll No.");
					else
					{
//opens new window//

new SecondFrame(s1,Iproll);
						
								
					}
				}
			}
			else if(command.equals("marks"))
			{
				if((marks.getText().equals("")))
					bottom.setText("Provide marks seperated by spaces");
				else if((roll.getText().equals("")))
					bottom.setText("Provide roll no");
				else if( list[Integer.parseInt(roll.getText())] == null)
					bottom.setText("Invalid roll no");	
				else{
					int Iproll = Integer.parseInt(roll.getText());
					s1 = list[Iproll];
					s1.updateMarks(marks.getText());
					bottom.setText("Updated marks ");
					clear();
				}				
			}
		}
	}

//class Second frame to open a new window to show details of a student//

public class SecondFrame {
private JFrame second=new JFrame("Details");
 
private JPanel viewer=new JPanel();										
	public SecondFrame(StudentDetails s1,int Iproll) {
second.setSize(300,300);	 
viewer.setLayout(new GridLayout(12,1));
		viewer.setSize(500,500);
		 JLabel view,vl1,vl2,vl3,vl4,vl5,vl6,vl7,vl8,vl9,vl10,vl11;
		view = new JLabel("" , JLabel.CENTER);
		vl1 = new JLabel("" , JLabel.CENTER);
		vl2 = new JLabel("" , JLabel.CENTER);
		vl3 = new JLabel("" , JLabel.CENTER);
		vl4 = new JLabel("" , JLabel.CENTER);
		vl5 = new JLabel("",JLabel.CENTER);
		vl6 = new JLabel("",JLabel.CENTER);								
		vl7 = new JLabel("",JLabel.CENTER);
		vl8 = new JLabel("",JLabel.CENTER);
		vl9 = new JLabel("",JLabel.CENTER);		
		vl10=new JLabel("",JLabel.CENTER);
		vl11=new JLabel("",JLabel.CENTER);
		
		viewer.add(view);
		viewer.add(vl1);
		viewer.add(vl2);
		viewer.add(vl3);
		viewer.add(vl11);						
		viewer.add(vl4);
		viewer.add(vl7);
		viewer.add(vl8);
		viewer.add(vl9);
		viewer.add(vl10);
		viewer.add(vl5);
		viewer.add(vl6);
s1 = list[Iproll];
view.setText("Details for:" + s1.getName());
vl1.setText("Roll No. :" + s1.getRoll());
vl2.setText("Department :" + s1.getDept());
vl3.setText("Date of Admission :" + s1.getDate());
vl4.setText("Subject 1 :" + s1.getMarks(0));
vl7.setText("Subject 1 :" + s1.getMarks(1));
vl8.setText("Subject 1 :" + s1.getMarks(2));
vl9.setText("Subject 1 :" + s1.getMarks(3));
vl10.setText("Subject 1 :" + s1.getMarks(4));
vl11.setText("MARKSHEET OF " + s1.getName());
if(s1.get_gpa()!=0)		

{
vl5.setText("CGPA " + s1.get_gpa());
vl6.setText("Remarks : Passed"); 
}
else
{
vl5.setText("CGPA : - ");
vl6.setText("Remarks : Failed ");

}
second.add(viewer);
second.setVisible(true);								

	}
}
	public static void main(String args[])
	{
		Student S = new Student();
		S.showEvent();
	}
	
	


}

//Class for Student Details//

class StudentDetails{
	int roll;
	String name;
	String course;
	int admissionDay;
	int admissionMonth;
	int admissionYear;		
	int[] marks=new int[5];
	double gpa;
	String validate(String name,int roll,String dept,int dd,int mm,int yy)
		{
			String res = "ok";
			if(yy<2010 || yy > 2016)	res = "InvalidDate";
			else if(mm<1 || mm >12)		res = "InvalidDate";
			else if(dd<1 || dd > 31)	res = "InvalidDate";
			if(roll < 1 || roll > 59)	res = "Roll no out of bound";
			if(name.equals(""))		res = "Name not given";
			if(dept.equals(""))		res = "Department not given";
			for(int i=0;i<5;i++){
if(marks[i]>100 || marks[i]<0)  res="Invalid Marks";
}			
			return res;
		}
	StudentDetails(int roll,String name,String course,int admissionDay,int admissionMonth,int admissionYear)
	{
		this.roll = roll;
		this.name = name;
		this.course = course;
		this.admissionDay = admissionDay;
		this.admissionMonth = admissionMonth;
		this.admissionYear = admissionYear;
		for(int i=0;i<5;i++)	marks[i] = 0;
		gpa=get_gpa();
	}
	String getRoll(){	
return Integer.toString(roll);	
}
	String getName(){	
return name;	
}
	String getDept(){	
return course;	
}
	String getDate(){	
return Integer.toString(admissionDay)+"/"+Integer.toString(admissionMonth)+"/"+Integer.toString(admissionYear);	
}	
	String getMarks(int i){	
return Integer.toString(marks[i]);		
}
	void updateMarks(String markstr)
	{
		String[] m = markstr.split(" ");
		for(int i=0;i<m.length && i<5; i++)
			marks[i] = Integer.parseInt(m[i]);
	}
 
int grade(int marks){
if(marks>=90 && marks<=100){
return 10;
}
else if(marks>=80 && marks<90){

return 9;
}
else if(marks>=70 && marks<80){

return 8;
}
else if(marks>=60 && marks<70){

return 7;
}
else if(marks>=50 && marks<60){

return 6;
}
else if(marks>=40 && marks<50){

return 5;
}
return 0;
}

double get_gpa(){
for(int i=0;i<5;i++){
if(grade(marks[i])==0)
{
return 0;
}
}
return ((grade(marks[0])+grade(marks[2])+grade(marks[3])+grade(marks[4])+grade(marks[1]))/5.0);
}
}
