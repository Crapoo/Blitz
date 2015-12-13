//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2015.12.13 à 05:06:53 PM CET 
//


package be.ipl.blitz.domaine;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour anonymous complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{}dice"/>
 *         &lt;element ref="{}cards"/>
 *       &lt;/sequence>
 *       &lt;attribute name="goal" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "dice",
    "cards"
})
@XmlRootElement(name = "blitz")
public class Blitz {

    @XmlElement(required = true)
    protected Dice dice;
    @XmlElement(required = true)
    protected Cards cards;
    @XmlAttribute(name = "goal")
    protected String goal;

    /**
     * Obtient la valeur de la propriété dice.
     * 
     * @return
     *     possible object is
     *     {@link Dice }
     *     
     */
    public Dice getDice() {
        return dice;
    }

    /**
     * Définit la valeur de la propriété dice.
     * 
     * @param value
     *     allowed object is
     *     {@link Dice }
     *     
     */
    public void setDice(Dice value) {
        this.dice = value;
    }

    /**
     * Obtient la valeur de la propriété cards.
     * 
     * @return
     *     possible object is
     *     {@link Cards }
     *     
     */
    public Cards getCards() {
        return cards;
    }

    /**
     * Définit la valeur de la propriété cards.
     * 
     * @param value
     *     allowed object is
     *     {@link Cards }
     *     
     */
    public void setCards(Cards value) {
        this.cards = value;
    }

    /**
     * Obtient la valeur de la propriété goal.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGoal() {
        return goal;
    }

    /**
     * Définit la valeur de la propriété goal.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGoal(String value) {
        this.goal = value;
    }

}
