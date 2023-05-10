/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *//*
package amena.services;

import amena.entities.User;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import amena.util.Vars;
import static amena.util.Vars.base_url;
import com.codename1.io.ConnectionRequest;

public class Userservices {

    public static Userservices instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    private Userservices() {
        req = new ConnectionRequest();
    }

     public boolean addUser(User u) {
        String url = Vars.base_url + "/user/new";

        req.removeAllArguments();
        req.setUrl(url);
        req.addArgument("nom", u.getNom());
        req.addArgument("prenom", u.getPrenom());
        req.addArgument("cin", u.getCin());
        req.addArgument("email", u.getEmail());
        req.addArgument("password", u.getPassword());
        req.addArgument("status", "1");
        req.addArgument("numetel", u.getNumtel());
        req.addArgument("adress", u.getAdress());

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

    public boolean UpdateUser(User u) {
        String url = base_url + "/userm/editm" + u.getId()
                + "/" + u.getNom()
                + "/" + u.getPrenom()
                + "/" + u.getAdress()
                + "/" + u.getCin()
                + "/" + u.getRole()
                + "/" + u.getPassword()
                + "/" + u.getEmail()
                + "/" + u.getToken()
                + "/" + u.getScore()
                + "/" + u.getNumtel()
                + "/" + u.getImage()
                + "/" + u.isStatus();

        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

}
 */
package amena.services;

import amena.entities.User;
import amena.util.Vars;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Map;

public class UserService {
 public static boolean resultOk = true;
    private static UserService instance = null;
    private ConnectionRequest req;

    public UserService() {
        req = new ConnectionRequest();
    }

    public static UserService getInstance() {
        if (instance == null) {
            instance = new UserService();
        }
        return instance;
    }

    public boolean addUser(User u) {
        String url = Vars.base_url + "/userm/newm";

        req.setUrl(url);
        req.setPost(true);
        req.addArgument("nom", u.getNom());
        req.addArgument("prenom", u.getPrenom());
        req.addArgument("cin", u.getCin());
        req.addArgument("email", u.getEmail());
        req.addArgument("password", u.getPassword());
        req.addArgument("numtel", u.getNumtel());
        req.addArgument("adress", u.getAdress());

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                req.removeResponseListener(this);
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);
        return req.getResponseCode() == 200;
    }

    public boolean updateUser(User u) {
        String url = Vars.base_url + "/userm/editm/" + u.getId()
                + "?nom=" + u.getNom()
                + "&prenom=" + u.getPrenom()
                + "&adress=" + u.getAdress()
                + "&cin=" + u.getCin()
                + "&roles=" + u.getRole()
                + "&password=" + u.getPassword()
                + "&email=" + u.getEmail()
                + "&token=" + u.getToken()
                + "&score=" + u.getScore()
                + "&numtel=" + u.getNumtel()
                + "&image=" + u.getImage()
                + "&status=" + u.isStatus();

        req.setUrl(url);
        req.setPost(false);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                req.removeResponseListener(this);
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);
        return req.getResponseCode() == 200;
    }

    public ArrayList<User> getAllUsers() {
        ArrayList<User> userList = new ArrayList<>();
        String url = Vars.base_url + "/userm";
        req.setUrl(url);
        req.setPost(false);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    JSONParser parser = new JSONParser();
                    Map<String, Object> result = parser.parseJSON(new InputStreamReader(new ByteArrayInputStream(req.getResponseData()), "UTF-8"));
                    ArrayList<Map<String, Object>> userMap = (ArrayList<Map<String, Object>>) result.get("root");
                    for (Map<String, Object> map : userMap) {
                        User u = new User();
                        u.setId((int) Float.parseFloat(map.get("id").toString()));
                        u.setNom(map.get("nom").toString());
                        u.setPrenom(map.get("prenom").toString());
                        u.setAdress(map.get("adress").toString());

                        u.setStatus(Boolean.parseBoolean(map.get("status").toString()));
                        userList.add(u);
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                req.removeResponseListener(this);
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);
        return userList;
    }

    public boolean deleteUser(int id) {
    String url = Vars.base_url + "/userm/deletem/" + id;
    req.setUrl(url);
   
    req.addResponseListener(new ActionListener<NetworkEvent>() {
        @Override
        public void actionPerformed(NetworkEvent evt) {
            if (req.getResponseCode() == 200) {
                resultOk = true;
            }
            req.removeResponseListener(this);
        }
    });
    NetworkManager.getInstance().addToQueueAndWait(req);
    return resultOk;
}

    public User getUserById(int id) {
    User u = new User();
    String url = Vars.base_url + "/userm/"+49;
    req.setUrl(url);
    req.setPost(false);

    req.addResponseListener(new ActionListener<NetworkEvent>() {
        @Override
        public void actionPerformed(NetworkEvent evt) {
            try {
                JSONParser parser = new JSONParser();
                Map<String, Object> result = parser.parseJSON(new InputStreamReader(new ByteArrayInputStream(req.getResponseData()), "UTF-8"));
                Map<String, Object> userMap = (Map<String, Object>) result.get("root");

                u.setId((int) Float.parseFloat(userMap.get(49).toString()));
                u.setNom(userMap.get("nom").toString());
                u.setPrenom(userMap.get("prenom").toString());
                u.setAdress(userMap.get("adress").toString());
                u.setCin(userMap.get("cin").toString());
                u.setEmail(userMap.get("email").toString());
                u.setNumtel(userMap.get("numtel").toString());
                u.setRole(userMap.get("roles").toString());
                u.setScore(userMap.get("score").toString());
                u.setImage(userMap.get("image").toString());
                u.setStatus(Boolean.parseBoolean(userMap.get("status").toString()));

            } catch (IOException ex) {
                ex.printStackTrace();
            }
            req.removeResponseListener(this);
        }
    });

    NetworkManager.getInstance().addToQueueAndWait(req);
    return u;
}
public void loginUser(String email, String password, ActionListener<NetworkEvent> callback) {
        String url = Vars.base_url + "/loginm";

        req.setUrl(url);
        req.setPost(true);
        req.addArgument("email", email);
        req.addArgument("password", password);

        req.addResponseListener(callback);

        NetworkManager.getInstance().addToQueue(req);
    }


}
