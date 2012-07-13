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
package be.ibiiztera.md.pmatrix.pushmatrix.math;

import java.util.Vector;

/**
 * @author MANUEL DAHMEN
 *
 * dev
 *
 * 17 oct. 2011
 *
 */
public class OpConteneur {
	private int nIN;
	private int nOUT;
	private Vector<Double> in;
	private Vector<Double> out;
	
	private Operateur app;
	
	public OpConteneur(Operateur app)
	{
		this.app = app;
	}
	
	public int getnIN() {
		return nIN;
	}
	public void setnIN(int nIN) {
		this.nIN = nIN;
	}
	public int getnOUT() {
		return nOUT;
	}
	public void setnOUT(int nOUT) {
		this.nOUT = nOUT;
	}
	public Vector<Double> getOut() {
		return out;
	}
	public void setIn(Vector<Double> in) {
		this.in = in;
	}
	
	
}
