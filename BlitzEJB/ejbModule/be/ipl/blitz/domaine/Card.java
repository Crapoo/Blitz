package be.ipl.blitz.domaine;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import be.ipl.blitz.utils.Util;

@Entity
@Table(name = "CARDS", schema = "BLITZ", indexes = {
		@Index(name = "num_effect", columnList = "effectNumber", unique = true) })
public class Card implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column
	@NotNull
	private int effectNumber;
	@Column
	@NotNull
	private String effect;
	@Column
	@NotNull
	@Min(value = 0)
	@Max(value = 4)
	private int price;

	public Card() {
	}

	public Card(int effectNumber, String effect, int price) {
		super();
		Util.checkPositive(effectNumber);
		Util.checkString(effect);
		Util.checkPositiveOrZero(price);
		this.effectNumber = effectNumber;
		this.effect = effect;
		this.price = price;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		Util.checkPositiveOrZero(id);
		this.id = id;
	}

	public int getEffectNumber() {
		return effectNumber;
	}

	public void setEffectNumber(int effectNumber) {
		Util.checkPositiveOrZero(effectNumber);
		this.effectNumber = effectNumber;
	}

	public String getEffect() {
		return effect;
	}

	public void setEffect(String effect) {
		Util.checkString(effect);
		this.effect = effect;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		Util.checkPositiveOrZero(price);
		this.price = price;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		Card other = (Card) obj;
		if (id != other.id)
			return false;
		return true;
	}

}