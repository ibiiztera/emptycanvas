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

public class ZBufferFactory
{
    private static ZBufferImpl insta = null;
    private static int la=-1, ha=-1;
	public static ZBuffer instance(int x, int y)
	{
            if(la==x&&ha==y&&insta!=null)
            {
                return insta;
            }
            la = x;
            ha = y;
            insta = new ZBufferImpl(x, y);
            return insta;
	}
	public static ZBuffer instance(int x, int y, Scene s)
	{
		
		ZBuffer z =  new ZBufferImpl(x, y);
		z.scene(s);
		return z;
	}
}