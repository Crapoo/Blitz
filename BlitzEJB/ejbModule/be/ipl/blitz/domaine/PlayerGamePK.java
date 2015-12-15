package be.ipl.blitz.domaine;

import java.io.Serializable;

public class PlayerGamePK implements Serializable {

	private int userId;
	private int gameId;

	public PlayerGamePK() {
	}
	
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getGameId() {
		return gameId;
	}

	public void setGameId(int gameId) {
		this.gameId = gameId;
	}

	@Override
	public int hashCode() {
		return (int) (userId + gameId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PlayerGamePK other = (PlayerGamePK) obj;
		if (gameId != other.gameId)
			return false;
		if (userId != other.userId)
			return false;
		return true;
	}

}
