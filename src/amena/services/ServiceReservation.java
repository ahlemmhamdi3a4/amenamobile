
package amena.services;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;
import amena.entities.Vehicule;
import amena.entities.Reservation;
import amena.util.Vars;
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
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
/**
 *
 * @author Lenovo
 */
public class ServiceReservation {
    
    //singleton 
    public static ServiceReservation instance = null ;
    
    public static boolean resultOk = true;

    //initilisation connection request 
    private ConnectionRequest req;
    
    
    public static ServiceReservation getInstance() {
        if(instance == null )
            instance = new ServiceReservation();
        return instance ;
    }
         
    public ServiceReservation() {
        req = new ConnectionRequest();
    }    
    
    
    public static List<Date> getDatesBetween(Date startDate, Date endDate) {
    List<Date> datesInRange = new ArrayList<>();
    long interval = 24 * 1000 * 60 * 60; // 1 day in milliseconds

    long endTime = endDate.getTime();
    long curTime = startDate.getTime();

    while (curTime <= endTime) {
        datesInRange.add(new Date(curTime));
        curTime += interval;
    }

    return datesInRange;
}

    
public boolean addReservation(Reservation reservation) {
    String url = Statics.BASE_URL + "reservation/newJson";
    ConnectionRequest req = new ConnectionRequest();
    req.setUrl(url);
    req.setPost(true);
   
        req.addArgument("idV", Float.toString(reservation.getIdVeh()));
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");        
        String date_deb = dateFormat.format(reservation.getDate_deb());
        req.addArgument("date_deb", date_deb);

        String date_fin = dateFormat.format(reservation.getDate_fin());
        req.addArgument("date_fin", date_fin);
        System.out.println("aaaaaaaaaaaaaa" +Vars.current_user.getId());
   req.addArgument("id_user", Integer.toString(Vars.current_user.getId()));
   req.addResponseListener(new ActionListener<NetworkEvent>() {
        @Override
        public void actionPerformed(NetworkEvent evt) {
            resultOk = req.getResponseCode() == 200;
            req.removeResponseListener(this);
        }
    });
    
    NetworkManager.getInstance().addToQueueAndWait(req);
    return resultOk;

}

    public ArrayList<Reservation>affichageReservations() {
        ArrayList<Reservation> result = new ArrayList<>();
        
        String url = Statics.BASE_URL+"reservation/Allreservation";
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
                        float id = Float.parseFloat(obj.get("id").toString());                        
                        String date_deb = obj.get("date_deb").toString();
                        String date_fin = obj.get("date_fin").toString();
                       String etat = obj.get("etat").toString();
                         float idv =  Float.parseFloat(obj.get("idvehicule").toString());     

                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
                        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");

                        Date date_debD = formatter.parse(date_deb);
                        String outputDateDeb = outputFormat.format(date_debD) ;
                        
                        Date date_finF = formatter.parse(date_fin);
                        
                        Reservation r = new Reservation((int)id,date_debD,date_finF,(int)idv);
                        r.setEtat(etat);
                        //insert data into ArrayList result
                        result.add(r);
                       
                    
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