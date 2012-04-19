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
