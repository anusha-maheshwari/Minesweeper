import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class Tile {
	public static final int SQUARE_SIZE = 45;
	/**
	 * Might be plenty of instance variables you might want here
	 */
	private boolean isMine; // does the tile have a mine
	private int clickState; // left or right click
	private int row;// location purposes
	private int col;// location purposes
	public boolean isClicked;
	private boolean hasAFlag;
	public boolean left;
	public boolean right;
	private boolean unClick;
	private boolean flag;
	private boolean bomb;
	



	private static Image sprites;
	private static Image[] images = new Image [14];// might want to store all the subimages in this shared array
	private static int[][] coords;// or might want to save the coordinates of the subimages


	public Tile(int r, int c) {
		row = r;
		col = c;
		setUpImages();
	}
	private void setUpImages() {
		if(sprites == null) {// only open the file once
			try {

				sprites = ImageIO.read(new File("minesweepersprites.PNG"));
				images[0] = ((BufferedImage)sprites).getSubimage(250,745,233,233).getScaledInstance(SQUARE_SIZE, SQUARE_SIZE, BufferedImage.SCALE_SMOOTH);
				images[1] = ((BufferedImage)sprites).getSubimage(5,990,233,233).getScaledInstance(SQUARE_SIZE, SQUARE_SIZE, BufferedImage.SCALE_SMOOTH);
				images[2] = ((BufferedImage)sprites).getSubimage(250,990,233,233).getScaledInstance(SQUARE_SIZE, SQUARE_SIZE, BufferedImage.SCALE_SMOOTH);
				images[3] = ((BufferedImage)sprites).getSubimage(500,990,233,233).getScaledInstance(SQUARE_SIZE, SQUARE_SIZE, BufferedImage.SCALE_SMOOTH);
				images[4] = ((BufferedImage)sprites).getSubimage(740,990,233,233).getScaledInstance(SQUARE_SIZE, SQUARE_SIZE, BufferedImage.SCALE_SMOOTH);
				images[5] = ((BufferedImage)sprites).getSubimage(990,990,233,233).getScaledInstance(SQUARE_SIZE, SQUARE_SIZE, BufferedImage.SCALE_SMOOTH);
				images[6] = ((BufferedImage)sprites).getSubimage(1235,990,233,233).getScaledInstance(SQUARE_SIZE, SQUARE_SIZE, BufferedImage.SCALE_SMOOTH);
				images[7] = ((BufferedImage)sprites).getSubimage(1485,990,233,233).getScaledInstance(SQUARE_SIZE, SQUARE_SIZE, BufferedImage.SCALE_SMOOTH);
				images[8] = ((BufferedImage)sprites).getSubimage(1730,990,233,233).getScaledInstance(SQUARE_SIZE, SQUARE_SIZE, BufferedImage.SCALE_SMOOTH);
				images[9] = ((BufferedImage)sprites).getSubimage(2,745,233,233).getScaledInstance(SQUARE_SIZE, SQUARE_SIZE, BufferedImage.SCALE_SMOOTH);
				images[10] = ((BufferedImage)sprites).getSubimage(500,745,233,233).getScaledInstance(SQUARE_SIZE, SQUARE_SIZE, BufferedImage.SCALE_SMOOTH);
				images[11] = ((BufferedImage)sprites).getSubimage(1235,745,233,233).getScaledInstance(SQUARE_SIZE, SQUARE_SIZE, BufferedImage.SCALE_SMOOTH);
				images[12] = ((BufferedImage)sprites).getSubimage(1485,745,233,233).getScaledInstance(SQUARE_SIZE, SQUARE_SIZE, BufferedImage.SCALE_SMOOTH);
				images[13] = ((BufferedImage)sprites).getSubimage(1730,745,233,233).getScaledInstance(SQUARE_SIZE, SQUARE_SIZE, BufferedImage.SCALE_SMOOTH);

			} catch (IOException e) {	
				e.printStackTrace();
			}
		}
	}
	/**
	 * Draw this tile in its current state.  What are the states of a Tile?
	 */
	public void draw(Graphics g) {

		g.drawImage(images[9], col*SQUARE_SIZE, row*SQUARE_SIZE, null);
		
		if(left) {
			g.drawImage(images[0], col*SQUARE_SIZE, row*SQUARE_SIZE, null);
		}
		if(unClick) {
			g.drawImage(images[9], col*SQUARE_SIZE, row*SQUARE_SIZE, null);
		}if (flag) {
			g.drawImage(images[10], col*SQUARE_SIZE, row*SQUARE_SIZE, null);
		}
		if(bomb) {
			g.drawImage(images[11], col*SQUARE_SIZE, row*SQUARE_SIZE, null);
		}

	}
	public boolean setMine() {
		return isMine = true;
	}

	public boolean hasMine() {
		return false;
	}

	public void reveal() {
		bomb = true;
		
	}
	public int minesAround() {
		// TODO Auto-generated method stub
		return 0;
	}
	public void removeFlag() {
		unClick = true;
		
	}
	public void plantFlag() {
		flag = true;
		
	}
	public boolean hasBeenLeftClicked() {
		return isClicked=false;
	}
	public boolean hasAFlag() {
		return false;
	}
	public boolean hasBeenRightClicked() {
		return false;
	}
	public boolean leftClicked() {
		return left = true;
		
	}
}