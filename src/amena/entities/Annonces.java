/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amena.entities;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.Date;

/**
 *
 * @author Nour Saidi
 */
public class Annonces {
    private int id,Prix,idu;
    private String Type,VilleDep,VilleArr,DateDep,DateArr,Description;

    public Annonces(int Prix, int idu, String Type, String VilleDep, String VilleArr, String DateDep, String DateArr, String Description) {
        this.Prix = Prix;
        this.idu = idu;
        this.Type = Type;
        this.VilleDep = VilleDep;
        this.VilleArr = VilleArr;
        this.DateDep = DateDep;
        this.DateArr = DateArr;
        this.Description = Description;
    }

    public Annonces() {
    }

    public Annonces(String text, String text0, String text1, Date date, Date date0, String text2, double parseDouble) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrix() {
        return Prix;
    }

    public void setPrix(int Prix) {
        this.Prix = Prix;
    }

    public String getType() {
        return Type;
    }

    public void setType(String Type) {
        this.Type = Type;
    }

    public String getVilleDep() {
        return VilleDep;
    }

    public void setVilleDep(String VilleDep) {
        this.VilleDep = VilleDep;
    }

    public String getVilleArr() {
        return VilleArr;
    }

    public void setVilleArr(String VilleArr) {
        this.VilleArr = VilleArr;
    }

    public String getDateDep() {
        return DateDep;
    }

    public void setDateDep(String DateDep) {
        this.DateDep = DateDep;
    }

    public String getDateArr() {
        return DateArr;
    }

    public void setDateArr(String DateArr) {
        this.DateArr = DateArr;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public int getIdu() {
        return idu;
    }

    public void setIdu(int idu) {
        this.idu = idu;
    }

    @Override
    public String toString() {
        return "Annonces{" + "id=" + id + ", Prix=" + Prix + ", idu=" + idu + ", Type=" + Type + ", VilleDep=" + VilleDep + ", VilleArr=" + VilleArr + ", DateDep=" + DateDep + ", DateArr=" + DateArr + ", Description=" + Description + '}';
    }
    


    
    
}