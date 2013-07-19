package balls;

import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import cartago.*;
import cartago.tools.*;

public class FakeBallSceneState extends GUIArtifact {

	private static final String STATE = "state";
	private MyFrame frame;
	
	public void setup(){
		defineObsProperty(STATE, "stop");
		frame = new MyFrame();
		linkActionEventToOp(frame.go,"setGoState");
		linkActionEventToOp(frame.stop,"setIdleState");
		linkActionEventToOp(frame.revert,"revertDir");
		frame.setVisible(true);
	}
	
	@INTERNAL_OPERATION void setGoState(ActionEvent ev){
		getObsProperty(STATE).updateValue("running");
	}

	@INTERNAL_OPERATION void setIdleState(ActionEvent ev){
		getObsProperty(STATE).updateValue("idle");
	}

	@INTERNAL_OPERATION void revertDir(ActionEvent ev){
		signal("revert_dir");
	}

	class MyFrame extends JFrame {		
		
		private JButton go;
		private JButton stop;
		private JButton revert;
		
		public MyFrame(){
			setTitle("Controller ");
			setSize(200,100);
			
			JPanel panel = new JPanel();
			setContentPane(panel);
			
			go = new JButton("Go");
			go.setSize(80,50);
			stop = new JButton("Stop");
			stop.setSize(80,50);
			revert = new JButton("Revert");
			revert.setSize(80,50);
			
			panel.add(go);
			panel.add(stop);
			panel.add(revert);
		}
	}

}