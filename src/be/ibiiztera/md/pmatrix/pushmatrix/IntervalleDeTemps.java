/**
 * 
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
