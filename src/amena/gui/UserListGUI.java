package amena.gui;

import amena.entities.User;
import amena.entities.Vehicule;
import amena.services.ServiceVehicule;
import amena.services.UserService;
import com.codename1.components.MultiButton;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.util.Resources;
import java.util.ArrayList;
import com.codename1.components.SpanLabel;
import static com.codename1.push.PushContent.setTitle;
import com.codename1.ui.Component;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;

import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Painter;
import com.codename1.ui.URLImage;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.geom.Shape;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;

import com.codename1.ui.geom.Rectangle;
import javafx.scene.shape.Circle;

public class UserListGUI extends Form {

    /*
    private Form form;
    private SpanLabel usersLabel;
    private ArrayList<User> users;
    private int currentPage = 0;
    private int pageSize = 2;

    public UserListGUI(Resources res) {
        form = new Form("Liste des utilisateurs");
        form.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        usersLabel = new SpanLabel();
        form.add(usersLabel);
        loadUsers(res);
    }

    public class CirclePainter implements Painter {

        public void paint(Graphics g, Rectangle rect) {
            int width = rect.getSize().getWidth();
            int height = rect.getSize().getHeight();
            g.setColor(0xffffff);
            g.fillArc(rect.getX(), rect.getY(), width, height, 0, 360);
        }

    }

    

    public Form getForm() {
        return form;
    }

    private void loadUsers(Resources res) {
        UserService userService = UserService.getInstance();
        users = userService.getAllUsers();
        refreshUsersContainer(res);
    }

    private void refreshUsersContainer(Resources res) {
        Container usersContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        int startIndex = currentPage * pageSize;
        int endIndex = Math.min(startIndex + pageSize, users.size());
        for (int i = startIndex; i < endIndex; i++) {
            User u = users.get(i);
            Container userLine = createUserLine(u,res);
            userLine.setLeadComponent(userLine.getComponentAt(0));
            userLine.addPointerPressedListener(evt -> showUserProfile(u,res));
            usersContainer.add(userLine);
        }
        form.removeAll();
        form.add(usersContainer);
        form.add(createPaginationContainer(res));
        form.revalidate();
    }

  private Container createPaginationContainer(Resources res) {
    User u = new User();
    Button ajoutbtn = new Button("Ajouter");
    ajoutbtn.addActionListener(e -> {
        // Ajoouter l'utilisateur
        //UserService.getInstance().addUser(u);
        
        // Rediriger l'utilisateur vers la liste des utilisateurs
        AddUserForm addUserForm = new AddUserForm(res);
        addUserForm.show();
        refreshUsersContainer(res);
    });
    Container paginationContainer = new Container(new BorderLayout());
    paginationContainer.addComponent(BorderLayout.NORTH, ajoutbtn);
    Button prevButton = new Button("Page précédente");
    prevButton.addActionListener(evt -> {
        currentPage--;
        refreshUsersContainer(res);
    });

    // Vérifier si la page suivante existe
    int lastIndex = (currentPage + 1) * pageSize;
    if (lastIndex < users.size()) {
        Button nextButton = new Button("Page suivante");
        nextButton.addActionListener(evt -> {
            currentPage++;
            refreshUsersContainer(res);
        });
        Container buttonContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));
        if (currentPage > 0) {
            buttonContainer.add(prevButton);
        }
        buttonContainer.add(nextButton);
        paginationContainer.add(BorderLayout.CENTER, buttonContainer);
    } else {
        paginationContainer.add(BorderLayout.CENTER, prevButton);
    }

    return paginationContainer;
}


    private Container createUserLine(User user,Resources res) {
        Container userLine = new Container(new BorderLayout());
        // Ajouter un bouton pour accéder au profil de l'utilisateur
        Button viewProfileButton = new Button("");
        viewProfileButton.addActionListener(evt -> showUserProfile(user,res));
        userLine.add(BorderLayout.EAST, viewProfileButton);
        userLine.add(BorderLayout.WEST, new SpanLabel("nom     "+user.getNom()));
        userLine.add(BorderLayout.CENTER, new SpanLabel("prenm     "+user.getEmail()));
        userLine.add(BorderLayout.CENTER, new SpanLabel("adresse    "+user.getAdress()));
        

        return userLine;
    }

    private void showUserProfile(User user,Resources res) {
        int auto = 1;
        Form userProfileForm = new Form(user.getNom());
        userProfileForm.setLayout(new BorderLayout());

        // Ajouter un Container pour centrer le cadre
        Container centerContainer = new Container(new BorderLayout());
        centerContainer.getAllStyles().setMarginTop(auto);
        centerContainer.getAllStyles().setMarginBottom(auto);
        centerContainer.getAllStyles().setMarginLeft(auto);
        centerContainer.getAllStyles().setMarginRight(auto);

        // Ajouter un cercle en haut
        int diameter = Display.getInstance().getDisplayWidth() / 2;
        int paddingTop = Display.getInstance().getDisplayHeight() / 10;
        Container circleContainer = new Container(new BorderLayout());

        // Ajouter des composants pour afficher les détails de l'utilisateur
        Container detailsContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        detailsContainer.add(new SpanLabel("Nom: " + user.getNom()));
        detailsContainer.add(new SpanLabel("Prenom: " + user.getPrenom()));
        detailsContainer.add(new SpanLabel("Email: " + user.getEmail()));
        detailsContainer.add(new SpanLabel("Adresse: " + user.getAdress()));
          EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(50, 50, 0xffff0000), true);
              
            Image i = URLImage.createToStorage(placeholder,user.getImage(),user.getImage());
            MultiButton sp = new MultiButton(user.getNom());
            sp.setIcon(i.fill(200, 200));
             detailsContainer.add((sp));
        // Ajouter d'autres informations utilisateur ici

        // Ajouter un bouton de suppression d'utilisateur
        Button deleteButton = new Button("Supprimer");
        deleteButton.addActionListener(e -> {
            // Supprimer l'utilisateur
            UserService.getInstance().deleteUser(user.getId());

            // Rediriger l'utilisateur vers la liste des utilisateurs
            UserListGUI userListGUI = new UserListGUI(res);
            userListGUI.show();
        });
        detailsContainer.add(deleteButton);

        centerContainer.add(BorderLayout.CENTER, detailsContainer);
        userProfileForm.add(BorderLayout.CENTER, centerContainer);

        userProfileForm.show();
    }

    public void show() {
        form.show();
    }
     */ public static int idv;
    Form current;

    public UserListGUI(Resources res) {
        current = this;
        setTitle("Liste des user");
        // setLayout(BoxLayout.y());
        UserService sv = new UserService();
        ArrayList<User> lv = sv.getAllUsers();
        Container list = new Container(BoxLayout.y());
        list.setScrollableY(true);
        for (User lvs : lv) {

            EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(50, 50, 0xffff0000), true);

            Image i = URLImage.createToStorage(placeholder, lvs.getNom(), lvs.getImage());
            MultiButton sp = new MultiButton(lvs.getEmail());
            sp.setIcon(i.fill(200, 200));
            sp.setTextLine1(lvs.getNom() + " " + lvs.getPrenom());
            //  sp.setTextLine2(lvs.getPrix() + "dt");
            //  sp.setTextLine3("Age : "+lvs.getPrix()); 
             Button viewProfileButton = new Button("afficher Profil");
            viewProfileButton.addActionListener(evt -> showUserProfile(lvs, res));
          
            //sp.add(viewProfileButton);
                       list.add(viewProfileButton);

            list.add(sp);

           
           
        }

        //SpanLabel sp = new SpanLabel();
        //sp.setText(ServiceVoyage.getInstance().affichageVoyage().toString());
        this.add(list);
        /* getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> {
        new ProfileForm(res,this).show();
        });*/

    }

    private void showUserProfile(User user, Resources res) {
        int auto = 1;
        Form userProfileForm = new Form(user.getNom());
        userProfileForm.setLayout(new BorderLayout());

        // Ajouter un Container pour centrer le cadre
        Container centerContainer = new Container(new BorderLayout());
        centerContainer.getAllStyles().setMarginTop(auto);
        centerContainer.getAllStyles().setMarginBottom(auto);
        centerContainer.getAllStyles().setMarginLeft(auto);
        centerContainer.getAllStyles().setMarginRight(auto);

        // Ajouter un cercle en haut
        int diameter = Display.getInstance().getDisplayWidth() / 2;
        int paddingTop = Display.getInstance().getDisplayHeight() / 10;
        Container circleContainer = new Container(new BorderLayout());

        // Ajouter des composants pour afficher les détails de l'utilisateur
        Container detailsContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        detailsContainer.add(new SpanLabel("Nom: " + user.getNom()));
        detailsContainer.add(new SpanLabel("Prenom: " + user.getPrenom()));
        detailsContainer.add(new SpanLabel("Email: " + user.getEmail()));
        detailsContainer.add(new SpanLabel("Adresse: " + user.getAdress()));
        EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(50, 50, 0xffff0000), true);

        Image i = URLImage.createToStorage(placeholder, user.getImage(), user.getImage());
        MultiButton sp = new MultiButton(user.getNom());
        sp.setIcon(i.fill(200, 200));
        detailsContainer.add((sp));
        // Ajouter d'autres informations utilisateur ici

        // Ajouter un bouton de suppression d'utilisateur
        Button deleteButton = new Button("Supprimer");
        deleteButton.addActionListener(e -> {
            // Supprimer l'utilisateur
            UserService.getInstance().deleteUser(user.getId());

            // Rediriger l'utilisateur vers la liste des utilisateurs
            UserListGUI userListGUI = new UserListGUI(res);
            userListGUI.show();
        });
        detailsContainer.add(deleteButton);

        centerContainer.add(BorderLayout.CENTER, detailsContainer);
        userProfileForm.add(BorderLayout.CENTER, centerContainer);

        userProfileForm.show();
    }

}
