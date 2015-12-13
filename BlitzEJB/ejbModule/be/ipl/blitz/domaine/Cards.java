//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2015.12.13 à 05:06:53 PM CET 
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
 *         &lt;element ref="{}card" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *       &lt;attribute name="nbCardsByPlayer" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="totalNbCards" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
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
    "card"
})
@XmlRootElement(name = "cards")
public class Cards {

    @XmlElement(required = true)
    protected List<Card> card;
    @XmlAttribute(name = "nbCardsByPlayer", required = true)
    protected int nbCardsByPlayer;
    @XmlAttribute(name = "totalNbCards", required = true)
    protected int totalNbCards;
    @XmlAttribute(name = "minPlayers", required = true)
    protected int minPlayers;
    @XmlAttribute(name = "maxPlayers", required = true)
    protected int maxPlayers;

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
     * Obtient la valeur de la propriété totalNbCards.
     * 
     */
    public int getTotalNbCards() {
        return totalNbCards;
    }

    /**
     * Définit la valeur de la propriété totalNbCards.
     * 
     */
    public void setTotalNbCards(int value) {
        this.totalNbCards = value;
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
