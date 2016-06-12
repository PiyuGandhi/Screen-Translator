package com.translate.main;

import java.awt.AWTException;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainFrame
{	private SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd hh mm ss a");
	private JFrame frame;
	private int width,height,lastX,lastY;
	
	public MainFrame()
	{	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		width = (int) screenSize.getWidth();
		height = (int) screenSize.getHeight();
		frame=new JFrame("");
		frame.setBounds(0, 0, width/4, height/4);
		frame.setAlwaysOnTop(true);
		frame.setUndecorated(true);
		frame.setBackground(new Color(0,0,0,0));
		TranslucentPane trans=new TranslucentPane(); 
		frame.setContentPane(trans);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		AnimationStation(frame);
	}
	
	public void AnimationStation(JFrame frame) {
	    MouseAdapter ma = new MouseAdapter() {
	        @Override
	        public void mousePressed(MouseEvent e) {
	            lastX = e.getXOnScreen();
	            lastY = e.getYOnScreen();
	        }
	        @Override
	        public void mouseDragged(MouseEvent e) {
	            int x = e.getXOnScreen();
	            int y = e.getYOnScreen();
	            // Move frame by the mouse delta
	            frame.setLocation(frame.getLocationOnScreen().x + x - lastX,frame.getLocationOnScreen().y + y - lastY);
	            lastX = x;
	            lastY = y;
	        }
	        @Override
	        public void mouseReleased(MouseEvent e) 
	        {	//System.out.println("Send Request");
		        Calendar now = Calendar.getInstance();
				try {
					frame.setVisible(false);
					Robot robot = new Robot();
					BufferedImage screenShot = robot.createScreenCapture(new Rectangle(lastX-width/8,lastY-height/8,width/4,height/4));
					ImageIO.write(screenShot,"JPG",new File(formatter.format(now.getTime())+".jpg"));    
					frame.setVisible(true);
				} 
				catch (Exception e1) {
					e1.printStackTrace();
				}
		        //System.out.println(formatter.format(now.getTime()));
	        }
	    };
	    frame.addMouseListener(ma);
	    frame.addMouseMotionListener(ma);
	}
	
	public static void main(String[] args) {
		MainFrame frame=new MainFrame();
		try
		{	
		}
		catch(Exception e)
		{	e.printStackTrace();
		}
	}
	
	public class TranslucentPane extends JPanel {
		private static final long serialVersionUID = 1L;

		public TranslucentPane() {
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g); 
            Graphics2D g2d=(Graphics2D)g.create();
            g2d.setComposite(AlphaComposite.SrcOver.derive(0.1f));
            g2d.setColor(getBackground());
            g2d.fillRect(0,0,getWidth(),getHeight());
        }
    }
	
	public void screenShot(int x,int y) throws Exception
    {   
    }
}
