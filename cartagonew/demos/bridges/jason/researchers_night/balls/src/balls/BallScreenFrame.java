package balls;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


import javax.swing.*;

import balls.util.*;



public class BallScreenFrame extends JFrame {
    
    private VisualiserPanel panel;
    private GraphicsDevice device;
    
    public BallScreenFrame(String screenTitle){
        setTitle(screenTitle);
        setSize(400,400);
        setResizable(false);
        panel = new VisualiserPanel();
        getContentPane().add(panel);
        addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent ev){
				System.exit(-1);
			}
			public void windowClosed(WindowEvent ev){
				System.exit(-1);
			}
		});
    }

    public BallScreenFrame(String screenTitle, boolean fullScreen, GraphicsDevice device){
    	super(device.getDefaultConfiguration());
        this.device = device;
        panel = new VisualiserPanel();
        getContentPane().add(panel);

        //setTitle("Display Mode Test");
        //originalDM = device.getDisplayMode();
        //setDMLabel(originalDM);
        //setDefaultCloseOperation(EXIT_ON_CLOSE);
        // Make sure a DM is always selected in the list
        //exit.addActionListener(this);
        //changeDM.addActionListener(this);
        //changeDM.setEnabled(device.isDisplayChangeSupported());
        if (fullScreen){
	        boolean isFullScreen = device.isFullScreenSupported();
	        setUndecorated(isFullScreen);
	        setResizable(!isFullScreen);
	        if (isFullScreen) {
	            // Full-screen mode
	            device.setFullScreenWindow(this);
	            validate();
	        } else {
	            // Windowed mode
	            pack();
	            setVisible(true);
	        }
        } else {
            setTitle(screenTitle);
            setSize(400,400);
            setResizable(false);
        }
        addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent ev){
				System.exit(-1);
			}
			public void windowClosed(WindowEvent ev){
				System.exit(-1);
			}
		});
    }
    
    
    public void updatePosition(BallInfo[] info){
        panel.updatePositions(info);
    }
        
    public static class VisualiserPanel extends JPanel {
        private BallInfo[] info;
        
        public VisualiserPanel(){
            setSize(400,400);
        }

        public void paint(Graphics g){
        	g.setColor(Color.WHITE);
            g.fillRect(0,0,400,400);
            g.setColor(Color.BLACK);
            
            Graphics2D g2 = (Graphics2D)g;
            
            Stroke stroke = new BasicStroke(3);
            //, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0,
            //		new float[] { 3, 1 }, 0);
            g2.setStroke(stroke);
            
            synchronized (this){
	            if (info!=null){
	                for (int i=0; i<info.length; i++){
		                P2d p = info[i].pos;
		                int x0 = (int)(200+p.x*200);
		                int y0 = (int)(200-p.y*200);
		                if (!info[i].controlled){
		                	g.setColor(Color.BLACK);
		                	g.drawOval(x0,y0,20,20);
		                } else {
		                	g.setColor(Color.RED);
		                	g.drawOval(x0,y0,20,20);
		                }
		            }
	            }
            }
        }
        
        public void updatePositions(BallInfo[] info){
            synchronized(this){
                this.info = info;
            }
            repaint();
        }
    }
}
