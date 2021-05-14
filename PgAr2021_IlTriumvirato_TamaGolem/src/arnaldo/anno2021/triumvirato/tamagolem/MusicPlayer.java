package arnaldo.anno2021.triumvirato.tamagolem;

import java.io.File;
import java.util.Scanner;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class MusicPlayer {
	 private Clip audioclip;
	 private String fileName;
	 private boolean currentlyPlaying;
	
	 public MusicPlayer(String fileName) {
		 this.fileName=fileName;
		 createOrResetClip(fileName);
	 }
	 
	 private void createOrResetClip(String filename) {
		 try {
			    File fileAudio=new File(fileName).getAbsoluteFile();
			    if(fileAudio==null) {
			    	fileAudio=new File(Constants.MUSIC_FILE_PATHNAME_ALTERNATIVE).getAbsoluteFile();
			    }
	            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(fileAudio);
	            audioclip = AudioSystem.getClip();
	            audioclip.open(audioInputStream);
	            currentlyPlaying=false;
	            
	        } catch(Exception ex) {
	            System.out.println("Error with playing sound.");
	            ex.printStackTrace();
	        }
	 }
	 
	 public void play() {
		 audioclip.loop(audioclip.LOOP_CONTINUOUSLY);
		 audioclip.start();
		 currentlyPlaying=true;
	 }
	 
	 public void stop() {
		 audioclip.stop();
		 currentlyPlaying=false;
		 createOrResetClip(this.fileName);
	 }
	 
	 public void playOrStop(){
		 if(currentlyPlaying) {
			 stop();
		 }else {
			 play();
		 }
	 }
	 
	 //String bip = "Kevin_Macleod_Final_Battle_of_the_Dark_Wizards.wav";
		
	 
	 
}
