package models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
import jakarta.persistence.Column;


@Entity //1 creer l'entité (apres avoir testé que le ping marche tjrs)
@Access(AccessType.FIELD) //2 ajouter ceci avec les imports qui conviennent 
public class Commune {
	
	
	@Id //2 ajouter l'id et gen value
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id; //2 bis, ajouter le guetteur et setteur pour id
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	@Column(name ="codePostal", length=50) //3 mettre les annotations column 
    private int codePostal;
	
	@Column(name ="nom", length=100)
	private String nom;

    //4 creer service CommuneServiceEJB et y a jouter l'annotations @Stateless, suite dans le CommuneServiceEJB.java

    public Commune() {
    }

    public Commune(int codePostal, String nom) {
        this.codePostal = codePostal;
        this.nom = nom;
    }

    public int getCodePostal() { return codePostal; }
    public void setCodePostal(int codePostal) { this.codePostal = codePostal; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
}