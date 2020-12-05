package model;

import java.io.File;

import javafx.scene.image.Image;

public class AnimatedImage {
	/**
	 * The array of frames
	 */
	Image[] frames;
	/**
	 * The image current
	 */
	Image currentFrame;
	//Methods
	/**
	 * Creates an instance of AnimatedImage
	 */
	public AnimatedImage() {
	}
	/**
	 * Returns the current frame
	 * @return Image current frame
	 */
	public Image getCurrentFrame() {
		return currentFrame;
	}
	/**
	 * Returns the array of frames
	 * @return Image[] frames
	 */
	public Image[] getFrames() {
		return frames;
	}
	/**
	 * Sets the current frame
	 * @param Image frame
	 */
	public void setCurrentFrame(Image frame) {
		currentFrame = frame;
	}
	/**
	 * Save the frames
	 * @param String[] paths of the frames
	 * @param int QuantityOFSprites
	 */
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
