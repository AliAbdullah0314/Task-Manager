package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SampleOthController  {
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
    Button newtaskbutton;

    @FXML
    private AnchorPane rootLayout;

    @FXML
    private VBox todaybox;

    @FXML
    private VBox upcombox;

    @FXML
    private BorderPane innerborderpane;

    private String theme;



    ArrayList<TaskStructure> tasklistoth = new ArrayList<>();

    //private String theme="";

    /*public void setTheme(String thm)
    {
        theme=thm;
        System.out.println("theme has been set to "+theme);

    }*/

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
        System.out.println("After pressing button "+ theme);
        todaybox.getStylesheets().add(getClass().getResource(theme+".css").toString());
        upcombox.getStylesheets().add(getClass().getResource(theme+".css").toString());
        rootLayout.getStylesheets().add(getClass().getResource(theme+".css").toString());
        innerborderpane.getStylesheets().add(getClass().getResource(theme+".css").toString());
        String line = "";
        FileReader fr = new FileReader("src/sample/SortedUpcomingTasksOth");
        BufferedReader br = new BufferedReader(fr); //filereader used to create new bufferedreader
        while ((line = br.readLine()) != null) {
            String[] values = line.split(",");  //while line contains some text, it will the line wherever there
                                                      //are commas and loads each split value into an array as elements
            LocalDate datedue = LocalDate.parse(values[0]);
            String task = values[1];
            String subject = values[2];
            LocalDate dateset = LocalDate.parse(values[3]);
            LocalDate datecompleted = LocalDate.parse(values[4]);
            Boolean completed = Boolean.parseBoolean(values[5]);
            tasklistoth.add(new TaskStructure(datedue, task, subject, dateset, datecompleted, completed));
            //variables will be initialised with the values in the elements of the array and new element will be added to the arraylist tasklist

        }

        int size = tasklistoth.size();

        try { //load task items to vbox
            Node[] nodes = new Node[size];
            for (int i = 0; i < nodes.length ; i++) {
                //load specific item
                /*FXMLLoader loader = new FXMLLoader(getClass().getResource("TaskObject.fxml"));
                TaskObjectController controller = new TaskObjectController();
                loader.setController(controller);
                nodes[i] = FXMLLoader.load(getClass().getResource("TaskObject.fxml"));
                System.out.println(nodes[i]);
                completedbox.getChildren().add(nodes[i]);*/
//LOOK AT THE NODES
                //TaskObjectPane newPane = new TaskObjectPane();
                //newPane.setId("task"+i); // Don't know why setting the CSS id is necessary here
                //newPane.setOnAction(this::addNewPane); // set onAction property
                //completedbox.getChildren().addAll(newPane);
                if (!tasklistoth.get(i).completed && ((tasklistoth.get(i).getDatedue().compareTo(LocalDate.now()))<=0)) {
                    // lblTaskName.setId("labeltask"+i);
                    //lblTaskName.setText(tasklistoth.get(i).getName());
                    //controller.setTask(tasklistoth.get(i), true);
                    additem(tasklistoth.get(i).getName(), tasklistoth.get(i).getDatedue(),tasklistoth.get(i).getSubject(),todaybox);
                }
                if (!tasklistoth.get(i).completed && ((tasklistoth.get(i).getDatedue().compareTo(LocalDate.now()))>0)) {
                    //lblTaskName.setId("labeltask"+i);
                    //(lblTaskName).setText(tasklistoth.get(i).getName());
                    //controller.setTask(tasklistoth.get(i), false,i);
                    additem(tasklistoth.get(i).getName(), tasklistoth.get(i).getDatedue(),tasklistoth.get(i).getSubject(),upcombox);
                }
            }
        } catch (Exception e) {
            System.out.println("something went wrong");
            e.printStackTrace();


        }


    }

    public void changescreennewTask(ActionEvent event) throws IOException {
        Parent tasksParent = FXMLLoader.load(getClass().getResource("TaskEntryOth.fxml"));
        Scene tasks = new Scene(tasksParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(tasks);
        window.setFullScreen(true);
        window.show();


    }

    public void additem(String name, LocalDate datedue, String subject, VBox taskbox)
    {
        BorderPane bpane= new BorderPane();
        bpane.setMaxHeight(100);
        bpane.setMinHeight(100);
        bpane.setMaxWidth(1342);
        bpane.setMinWidth(1342);
        //bpane.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
        bpane.getStylesheets().add("src/sample/"+theme+".css");
        bpane.getStyleClass().add("taskbpane");
        //bpane.setStyle("-fx-background-radius: 50; -fx-border-radius: 50;-fx-background-color: #FBFCFC;");
        bpane.setPadding(new Insets(0,10,0,10));

        HBox objbox=new HBox();
        objbox.setMaxHeight(100);
        objbox.setMinHeight(100);
        objbox.setMaxWidth(1330);
        objbox.setMinWidth(1330);

        Label lbltaskname=new Label();
        lbltaskname.setMaxHeight(60);
        lbltaskname.setMinHeight(60);
        lbltaskname.setMaxWidth(1000);
        lbltaskname.setMinWidth(1000);
        lbltaskname.setFont(Font.font("Gill Sans", 40));
        lbltaskname.setText(name+"   ---   "+subject);
        lbltaskname.setAlignment(Pos.CENTER_LEFT);
        lbltaskname.setTranslateX(30);
        System.out.println(name+"   ---   "+subject);
        lbltaskname.getStylesheets().add(theme+".css");
        lbltaskname.getStyleClass().add("lbltaskname");

        Label lblduedate=new Label();
        lblduedate.setMaxHeight(60);
        lblduedate.setMinHeight(60);
        lblduedate.setMaxWidth(300);
        lblduedate.setMinWidth(300);
        lblduedate.setFont(Font.font("Gill Sans", 30));
        //lblduedate.setStyle("-fx-border-color: grey; -fx-background-radius: 200; -fx-border-radius: 200;");
        lblduedate.getStylesheets().add(theme+".css");
        lblduedate.getStyleClass().add("lblduedate");
        if (LocalDate.now().compareTo(datedue)==0)
        {
            lblduedate.setText("Due Today");
        }
        else
        {
            lblduedate.setText("Due in "+ ChronoUnit.DAYS.between(LocalDate.now(), datedue)+" days");
        }
        lblduedate.setAlignment(Pos.CENTER);
        lblduedate.setTranslateX(-20);
        System.out.println(datedue);

        Button taskindicator=new Button();
        taskindicator.setAlignment(Pos.CENTER);
        taskindicator.setMaxHeight(60);
        taskindicator.setMinHeight(60);
        taskindicator.setMaxWidth(60);
        taskindicator.setMinWidth(60);
        taskindicator.setStyle("-fx-background-radius: 100; -fx-padding: 0; -fx-background-color: #FBFCFC #FBFCFC; -fx-border-color: grey; -fx-border-radius: 100;");
        taskindicator.setTranslateX(20);
        taskindicator.setOnAction(event -> {removeTask("src/sample/UpcomingTasksOth",tasklistoth.get(findtask(name)).getDatedue()
                +","+tasklistoth.get(findtask(name)).getName()+","+tasklistoth.get(findtask(name)).getSubject()+","+tasklistoth.get(findtask(name)).getDateset()
                +","+tasklistoth.get(findtask(name)).getDatecompleted()+","+tasklistoth.get(findtask(name)).getCompleted());
            removeTask("src/sample/SortedUpcomingTasksOth",tasklistoth.get(findtask(name)).getDatedue()
                    +","+tasklistoth.get(findtask(name)).getName()+","+tasklistoth.get(findtask(name)).getSubject()+","+tasklistoth.get(findtask(name)).getDateset()
                    +","+tasklistoth.get(findtask(name)).getDatecompleted()+","+tasklistoth.get(findtask(name)).getCompleted());
            addtasktocompleted(tasklistoth.get(findtask(name)).getDatedue()
                    ,tasklistoth.get(findtask(name)).getName(),tasklistoth.get(findtask(name)).getSubject(),tasklistoth.get(findtask(name)).getDateset()
                    ,tasklistoth.get(findtask(name)).getDatecompleted(),tasklistoth.get(findtask(name)).getCompleted());
            taskbox.getChildren().remove(bpane);

        });

        objbox.getChildren().addAll(taskindicator,lbltaskname,lblduedate);
        bpane.setCenter(objbox);
        objbox.setAlignment(Pos.CENTER);
        //bpane.getChildren().addAll(taskindicator,lbltaskname,lblduedate);
        taskbox.getChildren().add(bpane);
    }

    public static void removeTask(String filepath, String tasktormv) {
        System.out.println("remove method was called"); //for debugging
        try {

            File inFile = new File(filepath); //creates a new File object using the parameter ‘filepath’.

            if (!inFile.isFile()) { //for debugging
                System.out.println("Parameter is not an existing file");
                return;
            }

            //creates a new file that will afterwards be renamed to the original filename from the string 'filepath'.
            File tempFile = new File(inFile.getAbsolutePath() + ".tmp");

            BufferedReader br = new BufferedReader(new FileReader(filepath));
            PrintWriter pw = new PrintWriter(new FileWriter(tempFile));
            //a new buffered reader object is created which will read from the original file.
            //a new printwriter object is created which will print text to the temporary file.
            String line;

            while ((line = br.readLine()) != null) {

                if (!line.trim().equals(tasktormv)) {

                    pw.println(line);
                    pw.flush();
                }
            }
            //the program will first check if the next line is not null, and then it will print everything except the
            // information for the task to be removed to the temporary file.
            pw.close();
            br.close();

            inFile.delete();//original file is deleted hence deleting data which was not transferred to the temp file.
            tempFile.renameTo(inFile);// temp file is renamed to original file which contains everything except the data meant to be deleted.


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addtasktocompleted(LocalDate datedue, String taskname, String subject, LocalDate dateset, LocalDate datecompleted,  Boolean completed)
    {
        try
        {
            /*String taskname=l.getText();
            LocalDate datedue=datepicker.getValue();
            LocalDate dateset=LocalDate.now();*/
            datecompleted=LocalDate.now();
            //String subject= (String) subjectfield.getValue(); //typecasting to string
            completed=true;

            System.out.println(datedue+","+taskname+","+subject+","+dateset+","+ datecompleted+","+completed);

            /*File mFile = new File("/Users/aliabdullah/Desktop/FXML-Example/src/sample/CompletedTasks");
            FileInputStream fis = new FileInputStream(mFile);
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));
            String result = "";
            String line = "";
            while( (line = br.readLine()) != null){
                result = result +line;
            }

            result = datedue+","+taskname+","+subject+","+dateset+","+ datecompleted+","+completed + result;

            mFile.delete();
            FileOutputStream fos = new FileOutputStream(mFile);
            fos.write(result.getBytes());
            fos.flush();
            */
            File actfile=new File("src/sample/CompletedTasks");
            File tempfle=new File("src/sample/CompletedTasks.tmp");
            String line = "";
            FileReader fr = new FileReader(actfile);
            BufferedReader br = new BufferedReader(fr);
            FileWriter fw=new FileWriter(tempfle,true);
            BufferedWriter bw=new BufferedWriter(fw);
            PrintWriter pw=new PrintWriter(bw);
            pw.println(datedue+","+taskname+","+subject+","+dateset+","+ datecompleted+","+completed);
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                pw.println(LocalDate.parse(values[0])+","+values[1]+","+values[2]+","+LocalDate.parse(values[3])+","+LocalDate.parse(values[4])+","+Boolean.parseBoolean(values[5]));

            }
            actfile.delete();
            tempfle.renameTo(actfile);
            tasklistoth.remove(findtask(taskname));
            pw.flush();

            /*FileWriter fw=new FileWriter("/Users/aliabdullah/Desktop/FXML-Example/src/sample/CompletedTasks",true);
            BufferedWriter bw=new BufferedWriter(fw);
            PrintWriter pw=new PrintWriter(bw);
            pw.println(datedue+","+taskname+","+subject+","+dateset+","+ datecompleted+","+completed);

            tasklistoth.remove(findtask(taskname));

            pw.flush();*/



        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.out.println(e.getCause());
        }


    }

    public int findtask(String taskname)
    {
        boolean found=false;
        int i;

        for(i=0; i<tasklistoth.size();i++)
        {
            if(taskname.equals(tasklistoth.get(i).getName()))
            {
                found=true;
                break;

            }


        }
        if (found)
        {
            return i;
        }
        else
        {
            return -1;

        }
    }


}


    /*@Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String line="";
        FileReader fr= null;
        try {
            fr = new FileReader("/Users/aliabdullah/Desktop/FXML-Example/src/sample/UpcomingTasks");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedReader br=new BufferedReader(fr);
        while (true)
        {
            try {
                if (!((line= br.readLine())!=null)) break;
            } catch (IOException e) {
                e.printStackTrace();
            }
            String[] values= line.split(",");
            LocalDate datedue=LocalDate.parse(values[0]);
            String task=values[1];
            String subject=values[2];
            LocalDate dateset=LocalDate.parse(values[3]);
            LocalDate datecompleted=LocalDate.parse(values[4]);
            Boolean completed=Boolean.parseBoolean(values[5]);


            tasklistoth.add(new TaskStructure(datedue,task,subject,dateset,datecompleted,completed));





        }

        int size=tasklistoth.size();

        try
        { //load task items to vbox
            Node[] nodes = new Node[size];
            for (int i = 0; i < nodes.length-1; i++) {
                //load specific item
                FXMLLoader loader = new FXMLLoader(getClass().getResource("TaskObject.fxml"));
                TaskObjectController controller = new TaskObjectController();
                loader.setController(controller);
                nodes[i] = FXMLLoader.load(getClass().getResource("TaskObject.fxml"));
                System.out.println(nodes[i]);
                completedbox.getChildren().add(nodes[i]);
                if(!tasklistoth.get(i).completed && (tasklistoth.get(i).getDatedue().equals(LocalDate.now())))
                {
                    controller.setTask(tasklistoth.get(i), true);
                }
                if (!tasklistoth.get(i).completed && (!tasklistoth.get(i).getDatedue().equals(LocalDate.now())))
                {
                    controller.setTask(tasklistoth.get(i), false);
                }
            }
        }
        catch(Exception e)
        {
            System.out.println("something went wrong");
            e.printStackTrace();


        }

    }
}

     */
