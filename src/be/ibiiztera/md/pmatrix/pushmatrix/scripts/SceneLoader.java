package be.ibiiztera.md.pmatrix.pushmatrix.scripts;

import java.io.File;

import be.ibiiztera.md.pmatrix.pushmatrix.Scene;

public interface SceneLoader {
	public void loadFObject(File file, Scene sc) throws Exception;
}
