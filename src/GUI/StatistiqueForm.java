package GUI;

import com.codename1.charts.ChartComponent;
import com.codename1.charts.models.CategorySeries;
import com.codename1.charts.renderers.DefaultRenderer;
import com.codename1.charts.views.PieChart;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BorderLayout;
import java.util.ArrayList;
import modeles.Colis;
import service.ColisCRUD;

public class StatistiqueForm extends Form {

    public StatistiqueForm() {
        super("Statistique", new BorderLayout());
        createPieChart();
    }

    private void createPieChart() {
        // Récupérer la liste des colis
        ColisCRUD colisCRUD = ColisCRUD.getInstance();
        ArrayList<Colis> colisList = colisCRUD.getColisList();

        // Compter le nombre de colis livrés et le nombre de colis en attente
        int colisLivreCount = 0;
        int colisAttenteCount = 0;
        for (Colis colis : colisList) {
            if (colis.getStatut().equals("livré")) {
                colisLivreCount++;
            } else if (colis.getStatut().equals("en attente")) {
                colisAttenteCount++;
            }
        }

        // Créer les données pour le diagramme circulaire
        CategorySeries series = new CategorySeries("Statistique");
        series.add("Colis livrés", colisLivreCount);
        series.add("Colis en attente", colisAttenteCount);

        // Créer le rendu du diagramme circulaire
        DefaultRenderer renderer = new DefaultRenderer();
        renderer.setChartTitle("Statistique des colis");
        renderer.setChartTitleTextSize(30);
        renderer.setLabelsTextSize(20);
        renderer.setLegendTextSize(20);

        // Ajouter les couleurs pour chaque section du diagramme circulaire
        int[] colors = new int[]{0xFF3366FF, 0xFFFF9933};
      

        // Créer le diagramme circulaire
        PieChart pieChart = new PieChart(series, renderer);

        // Créer le composant de diagramme circulaire
        ChartComponent chartComponent = new ChartComponent(pieChart);

        // Ajouter le composant de diagramme circulaire à la forme
        add(BorderLayout.CENTER, chartComponent);
    }

}