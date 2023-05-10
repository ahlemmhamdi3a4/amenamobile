
package amena.services;
import amena.entities.Vehicule;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.utils.Statics;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
/**
 *
 * @author Lenovo
 */
public class ServiceVehicule {
    
    //singleton 
    public static ServiceVehicule instance = null ;
    
    public static boolean resultOk = true;

    //initilisation connection request 
    private ConnectionRequest req;
    
    
    public static ServiceVehicule getInstance() {
        if(instance == null )
            instance = new ServiceVehicule();
        return instance ;
    }
    
    
    
    public ServiceVehicule() {
        req = new ConnectionRequest();
    }
    
    
    
    
    //affichage


    public ArrayList<Vehicule>affichageVehicule() {
        ArrayList<Vehicule> result = new ArrayList<>();
        
        String url = Statics.BASE_URL+"vehicule/AllVehicule";
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp ;
                jsonp = new JSONParser();
                
                try {
                    Map<String,Object>mapReclamations = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    
                    List<Map<String,Object>> listOfMaps =  (List<Map<String,Object>>) mapReclamations.get("root");
                    
                    for(Map<String, Object> obj : listOfMaps) {
                        
                        //dima id fi codename one float 5outhouha
                        float id = Float.parseFloat(obj.get("idV").toString());
                        
                     //   String objet = obj.get("objet").toString();
                        
                        String type = obj.get("type").toString();
                        String immat = obj.get("immat").toString();
                        String etat = obj.get("etat").toString();
                        String kilometrage = obj.get("kilometrage").toString();
                        String chevaux = obj.get("chevaux").toString();
                        String marque = obj.get("marque").toString();
                       String modele = obj.get("modele").toString();

                        String lpec = obj.get("lpec").toString();
                      String prix = obj.get("prix").toString();
                        String img = obj.get("img").toString();
                        boolean b ;
                        if(etat.equals("0"))
                         b = false ;           
                        else 
                            b = true;
                        
                     float f = Float.parseFloat(chevaux);
                      Vehicule v = new Vehicule((int)id,type, immat,b,kilometrage,(int) f,marque, modele,lpec,Float.parseFloat(prix),img);
                        //insert data into ArrayList result
                        result.add(v);
                       
                    
                    }
                    
                }catch(Exception ex) {
                    
                    ex.printStackTrace();
                }
            
            }
        });
        
      NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha

        return result;
        
        
    }
    
    
    
    

    
}