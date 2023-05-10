package amena.services;

import amena.entities.Competition;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import amena.util.Vars;
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
public class ServiceCompetition {

    //singleton 
    public static ServiceCompetition instance = null;

    //initilisation connection request 
    private ConnectionRequest req;

    public static ServiceCompetition getInstance() {
        if (instance == null) {
            instance = new ServiceCompetition();
        }
        return instance;
    }

    public ServiceCompetition() {
        req = new ConnectionRequest();

    }

    //affichage
    public ArrayList<Competition> affichageCompetition() {
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

                        Competition v = new Competition(title, date1, date2, (int) f1, (int) f2);
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

    public boolean ajouterCompetition(Competition competition) {
        String url = Vars.base_url + "/userm/newm";

        req.setPost(true);
        req.addArgument("title", competition.getTitle());
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

}
