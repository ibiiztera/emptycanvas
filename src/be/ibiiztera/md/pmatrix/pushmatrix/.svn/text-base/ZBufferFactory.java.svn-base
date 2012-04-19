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