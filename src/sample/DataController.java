package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class DataController
{
    @FXML
    private Button tasksbutton;

    @FXML
    private Button timerbutton;

    @FXML
    private Button databutton;

    @FXML
    private Button schoolbutton;

    @FXML
    private Button otherbutton;

    @FXML
    private LineChart<?, ?> ProcChart;

    @FXML
    public CategoryAxis xaxis;

    @FXML
    public NumberAxis yaxis;

    @FXML
    private Label procindex;

    String theme;

    double[][] numvsratio=new double[10][2];
    double [] ratioarr=new double[10];

    @FXML
    Pane procpane;

    @FXML
    private Circle proccircle;

    @FXML
    private Label proctext;

    @FXML
    private AnchorPane datarootLayout;

    @FXML
    private AnchorPane dataLayout;

    @FXML
    private BorderPane databpane;

    @FXML
    private Label title;

    public void changescreenTask(ActionEvent event) throws IOException
    {

        Parent tasksParent = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Scene tasks=new Scene(tasksParent);
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(tasks);
        window.setFullScreen(true);
        window.show();

    }

    public void changescreenTaskOth(ActionEvent event) throws IOException
    {

        Parent tasksParent = FXMLLoader.load(getClass().getResource("sampleoth.fxml"));
        Scene tasks=new Scene(tasksParent);
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(tasks);
        window.setFullScreen(true);
        window.show();

    }

    public void changescreenTimer(ActionEvent event) throws IOException
    {
        Parent tasksParent = FXMLLoader.load(getClass().getResource("Timer.fxml"));
        Scene tasks=new Scene(tasksParent);
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(tasks);
        window.setFullScreen(true);
        window.show();
        //TimerController obj= new TimerController();
        //obj.initialize();

    }

    public void changescreenData(ActionEvent event) throws IOException
    {
        Parent tasksParent = FXMLLoader.load(getClass().getResource("Data.fxml"));
        Scene tasks=new Scene(tasksParent);
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(tasks);
        window.setFullScreen(true);
        window.show();

    }

    public void initialize() throws IOException {
        String line1 = "";
        FileReader filer = new FileReader("src/sample/themetext");
        BufferedReader buffr = new BufferedReader(filer);
        while ((line1 = buffr.readLine()) != null) {
            String[] values = line1.split(",");
            theme=values[0];
        }
        procpane.getStylesheets().add(getClass().getResource(theme+".css").toString());
        proccircle.getStyleClass().add("proccircle");
        datarootLayout.getStylesheets().add(getClass().getResource(theme+".css").toString());
        dataLayout.getStylesheets().add(getClass().getResource(theme+".css").toString());
        databpane.getStylesheets().add(getClass().getResource(theme+".css").toString());
        databpane.getStyleClass().add("timerbpane");
        title.getStylesheets().add(getClass().getResource(theme+".css").toString());
        title.getStyleClass().add("titletext");

        String line = "";
        FileReader fr = new FileReader("src/sample/CompletedTasks");
        BufferedReader br = new BufferedReader(fr);
        int i=0;
        while ((line = br.readLine()) != null) {
            String[] values = line.split(",");
            LocalDate datedue = LocalDate.parse(values[0]);
            LocalDate dateset = LocalDate.parse(values[3]);
            LocalDate datecompleted = LocalDate.parse(values[4]);
            long daysbetsetcomp= ChronoUnit.DAYS.between(dateset, datecompleted);
            long daysbetsetdue=ChronoUnit.DAYS.between(dateset, datedue);
            if (daysbetsetdue==0)
            {
                daysbetsetdue=1;
            }
            double ratio=((double) daysbetsetcomp / (double) daysbetsetdue);
            System.out.println("daysbetsetcomp:"+daysbetsetcomp);
            System.out.println("daysbetsetdue:"+daysbetsetdue);
            System.out.println("ratio:"+ratio);

            if (i<ratioarr.length)
            {
                ratioarr[i]=ratio;
            }
            i++;

        }

        fillarray(numvsratio,ratioarr, 0);




        XYChart.Series series=new XYChart.Series();
        for (int j=0;j<numvsratio.length;j++)
        {
            System.out.println(j);
            series.getData().add(new XYChart.Data(String.valueOf((numvsratio[j][0])),numvsratio[j][1]));

        }
        ProcChart.getData().addAll(series);

        xaxis=new CategoryAxis();
        xaxis.setLabel("Tasks");

        double sum=0;

        for (int h=0;h<numvsratio.length;h++)
        {
            sum=sum+numvsratio[h][1];
            System.out.println("sum:"+sum);


        }
        double procind=(sum/10)*100;
        System.out.println(procind);
        procindex.setText(Math.round(procind)+"");




    }

    public static void fillarray(double[][] numvsratio, double [] ratioarr, int i)
    {
        numvsratio[i][0]= (i + 1);
        numvsratio[i][1]=(ratioarr[i]);
        i++;
        if (i<numvsratio.length)
        {
            fillarray(numvsratio,ratioarr,i);
        }


    }
}
