package projector;

import java.awt.Color;
import java.awt.Container;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import cartago.AgentId;
import cartago.GUARD;
import cartago.OPERATION;
import cartago.tools.GUIArtifact;

/**
 * 
 * @author mguidi
 *
 */
public class DeviceArtifact extends GUIArtifact {
	
	private MyFrame mFrame;
	private AgentId mAgentId;
	private String mDefautlUlr;
	
	public void setup() {
		super.setup();
		
		mAgentId = null;
		mDefautlUlr = "http://dl.dropbox.com/u/1366161/research_night_cesena.png";
		defineObsProperty("imageUrl", mDefautlUlr);
		mFrame = new MyFrame();
		
		try {
			URL url= new URL(mDefautlUlr);
			Image img = Toolkit.getDefaultToolkit().createImage(url);
			mFrame.drawImage(img);
		} catch (MalformedURLException e) {
			mFrame.clean();
		}
		
		mFrame.setVisible(true);
		
	}
	
	
	@GUARD boolean isNotLocked() {
		return mAgentId==null;
	}
	
	@OPERATION(guard="isNotLocked") void lock() {
		mAgentId = getOpUserId();
	}
	
	@OPERATION void unlock() {
		mAgentId = null;
		
		getObsProperty("imageUrl").updateValue(mDefautlUlr);
		try {
			URL url= new URL(mDefautlUlr);
			Image img = Toolkit.getDefaultToolkit().createImage(url);
			mFrame.drawImage(img);
		} catch (MalformedURLException e) {
			mFrame.clean();
		}
		
	}
	
	@OPERATION void drawImage(byte[] data, String spec) {
		if (mAgentId!=null && mAgentId.equals(getOpUserId())) {
			if (data !=null && spec!=null && !spec.equals("")) {
				Image img = Toolkit.getDefaultToolkit().createImage(data);
				mFrame.drawImage(img);
				getObsProperty("imageUrl").updateValue(spec);
			} else {
				getObsProperty("imageUrl").updateValue("");
			}
		} else {
			failed("Agent must retain device lock.");
		}
	}
	
	@OPERATION void drawImageUrl(String spec) {
		if (mAgentId!=null && mAgentId.equals(getOpUserId())) {
			URL url;
			try {
				if (spec !=null && !spec.equals("")) {
					url = new URL(spec);
					Image img = Toolkit.getDefaultToolkit().createImage(url);
					mFrame.drawImage(img);
					getObsProperty("imageUrl").updateValue(spec);
				} else {
					getObsProperty("imageUrl").updateValue("");
				}
			} catch (MalformedURLException e) {
				getObsProperty("imageUrl").updateValue("");
				failed("malformed url exception");
			}
		} else {
			failed("Agent must retain device lock.");
		}
	}

	class MyFrame extends JFrame {
		
		private static final long serialVersionUID = 1L;
		private DrawingPanel mDrawing_panel;
		
		public MyFrame() {
			Container content_pane = getContentPane();
			
			mDrawing_panel =  new DrawingPanel();
			setBackground(Color.BLACK);
		    content_pane.add (mDrawing_panel); 
			setUndecorated(true);
			pack();
			setExtendedState(Frame.MAXIMIZED_BOTH);
		}
		
		public void clean() {
			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					mDrawing_panel.clean();
				}
			});
		}
		
		public void drawImage(final Image img) {
			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					mDrawing_panel.drawImage(img);
				}
			});
		}
		
		public void drawImage(final byte[] data) {
			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					Image img = null;
					if (data!=null) {
						img = Toolkit.getDefaultToolkit().createImage(data);
					}
					mDrawing_panel.drawImage(img);
				}
			});
		}
		
		class DrawingPanel extends JPanel {
			private static final long serialVersionUID = 1L;
			private Image m_Image;
			private Image m_ScaledImage;    // scaled instance of the image to be displayed
			
			public DrawingPanel() {
				setBackground(Color.BLACK);
			}
			
			void clean() {
				m_Image = null;
				repaint();
			}
			
			void drawImage(Image img) {
				m_Image = img;
				repaint();
			}
			
			/** Calculate the scale required to correctly fit the image into panel */
		    private double GetScale(int panelWidth, int panelHeight, int imageWidth, int imageHeight) {
		        double scale = 1;
		        double xScale;
		        double yScale;
		        
		        if (imageWidth > panelWidth || imageHeight > panelHeight) {
		            xScale = (double)imageWidth  / panelWidth;
		            yScale = (double)imageHeight / panelHeight;
		            scale = Math.min(xScale, yScale);
		        }
		        else if (imageWidth < panelWidth && imageHeight < panelHeight) {
		            xScale = (double)panelWidth / imageWidth;
		            yScale = (double)panelHeight / imageHeight;
		            scale = Math.min(xScale, yScale);
		        }
		        else {
		            scale = 1;
		        }
		 
		        return scale;
		    }
		    
		    /** Override paint method of the panel */
		    public void paint(Graphics g) {
		    	super.paintComponent(g);
		        if( m_Image != null) {
		 
		            Graphics2D g2 = (Graphics2D)g;
		            g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
		 
		            m_ScaledImage = m_Image;
		            
		            // Get the required sizes for display and calculations
		            int panelWidth = this.getWidth();
		            int panelHeight = this.getHeight();
		            int imageWidth = m_ScaledImage.getWidth(this);
		            int imageHeight = m_ScaledImage.getHeight(this);
		            
		            double scale = GetScale(panelWidth, panelHeight, imageWidth, imageHeight);
		            
		            /**
		            System.out.println("---------------------------------------");
		            System.out.println("Panel: "+this.getName()+".");
		            System.out.println("Size: "+panelWidth+", "+panelHeight+".");
		            System.out.println("Image size: "+m_ScaledImage.getWidth()+", "+m_ScaledImage.getHeight()+".");
		            System.out.println("Scale: "+scale+".");
		             **/
		            
		            // Calculate the center position of the panel -- with scale
		            double xPos = (panelWidth - scale * imageWidth)/2;
		            double yPos = (panelHeight - scale * imageHeight)/2;
		 
		            // Locate, scale and draw image
		            AffineTransform at = AffineTransform.getTranslateInstance(xPos, yPos);
		            at.scale(scale, scale);
		            //g2.drawRenderedImage(m_ScaledImage, at);
		            g2.drawImage(m_ScaledImage, at, this);
		        }
		    } 
			
		}
	}
}
