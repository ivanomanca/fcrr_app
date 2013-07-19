package events;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import cartago.INTERNAL_OPERATION;
import cartago.OPERATION;
import cartago.tools.GUIArtifact;

public class GUIAddParticipant extends GUIArtifact {

	private MyFrame frame;
	
	public void setup() {
		frame = new MyFrame();
		
		linkActionEventToOp(frame.addEvent,"addEvent");
		linkActionEventToOp(frame.addParticipant,"addParticipant");
		linkWindowClosingEventToOp(frame, "closed");
		frame.setVisible(true);		
	} 

	@OPERATION void addEvent(ActionEvent ev){
		String[] coord = new String[2];
		coord = getCoordinates().split(",");
		//System.out.println("seninding addEvent signal");
		if((getTextEventName().equals("")) || (coord == null) 
				|| (coord[0].equals("")) || (coord[1].equals("")) || getMinNumber().equals(""))
			JOptionPane.showMessageDialog(frame, "Completare correttamente tutti i campi", "Error", JOptionPane.ERROR_MESSAGE);
		else
			signal("gui_addEvent", getTextEventName(), Double.valueOf(coord[0]), Double.valueOf(coord[1]), getDescription(), Integer.valueOf(getMinNumber()));
		
	}

	@OPERATION void addParticipant(ActionEvent ev){
		//System.out.println("seninding addParticipant signal");
		if(getParticipant().equals(""))
			JOptionPane.showMessageDialog(frame, "Inserire le informazione del partecipante e il nome dell'evento", "Error", JOptionPane.ERROR_MESSAGE);
		else
			signal("gui_addParticipant", getTextEventName(), getParticipant());
	}
	
	@INTERNAL_OPERATION void closed(WindowEvent ev){
		signal("closed");
	}
	
	private String getTextEventName(){
		return frame.getTextEventName();
	}
	private String getParticipant(){
		return frame.getTextParticipant();
	}

	private String getCoordinates(){
		return frame.getTextEventCoordinates();
	}

	private String getDescription(){
		return frame.getTextEventDescription();
	}

	private String getMinNumber(){
		return frame.getTextMinNumber();
	}

	
	private class MyFrame extends JFrame {		
		
		private JButton addEvent;
		private JButton addParticipant;
		private JTextField textEventName;
		private JTextField textEventCoordinates;
		private JTextField textEventDescription;
		private JTextField textParticipant;
		private JTextField textMinNumber;
		private String[] labels = new String[]{"Nome evento", "Coordinate", "Descrizione" ,"N° minimo", "", "Partecipante", ""}; 
		public MyFrame(){
			setTitle("Simple GUI ");
			setSize(300,400);
			
			
	        JPanel p = new JPanel(new SpringLayout());

            JLabel l0 = new JLabel(labels[0], JLabel.TRAILING);
            p.add(l0);
            textEventName = new JTextField(10);
            l0.setLabelFor(textEventName);
            p.add(textEventName);
            
            JLabel l1 = new JLabel(labels[1], JLabel.TRAILING);
            p.add(l1);
            textEventCoordinates = new JTextField(10);
            l1.setLabelFor(textEventCoordinates);
            p.add(textEventCoordinates);
            
            JLabel l2 = new JLabel(labels[2], JLabel.TRAILING);
            p.add(l2);
            textEventDescription = new JTextField(10);
            l2.setLabelFor(textEventDescription);
            p.add(textEventDescription);

            JLabel l3 = new JLabel(labels[3], JLabel.TRAILING);
            p.add(l3);
            textMinNumber = new JTextField();
            l3.setLabelFor(textMinNumber);
            p.add(textMinNumber);

            JLabel l4 = new JLabel(labels[4], JLabel.TRAILING);
            p.add(l4);
            addEvent = new JButton("Add event");
            l4.setLabelFor(addEvent);
            p.add(addEvent);

            JLabel l5 = new JLabel(labels[5], JLabel.TRAILING);
            p.add(l5);
            textParticipant = new JTextField(10);
            l5.setLabelFor(textParticipant);
            p.add(textParticipant);

            JLabel l6 = new JLabel(labels[6], JLabel.TRAILING);
            p.add(l6);
            addParticipant = new JButton("Add participant");
             l6.setLabelFor(addParticipant);
            p.add(addParticipant);
            
	        //Lay out the panel.
	        SpringUtilities.makeCompactGrid(p,
	                                        7, 2, //rows, cols
	                                        6, 6,        //initX, initY
	                                        6, 6);       //xPad, yPad
			setContentPane(p);
		}
		
		public String getTextParticipant(){
			return textEventName.getText();
		}

		public String getTextEventName() {
			return textEventName.getText();
		}

		public String getTextEventCoordinates() {
			return textEventCoordinates.getText();
		}

		public String getTextEventDescription() {
			return textEventDescription.getText();
		}

		public String getTextMinNumber() {
			return textMinNumber.getText();
		}
	}
}
