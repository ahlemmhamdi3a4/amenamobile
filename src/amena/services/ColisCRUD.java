package amena.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import java.io.ByteArrayInputStream;
import amena.entities.Colis;
import amena.util.Vars;

import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


public class ColisCRUD {

    private ArrayList<Colis> colisList;
    public static ColisCRUD instance = null;
    private ConnectionRequest request;
    private boolean resultOK;

    private ColisCRUD() {
        request = new ConnectionRequest();
    }

    public static ColisCRUD getInstance() {
        if (instance == null) {
            instance = new ColisCRUD();
        }
        return instance;
    }

    public ArrayList<Colis> getColisList() {
        String url = Vars.base_url + "/Json/allColis";
        request.setUrl(url);
        request.setPost(false);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                colisList = parseColisList(new String(request.getResponseData()));
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);
        return colisList;
    }

    private ArrayList<Colis> parseColisList(String json) {
    ArrayList<Colis> add = new ArrayList<>();
    try {
        JSONParser parser = new JSONParser();
        Map<String, Object> colisListJson = parser.parseJSON(new CharArrayReader(json.toCharArray()));
        List<Map<String, Object>> colisListMap = (List<Map<String, Object>>) colisListJson.get("root");
        if (colisListMap != null) { // Vérifier si la liste n'est pas null
            for (Map<String, Object> colisMap : colisListMap) {
                Colis colis = new Colis();
                colis.setId(((Double) colisMap.get("id")).intValue());
                String nomE = colisMap.get("nomExpediteur").toString() ;
                colis.setNomExpediteur(nomE);
                String adrsE = colisMap.get("adresseExpediteur").toString() ;
                colis.setAdresseExpediteur(adrsE);
                String nomD =colisMap.get("nomDestinataire").toString();
                colis.setNomDestinataire(nomD);
                String adrsD = colisMap.get("adresseDestinataire").toString() ; 
                colis.setAdresseDestinataire(adrsD);
                colis.setPoids(((Double) colisMap.get("poids")).floatValue());
                add.add(colis);
            }
        } else {
            System.out.println("La liste des colis est null");
        }
    } catch (IOException ex) {
        System.out.println(ex.getMessage());
    }
    return add;
}

    public boolean addColis(Colis colis) {
    String url = Vars.base_url + "/Json/new";
    ConnectionRequest req = new ConnectionRequest();
    req.setUrl(url);
    req.setPost(true);
    req.addArgument("NomExpediteur", colis.getNomExpediteur());
    req.addArgument("AdresseExpediteur", colis.getAdresseExpediteur());
    req.addArgument("NomDestinataire", colis.getNomDestinataire());
    req.addArgument("AdresseDestinataire", colis.getAdresseDestinataire());
    req.addArgument("poids", Float.toString(colis.getPoids()));
     req.addArgument("id_u", Float.toString(colis.getIdu()));
    
    if (colis.getDateExpedition() != null) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateExpedition = dateFormat.format(colis.getDateExpedition());
        req.addArgument("dateExpedition", dateExpedition);
    }

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
    public boolean updateColis(Colis colis) {
    String url = Vars.base_url + "/Json/" + colis.getId() + "/edit";
    ConnectionRequest req = new ConnectionRequest();
    req.setUrl(url);
    req.setPost(true);
    req.addArgument("NomExpediteur", colis.getNomExpediteur());
    req.addArgument("AdresseExpediteur", colis.getAdresseExpediteur());
    req.addArgument("NomDestinataire", colis.getNomDestinataire());
    req.addArgument("AdresseDestinataire", colis.getAdresseDestinataire());
    req.addArgument("poids", Float.toString(colis.getPoids()));

    if (colis.getDateExpedition() != null) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateExpedition = dateFormat.format(colis.getDateExpedition());
        req.addArgument("dateExpedition", dateExpedition);
    }

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

public boolean deleteColis(int id) {
    String url = Vars.base_url + "/Json/delete/" + id;
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
