//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2015.12.13 à 05:06:53 PM CET 
//

package be.ipl.blitz.domaine;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Classe Java pour anonymous complex type.
 * 
 * <p>
 * Le fragment de schéma suivant indique le contenu attendu figurant dans cette
 * classe.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{}face" maxOccurs="6"/>
 *       &lt;/sequence>
 *       &lt;attribute name="nbByPlayer" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="totalNbDices" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "face" })
@XmlRootElement(name = "dice")
@Entity
@Table(name = "DICES", schema = "BLITZ")
public class Dice implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@XmlTransient
	private int id;

	@XmlElement(required = true)
	@Transient
	protected List<Face> face;
	@XmlAttribute(name = "nbByPlayer", required = true)
	@Transient
	protected int nbByPlayer;
	@XmlAttribute(name = "totalNbDices", required = true)
	@Transient
	protected int totalNbDices;

	public Dice() {
	}

	/**
	 * Gets the value of the face property.
	 * 
	 * <p>
	 * This accessor method returns a reference to the live list, not a
	 * snapshot. Therefore any modification you make to the returned list will
	 * be present inside the JAXB object. This is why there is not a
	 * <CODE>set</CODE> method for the face property.
	 * 
	 * <p>
	 * For example, to add a new item, do as follows:
	 * 
	 * <pre>
	 * getFace().add(newItem);
	 * </pre>
	 * 
	 * 
	 * <p>
	 * Objects of the following type(s) are allowed in the list {@link Face }
	 * 
	 * 
	 */
	public List<Face> getFace() {
		if (face == null) {
			face = new ArrayList<Face>();
		}
		return this.face;
	}

	/**
	 * Obtient la valeur de la propriété nbByPlayer.
	 * 
	 */
	public int getNbByPlayer() {
		return nbByPlayer;
	}

	/**
	 * Définit la valeur de la propriété nbByPlayer.
	 * 
	 */
	public void setNbByPlayer(int value) {
		this.nbByPlayer = value;
	}

	/**
	 * Obtient la valeur de la propriété totalNbDices.
	 * 
	 */
	public int getTotalNbDices() {
		return totalNbDices;
	}

	/**
	 * Définit la valeur de la propriété totalNbDices.
	 * 
	 */
	public void setTotalNbDices(int value) {
		this.totalNbDices = value;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
		Dice other = (Dice) obj;
		if (id != other.id)
			return false;
		return true;
	}
}
