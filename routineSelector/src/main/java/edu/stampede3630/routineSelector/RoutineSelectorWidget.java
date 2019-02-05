package edu.stampede3630.routineSelector;

import edu.wpi.first.shuffleboard.api.data.types.StringType;
import edu.wpi.first.shuffleboard.api.widget.Description;
import edu.wpi.first.shuffleboard.api.widget.ParametrizedController;
import edu.wpi.first.shuffleboard.api.widget.SimpleAnnotatedWidget;
import javafx.scene.Node;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.fxml.FXML;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Toggle;

@Description(dataTypes = { StringType.class }, name = "MyTestWidget")
@ParametrizedController(value = "RoutineSelectorWidget.fxml")
public class RoutineSelectorWidget extends SimpleAnnotatedWidget<String> {
    @FXML
    protected AnchorPane _thePane;
    
    @FXML
    protected ToggleGroup _theFieldTG;

    @FXML
    protected ToggleGroup _theGamePieceTG;

    @FXML
    protected TextField _selected;
    


    public Pane getView() {
        //_selected.setText("No selection ahs been made");
        _theFieldTG.selectedToggleProperty().addListener(new ChangeListener<Toggle>(){
            public void changed(ObservableValue<? extends Toggle> ov, Toggle old_toggle, Toggle new_toggle) {
                if (_theFieldTG.getSelectedToggle() != null) {
                    RadioButton selectedRadioButton = (RadioButton) _theFieldTG.getSelectedToggle();
                    _selected.setText(selectedRadioButton.getId());
                }
            }
        });

        _thePane.heightProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                double multiplier =  newValue.doubleValue()/100;
                for (Node node : _thePane.getChildren()) {
                    node.setTranslateY(node.getLayoutY()*multiplier - node.getLayoutY());
                }
            }
        });

        _thePane.widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                double multiplier =  newValue.doubleValue()/100;
                for (Node node : _thePane.getChildren()) {
                    node.setTranslateX(node.getLayoutX()*multiplier - node.getLayoutX());
                }
            }
        });
        
        this.dataProperty().bind(_selected.textProperty());
        return _thePane;
    }
} 