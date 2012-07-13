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
package be.ibiiztera.md.pmatrix.pushmatrix;

/**
 * @author MANUEL DAHMEN
 *
 * dev
 *
 * 15 déc. 2011
 *
 */
public class IntervalleDeTemps {
	private Animation a;
	private int frame0 = 0;
	private int frame1 = 0;

	public static final int T0ANIME_STARTI = -1;
	public static final int T1ANIME_ENDING = -1;

	
	public void animation(Animation anim)
	{
		this.a = anim;
	}
	public IntervalleDeTemps(int frame0, int frame1) {
		if (frame0 == T0ANIME_STARTI)
			this.frame0 = 0;
		else
			this.frame0 = frame0;
		if (frame1 == T1ANIME_ENDING)
			this.frame1 = (int) (a.dureeSec() * a.fps());
		else
			this.frame1 = frame1;
	}

	public IntervalleDeTemps(double t0, double t1) {
		if (frame0 == T0ANIME_STARTI)
			this.frame0 = 0;
		else
			frame0 = (int) (t0 * a.fps());
		if (frame1 == T1ANIME_ENDING)
			this.frame1 = (int) (a.dureeSec() * a.fps());
		else
			frame1 = (int) (t1 * a.fps());
	}

}
