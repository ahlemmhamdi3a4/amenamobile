package amena.services;

import amena.entities.User;
import amena.util.Vars;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UserService {

    private static final String TEST_EMAIL = "aymen.zouaoui@esprit.tn";

    public static boolean resultOk = true;
    private static UserService instance = null;
    private ConnectionRequest req, con;

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
        req.addArgument("roles", u.getRoles());

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date_n = dateFormat.format(u.getDate_n());
        req.addArgument("date_n", date_n);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                req.removeResponseListener(this);
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);
        return req.getResponseCode() == 200;
    }

    /*
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
     */
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
                        u.setImage(map.get("image").toString());

                        //   u.setStatus(Boolean.parseBoolean(map.get("status").toString()));
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
        Map<String, Object> result;
        String url = Vars.base_url + "/userm/" + id;
        /*req.setUrl(url);
        req.setPost(false);
        System.out.println("req");*/
        ConnectionRequest request = new ConnectionRequest(url, false);
        NetworkManager.getInstance().addToQueueAndWait(request);
        JSONParser j = new JSONParser();
        String json = new String(request.getResponseData()) + "";
        try {
            result = j.parseJSON(new CharArrayReader(json.toCharArray()));
            if (result.isEmpty()) {
                System.out.println("user not found");
            } else {
                System.out.println("user correct, password correct");

               u.setId((int) Float.parseFloat(result.get("id").toString()));
                u.setNom(result.get("nom").toString());
                u.setRoles(result.get("roles").toString());
                u.setPrenom(result.get("prenom").toString());
                u.setEmail(result.get("email").toString());
                u.setImage(result.get("image").toString());

            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        //NetworkManager.getInstance().addToQueueAndWait(req);
        /*req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    JSONParser parser = new JSONParser();
                    Map<String, Object> result = parser.parseJSON(new InputStreamReader(new ByteArrayInputStream(req.getResponseData()), "UTF-8"));
                    Map<String, Object> userMap = (Map<String, Object>) result.get("root");
                    System.out.println("usermap");
                    System.out.println(userMap);
                    System.out.println("#######");
                    u.setId((int) Float.parseFloat(userMap.get(id).toString()));
                    u.setNom(userMap.get("nom").toString());
                    u.setPrenom(userMap.get("prenom").toString());
                    u.setAdress(userMap.get("adress").toString());
                    u.setCin(userMap.get("cin").toString());
                    u.setEmail(userMap.get("email").toString());
                    u.setNumtel(userMap.get("numtel").toString());
                    u.setImage(userMap.get("image").toString());
                    /* u.setRole(userMap.get("roles").toString());
                u.setScore(userMap.get("score").toString());
               
                u.setStatus(Boolean.parseBoolean(userMap.get("status").toString()));

                } catch (IOException ex) {
                    System.out.println("err"+ex);
                    ex.printStackTrace();
                }
                req.removeResponseListener(this);
            }
        });

         */
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

    public boolean Login(String username, String password) {
        ConnectionRequest con = new ConnectionRequest();
        con.removeAllArguments();
        con.setPost(false);
        con.setUrl(Vars.base_url + "/loginm");

        con.addArgument("username", username);
        con.addArgument("password", password);

        NetworkManager.getInstance().addToQueueAndWait(con);

        System.out.println("login username: " + username + " password: " + password);

        System.out.println("test");

        JSONParser j = new JSONParser();
        String json = new String(con.getResponseData()) + "";
        if (json.compareTo("Failed") > 0) {

            Map<String, Object> u;

            try {
                u = j.parseJSON(new CharArrayReader(json.toCharArray()));
                System.out.println(u);
                //List<Map<String, Object>> list = (List<Map<String, Object>>) u.get("root");
                //System.out.println(list);
                //Vars.current_user = new User((int) Float.parseFloat(u.get("id").toString()));
                // System.out.println(list.get(0).get("nom").toString());
                Vars.current_user = new User();
                //System.out.println("nom: "+u);
                //Vars.current_user = new User((int) Float.parseFloat(u.get("id").toString()));
               // Vars.current_user.setId((int) Float.parseFloat(u.get("id").toString()));
                
                Vars.current_user.setId((int) Float.parseFloat(u.get("id").toString()));
                 System.out.println(Vars.current_user.getId());
                Vars.current_user.setNom(u.get("nom").toString());
                Vars.current_user.setPrenom(u.get("prenom").toString());
                Vars.current_user.setEmail(u.get("email").toString());
                Vars.current_user.setImage(u.get("image").toString());
                Vars.current_user.setAdress(u.get("adress").toString());
                Vars.current_user.setRoles(u.get("roles").toString());
                Vars.current_user.setPrenom(u.get("prenom").toString());
                Vars.current_user.setNumtel(u.get("numtel").toString());
                //Vars.current_user.setRoles(u.get("roles").toString());
                // System.out.println("tel : "+u.get("telephone").toString());
                System.out.println("connexion" + Vars.current_user);

                // Verify password
                if (!(Vars.current_user == null)) {

                    return true;
                } else {
                    Vars.current_user = null;
                    return false;
                }
            } catch (IOException ex) {
                System.out.println(ex);
            }
        }

        return false;
    }

    /*  
private static String hashPassword(String password) {
    String encodedPassword = null;
    try {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] encodedhash = digest.digest(password.getBytes("UTF-8"));
        encodedPassword = Base64.encode(encodedhash);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return encodedPassword;
}
     */
    public boolean resetPassword(String email) {
        String url = Vars.base_url + "/reset-password/processSending";

        req.setUrl(url);
        req.setPost(true);
        req.addArgument("email", email);

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
