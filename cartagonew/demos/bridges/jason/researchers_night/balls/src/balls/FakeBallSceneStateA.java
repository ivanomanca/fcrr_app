package balls;

import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import cartago.*;
import cartago.tools.*;

public class FakeBallSceneStateA extends GUIArtifact {

	private MyFrame frame;
	
	public void setup(){
		frame = new MyFrame();
		linkActionEventToOp(frame.go,"go");
		linkActionEventToOp(frame.stop,"freeze");
		frame.setVisible(true);
		frame.go.setEnabled(true);
		frame.stop.setEnabled(false);
	}
	
	@INTERNAL_OPERATION void go(ActionEvent ev){
		frame.go.setEnabled(false);
		frame.stop.setEnabled(true);
		signal("go");
	}

	@INTERNAL_OPERATION void freeze(ActionEvent ev){
		frame.go.setEnabled(true);
		frame.stop.setEnabled(false);
		signal("freeze");
	}

	class MyFrame extends JFrame {		
		
		private JButton go;
		private JButton stop;
		
		public MyFrame(){
			setTitle("Controller ");
			setSize(200,100);
			
			JPanel panel = new JPanel();
			setContentPane(panel);
			
			go = new JButton("Go");
			go.setSize(80,50);
			stop = new JButton("Freeze");
			stop.setSize(80,50);

			panel.add(go);
			panel.add(stop);
		}
	}

}