package entities;

import java.util.Random;

public enum RecycleBinColor {
	RED, GREEN, YELLOW, BLUE;

	// method to get a random color
	public static RecycleBinColor getRandomColor() {
        Random random = new Random();
        return values()[random.nextInt(values().length)];
    
	}
}
