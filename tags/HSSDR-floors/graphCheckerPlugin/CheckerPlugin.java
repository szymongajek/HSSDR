package graphCheckerPlugin;

import java.lang.reflect.Method;
import java.util.LinkedList;

import hyperGraphs.GraphMessage;
import hyperGraphs.HLH;

public class CheckerPlugin
{
	static String plugins[] = {
		"graphCheckerPlugin.AccessibilityChecker",
		"graphCheckerPlugin.PositoningChecker",
		"graphCheckerPlugin.MovingRobotChecker"
	};

	public static GraphMessage[] checkStructure(HLH graph)
	{
		Class[] argTypes = new Class[] { graph.getClass() };
		Object[] args = new Object[] { graph };
		LinkedList<GraphMessage> results = new LinkedList<GraphMessage>();
		for (int i = 0; i < plugins.length; ++i) {
			try {
				Class<?> plugin = Class.forName(plugins[i]);
				Method m = plugin.getMethod("checkStructure", argTypes);
				GraphMessage[] arr = (GraphMessage[]) m.invoke(null, args);
				for (int j = 0; j < arr.length; j++) {
					results.add(arr[j]);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return results.toArray(new GraphMessage[0]);
	}
}
