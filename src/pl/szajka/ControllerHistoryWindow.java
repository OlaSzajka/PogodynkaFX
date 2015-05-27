package pl.szajka;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ControllerHistoryWindow extends Stage{

    @FXML
    private Button buttonClose;

    @FXML
    private Label labelMeanTemp;

    @FXML
    private Label labelMinTemp;

    @FXML
    private Label labelMaxTemp;
    
    public ControllerHistoryWindow( Parent parent)
    {
        setTitle("This is a test dialog");

        //this.defaultName = defaultName;
        //this.defaultAddress = defaultAddress;

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("HistoryWindow.fxml"));
        fxmlLoader.setController(this);

        // Nice to have this in a load() method instead of constructor, but this seems to be de-facto standard.
        try
        {
            setScene(new Scene((Parent) fxmlLoader.load()));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
