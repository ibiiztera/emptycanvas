import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
public class Lanceur extends JFrame
{
	public static String  compPATH = "c:\\java\\bin\\";
	private JButton compile;
	private JButton dist;
	private JButton run;
	private String src = "c:\\dev\\starbuck-0.1\\src\\";
	public Lanceur(String t)
	{
		super(t);
		compile = new JButton("Compiler");
		dist = new JButton("Distribuer");
		run = new JButton("Lancer");
		
		compile.addActionListener(
			new ActionListener()
			{
				Runtime rt = Runtime.getRuntime();
				public void actionPerformed(ActionEvent e)
				{
					File f = new File(src);
					readdir(f);
				}
				public void readdir(File dir)
				{
					if(dir.isDirectory())
					{
						String[] files = dir.list();
						for(int i=0; i<files.length; i++)
						{
							String f = files[i];
							File ff = new File(dir.getAbsolutePath()+File.separator+f);
							if(f.endsWith(".java"))
							{
								String cmd = compPATH + "javac.exe -d bin\\" + ff.getAbsolutePath();
								try{rt.exec(cmd);}
								catch(Exception ex) { ex.printStackTrace();}
								System.out.println(cmd);
							}
							if(ff.isDirectory())
							{
								readdir(ff);
							}
						}
					}
}
			}
		);
		dist.addActionListener(
			new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
				}
			}
		);
		run.addActionListener(
			new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					be.ibiiztera.md.pmatrix.starbuck01.NEWMain.main(new String [] {});
				}
			}
		);
		
		
		JPanel panel = new JPanel();
		this.add(panel);
		
		panel.add(compile, BorderLayout.NORTH);
		panel.add(dist, BorderLayout.CENTER);
		panel.add(run, BorderLayout.SOUTH);
		
		this.pack();
		
		this.setVisible(true);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void main(String [] args)
	{
		Lanceur l = new Lanceur("Ibiiz Tera");
		
	}
}