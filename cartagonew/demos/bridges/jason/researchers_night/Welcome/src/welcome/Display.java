package welcome;

import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.*;
import java.util.concurrent.locks.ReentrantLock;

import cartago.*;
import cartago.tools.*;

public class Display extends GUIArtifact {

	private MyFrame frame;
	
	private final static long REFRESH_TIME = 5000;
	private final static long AWIT_TIME = 1000;
	
	private boolean isModified;
	private long lastMod;
	private final static String DEFAULT_MESSAGE = "The most profound technologies are those that disappear";

	
	public void setup() {
		frame = new MyFrame();

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);	
		
		execInternalOp("exec");
	}
	
	@INTERNAL_OPERATION void exec() {
		if (isModified) {
			if (System.currentTimeMillis() - lastMod > REFRESH_TIME) {
				execInternalOp("setDefaultValue");
			}
		}
		else {
			try {
				this.await_time(AWIT_TIME);
			}
			catch (Exception e) { }
		}
		execInternalOp("exec");
	}
	
	@INTERNAL_OPERATION void setDefaultValue() {
		frame.setText(DEFAULT_MESSAGE,30);
		isModified = false;
		frame.repaint();
	}

	@OPERATION void setValue(String value){
		frame.setText(value,80);
		frame.repaint();
		isModified = true;
		lastMod = System.currentTimeMillis();
	}
	
	class MyFrame extends JFrame {		
		
		private JLabel label;
		
		public MyFrame(){
			Toolkit kit = getToolkit();
			Dimension d = kit.getScreenSize();
			this.setSize((int)d.getWidth(),(int)d.getHeight());
			Dimension w = this.getSize();
			setBounds((int)(d.getWidth()/2 - w.width/2),(int)d.getHeight()/2 - w.height/2,w.width,w.height);
			
			ImagePanel panel = new ImagePanel("img/bckg2.png");
			setContentPane(panel);
			panel.setLayout(new BorderLayout());
			//this.setBackground(new Color(15,5,88));
			
			panel.add(label = new JLabel(),BorderLayout.CENTER);
			
			label.setFont(new Font("Lucida Handwriting", Font.PLAIN, 30));
			label.setForeground(Color.WHITE);
			label.setText(" " + DEFAULT_MESSAGE);
			label.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		}
		
		public void setText(String s, int size){
			label.setText(" " + s);
			label.setFont(new Font("Lucida Handwriting", Font.PLAIN, size));
		}
	}
	
	class ImagePanel extends JPanel {

		  private Image img;

		  public ImagePanel(String img) {
		    this(new ImageIcon(img).getImage());
		  }

		  public ImagePanel(Image img) {
		    this.img = img;
		    setLayout(null);
		  }

		  public void paintComponent(Graphics g) {
		    g.drawImage(img, 0, 0, null);
		  }
	}

}
