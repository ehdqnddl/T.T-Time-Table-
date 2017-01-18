package Main.Frame.Body.View;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class HomeView extends JPanel implements MouseMotionListener,MouseListener {
	private ArrayList<DragValue> dragList;
	private DragValue dragValue;
	private JLabel lblCount;
	private int nCount;

	public HomeView() {
		// TODO Auto-generated constructor stub
		this.setLayout(null);
		dragList = new ArrayList<DragValue>();
		dragValue = new DragValue();
		nCount =0;
		lblCount = new JLabel("count : "+nCount);
		lblCount.setBounds(10, 10, 150, 30);
		this.add(lblCount);

		this.addMouseListener(this);
		this.addMouseMotionListener(this);
	}

	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);

		for(int i=0;i<dragList.size();i++)
			dragList.get(i).paintPoint(g);
		dragValue.paintPoint(g);
		lblCount.setText("count : "+nCount);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		dragValue = new DragValue();
		dragValue.addPoint(e.getPoint());
		nCount++;
		this.repaint();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		dragList.add(dragValue);
		this.repaint();
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		dragValue.addPoint(e.getPoint());
		nCount++;
		this.repaint();

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	private class DragValue
	{
		private ArrayList<Point> pointList;

		public DragValue()
		{
			pointList = new ArrayList<Point>();
		}
		public void addPoint(Point point)
		{
			pointList.add(point);
		}
		public void paintPoint(Graphics g)
		{
			for(int i=0;i<pointList.size()-1;i++)
				g.drawLine(pointList.get(i).x, pointList.get(i).y, pointList.get(i+1).x, pointList.get(i+1).y);
		}
	}

}
