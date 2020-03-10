package entities;

public class Player {

	private int score;
	private String nickname;
	
	public Player(String nickname){
		setNickname(nickname);
	}
	
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
