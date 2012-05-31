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
package be.ibiiztera.md.pmatrix.pushmatrix.ui;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import be.ibiiztera.md.pmatrix.pushmatrix.Representable;
import be.ibiiztera.md.pmatrix.pushmatrix.Scene;
import java.io.BufferedOutputStream;
import java.io.PrintWriter;

public class ModeleIO {
	public static boolean sauvergarder(Scene sc, File file) {
		boolean r = false;
		ObjectOutputStream dos = null;
		try {
			sc.dumpDATA();
			dos = new ObjectOutputStream(new FileOutputStream(file));
			r = true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			dos.writeObject(sc);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return r;
	}
	public static boolean sauvergarderTXT(Scene sc, File file) {
        	String txt = sc.toString();
		boolean r = false;
		FileOutputStream dos = null;
                BufferedOutputStream pw =null;
		try {
                        dos = new FileOutputStream(file);
                        pw = new BufferedOutputStream(dos);
			pw.write(txt.getBytes(), 0, txt.length());
                        pw.close();
                        dos.close();
			r = true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return r;
	}

	public Scene charger(Scene sc, File file) {
		boolean r = false;
		ObjectInputStream dos = null;
		try {
			dos = new ObjectInputStream(new FileInputStream(file));
			r = true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			return (Scene) dos.readObject();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static boolean sauvergarder(Representable o, File file) {
		boolean r = false;
		ObjectOutputStream dos = null;
		try {
			dos = new ObjectOutputStream(new FileOutputStream(file));
			r = true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			dos.writeObject(o);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return r;
	}
}
