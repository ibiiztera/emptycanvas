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
/***
 * rotation, translation, scaling

Translation et rotation d'objets.
(OBJET) @ ((TRANSLATION: V3D) (ROTATION : P3D, M3D))
Homothétie
(OBJET) * (CENTRE: P3D, FACTEUR: DOUBLE)
L'ordre a de l'importance.
@ (T) @ (R) * (H)
 * @author Manuel
 */
public class MODObjet
{
    private MODRotation rotation;
    private MODTranslation translation;
    private MODHomothetie homothetie;
    public void modificateurs(MODRotation r, MODTranslation t,  MODHomothetie h)
        {
            this.rotation = r;
            this.translation = t;
            this.homothetie =h;
        }
    public MODRotation rotation() {return rotation;}
    public MODTranslation translation() {return translation;}
    public MODHomothetie homothetie() {return homothetie;}
    /*
    public Representable place(Representable r)
    {
        return r.place(this);
    }
    * 
    */
}