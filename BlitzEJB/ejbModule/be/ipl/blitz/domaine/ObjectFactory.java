//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2015.12.14 à 11:46:14 AM CET 
//


package be.ipl.blitz.domaine;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the be.ipl.blitz.domaine package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _CardFigure_QNAME = new QName("", "figure");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: be.ipl.blitz.domaine
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Card }
     * 
     */
    public Card createCard() {
        return new Card();
    }

    /**
     * Create an instance of {@link Die }
     * 
     */
    public Die createDie() {
        return new Die();
    }

    /**
     * Create an instance of {@link Face }
     * 
     */
    public Face createFace() {
        return new Face();
    }

    /**
     * Create an instance of {@link Blitz }
     * 
     */
    public Blitz createBlitz() {
        return new Blitz();
    }

    /**
     * Create an instance of {@link Card.Figure }
     * 
     */
    public Card.Figure createCardFigure() {
        return new Card.Figure();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Card.Figure }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "figure", scope = Card.class)
    public JAXBElement<Card.Figure> createCardFigure(Card.Figure value) {
        return new JAXBElement<Card.Figure>(_CardFigure_QNAME, Card.Figure.class, Card.class, value);
    }

}
