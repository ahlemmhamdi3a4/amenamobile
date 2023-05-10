/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amena.services;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import amena.entities.Competition;
import amena.util.Vars;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;




/**
 *
 * @author Ahlem
 */
public class SserviceComp {

    private ArrayList<Competition> compList;
    public static SserviceComp instance = null;
    private ConnectionRequest request,req;
    private boolean resultOK;
    
    

    private SserviceComp() {
        request = new ConnectionRequest(); 
        req = new ConnectionRequest();
    }

    public static SserviceComp getInstance() {
        if (instance == null) {
            instance = new SserviceComp();
        }
        return instance;
    }

   /* public ArrayList<Competition> getCompList() {
        String url = Vars.base_url + "/competition/Allcompetition";
        request.setUrl(url);
        request.setPost(false);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                
                
                try {
                    compList = parseCompList(new String(request.getResponseData()));
                } catch (ParseException ex) {
                }
                    
                    request.removeResponseListener(this);
                }
            
        });
        NetworkManager.getInstance().addToQueueAndWait(request);
        return compList;
    }

    private ArrayList<Competition> parseCompList(String json) throws ParseException {
    ArrayList<Competition> add = new ArrayList<>();
    try {
        JSONParser parser = new JSONParser();
        Map<String, Object> compListJson = parser.parseJSON(new CharArrayReader(json.toCharArray()));
        List<Map<String, Object>> compListMap = (List<Map<String, Object>>) compListJson.get("root");
        if (compListMap != null) { // Vérifier si la liste n'est pas null
            for (Map<String, Object> compMap : compListMap) {
                Competition competition = new Competition();
                competition.setId(((Double) compMap.get("id")).intValue());
                String titreC = compMap.get("title").toString() ;
                competition.setTitle(titreC);
                String typeC = compMap.get("type").toString() ;
                competition.setType(Integer.parseInt(typeC));
                  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String dateDeb =compMap.get("date deb").toString();
                competition.setDate_deb( sdf.parse(dateDeb));
                String dateFin = compMap.get("date fin").toString() ; 
                competition.setDate_fin( sdf.parse(dateFin));
                
                add.add(competition);
            }
        } else {
            System.out.println("La liste des comp est nulle");
        }
    } catch (IOException ex) {
        System.out.println(ex.getMessage());
    }
    return add;
}*/public ArrayList<Competition> affichageCompetition() {
        ArrayList<Competition> result = new ArrayList<>();

        String url = Vars.base_url + "/competition/Allcompetition";
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp;
                jsonp = new JSONParser();

                try {
                    Map<String, Object> mapReclamations = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));

                    List<Map<String, Object>> listOfMaps = (List<Map<String, Object>>) mapReclamations.get("root");

                    for (Map<String, Object> obj : listOfMaps) {

                        //dima id fi codename one float 5outhouha
                        float id = Float.parseFloat(obj.get("id").toString());

                        //   String objet = obj.get("objet").toString();
                        String title = obj.get("title").toString();
                        String date_deb = obj.get("dateDeb").toString();
                        String date_fin = obj.get("dateFin").toString();
                        String type = obj.get("type").toString();
                        String nbp = obj.get("nbp").toString();
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        Date date1 = sdf.parse(date_deb);
                        Date date2 = sdf.parse(date_fin);
                        float f1 = Float.parseFloat(nbp);
                        float f2 = Float.parseFloat(type);

                        Competition v = new Competition((int)id,title, date1, date2, (int) f1, (int) f2);
                        result.add(v);

                    }

                } catch (Exception ex) {

                    ex.printStackTrace();
                }

            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha

        return result;

}

    public boolean ajouterComp(Competition competition) {
         String url = Vars.base_url + "/competition/newm/b";
         ConnectionRequest req = new ConnectionRequest();
        req.setUrl(url);
        req.setPost(true);
        req.addArgument("titre", competition.getTitle());
        req.addArgument("dateDeb", new SimpleDateFormat("yyyy-MM-dd").format(competition.getDate_deb()));
        req.addArgument("dateFin", new SimpleDateFormat("yyyy-MM-dd").format(competition.getDate_fin()));
        req.addArgument("type", String.valueOf(competition.getType()));

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                req.removeResponseListener(this);
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);
        return req.getResponseCode() == 200;
    
}
    public boolean updateComp(Competition competition) {
    String url = Vars.base_url +"/competition/edit/b/"+ competition.getId() ;
    ConnectionRequest req = new ConnectionRequest();
    req.setUrl(url);
    req.setPost(true);
    req.addArgument("titre", competition.getTitle());
   req.addArgument("dateDeb", new SimpleDateFormat("yyyy-MM-dd").format(competition.getDate_deb()));
        req.addArgument("dateFin", new SimpleDateFormat("yyyy-MM-dd").format(competition.getDate_fin()));
        req.addArgument("type", String.valueOf(competition.getType()));
  

    req.addResponseListener(new ActionListener<NetworkEvent>() {
        @Override
        public void actionPerformed(NetworkEvent evt) {
            resultOK = req.getResponseCode() == 200;
            req.removeResponseListener(this);
        }
    });

    NetworkManager.getInstance().addToQueueAndWait(req);
    return resultOK;
}

public boolean deleteComp(int id) {
    String url = Vars.base_url + "/competition/deleteCompetitionJSON/" + id;
    ConnectionRequest req = new ConnectionRequest();
    req.setUrl(url);
    req.setPost(false);

    req.addResponseListener(new ActionListener<NetworkEvent>() {
        @Override
        public void actionPerformed(NetworkEvent evt) {
            resultOK = req.getResponseCode() == 200;
            req.removeResponseListener(this);
        }
    });

    NetworkManager.getInstance().addToQueueAndWait(req);
    return resultOK;
}

    
}


