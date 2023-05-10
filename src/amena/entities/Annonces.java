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
    private int id,Prix;
    private String Type,VilleDep,VilleArr,DateDep,DateArr,Description;

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
    


    
    
}