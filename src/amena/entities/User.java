/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amena.entities;


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

    private boolean status;
    private String password;
    private String email;
    private String role;
    private String score;
    private String image;

    private String token;
    private String numtel;

    public User(int id, String cin, String adress, String nom, String prenom, boolean status, String password, String email, String role, String score, String image, String token, String numtel) {
        this.id = id;
        this.cin = cin;
        this.adress = adress;
        this.nom = nom;
        this.prenom = prenom;
        this.status = status;
        this.password = password;
        this.email = email;
        this.role = role;
        this.score = score;
        this.image = image;
        this.token = token;
        this.numtel = numtel;
    }

    public User(String cin, String adress, String nom, String prenom, boolean status, String password, String email, String role, String score, String image, String token, String numtel) {
        this.adress = adress;
        this.cin = cin;
       
        this.nom = nom;
        this.prenom = prenom;
        this.status = status;
        this.password = password;
        this.email = email;
        this.role = role;
        this.score = score;
        this.image = image;
        this.token = token;
        this.numtel = numtel;
    }

    public User(String cin, String adress, String nom, String prenom, String password, String email, String role, String numtel) {
        this.cin = cin;
        this.adress = adress;
        this.nom = nom;
        this.prenom = prenom;
        this.password = password;
        this.email = email;
        this.role = role;
        this.numtel = numtel;
    }

    public User(String cin, String adress, String nom, String prenom, String password, String email, String numtel) {
        this.cin = cin;
        this.adress = adress;
        this.nom = nom;
        this.prenom = prenom;
        this.password = password;
        this.email = email;
        this.numtel = numtel;
    }

    public User(String nom, String email) {
        this.nom = nom;
        this.email = email;
    }

    public User() {
    }

    public User(int id, String cin, String adress, String nom, String prenom, boolean status, String email, String role, String score, String image, String token, String numtel) {
        this.id = id;
        this.cin = cin;
        this.adress = adress;
        this.nom = nom;
        this.prenom = prenom;
        this.status = status;
        this.email = email;
        this.role = role;
        this.score = score;
        this.image = image;
        this.token = token;
        this.numtel = numtel;
    }

    public User(int id, String text, String text0, String text1, String text2, String role, String text3, String text4, String token, String score, String image, boolean status) {
         this.id = id;
        this.cin = cin;
        this.adress = adress;
        this.nom = nom;
        this.prenom = prenom;
        this.status = status;
        this.email = email;
        this.role = role;
        this.score = score;
        this.image = image;
        this.token = token;
        this.numtel = numtel;
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

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getNumtel() {
        return numtel;
    }

    public void setNumtel(String numtel) {
        this.numtel = numtel;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", cin=" + cin + ", adress=" + adress + ", nom=" + nom + ", prenom=" + prenom + ", status=" + status + ", password=" + password + ", email=" + email + ", role=" + role + ", score=" + score + ", image=" + image + ", token=" + token + ", numtel=" + numtel + '}';
    }

    

    

}
