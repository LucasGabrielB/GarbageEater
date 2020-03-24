package entities;

public class Player {
	// attributes
	private int score;
	private String nickname;
	
	// constructor
	public Player(String nickname){
		setNickname(nickname);
	
	}
	
	// constructor 
	public Player(String nickname, int score){
		this(nickname);
		setScore(score);
	
	}
	
	// getters and setters
	public int getScore() {
		return this.score;
	
	}
	
	public void setScore(int score) {
		this.score = score;
	
	}
	
	public String getNickname() {
		return this.nickname;
	
	}
	
	public void setNickname(String nickname) {
		this.nickname = nickname;
	
	}

}
