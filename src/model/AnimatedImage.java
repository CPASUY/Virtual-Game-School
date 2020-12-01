package model;

import java.io.File;

import javafx.scene.image.Image;

	public class AnimatedImage {
	
	Image[] frames;
	Image currentFrame;
	
	public AnimatedImage() {
	}
	
	public Image getCurrentFrame() {
		return currentFrame;
	}
	
	public Image[] getFrames() {
		return frames;
	}
	
	public void setCurrentFrame(Image frame) {
		currentFrame = frame;
	}
	
	public void fillFrames(String[] paths,int QuantityOFSprites) {
		frames = new Image[QuantityOFSprites];
		for(int i = 0;i<frames.length;i++) {
			File file = new File(paths[i]);
	    	Image imload = new Image(file.toURI().toString());
	    	frames[i] = imload;
		}
		currentFrame = getFrames()[0];
	}

	
}
