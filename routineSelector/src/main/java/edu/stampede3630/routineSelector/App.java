package edu.stampede3630.routineSelector;

import java.util.List;

import com.google.common.collect.ImmutableList;

import edu.wpi.first.shuffleboard.api.plugin.Description;
import edu.wpi.first.shuffleboard.api.plugin.Plugin;
import edu.wpi.first.shuffleboard.api.widget.ComponentType;
import edu.wpi.first.shuffleboard.api.widget.WidgetType;

/**
 * This is the plugin file (notice that it extends Plugin from the shuffleboard API).
 * The group, name, summary, version are arbitrary, but try to keep the group name for all stampede plugins.
 * In hindsight, I would have named the "name" DeepSpacePlugins or something to that effect.
 */
@Description(group = "edu.stampede3630", name = "routineSelector", summary = "My first try at this crap", version = "1.0.0")
public class App extends Plugin {
   //this will tell shuffleboard, hey these are all the neat widgets I created
   //The widgets should be stored in the same folder as this plugin.  This formula should be formatted thusly:
   // return ImmutableList.of(WidgetType.forAnnotatedWidget(javaWidget.class), WidgetType.forAnnotatedWidget(NEXTJavaWidget.class));
   @Override
   public List<ComponentType> getComponents() {
       return ImmutableList.of(WidgetType.forAnnotatedWidget(RoutineSelectorWidget.class) );
   }

}
