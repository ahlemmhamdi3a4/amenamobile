/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amena.services;


import amena.entities.Annonces;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import java.io.ByteArrayInputStream;

import amena.util.Vars;

import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


public class AnnonceCRUD {

    private ArrayList<Annonces> annonceList;
    public static AnnonceCRUD instance = null;
    private ConnectionRequest request;
    private boolean resultOK;

    private AnnonceCRUD() {
        request = new ConnectionRequest();
    }

    public static AnnonceCRUD getInstance() {
        if (instance == null) {
            instance = new AnnonceCRUD();
        }
        return instance;
    }

    public ArrayList<Annonces> getAnnonceList() {
        String url = Vars.base_url + "/Json/allAnnonces";
        request.setUrl(url);
        request.setPost(false);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                annonceList = parseAnnonceList(new String(request.getResponseData()));
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);
        return annonceList;
    }

    private ArrayList<Annonces> parseAnnonceList(String json) {
    ArrayList<Annonces> add = new ArrayList<>();
    try {
        JSONParser parser = new JSONParser();
        Map<String, Object> annonceListJson = parser.parseJSON(new CharArrayReader(json.toCharArray()));
        List<Map<String, Object>> annonceListMap = (List<Map<String, Object>>) annonceListJson.get("root");
        if (annonceListMap != null) { // Vérifier si la liste n'est pas null
            for (Map<String, Object> annonceMap : annonceListMap) {
                Annonces annonce=new Annonces();

                
                annonce.setId(((Double) annonceMap.get("id")).intValue());
                String type = annonceMap.get("type").toString() ;
                annonce.setVilleDep(type);
                String villeD = annonceMap.get("ville_dep").toString() ;
                annonce.setVilleDep(villeD);
                
                String villeA = annonceMap.get("ville_arr").toString() ;
                annonce.setVilleArr(villeA);
                String DateD =annonceMap.get("date_dep").toString();
                annonce.setDateDep(DateD);
                String DateA = annonceMap.get("date_arr").toString() ; 
                annonce.setDateArr(DateA);
                String Desc = annonceMap.get("description").toString() ;  
                annonce.setDescription(Desc);
                annonce.setPrix(((Double) annonceMap.get("prix")).intValue());
                add.add(annonce);
            }
        } else {
            System.out.println("La liste des annonces est null");
        }
    } catch (IOException ex) {
        System.out.println(ex.getMessage());
    }
    return add;
}

    public boolean addAnnonce(Annonces annonce) {
    String url = Vars.base_url + "/Json/annonce/new";
    ConnectionRequest req = new ConnectionRequest();
    req.setUrl(url);
    req.setPost(false);
    // req.addArgument("idc", annonce.getType());
    req.addArgument("Type", annonce.getType());
    req.addArgument("VilleDep", annonce.getVilleDep());
    req.addArgument("VilleArr", annonce.getVilleArr());
    req.addArgument("DateDep", annonce.getDateDep());
    req.addArgument("DateArr", annonce.getDateArr());
    req.addArgument("Description", annonce.getDescription());
    req.addArgument("Prix", Float.toString(annonce.getPrix()));
        req.addArgument("id_u", Float.toString(annonce.getIdu())); 
  
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
    public boolean updateAnnonce(Annonces annonce) {
    String url = Vars.base_url + annonce.getId() + "/Json/edita";
    ConnectionRequest req = new ConnectionRequest();
    req.setUrl(url);
    req.setPost(false);
      req.addArgument("Type", annonce.getType());
    req.addArgument("VilleDep", annonce.getVilleDep());
    req.addArgument("VilleArr", annonce.getVilleArr());
    req.addArgument("DateDep", annonce.getDateDep());
    req.addArgument("DateArr", annonce.getDateArr());
    req.addArgument("Description", annonce.getDescription());
    req.addArgument("Prix", Float.toString(annonce.getPrix()));
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

public boolean deleteAnnonce(int id) {
    String url = Vars.base_url + "/Json/annonce/delete/" + id;
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
/*public void sendSMS(String recipient, String message) {
String url = "https://api.twilio.com/2010-04-01/Accounts/" + ACCOUNT_SID + "/Messages.json";
ConnectionRequest request = new ConnectionRequest();
request.setUrl(url);
request.setPost(true);
request.addRequestHeader("Authorization", "Basic " + Base64.encodeNoNewline((ACCOUNT_SID + ":" + AUTH_TOKEN).getBytes()));
request.addArgument("To", recipient);
request.addArgument("From", TWILIO_NUMBER);
request.addArgument("Body", message);
request.addResponseListener(new ActionListener<NetworkEvent>() {
public void actionPerformed(NetworkEvent evt) {
byte[] data = request.getResponseData();
if (data != null) {
String response = new String(data);
Map<String, Object> result = null;
try {
result = new JSONParser().parseJSON(new InputStreamReader(new ByteArrayInputStream(response.getBytes())));
} catch (IOException e) {
e.printStackTrace();
}
if (result != null && result.get("error_message") != null) {
System.out.println("SMS not sent: " + result.get("error_message"));
} else {
System.out.println("SMS sent successfully");
}
} else {
System.out.println("Error sending SMS");
}
}
});
NetworkManager.getInstance().addToQueue(request);
}*/