package be.ipl.blitz.domaine;

import java.io.Serializable;

public class PlayerGamePK implements Serializable {
	private int player;
	private int game;

	public PlayerGamePK() {
	}

	public int getPlayer() {
		return player;
	}

	public void setPlayer(int player) {
		this.player = player;
	}

	public int getGame() {
		return game;
	}

	public void setGame(int game) {
		this.game = game;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + game;
		result = prime * result + player;
		return result;
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
		if (game != other.game)
			return false;
		if (player != other.player)
			return false;
		return true;
	}

}
