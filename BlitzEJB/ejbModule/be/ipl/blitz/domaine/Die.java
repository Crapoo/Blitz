//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2015.12.14 à 11:46:14 AM CET 
//

package be.ipl.blitz.domaine;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
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
 *       &lt;attribute name="nbTotalDice" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "face" })
@XmlRootElement(name = "die")
@Entity
@Table(name = "DICE", schema = "BLITZ")
public class Die implements Serializable {

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
	@XmlAttribute(name = "nbTotalDice", required = true)
	@Transient
	protected int nbTotalDice;

	public Die() {
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
	 * Obtient la valeur de la propriété nbTotalDice.
	 * 
	 */
	public int getNbTotalDice() {
		return nbTotalDice;
	}

	/**
	 * Définit la valeur de la propriété nbTotalDice.
	 * 
	 */
	public void setNbTotalDice(int value) {
		this.nbTotalDice = value;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public Face throwDice(){
		Random r=new Random();
		return this.face.get(r.nextInt(6));
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
		Die other = (Die) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
