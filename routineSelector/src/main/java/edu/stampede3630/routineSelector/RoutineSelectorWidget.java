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

/**
 * If you haven't looked at App.Java yet.... Do so.....
 * Congrats! You created a plugin that houses all the cool widgets you are about to make.  This file
 * contains one of those widgets.  First things first, this specific java class extends SIMPLEannotatedWidget,
 * which means that this widget only deals with one data property.  This specific widget only returns the
 * selected deliver location.  That location is, of course, a string.  So in the attributes portion of this 
 * method we return StringType.  You can see all the other different datatypes by right clicking on dataTypes
 * and looking at all the other definitions.
 *
 * This java class only sets up the behavior of the widget.  For THIS specific widget, we care about when the
 * user selects a button, and when the user resizes the widget.
 * 
 * All the specifics of the graphics and the placement of the widget are placed in another attribute called
 * "Parametrized Controller" The value passed into this attribute is the name of the FXML file.  This file
 * just needs to have the same package declared at the top of page.
 */

@Description(dataTypes = { StringType.class }, name = "PathSelector")
@ParametrizedController(value = "RoutineSelectorWidget.fxml")
public class RoutineSelectorWidget extends SimpleAnnotatedWidget<String> {

    /**
     * When we create widgets in shuffleboard we are actually creating PANEs.  These PANES house all the GUI
     * elements that can be interacted with or viewed.  There are several different types of panes.  I chose
     * AnchorPane, I could very well have chosen Pane.  Since the manaipulation of the pane takes place in the
     * FXML file I need a way for this class file and the fxml file to reference the same things.  We do this 
     * by adding the "@FXML" tag right befor defining the PANE.
     */
    
    @FXML
    protected AnchorPane _thePane;
    
     /**
      * Since this is essentiall our "autonomous chooser" I need to know when the toggle group changes,  
      * so I can change the "StringType" ouput whenever the user selects a different radioButton.  So like 
      * the PANE I need to put the "@FXML" descriptor so I can manipulate the Toggle group in the FXML.  
      * I will also create a text field to give some user feedback when each radio button is clicked.
      */
    @FXML
    protected ToggleGroup _theFieldTG;

    @FXML
    protected TextField _selected;
     /**
      * Now the fun stuff.  getView() is a method that is required to be implemented for all 
      *  SimpleAnnotated Widgets. It needs to return the Pane.
      */

    public Pane getView() {
        /**
         * I'm not entirely sure that these listeners need to be in this getView method.... but... oh well,
         * It works, so I ain't gonna fix it.  I googled "JavaFX ToggleGroup Listener" and found the 
         * property that listens for the selectedToggle to change.  After I know which toggle it changed to,
         * I just change my textField _selected = to the ID of the Radiobutton.
         * VS code created the Changed method skeleton for me.
         */
        _theFieldTG.selectedToggleProperty().addListener(new ChangeListener<Toggle>(){
            @Override
            public void changed(ObservableValue<? extends Toggle> ov, Toggle old_toggle, Toggle new_toggle) {
                if (_theFieldTG.getSelectedToggle() != null) {
                    RadioButton selectedRadioButton = (RadioButton) _theFieldTG.getSelectedToggle();
                    _selected.setText(selectedRadioButton.getId());
                }
            }
        });

        /**
         * These next two listeners look for changes when the user resizes the widget.  When the pane gets
         * resized I want to reposition ALL buttons to follow the pane.  If i don't, then the buttons would 
         * align with the background image. I purposely set the initial minimum height and width of the pane 
         * to be 100 by 100 (pixels me thinks).  That way any growth of the pane is a percentage growth. 
         * So I get the percentage growth (multiplier) and iterate through all the children of the pane and
         * reposition.
         */        
        _thePane.heightProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                double multiplier =  newValue.doubleValue()/100;
                for (Node node : _thePane.getChildren()) {
                    //TranslateY(or X) is a method that moves the node (child) over from the original position
                    node.setTranslateY(node.getLayoutY()*multiplier - node.getLayoutY());
                    node.setScaleY(multiplier/2);
                }
            }
        });

        _thePane.widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                double multiplier =  newValue.doubleValue()/100;
                for (Node node : _thePane.getChildren()) {
                    node.setTranslateX(node.getLayoutX()*multiplier - node.getLayoutX());
                    node.setScaleX(multiplier/2);
                }
            }
        });
        
        /**
         * FINALLY, the magic happens here! We changed the "_selected" textfield property, but what sends(or BINDS)
         * this data to a value in shuffleboard?  Its the line below.  After a user toggles the radio button, the
         * _selected.textfield property is changed above.  This change is then BOUND to the widget so NETWORK TABLES
         * can read the device
         */
        this.dataProperty().bind(_selected.textProperty());
        return _thePane;
    }
    /**
     * YOU MADE IT PAST PROBABLY THE HARDEST PART!!!!  Now navigate to: 
     * ShuffleBoardPlugins/routineSelector/src/main/resources/edu/stampede3630/routineSelector/RoutineSelectorWidget.fxml
    **/
} 

