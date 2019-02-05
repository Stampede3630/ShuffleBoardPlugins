package edu.stampede3630.routineSelector;

import java.util.List;

import com.google.common.collect.ImmutableList;

import edu.wpi.first.shuffleboard.api.plugin.Description;
import edu.wpi.first.shuffleboard.api.plugin.Plugin;
import edu.wpi.first.shuffleboard.api.widget.ComponentType;
import edu.wpi.first.shuffleboard.api.widget.WidgetType;;

/**
 * Hello world!
 *
 */
@Description(group = "edu.stampede3630", name = "routineSelector", summary = "My first try at this crap", version = "1.0.0")
public class App extends Plugin {
   @Override
   public List<ComponentType> getComponents() {
       return ImmutableList.of(WidgetType.forAnnotatedWidget(RoutineSelectorWidget.class) );
   }

}
