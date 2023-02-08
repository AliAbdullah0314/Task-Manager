package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class TaskEntryController
{
    @FXML
    private Button schoolbutton;

    @FXML
    private Button otherbutton;
    @FXML
    private TextField tasknamefield;

    @FXML
    private ChoiceBox subjectfield;

    @FXML
    private DatePicker datepicker;

    @FXML
    ImageView bgimage;

    @FXML
    private AnchorPane rootLayout;

    @FXML
    private Label title;

    String filepath="src/sample/UpcomingTasks";
    String filepath2="src/sample/SortedUpcomingTasks";

    ArrayList<TaskStructure> tasklist= new ArrayList<>();

    String theme;

    public void initialize() throws IOException {
        subjectfield.getItems().addAll("English","Spanish","French","Arabic","Humanities","Science","Math","Arts","PE","Design","Social Studies","Islamic");
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
        title.getStylesheets().add(getClass().getResource(theme+".css").toString());
        title.getStyleClass().add("titletext");

        String line = "";
        FileReader fr = new FileReader("src/sample/SortedUpcomingTasks");
        BufferedReader br = new BufferedReader(fr);
        while ((line = br.readLine()) != null) {
            String[] values = line.split(",");
            LocalDate datedue = LocalDate.parse(values[0]);
            String task = values[1];
            String subject = values[2];
            LocalDate dateset = LocalDate.parse(values[3]);
            LocalDate datecompleted = LocalDate.parse(values[4]);
            Boolean completed = Boolean.parseBoolean(values[5]);


            tasklist.add(new TaskStructure(datedue, task, subject, dateset, datecompleted, completed));


        }

    }
    public void addnewtask()
    {
        try
        {
            String taskname=tasknamefield.getText();
            LocalDate datedue=datepicker.getValue();
            LocalDate dateset=LocalDate.now();
            LocalDate datecompleted=LocalDate.EPOCH;
            String subject= (String) subjectfield.getValue(); //typecasting to string
            Boolean completed=false;

            System.out.println(datedue+","+taskname+","+subject+","+dateset+","+ datecompleted+","+completed);

            FileWriter fw=new FileWriter(filepath,true);//Data is added by creating a filewriter.
            BufferedWriter bw=new BufferedWriter(fw); // then a buffered writer is created using the filewriter
            PrintWriter pw=new PrintWriter(bw);// then a printwriter is created using the bufferedwriter
            pw.println(datedue+","+taskname+","+subject+","+dateset+","+ datecompleted+","+completed);
            // here the printwriter adds in information from the task screen to the file

            tasklist.add(new TaskStructure(datedue,taskname,subject,dateset, datecompleted,completed));
            tasklist.sort(new DateDueSorter()); // here, all of the upcoming tasks are sorted based on the due date

            File sortedfiledel=new File(filepath2);
            File tempFile = new File(filepath2+".tmp");
            sortedfiledel.delete();
            tempFile.renameTo(sortedfiledel); //sortedupcomingtasks file is made empty


            FileWriter filew=new FileWriter("src/sample/SortedUpcomingTasks",true);
            BufferedWriter buffw=new BufferedWriter(filew);
            PrintWriter printWriter=new PrintWriter(buffw);
            for (int i=0;i<tasklist.size();i++)
            {
                System.out.println("From loop "+i+"  "+tasklist.get(i).getDatedue()
                        +","+tasklist.get(i).getName()+","+tasklist.get(i).getSubject()+","+tasklist.get(i).getDateset()
                        +","+tasklist.get(i).getDatecompleted()+","+tasklist.get(i).getCompleted()); //for debugging
                printWriter.println(tasklist.get(i).getDatedue()
                        +","+tasklist.get(i).getName()+","+tasklist.get(i).getSubject()+","+tasklist.get(i).getDateset()
                        +","+tasklist.get(i).getDatecompleted()+","+tasklist.get(i).getCompleted());
                //tasks are added to the sorted file
            }


            pw.flush();
            printWriter.flush();

            tasknamefield.clear();

        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.out.println(e.getCause());
        }

    }
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
        Parent tasksParent = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Scene tasks=new Scene(tasksParent);
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(tasks);
        window.setFullScreen(true);
        window.show();

    }
}
