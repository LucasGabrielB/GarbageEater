package soundEffects;

import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class SoundEffect {
	// attributes
	private Clip clip;
	
	// constructor
	public SoundEffect(final String filePath){
		try {
			// open the audio file
			AudioInputStream audio = AudioSystem.getAudioInputStream(new File(filePath));
			clip = AudioSystem.getClip();
			clip.open(audio);
			
		}
		catch (Exception e) {
			e.printStackTrace();
		
		} 

	}
	
	// play the audio
	public void play(){
		clip.setMicrosecondPosition(0);
		clip.start();
	}
	
	// stop the audio
	public void stop(){
		clip.stop();
	}
	
	// play audio continuously
	public void loop(){
		clip.setMicrosecondPosition(0);
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}

}