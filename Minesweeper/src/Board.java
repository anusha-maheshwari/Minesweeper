import java.awt.Graphics;
import java.awt.event.MouseEvent;

public class Board {

	//Tile temp = new Tile();// just for testing purposes...
	private int seconds = 0;
	private int elapsedTime = 0;
	private Tile [][] tiles;

	public Board(int rows, int cols, int mines) {
		tiles = new Tile [rows][cols];
		for (int r = 0; r<rows;r++) {
			for (int c = 0; c<cols; c++) {
				tiles[r][c] = new Tile(r,c);
			}
		}
		while (mines > 0) {
			int mineRow = (int) (Math.random()*rows);
			int mineCol = (int) (Math.random()*cols);
			if((tiles[mineRow][mineCol] != null)) {
				if (!tiles[mineRow][mineCol].hasMine())
					tiles[mineRow][mineCol].setMine();
				mines--;
			}
		}

	}

	public void draw(Graphics g) {
		for (int r = 0; r<tiles.length; r++) {
			for (int c = 0; c<tiles[0].length; c++) {
				tiles[r][c].draw(g);
			}
		}
		g.drawString(""+seconds, 80, 80);
	}

	public void handleClick(MouseEvent me) {
		// write your clicking code here!!
		System.out.println("You just clicked: "+me);
		int colClicked = (me.getX())/Tile.SQUARE_SIZE;
		int rowClicked = (me.getY())/Tile.SQUARE_SIZE;
		int buttonClicked = me.getButton();

		if (buttonClicked == 1) {
			leftClicked(rowClicked, colClicked);
		}else if (buttonClicked == 3) {
			rigthClicked (rowClicked, colClicked);
		}



	}

	private void rigthClicked(int rowClicked, int colClicked) {
		// check whether its inbounds

		if (inbounds(rowClicked,colClicked)) {
			Tile t = tiles[rowClicked][colClicked];
			if(t.hasBeenLeftClicked()) {
				return;
			}
			t.isClicked =true;
			if(t.hasAFlag()) {
				t.removeFlag();
			}else {
				t.plantFlag();
			}
		}
	}

	private void leftClicked(int rowClicked, int colClicked) {
		if(!inbounds(rowClicked, colClicked)) {
			return;
		}
		Tile t = tiles[rowClicked][colClicked];
		if(t.hasBeenLeftClicked() || t.hasBeenRightClicked()) {
			return;
		}if(t.hasAFlag()) {
			return;
		}if(t.hasMine()) {
			blowUp();
			return;
		}
		t.reveal();
		t.leftClicked();
		
		if(t.minesAround() == 0) {
			leftClicked(rowClicked-1,colClicked-1); 
			leftClicked(rowClicked-1,colClicked); 
			leftClicked(rowClicked-1,colClicked+1);
			leftClicked(rowClicked,colClicked-1);
			leftClicked(rowClicked,colClicked+1);
			leftClicked(rowClicked+1,colClicked-1);
			leftClicked(rowClicked+1,colClicked);
			leftClicked(rowClicked+1,colClicked+1);
			
		}
		
	}

	private void blowUp() {
		// TODO Auto-generated method stub
		
	}

	private boolean inbounds(int rowClicked, int colClicked) {
		if((rowClicked> 0 && rowClicked < tiles.length) && (colClicked > 0 && colClicked<tiles[0].length)) {
			return true;
		}
		return false;
	}

	public void tick() {
		seconds++;

	}


}