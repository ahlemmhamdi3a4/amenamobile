package amena.entities;

import java.util.Date;


public class Colis {
  private int id;
  private String nomExpediteur;
  private String adresseExpediteur;
  private String nomDestinataire;
  private String adresseDestinataire;
  private float poids;
  private String statut;
  private Date dateExpedition;
   private int idu;

    public int getIdu() {
        return idu;
    }

    public void setIdu(int idu) {
        this.idu = idu;
    }

    public Colis(String nomExpediteur, String adresseExpediteur, String nomDestinataire, String adresseDestinataire, float poids, String statut, Date dateExpedition, int idu) {
        this.nomExpediteur = nomExpediteur;
        this.adresseExpediteur = adresseExpediteur;
        this.nomDestinataire = nomDestinataire;
        this.adresseDestinataire = adresseDestinataire;
        this.poids = poids;
        this.statut = statut;
        this.dateExpedition = dateExpedition;
        this.idu = idu;
    }
  
  
  public Colis() {
      
    }
  public Colis(int id, String nomExpediteur, String adresseExpediteur, String nomDestinataire, String adresseDestinataire, float poids) {
    this.id = id;
    this.nomExpediteur = nomExpediteur;
    this.adresseExpediteur = adresseExpediteur;
    this.nomDestinataire = nomDestinataire;
    this.adresseDestinataire = adresseDestinataire;
    this.poids = poids;
    this.statut = "en attente";
    this.dateExpedition = new Date();
    /*java.sql.Date sqlDate = java.sql.Date.valueOf(dateExpedition);*/

  }
    public Colis(String nomExpediteur, String adresseExpediteur, String nomDestinataire, String adresseDestinataire, float poids) {
    this.nomExpediteur = nomExpediteur;
    this.adresseExpediteur = adresseExpediteur;
    this.nomDestinataire = nomDestinataire;
    this.adresseDestinataire = adresseDestinataire;
    this.poids = poids;
    this.statut = "en attente";
    this.dateExpedition = new Date();}

 /*        LES SETTERS ET LES GETTERS             */
    
  public int getId() {
    return id;
  }
  
  public void setId(int id) {
    this.id = id;
  }
  
  public String getNomExpediteur() {
    return nomExpediteur;
  }
  
  public void setNomExpediteur(String nomExpediteur) {
    this.nomExpediteur = nomExpediteur;
  }
  
  public String getAdresseExpediteur() {
    return adresseExpediteur;
  }
  
  public void setAdresseExpediteur(String adresseExpediteur) {
    this.adresseExpediteur = adresseExpediteur;
  }
  
  public String getNomDestinataire() {
    return nomDestinataire;
  }
  
  public void setNomDestinataire(String nomDestinataire) {
    this.nomDestinataire = nomDestinataire;
  }
  
  public String getAdresseDestinataire() {
    return adresseDestinataire;
  }
  
  public void setAdresseDestinataire(String adresseDestinataire) {
    this.adresseDestinataire = adresseDestinataire;
  }
  
  public float getPoids() {
    return poids;
  }
  
  public void setPoids(float poids) {
    this.poids = poids;
  }
  
  public String getStatut() {
    return statut;
  }
  
  public void setStatut(String statut) {
    this.statut = statut;
  }
  
  public Date getDateExpedition() {
    return dateExpedition;
  }
  public void setDateExpedition(Date dateExpedition) {
    this.dateExpedition = dateExpedition;
  }

    @Override
    public String toString() {
        return "Colis{" + "id=" + id + ", nomExpediteur=" + nomExpediteur + ", adresseExpediteur=" + adresseExpediteur + ", nomDestinataire=" + nomDestinataire + ", adresseDestinataire=" + adresseDestinataire + ", poids=" + poids + ", statut=" + statut + ", dateExpedition=" + dateExpedition + ", idu=" + idu + '}';
    }
  

}