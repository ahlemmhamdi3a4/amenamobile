package GUI;



import com.codename1.location.Location;
import com.codename1.location.LocationManager;
import com.codename1.maps.Coord;
import com.codename1.maps.MapComponent;
import com.codename1.maps.MapListener;
import com.codename1.maps.layers.PointLayer;
import com.codename1.maps.providers.OpenStreetMapProvider;
import com.codename1.ui.Component;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.util.Resources;
import java.io.IOException;


public class Map extends Form {
    /*private MapComponent mapComponent;
    
    public Map(Resources res) throws IOException {
    super("Suivre Transporteur");
    
    // Create a new MapComponent for the map view
    mapComponent = new MapComponent(new OpenStreetMapProvider());
    mapComponent.zoomToLayers();
    
    // Set the layout for the Form
    setLayout(new BorderLayout());
    
    // Add the MapComponent to the Form's center
    add(BorderLayout.CENTER, mapComponent);
    
    // Request the current location of the transporter
    Location location = LocationManager.getLocationManager().getCurrentLocation();
    
    // Create a new PointLayer to display the transporter's location on the map
    PointLayer pointLayer = new PointLayer(new Coord(location.getLatitude(), location.getLongitude()), "Transporter");
    
    // Add the PointLayer to the MapComponent's layers
    mapComponent.getLayers().add(pointLayer);
    
    // Set a MapListener to track the transporter's location and update the PointLayer accordingly
    mapComponent.addMapListener(new MapListener() {
    @Override
    public void mapPositionUpdated(Component source, int zoom, Coord center) {
    // Get the new location of the transporter
    Location location = null;
    try {
    location = LocationManager.getLocationManager().getCurrentLocation();
    } catch (IOException ex) {
    Logger.getLogger(Map.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    // Update the PointLayer's position to match the transporter's new location
    pointLayer.setPoint(new Coord(location.getLatitude(), location.getLongitude()));
    }
    
    public void mapInitialized(Component source, int zoom, Coord center) {
    // Do nothing
    }
    });
    }*/
}






