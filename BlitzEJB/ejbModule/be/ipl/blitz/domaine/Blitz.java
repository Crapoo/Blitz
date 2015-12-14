//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2015.12.14 à 11:46:14 AM CET 
//


package be.ipl.blitz.domaine;

import java.util.ArrayList;
import java.util.List;
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
 *         &lt;element ref="{}die"/>
 *         &lt;element ref="{}card" maxOccurs="24"/>
 *       &lt;/sequence>
 *       &lt;attribute name="goal" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="nbCardsByPlayer" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="nbTotalCards" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="minPlayers" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="maxPlayers" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "die",
    "card"
})
@XmlRootElement(name = "blitz")
public class Blitz {

    @XmlElement(required = true)
    protected Die die;
    @XmlElement(required = true)
    protected List<Card> card;
    @XmlAttribute(name = "goal")
    protected String goal;
    @XmlAttribute(name = "nbCardsByPlayer", required = true)
    protected int nbCardsByPlayer;
    @XmlAttribute(name = "nbTotalCards", required = true)
    protected int nbTotalCards;
    @XmlAttribute(name = "minPlayers", required = true)
    protected int minPlayers;
    @XmlAttribute(name = "maxPlayers", required = true)
    protected int maxPlayers;

    /**
     * Obtient la valeur de la propriété die.
     * 
     * @return
     *     possible object is
     *     {@link Die }
     *     
     */
    public Die getDie() {
        return die;
    }

    /**
     * Définit la valeur de la propriété die.
     * 
     * @param value
     *     allowed object is
     *     {@link Die }
     *     
     */
    public void setDie(Die value) {
        this.die = value;
    }

    /**
     * Gets the value of the card property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the card property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCard().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Card }
     * 
     * 
     */
    public List<Card> getCard() {
        if (card == null) {
            card = new ArrayList<Card>();
        }
        return this.card;
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

    /**
     * Obtient la valeur de la propriété nbCardsByPlayer.
     * 
     */
    public int getNbCardsByPlayer() {
        return nbCardsByPlayer;
    }

    /**
     * Définit la valeur de la propriété nbCardsByPlayer.
     * 
     */
    public void setNbCardsByPlayer(int value) {
        this.nbCardsByPlayer = value;
    }

    /**
     * Obtient la valeur de la propriété nbTotalCards.
     * 
     */
    public int getNbTotalCards() {
        return nbTotalCards;
    }

    /**
     * Définit la valeur de la propriété nbTotalCards.
     * 
     */
    public void setNbTotalCards(int value) {
        this.nbTotalCards = value;
    }

    /**
     * Obtient la valeur de la propriété minPlayers.
     * 
     */
    public int getMinPlayers() {
        return minPlayers;
    }

    /**
     * Définit la valeur de la propriété minPlayers.
     * 
     */
    public void setMinPlayers(int value) {
        this.minPlayers = value;
    }

    /**
     * Obtient la valeur de la propriété maxPlayers.
     * 
     */
    public int getMaxPlayers() {
        return maxPlayers;
    }

    /**
     * Définit la valeur de la propriété maxPlayers.
     * 
     */
    public void setMaxPlayers(int value) {
        this.maxPlayers = value;
    }

}
