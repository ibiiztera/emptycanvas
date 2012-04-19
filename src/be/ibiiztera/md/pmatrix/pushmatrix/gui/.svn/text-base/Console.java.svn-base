package be.ibiiztera.md.pmatrix.pushmatrix.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;

import be.ibiiztera.md.pmatrix.pushmatrix.Scene;
import be.ibiiztera.md.pmatrix.pushmatrix.scripts.console.CommandResultat;
import be.ibiiztera.md.pmatrix.pushmatrix.scripts.console.Console3D;

@SuppressWarnings("serial")
public class Console extends JPanel{
	private JTextArea command;
	private JTable historique;
	private JButton btnOK;
	private Scene scene;
	private JButton dump;
	
	public void setOriginalScene(Scene sc)
	{
		this.scene = sc;
	}
	
	private int n = 0;
	public Console ()
	{
		super();
		
		command = new JTextArea("cmd?");
		command.setColumns(40);
		command.setRows(20);
		historique = new  JTable(10,1);
		btnOK = new JButton("Envoyer");
		
		btnOK.addActionListener(new ActionListener()
		{
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				CommandResultat cm ;
				Console3D c3D = new Console3D();
				if(scene!=null)
				{
					cm =  c3D.executeCommande(command.getText(), scene);
				}
				else
				{
					cm =new CommandResultat();
					cm.setResultat(false);
				}
				historique.setValueAt(command.getText()+ "{"+(cm.isResultat())+"}", n++, 0);
				
			}
		});
		dump = new JButton("DUMP");
		dump.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				command.setText(scene.toString());
				
			}
		});
		add(command);
		add(btnOK);
		add(historique);
		add(dump);
	}
	
	
	public JTextArea getCommand() {
		return command;
	}
	public void setCommand(JTextArea command) {
		this.command = command;
	}
	public JTable getHistorique() {
		return historique;
	}
	public void setHistorique(JTable historique) {
		this.historique = historique;
	}
	public JButton getBtnOK() {
		return btnOK;
	}
	public void setBtnOK(JButton btnOK) {
		this.btnOK = btnOK;
	}
	
	
}
