/*

    Copyright (C) 2010-2012  DAHMEN, Manuel, Daniel

    This library is free software; you can redistribute it and/or
    modify it under the terms of the GNU Lesser General Public
    License as published by the Free Software Foundation; either
    version 2.1 of the License, or (at your option) any later version.

    This library is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
    Lesser General Public License for more details.

    You should have received a copy of the GNU Lesser General Public
    License along with this library; if not, write to the Free Software
    Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA

*/
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