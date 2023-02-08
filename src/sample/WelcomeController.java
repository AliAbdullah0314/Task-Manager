package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import static javafx.application.Application.setUserAgentStylesheet;

public class WelcomeController
{
    @FXML
    private AnchorPane rootLayout;

    @FXML
    public Button taskbutton;
    @FXML
    public Button timerbutton;
    @FXML
    private Button databutton;

    @FXML
    private Button themered;

    @FXML
    private Button themeblue;

    @FXML
    private Button themegreen;

    @FXML
    private Button themepink;

    @FXML
    ImageView bgimage;
    private String theme;

    public void changescreenTask(ActionEvent event) throws IOException
    {

        Parent tasksParent = FXMLLoader.load(getClass().getResource("sample.fxml"));
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
        if (theme.equals("Red"))
        {
            Image bg=new Image(String.valueOf(getClass().getResource("redandblack.png")));
            bgimage.setImage(bg);
        }
        if (theme.equals("Blue"))
        {
            Image bg=new Image(String.valueOf(getClass().getResource("blueandblack.jpg")));
            bgimage.setImage(bg);
        }
        if (theme.equals("Green"))
        {
            Image bg=new Image(String.valueOf(getClass().getResource("greenandwhite.jpg")));
            bgimage.setImage(bg);
        }
        if (theme.equals("Pink"))
        {
            Image bg=new Image(String.valueOf(getClass().getResource("pinkandwhite.jpg")));
            bgimage.setImage(bg);
        }
        rootLayout.getStylesheets().add(getClass().getResource(theme+".css").toString());
        rootLayout.getStyleClass().add("anchorpane");

    }
    public void changeThemeRed(ActionEvent event) throws IOException {
        bgimage.getScene().getStylesheets().add(getClass().getResource("Red.css").toString());
        Image bg=new Image(String.valueOf(getClass().getResource("redandblack.png")));
        bgimage.setImage(bg);
        theme="Red";
        rootLayout.getStylesheets().add(getClass().getResource(theme+".css").toString());
        rootLayout.getStyleClass().add("anchorpane");
        FileWriter fw=new FileWriter("src/sample/themetext",false);
        BufferedWriter bw=new BufferedWriter(fw);
        PrintWriter pw=new PrintWriter(bw);
        pw.print(theme);
        pw.flush();

    }

    public void changeThemeBlue(ActionEvent event) throws IOException {
        bgimage.getScene().getStylesheets().add(getClass().getResource("Blue.css").toString());
        Image bg=new Image(String.valueOf(getClass().getResource("blueandblack.jpg")));
        bgimage.setImage(bg);
        theme="Blue";
        rootLayout.getStylesheets().add(getClass().getResource(theme+".css").toString());
        rootLayout.getStyleClass().add("anchorpane");
        FileWriter fw=new FileWriter("src/sample/themetext",false);
        BufferedWriter bw=new BufferedWriter(fw);
        PrintWriter pw=new PrintWriter(bw);
        pw.print(theme);
        pw.flush();

    }

    public void changeThemeGreen(ActionEvent event) throws IOException {
        bgimage.getScene().getStylesheets().add(getClass().getResource("Green.css").toString());
        Image bg=new Image(String.valueOf(getClass().getResource("greenandwhite.jpg")));
        bgimage.setImage(bg);
        theme="Green";
        rootLayout.getStylesheets().add(getClass().getResource(theme+".css").toString());
        rootLayout.getStyleClass().add("anchorpane");
        FileWriter fw=new FileWriter("src/sample/themetext",false);
        BufferedWriter bw=new BufferedWriter(fw);
        PrintWriter pw=new PrintWriter(bw);
        pw.print(theme);
        pw.flush();

    }

    public void changeThemePink(ActionEvent event) throws IOException {
        bgimage.getScene().getStylesheets().add(getClass().getResource("Pink.css").toString());
        Image bg=new Image(String.valueOf(getClass().getResource("pinkandwhite.jpg")));
        bgimage.setImage(bg);
        theme="Pink";
        rootLayout.getStylesheets().add(getClass().getResource(theme+".css").toString());
        rootLayout.getStyleClass().add("anchorpane");
        FileWriter fw=new FileWriter("src/sample/themetext",false);
        BufferedWriter bw=new BufferedWriter(fw);
        PrintWriter pw=new PrintWriter(bw);
        pw.print(theme);
        pw.flush();

    }

}
