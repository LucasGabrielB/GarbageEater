package soundEffects;

import java.io.BufferedInputStream;
import java.io.InputStream;

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
			InputStream audioSrc = getClass().getResourceAsStream(filePath);
			InputStream bufferedIn = new BufferedInputStream(audioSrc);
			AudioInputStream audio = AudioSystem.getAudioInputStream(bufferedIn);
			
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