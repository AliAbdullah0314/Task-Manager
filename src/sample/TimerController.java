package sample;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.io.*;
import java.util.Timer;
import java.util.TimerTask;

public class TimerController
{
    @FXML
    private AnchorPane timerRootlayout;

    @FXML
    private AnchorPane timerLayout;

    @FXML
    private BorderPane timerbpane;

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
    private Label colonlabel;

    @FXML
    private HBox secfieldbox;

    @FXML
    private TextField secTfield;

    @FXML
    private TextField secUfield;

    @FXML
    private HBox minfieldbox;

    @FXML
    private TextField minTfield;

    @FXML
    private TextField minUfield;

    @FXML
    private HBox secbox;

    @FXML
    private Label secTlabel;

    @FXML
    private Label secUlabel;

    @FXML
    private HBox minbox;

    @FXML
    private Label minTlabel;

    @FXML
    private Label minUlabel;

    @FXML
    private Button playbutton;

    @FXML
    private Button pausebutton;

    @FXML
    private Button stopbutton;

    @FXML
    private Circle circletimer;

    //int x= 5;//(Integer.parseInt(minTfield.getText()))*10+(Integer.parseInt(minUfield.getText()))*60+((Integer.parseInt(secTfield.getText()))*10+(Integer.parseInt(secUfield.getText())));
    int minutes;
    int seconds;

    String theme;
    int x;
    int y;

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

   /* public void initialize()
    {
        pausebutton.setVisible(false);
        stopbutton.setVisible(false);
    }*/

   public void initialize() throws IOException {
       String line1 = "";
       FileReader filer = new FileReader("src/sample/themetext");
       BufferedReader buffr = new BufferedReader(filer);
       while ((line1 = buffr.readLine()) != null) {
           String[] values = line1.split(",");
           theme=values[0];
       }
       timerRootlayout.getStylesheets().add(getClass().getResource(theme+".css").toString());
       timerLayout.getStylesheets().add(getClass().getResource(theme+".css").toString());
       timerbpane.getStylesheets().add(getClass().getResource(theme+".css").toString());
       timerbpane.getStyleClass().add("timerbpane");

       secUlabel.getStylesheets().add(getClass().getResource(theme+".css").toString());
       secUlabel.getStyleClass().add("timerlabels");
       secTlabel.getStylesheets().add(getClass().getResource(theme+".css").toString());
       secTlabel.getStyleClass().add("timerlabels");
       minUlabel.getStylesheets().add(getClass().getResource(theme+".css").toString());
       minUlabel.getStyleClass().add("timerlabels");
       minTlabel.getStylesheets().add(getClass().getResource(theme+".css").toString());
       minTlabel.getStyleClass().add("timerlabels");
       colonlabel.getStylesheets().add(getClass().getResource(theme+".css").toString());
       colonlabel.getStyleClass().add("timerlabels");



   }

    public void starttimer()
    {
        try {
            Integer.parseInt(minTfield.getText());
            Integer.parseInt(minUfield.getText());
            Integer.parseInt(secTfield.getText());
            Integer.parseInt(secUfield.getText());


        }
        catch (Exception exc)
        {
            errormessage();
        }

        if (0>(Integer.parseInt(minTfield.getText()))||(Integer.parseInt(minTfield.getText()))>9||0>(Integer.parseInt(minUfield.getText()))||(Integer.parseInt(minUfield.getText()))>9||0>(Integer.parseInt(secTfield.getText()))||(Integer.parseInt(secTfield.getText()))>9||0>(Integer.parseInt(secUfield.getText()))||(Integer.parseInt(secUfield.getText()))>9)
        {
            errormessage();
        }
        if (minfieldbox.isVisible())
        {
            x=(Integer.parseInt(minTfield.getText()))*600+(Integer.parseInt(minUfield.getText()))*60+((Integer.parseInt(secTfield.getText()))*10+(Integer.parseInt(secUfield.getText())));

        }
        else
        {
            x=y;
        }

        Timer myRepeatingTimer = new Timer();
        myRepeatingTimer.scheduleAtFixedRate(new TimerTask()
        {


            @Override
            public void run()
            {

                minfieldbox.setVisible(false);
                secfieldbox.setVisible(false);
                minbox.setVisible(true);
                secbox.setVisible(true);
                playbutton.setVisible(false);
                pausebutton.setVisible(true);
                stopbutton.setVisible(true);
                if (x==-1)
                {
                    System.out.println("Timer has finished");
                    TimerController obj = new TimerController();
                    obj.playsound("src/sample/1_short_Open_16_16.wav");
                    x=0;
                    minfieldbox.setVisible(true);
                    secfieldbox.setVisible(true);
                    minbox.setVisible(false);
                    secbox.setVisible(false);
                    /*String min = minutes + "";
                    Platform.runLater(() ->minutelabel.setText(min));
                    String sec = String.valueOf(seconds);
                    Platform.runLater(() ->secondlab.setText(sec));*/
                    playbutton.setVisible(true);
                    pausebutton.setVisible(false);
                    stopbutton.setVisible(false);
                    myRepeatingTimer.cancel();
                }

                pausebutton.setOnAction(event -> {
                    pausebutton.setVisible(false);
                    stopbutton.setVisible(false);
                    playbutton.setVisible(true);
                    y=x;
                    myRepeatingTimer.cancel();
                    });
                stopbutton.setOnAction(event -> {
                    pausebutton.setVisible(false);
                    stopbutton.setVisible(false);
                    playbutton.setVisible(true);
                    minfieldbox.setVisible(true);
                    secfieldbox.setVisible(true);
                    minbox.setVisible(false);
                    secbox.setVisible(false);
                    myRepeatingTimer.cancel();
                });
                minutes=x/60;
                int minT= minutes/10;
                int minU= minutes%10;
                seconds= x-(minutes*60);
                int secT= seconds/10;
                int secU=seconds%10;
                System.out.println("MIn t "+minT);
                System.out.println("MIn u "+minU);
                System.out.println("Sec t "+secT);
                System.out.println("Sec u "+secU);
                System.out.println();

                String minTval = minT + "";
                Platform.runLater(() ->minTlabel.setText(minTval));
                String minUval = minU + "";
                Platform.runLater(() ->minUlabel.setText(minUval));

                String secTval = secT + "";
                Platform.runLater(() ->secTlabel.setText(secTval));
                String secUval = secU + "";
                Platform.runLater(() ->secUlabel.setText(secUval));
                x--;
            }
        }, 0, 1000);
        /*Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), ev ->
        {
            playbutton.setVisible(false);
            minutes = x / 60;
            seconds = x - (minutes * 60);

            String min = minutes + "";
            minutelabel.setText(min);

            String sec = String.valueOf(seconds);
            secondlab.setText(sec);
            x--;
            if (x == 0)
            {
                System.out.println("Timer has finished");
                TimerController obj = new TimerController();
                try {
                    obj.playsound("/Users/aliabdullah/Desktop/FXML-Example/src/sample/1_short_Open_16_16.wav");
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
                timeline.stop();



            }
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play(); */
    }


    public void playsound(String sndloc)
    {
        try
        {
            File sndpath=new File(sndloc);
            if (sndpath.exists())
            {
                AudioInputStream audioInput= AudioSystem.getAudioInputStream(sndpath);
                Clip clip=AudioSystem.getClip();
                clip.open(audioInput);
                clip.start();
            }
            else
            {
                System.out.println("File doesn't exist");

            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    public void errormessage()
    {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Invalid Input");
        window.setMinWidth(250);

        Label label = new Label();
        label.setText("Please only put numbers 0-9 in each of the boxes");
        Button closeButton = new Button("Okay");
        closeButton.setOnAction(e -> window.close());

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, closeButton);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }


}
