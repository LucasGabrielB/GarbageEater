package entities;

import java.util.Random;

public enum Colors {
	RED, GREEN, YELLOW, BLUE;

	// method to get a random color
	public static Colors getRandomColor() {
        Random random = new Random();
        return values()[random.nextInt(values().length)];
    
	}
}
