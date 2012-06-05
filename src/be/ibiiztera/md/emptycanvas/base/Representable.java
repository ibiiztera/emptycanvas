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
package be.ibiiztera.md.emptycanvas.base;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;

public interface Representable extends Serializable {

    public String id();

    public void setId(String id);

    //public int type();

    //public String typeString();

    //public int encode(OutputStream out);

    //public Representable decode(InputStream out);

    public Representable place(MODObjet aThis);
}