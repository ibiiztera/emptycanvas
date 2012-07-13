import be.ibiiztera.md.pmatrix.pushmatrix.Scene;
import be.ibiiztera.md.pmatrix.test.pushmatrix.newtest.TestObjet;
import java.io.File;
public abstract class Config
{
	public Config()
	{}
	public abstract File[] output(TestObjet c);
	public abstract File output(Scene scene);
	public abstract Scene input(File mood);

}