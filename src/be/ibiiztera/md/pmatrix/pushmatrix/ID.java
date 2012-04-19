/**
 * 
 */
package be.ibiiztera.md.pmatrix.pushmatrix;


/**
 * @author MANUEL DAHMEN
 * 
 *         dev
 * 
 *         21 oct. 2011
 * 
 */
public class ID {
	public static String GEN(Object o) {
		String id = "";
		if (o instanceof Representable) {
			id = "19780626-091-33-05h--" + o.getClass().getName() + "--"
					+ System.currentTimeMillis();
		}
		return id;
	}
}
