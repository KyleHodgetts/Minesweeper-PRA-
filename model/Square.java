package model;
/**
 * Possible values that a square can take.
 * @author Kyle Hodgetts
 * @author Peter Barta
 *
 */
public enum Square {
	ZERO,
	ONE,
	TWO,
	THREE,
	FOUR,
	FIVE,
	SIX,
	SEVEN,
	EIGHT,
	MINE;

	/**
	 * 
	 * @param x the square
	 * @return the integer value of the square
	 */
	public int returnValue(Square x){
		if(x == ZERO){
			return 0;
		}

		if(x == ONE){
			return 1;
		}
		if(x == TWO){
			return 2;
		}
		if(x == THREE){
			return 3;
		}
		if(x == FOUR){
			return 4;
		}
		if(x == FIVE){
			return 5;
		}
		if(x == SIX){
			return 6;
		}
		if(x == SEVEN){
			return 7;
		}

		if(x == EIGHT){
			return 8;
		}

		if(x == MINE){
			return 99;
		}

		return 0;
	}
}