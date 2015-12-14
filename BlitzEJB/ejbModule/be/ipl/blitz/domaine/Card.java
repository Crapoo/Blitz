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
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlMixed;
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
 *         &lt;element name="figure" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;attribute name="ref" type="{http://www.w3.org/2001/XMLSchema}string" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *       &lt;attribute name="cost" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="nb" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="effect" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="effectCode" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="src" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "content" })
@XmlRootElement(name = "card")
@Entity
@Table(name = "CARDS", schema = "BLITZ")
public class Card implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@XmlTransient
	private int id;

	@XmlElementRef(name = "figure", type = JAXBElement.class, required = false)
	@XmlMixed
	@Transient
	protected List<Serializable> content;
	@XmlAttribute(name = "cost", required = true)
	@Column
	@NotNull
	protected int cost;
	@XmlAttribute(name = "nb", required = true)
	@Transient
	protected int nb;
	@XmlAttribute(name = "effect", required = true)
	@Column
	@NotNull
	protected String effect;
	@XmlAttribute(name = "effectCode", required = true)
	protected int effectCode;
	@Column
	@NotNull
	@XmlAttribute(name = "src")
	protected String src;

	public Card() {
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		Card clone = new Card();
		clone.setCost(cost);
		clone.setEffect(effect);
		clone.setEffectCode(effectCode);
		clone.setSrc(src);
		return clone;
	}

	/**
	 * Gets the value of the content property.
	 * 
	 * <p>
	 * This accessor method returns a reference to the live list, not a
	 * snapshot. Therefore any modification you make to the returned list will
	 * be present inside the JAXB object. This is why there is not a
	 * <CODE>set</CODE> method for the content property.
	 * 
	 * <p>
	 * For example, to add a new item, do as follows:
	 * 
	 * <pre>
	 * getContent().add(newItem);
	 * </pre>
	 * 
	 * 
	 * <p>
	 * Objects of the following type(s) are allowed in the list
	 * {@link JAXBElement }{@code <}{@link Card.Figure }{@code >} {@link String
	 * }
	 * 
	 * 
	 */
	public List<Serializable> getContent() {
		if (content == null) {
			content = new ArrayList<Serializable>();
		}
		return this.content;
	}

	/**
	 * Obtient la valeur de la propriété cost.
	 * 
	 */
	public int getCost() {
		return cost;
	}

	/**
	 * Définit la valeur de la propriété cost.
	 * 
	 */
	public void setCost(int value) {
		this.cost = value;
	}

	/**
	 * Obtient la valeur de la propriété nb.
	 * 
	 */
	public int getNb() {
		return nb;
	}

	/**
	 * Définit la valeur de la propriété nb.
	 * 
	 */
	public void setNb(int value) {
		this.nb = value;
	}

	/**
	 * Obtient la valeur de la propriété effect.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getEffect() {
		return effect;
	}

	/**
	 * Définit la valeur de la propriété effect.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setEffect(String value) {
		this.effect = value;
	}

	/**
	 * Obtient la valeur de la propriété effectCode.
	 * 
	 */
	public int getEffectCode() {
		return effectCode;
	}

	/**
	 * Définit la valeur de la propriété effectCode.
	 * 
	 */
	public void setEffectCode(int value) {
		this.effectCode = value;
	}

	/**
	 * Obtient la valeur de la propriété src.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getSrc() {
		return src;
	}

	/**
	 * Définit la valeur de la propriété src.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setSrc(String value) {
		this.src = value;
	}

	/**
	 * <p>
	 * Classe Java pour anonymous complex type.
	 * 
	 * <p>
	 * Le fragment de schéma suivant indique le contenu attendu figurant dans
	 * cette classe.
	 * 
	 * <pre>
	 * &lt;complexType>
	 *   &lt;complexContent>
	 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
	 *       &lt;attribute name="ref" type="{http://www.w3.org/2001/XMLSchema}string" />
	 *     &lt;/restriction>
	 *   &lt;/complexContent>
	 * &lt;/complexType>
	 * </pre>
	 * 
	 * 
	 */
	@XmlAccessorType(XmlAccessType.FIELD)
	@XmlType(name = "")
	public static class Figure {

		@XmlAttribute(name = "ref")
		protected String ref;

		/**
		 * Obtient la valeur de la propriété ref.
		 * 
		 * @return possible object is {@link String }
		 * 
		 */
		public String getRef() {
			return ref;
		}

		/**
		 * Définit la valeur de la propriété ref.
		 * 
		 * @param value
		 *            allowed object is {@link String }
		 * 
		 */
		public void setRef(String value) {
			this.ref = value;
		}

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
		Card other = (Card) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
