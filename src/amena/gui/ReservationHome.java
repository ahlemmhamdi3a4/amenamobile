package amena.gui;

import amena.entities.User;
import amena.entities.Vehicule;
import amena.services.ServiceVehicule;
import amena.services.UserService;
import amena.util.Vars;
import com.codename1.charts.util.ColorUtil;
import com.codename1.components.ImageViewer;
import com.codename1.components.InteractionDialog;
import com.codename1.components.MultiButton;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.util.Resources;
import java.util.ArrayList;
import com.codename1.components.SpanLabel;
import com.codename1.l10n.Format;
import com.codename1.l10n.SimpleDateFormat;
import static com.codename1.push.PushContent.setTitle;
import com.codename1.ui.Component;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;

import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Painter;
import com.codename1.ui.PickerComponent;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.geom.Shape;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;

import com.codename1.ui.geom.Rectangle;
import com.codename1.ui.layouts.FlowLayout;
import java.io.IOException;
import javafx.scene.shape.Circle;

public class ReservationHome extends SideMenuBaseForm {
private EncodedImage palceHolder;
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

    public ReservationHome(Form previous,Resources res) {
        /*current = this;
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
            viewProfileButton.addActionListener(evt -> showUserProfile(this,lvs, res));
          
            //sp.add(viewProfileButton);
                       list.add(viewProfileButton);

            list.add(sp);

           
           
        }

        //SpanLabel sp = new SpanLabel();
        //sp.setText(ServiceVoyage.getInstance().affichageVoyage().toString());
        this.add(list);
        /* getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> {
        new ProfileForm(res,this).show();
        });
  getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }

    private void showUserProfile(Form previous,User user, Resources res) {
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
            UserListGUI userListGUI = new UserListGUI(this,res);
            userListGUI.show();
        });
        detailsContainer.add(deleteButton);

        centerContainer.add(BorderLayout.CENTER, detailsContainer);
        userProfileForm.add(BorderLayout.CENTER, centerContainer);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
        userProfileForm.show();
         
    }*/
        
          super(new BorderLayout());
        Toolbar tb = getToolbar();
        tb.setTitleCentered(false);
        Image profilePic = res.getImage("logo.png");        
        Image tintedImage = Image.createImage(profilePic.getWidth(), profilePic.getHeight());
        Graphics g = tintedImage.getGraphics();
        g.drawImage(profilePic, 0, 0);
        g.drawImage(res.getImage("gradient-overlay.png"), 0, 0, profilePic.getWidth(), profilePic.getHeight());
        
        tb.getUnselectedStyle().setBgImage(tintedImage);
        
        Button menuButton = new Button("");
        menuButton.setUIID("Title");
        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
        menuButton.addActionListener(e -> getToolbar().openSideMenu());

        Button settingsButton = new Button("");
        settingsButton.setUIID("Title");
        FontImage.setMaterialIcon(settingsButton, FontImage.MATERIAL_SETTINGS);
        
        Label space = new Label("", "TitlePictureSpace");
        space.setShowEvenIfBlank(true);
        Container titleComponent = 
                BorderLayout.north(
                    BorderLayout.west(menuButton).add(BorderLayout.EAST, settingsButton)
                ).
                add(BorderLayout.CENTER, space).
                add(BorderLayout.SOUTH, 
                        FlowLayout.encloseIn(
                                new Label("  Liste des ", "WelcomeBlue"),
                                new Label("vehicules", "WelcomeWhite")
                        ));
        titleComponent.setUIID("BottomPaddingContainer");
        tb.setTitleComponent(titleComponent);
        
        Label separator = new Label("", "BlueSeparatorLine");
        separator.setShowEvenIfBlank(true);
        add(BorderLayout.NORTH, separator);
        Container con4=new Container(BoxLayout.x());
        Container con5=new Container(new BorderLayout());
        
        PickerComponent signallPicker = PickerComponent.createStrings("Vélo","Pièce de rechange","Accessoire","Prix croissant","Prix décroissant","Plus récent").label("Critère");        
        InteractionDialog dlg1 = new InteractionDialog("Choisissez le critère de tri");
        Dimension pre = dlg1.getContentPane().getPreferredSize();
        dlg1.setLayout(new BorderLayout());
        dlg1.add(BorderLayout.CENTER, signallPicker);
        Button ok = new Button("Ok");
        Button close = new Button("Annuler");
        close.addActionListener((ee) -> dlg1.dispose());
        Container cont4=new Container(BoxLayout.x());
        Container cont5=new Container(BoxLayout.y());
//        cont5.add(BoxLayout.encloseXCenter(cont4));
        cont4.addAll(close,ok);
        cont5.add(BoxLayout.encloseXCenter(cont4));
        dlg1.addComponent(BorderLayout.SOUTH, cont5);
        ok.addActionListener(e->{
            System.out.println("Critère: "+signallPicker.getPicker().getSelectedString());
            if(signallPicker.getPicker().getSelectedString().equals("nom") || signallPicker.getPicker().getSelectedString().equals("prenom") /*|| signallPicker.getPicker().getSelectedString().equals("Accessoire")*/)
                {
                    Vars.current_type=signallPicker.getPicker().getSelectedString();
                    Vars.current_choice = 5;
                    new UserListGUI(this,res,Vars.current_user).show();
                }
            if(signallPicker.getPicker().getSelectedString().equals("nom")  || signallPicker.getPicker().getSelectedString().equals("prenom"))
                {
                   if(signallPicker.getPicker().getSelectedString().equals("prenom")) {
                       Vars.current_type="asc";
                   }else{
                       Vars.current_type="desc"; 
                   }
                    Vars.current_choice = 6;
                  new UserListGUI(this,res,Vars.current_user).show();
                }
            if(signallPicker.getPicker().getSelectedString().equals("Plus récent")){
                Vars.current_choice = 7;
                new UserListGUI(this,res,Vars.current_user).show();
            }
        });
        
        
        Button bTri=new Button("Trier par");
        bTri.getAllStyles().setBgColor(ColorUtil.BLACK);
        bTri.getAllStyles().setFgColor(ColorUtil.BLACK);
        FontImage.setMaterialIcon(bTri, FontImage.MATERIAL_SORT,5);
        bTri.addActionListener(e -> {
        dlg1.show(200, 200, 100, 100);
        });
        con5.add(BorderLayout.EAST, bTri);
        con5.add(BorderLayout.WEST, con4);
        TextField tRech=new TextField("","Recherche");
        tRech.getAllStyles().setFgColor(ColorUtil.BLACK);
        tRech.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_SMALL));
        Button rechButton = new Button("");
        rechButton.getAllStyles().setBgColor(ColorUtil.BLACK);
        rechButton.getAllStyles().setFgColor(ColorUtil.BLACK);
        int color = 0xffffff;
        Font materialFontBack = FontImage.getMaterialDesignFont();           
        FontImage imageBack = FontImage.createFixed("\uE8B6", materialFontBack, ColorUtil.BLACK, 30, 30);
        

        rechButton.setUIID("Title");
//        FontImage.setMaterialIcon(rechButton, FontImage.MATERIAL_SEARCH);
        rechButton.setIcon(imageBack);
        rechButton.addActionListener(e -> {
                                                if(!tRech.getText().equals("")){
                                                System.out.println("recherche: "+tRech.getText());
                                                Vars.current_search=tRech.getText();
                                                Vars.current_choice=3;
                                                 new UserListGUI(this,res,Vars.current_user).show();
                                                }
                                     });
        con4.add(BoxLayout.encloseXRight(tRech,rechButton));
        
        add(BorderLayout.NORTH, con5);
        

       //#####begin
//         AnnonceService as = new AnnonceService();
        if (!ServiceVehicule.getInstance().affichageVehicule().isEmpty()) {
            Container con3=new Container(BoxLayout.y());
         for (Vehicule a : ServiceVehicule.getInstance().affichageVehicule()) {              
                int id=a.getId();
                String marque=a.getMarque();
                String modele=a.getModele() ;
                String kilo =a.getKilometrage();               
                String prix =Float.toString(a.getPrix()) ;              
                String image=a.getImg();
             //   String prix=String.valueOf(a.getScore());
                Label lmarque=new Label("Marque: "+marque);
                Label lmodele=new Label("Modele: "+modele);
                Label lkilo =new Label("kilo: "+kilo);
                Label lprix =new Label("Prix: "+prix);     
                Button bAfficher=new Button("Reserver");
                bAfficher.addActionListener(e->{
//                    Annonce a1=new Annonce();
                  //  Vars.current_user=a;
                    System.out.println("testtt");
                 ReserverForm afficherAnnonce=new ReserverForm(this,a.getId());
//                  Form afficherAnnonceForm=afficherAnnonce.getAffichertAnnonceForm();
                  afficherAnnonce.show();
//                    Form fa=afficher(a);
//                    fa.show();
                });
                
            
                Container con=new Container(BoxLayout.x());
                Container con1=new Container(BoxLayout.y());
                        //Image green = Image.createImage(100, 100, 0xff00ff00);

//                ImageViewer imgv=new ImageViewer(theme.getImage("a.jpg").scaled(120, 100));
                ImageViewer imgv = new ImageViewer();
//                EncodedImage palceHolder = EncodedImage.createFromImage(theme.getImage("round.png").scaled(120, 100), false);
                try {
                    palceHolder = EncodedImage.create("/giphy.gif");
                } catch (IOException ex) {

                }
                if(image!=null){
                URLImage urlImage = URLImage.createToStorage(palceHolder, image, image);
                imgv.setImage(urlImage);

                }
                if(image==null){
//                    imgv=new ImageViewer(res.getImage("noimagefound.jpg").scaled(120, 100));
                }
                con1.addAll(lmarque,lmodele,lkilo,lprix);
                con.addAll(imgv,con1);
                
                con3.addAll(con,bAfficher);
            }
         con3.setScrollableY(true);
            System.out.println("sroll : "+con3.isScrollableY());
                         add(BorderLayout.CENTER,con3);

        }
        else {
            Container cont=new Container(BoxLayout.y());
            cont.add(BoxLayout.encloseXCenter(new Label("Aucune vehicule à afficher")));
            add(BorderLayout.CENTER,cont);

        } 
       //#####end

        
        
        
        
        
        
        
        
       // setupSideMenu(res);
        setupSideMenu(res);
    

//    public AfficherListeAnnonces(Resources res) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
    }
    
    
    
@Override
    protected void showOtherForm(Resources res) {
        new ProfileForm(res).show();
    }
   


}

