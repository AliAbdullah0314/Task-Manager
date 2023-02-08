package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;

public class TaskObjectController

{
    @FXML
    BorderPane taskpane;

    @FXML
    Label lblTaskName;

    @FXML
    Button taskindicator;

    @FXML
    Label lblduedate;


    BorderPane view;



    public void setTask(TaskStructure obj, boolean today, int i) throws IOException {

        System.out.println(obj);
        System.out.println(obj.getName()+"--"+obj.getSubject());
        lblTaskName.setId("labeltask"+i);
        (lblTaskName).setText("test");

        if (today)
        {
            lblduedate.setText("Today");
        }
        else
        {
            lblduedate.setText(obj.getDatedue().toString());
        }
    }
    public BorderPane getPage() throws IOException
    {
        URL fileurl = SampleController.class.getResource("TaskObject.fxml");
        new FXMLLoader();
        view = FXMLLoader.load(fileurl);
        return view;
    }
}
