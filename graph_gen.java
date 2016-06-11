import java.io.*;
import java.util.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.*;
import java.awt.geom.*;
import javax.swing.JFrame;
import javax.swing.JPanel;

class Point
{
	public int x;
	public int y;
	Point(int x,int y){ this.x = x;this.y = y;	}
}
class Edge
{
	public int s;
	public int e;
	Edge(int x,int y){	s = x;e = y;	}
}

public class graph_gen extends JPanel{

static int maxdegree=6;
static int n=10;
static int []degree=new int[10];
static int sum=0;
static int []seqn=new int[maxdegree+1];
private static Edge edges[];
public static void main(String[] args){
seq();
assign_degree();
gen();
		JFrame f = new JFrame();
		f.setSize(2000, 1000);
		f.add(new graph_gen());
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);

}

//the normal distribution function//

public static double normal(int x,int maxdegree){

double m=5;
double v=1;
double t=t = -1 * (x-m)*(x-m)/(2*v*v);
return Math.exp(t)/(Math.sqrt(2*Math.PI)*v);

}


//generating degree sequence//

public static void seq(){


for(int i=0;i<=maxdegree;i++)
{

seqn[i]=(int)(n*(normal(i,maxdegree)));
System.out.println(seqn[i]);
sum+=seqn[i]*i;
}
//sum of degrees must be even//

if(sum%2!=0)
sum++;

System.out.println(sum);
edges=new Edge[sum/2];
}

//assign degree randomly to all vertices//

public static void assign_degree(){


for(int i=0;i<=maxdegree;){

if(seqn[i]==0)
i++;
else{
	for(int j=0;j<seqn[i];j++){


	int k=(int)(Math.random()*10);
if(degree[k]==0)
degree[k]=seqn[i];
else{
		while(degree[k]!=0)
				{

			k=(int)(Math.random()*10);
			if(degree[k]==0){
			degree[k]=seqn[i];
			break;
			}
				}
}

					}

i++;
}




}
for(int i=0;i<n;i++)
System.out.println("degree"+(i+1)+":"+degree[i]);

}


//generate graph randomly//

public static void gen(){

int e=0;

while(sum>0){

int a =(int)(n*Math.random());
int b=(int)(n*Math.random());
if(degree[a]==0 || degree[b]==0)
continue;
else{

degree[a]--;
degree[b]--;
System.out.println(a+"-->"+b);
sum-=2;
edges[e]=new Edge(a,b);
e++;

}


}

for(int i=0;i<n;i++)
{
if(degree[i]>0)
{
while(degree[i]>0)
{
System.out.println(i+"-->"+i);
edges[e]=new Edge(i,i);
e++;
degree[i]--;

}

}
}



}
private static int n1 = n;
private static int r = 200;
private static Point points[] = new Point[n1];
private static int numEdges=sum/2;
public void paint(Graphics g) 
	{
		Graphics2D g2d = (Graphics2D) g;
		int diameter = 10;
		int x=0,y=0;
		g2d.setPaint(new Color(100,20,150));
		for(int i=0;i<n1;i++)
		{
			x = (int) (r * Math.cos(Math.toRadians(i*360/n)));
			y = (int) (r * Math.sin(Math.toRadians(i*360/n)));	
			x = x + 600;
			y =  300 - y;
			g2d.fillOval(x ,y , diameter,diameter);	
			points[i] = new Point(x,y);
		}

for(int i=0;i<edges.length;i++){
if(edges[i].s!=edges[i].e)
			g2d.draw(new QuadCurve2D.Double(points[edges[i].s].x,points[edges[i].s].y,points[edges[i].e].x-i*40,points[edges[i].e].y-i*45,points[edges[i].e].x,points[edges[i].e].y));

else{

int a=points[edges[i].s].x;
int b=points[edges[i].e].y ;
int rectheight=i*10;
g2d.draw(new Ellipse2D.Double(a, b,
                            i*5 ,
                             rectheight));

}
}	
}
}
