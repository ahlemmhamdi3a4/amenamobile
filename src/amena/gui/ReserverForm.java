package amena.gui;
import com.codename1.ui.Calendar;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import amena.entities.Reservation;
import amena.services.ServiceReservation;
import com.codename1.ui.Tabs;
import com.codename1.ui.animations.CommonTransitions;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ReserverForm extends Form {
    Form current;
    private Resources theme;

    public ReserverForm(Form previous,int idv) {
     super(new LayeredLayout());
             theme = UIManager.initFirstTheme("/theme2");
  
     getTitleArea().removeAll();
        getTitleArea().setUIID("Container");

        setTransitionOutAnimator(CommonTransitions.createUncover(CommonTransitions.SLIDE_HORIZONTAL, true, 400));

        Tabs walkthruTabs = new Tabs();
        walkthruTabs.setUIID("Container");
        walkthruTabs.getContentPane().setUIID("Container");
        walkthruTabs.getTabsContainer().setUIID("Container");
        walkthruTabs.hideTabs();
        current = this;
            ServiceReservation sv = new ServiceReservation() ;    

    // create date pickers
    Label lb = new Label("Select Date Range:");
lb.getStyle().setFgColor(0x000000);

Picker datePickerDeb = new Picker();
datePickerDeb.setType(Display.PICKER_TYPE_DATE);
datePickerDeb.setDate(new java.util.Date());
datePickerDeb.getStyle().setFgColor(0x000000);

Picker datePickerFin = new Picker();
datePickerFin.setType(Display.PICKER_TYPE_DATE);
datePickerFin.setDate(new java.util.Date());
datePickerFin.getStyle().setFgColor(0x000000);

Button btnValider = new Button("Reserver");
btnValider.getStyle().setFgColor(0xFFFFFF);
btnValider.getStyle().setBgColor(0x000000);

    btnValider.getUnselectedStyle().setFgColor(0xffffff);
    btnValider.getUnselectedStyle().setBgColor(0x4caf50);

    ArrayList<Date> selectedDates = new ArrayList<>();

        ArrayList<Reservation> lv = sv.affichageReservations(); 
      Date d = null ;
    
    // create labels
    Label lblDateDeb = new Label("Date debut");
    Label lblDateFin = new Label("Date  fin :");

    // set up calendar
    Calendar calendar = new Calendar();
    calendar.setSelectedDate(new java.util.Date());
    calendar.setMultipleSelectionEnabled(true);
  
    
         Style selectedDateStyle;
        selectedDateStyle = calendar.getMonthViewSelectedStyle();
        selectedDateStyle.setBgColor(0xff0000); // set the background color to red
        selectedDateStyle.setBgTransparency(255); // make the background color opaque
        selectedDateStyle.setFgColor(0xffffff); // set the foreground color to white

for (Reservation lvs : lv) {
    if(lvs.getIdVeh()==idv){
    Date db = lvs.getDate_deb(); 
    Date df = lvs.getDate_fin();
    
    selectedDates.addAll(sv.getDatesBetween(db, df)) ;}
    }
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    btnValider.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent evt) {
        for (Date date : selectedDates) {
            String dateString = sdf.format(date);
            if (dateString.compareTo(sdf.format(datePickerDeb.getDate())) >= 0 && dateString.compareTo(sdf.format(datePickerFin.getDate())) <= 0) {
             Dialog.show("Erreur", "Vehicule reservé.", new Command("OK"));
             return ;
            }
        }
                    Reservation r = new Reservation(datePickerDeb.getDate(),datePickerFin.getDate(),idv) ;
                    sv.addReservation(r);
                    Dialog.show("Confirmation", "Votre réservation a été enregistrée.", new Command("OK"));
                } 
       });
Collection<Date> datesCollection = selectedDates;
calendar.setSelectedDays(datesCollection);
 // set layout and add components
 lb.setText(Integer.toString(idv));
    setLayout(new BoxLayout(BoxLayout.Y_AXIS));
    add(lblDateDeb);
    add(datePickerDeb);
    add(lblDateFin);
    add(datePickerFin);
    add(btnValider);
    add(calendar);

}

}
