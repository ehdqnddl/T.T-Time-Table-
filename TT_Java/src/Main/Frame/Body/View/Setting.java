package Main.Frame.Body.View;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class Setting extends JPanel implements MouseListener,Runnable{
	
	private ArrayList<Circle> circleList;
	private Boolean bStart;
	private Boolean bSuspend;
	private Thread thread;

	public Setting() {
		// TODO Auto-generated constructor stub
		this.setLayout(null);
		this.addMouseListener(this);
		this.setBackground(Color.BLACK);
		
		thread = new Thread(this);
		thread.start();
		
		circleList = new ArrayList<>();
		bStart = true;
		bSuspend = false;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		g.setColor(Color.WHITE);
		for(int i=0;i<circleList.size();i++)
			circleList.get(i).drawCircle(g);
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

		Circle circle = new Circle(e.getPoint());
		circleList.add(circle);
	}


	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public void checkThread(String str)
	{
		if(str!="S")
		{
			if(!bSuspend)
				bSuspend = true;
		}
		else
		{
			if(bSuspend)
				bSuspend = false;	
		}
	}
	public void setFinish()
	{
		bStart = false;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(bStart)
		{
			if(!bSuspend)
			{
			try {
				Thread.sleep(33);
				for(int i=0;i<circleList.size();i++)
					circleList.get(i).Operator();
				repaint();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
		}
		
	}
	
	private class Circle extends JPanel
	{
		private Point ptClick;
		private float fRadius;
		private Boolean bSwitch;
		
		public Circle(Point point) {
			// TODO Auto-generated constructor stub
			ptClick = new Point();
			ptClick = point;
			fRadius = 50.0f;
			bSwitch = true;
		}
		
		public float GetRadius()
		{
			return fRadius;
		}
		
		public Point GetPoint()
		{
			return ptClick;
		}
		
		public void drawCircle(Graphics g)
		{
			g.drawOval((int)(ptClick.x-fRadius/2.0f),(int)(ptClick.y-fRadius/2.0f),(int)(fRadius),(int)(fRadius));
		}
		
		public void Operator()
		{
			if(bSwitch)
			{
				fRadius-=2;
				if(fRadius<1.0f)
					bSwitch = false;
			}
			else
			{
				fRadius+=2;
				if(fRadius>29.0f)
					bSwitch = true;
			}
		}
		
	}
}
