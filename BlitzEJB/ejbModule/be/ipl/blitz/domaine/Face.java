//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2015.12.14 à 11:46:14 AM CET 
//

package be.ipl.blitz.domaine;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
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
 *       &lt;attribute name="figure" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="identif" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="src" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="nbFaces" use="required" type="{}dieType" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "face")
@Entity
@Table(name = "FACE", schema = "BLITZ")
public class Face implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@XmlTransient
	private int Id;

	@XmlAttribute(name = "figure", required = true)
	@Column
	@NotNull
	protected String figure;
	@XmlAttribute(name = "identif", required = true)
	@Column
	@NotNull
	protected String identif;
	@XmlAttribute(name = "src")
	@Column
	@NotNull
	protected String src;
	@XmlAttribute(name = "nbFaces", required = true)
	@Column
	@NotNull
	protected int nbFaces;

	@ManyToOne(cascade = CascadeType.ALL)
	@XmlTransient
	public Die die;

	/**
	 * Obtient la valeur de la propriété figure.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getFigure() {
		return figure;
	}

	/**
	 * Définit la valeur de la propriété figure.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setFigure(String value) {
		this.figure = value;
	}

	/**
	 * Obtient la valeur de la propriété identif.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getIdentif() {
		return identif;
	}

	/**
	 * Définit la valeur de la propriété identif.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setIdentif(String value) {
		this.identif = value;
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
	 * Obtient la valeur de la propriété nbFaces.
	 * 
	 */
	public int getNbFaces() {
		return nbFaces;
	}

	/**
	 * Définit la valeur de la propriété nbFaces.
	 * 
	 */
	public void setNbFaces(int value) {
		this.nbFaces = value;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Id;
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
		Face other = (Face) obj;
		if (Id != other.Id)
			return false;
		return true;
	}
}
