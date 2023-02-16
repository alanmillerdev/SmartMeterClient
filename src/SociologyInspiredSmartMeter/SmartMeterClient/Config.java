package SociologyInspiredSmartMeter.SmartMeterClient;

import java.awt.Color;

import javax.swing.border.EmptyBorder;

public class Config {
	
	private int viewXPos = 0;
	
	private int viewYPos = 0;
	
	private int viewWidth = 1400;
	
	private int viewHeight = 800;
	
	private String frameTitle = "Smart Meter";
	
	private EmptyBorder border = new EmptyBorder(5, 5, 5, 5);
	
	private Color backgroundColour = Color.WHITE;
	
	private Color textColour = Color.BLACK;

	/**
	 * @return the viewXPos
	 */
	public int getViewXPos() {
		return viewXPos;
	}

	/**
	 * @param viewXPos the viewXPos to set
	 */
	public void setViewXPos(int viewXPos) {
		this.viewXPos = viewXPos;
	}

	/**
	 * @return the viewYPos
	 */
	public int getViewYPos() {
		return viewYPos;
	}

	/**
	 * @param viewYPos the viewYPos to set
	 */
	public void setViewYPos(int viewYPos) {
		this.viewYPos = viewYPos;
	}

	/**
	 * @return the viewWidth
	 */
	public int getViewWidth() {
		return viewWidth;
	}

	/**
	 * @param viewWidth the viewWidth to set
	 */
	public void setViewWidth(int viewWidth) {
		this.viewWidth = viewWidth;
	}

	/**
	 * @return the viewHeight
	 */
	public int getViewHeight() {
		return viewHeight;
	}

	/**
	 * @param viewHeight the viewHeight to set
	 */
	public void setViewHeight(int viewHeight) {
		this.viewHeight = viewHeight;
	}

	/**
	 * @return the frameTitle
	 */
	public String getFrameTitle() {
		return frameTitle;
	}

	/**
	 * @param frameTitle the frameTitle to set
	 */
	public void setFrameTitle(String frameTitle) {
		this.frameTitle = frameTitle;
	}

	/**
	 * @return the border
	 */
	public EmptyBorder getBorder() {
		return border;
	}

	/**
	 * @param border the border to set
	 */
	public void setBorder(EmptyBorder border) {
		this.border = border;
	}

	/**
	 * @return the backgroundColour
	 */
	public Color getBackgroundColour() {
		return backgroundColour;
	}

	/**
	 * @param backgroundColour the backgroundColour to set
	 */
	public void setBackgroundColour(Color backgroundColour) {
		this.backgroundColour = backgroundColour;
	}

	/**
	 * @return the textColour
	 */
	public Color getTextColour() {
		return textColour;
	}

	/**
	 * @param textColour the textColour to set
	 */
	public void setTextColour(Color textColour) {
		this.textColour = textColour;
	}
	
}