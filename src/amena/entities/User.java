/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amena.entities;

import java.util.Date;

/**
 *
 * @author aymen
 */
public class User {

    private int id;
    private String cin;
    private String adress;
    private String nom;
    private String prenom;

    private String password;
    private String email;

    private String numtel;
   private String image;
    private String roles;
    private Date date_n;

    public Date getDate_n() {
        return date_n;
    }

    public void setDate_n(Date date_n) {
        this.date_n = date_n;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public User(String cin, String adress, String nom, String prenom, String password, String email, String numtel, String image, String roles) {
        this.cin = cin;
        this.adress = adress;
        this.nom = nom;
        this.prenom = prenom;
        this.password = password;
        this.email = email;
        this.numtel = numtel;
        this.image = image;
        this.roles = roles;
    }

    public User(int id, String cin, String adress, String nom, String prenom, String password, String email, String numtel, String image, String roles) {
        this.id = id;
        this.cin = cin;
        this.adress = adress;
        this.nom = nom;
        this.prenom = prenom;
        this.password = password;
        this.email = email;
        this.numtel = numtel;
        this.image = image;
        this.roles = roles;
    }

    public User(int id, String cin, String adress, String nom, String prenom, String password, String email, String numtel, String image) {
        this.id = id;
        this.cin = cin;
        this.adress = adress;
        this.nom = nom;
        this.prenom = prenom;
        this.password = password;
        this.email = email;
        this.numtel = numtel;
        this.image = image;
    }

    public User() {
    }

    public User(int id, String cin, String adress, String nom, String prenom, String password, String email, String numtel) {
        this.id = id;
        this.cin = cin;
        this.adress = adress;
        this.nom = nom;
        this.prenom = prenom;
        this.password = password;
        this.email = email;
        this.numtel = numtel;
    }

    public User(String nom, String prenom, String cin, String email, String password, String numtel, String adress) {

        this.nom = nom;
        this.prenom = prenom;
        this.cin = cin;
        this.email = email;

        this.password = password;

        this.numtel = numtel;
        this.adress = adress;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumtel() {
        return numtel;
    }

    public void setNumtel(String numtel) {
        this.numtel = numtel;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", cin=" + cin + ", adress=" + adress + ", nom=" + nom + ", prenom=" + prenom + ", password=" + password + ", email=" + email + ", numtel=" + numtel + ", image=" + image + ", roles=" + roles + ", date_n=" + date_n + '}';
    }

    public User(int id, String cin, String adress, String nom, String prenom, String password, String email, String numtel, String image, String roles, Date date_n) {
        this.id = id;
        this.cin = cin;
        this.adress = adress;
        this.nom = nom;
        this.prenom = prenom;
        this.password = password;
        this.email = email;
        this.numtel = numtel;
        this.image = image;
        this.roles = roles;
        this.date_n = date_n;
    }

    public User(String cin, String adress, String nom, String prenom, String password, String email, String numtel, String image, String roles, Date date_n) {
        this.cin = cin;
        this.adress = adress;
        this.nom = nom;
        this.prenom = prenom;
        this.password = password;
        this.email = email;
        this.numtel = numtel;
        this.image = image;
        this.roles = roles;
        this.date_n = date_n;
    }

   
    public User(String nom, String prenom, String cin, String email, String password, String numtel, String adress,Date date_n) {

        this.nom = nom;
        this.prenom = prenom;
        this.cin = cin;
        this.email = email;

        this.password = password;

        this.numtel = numtel;
        this.adress = adress;
        this.date_n = date_n;
    }

    

    
    
}
