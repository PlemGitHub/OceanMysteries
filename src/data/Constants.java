package data;

import java.awt.Color;
import java.awt.GraphicsEnvironment;

public interface Constants {
	
//	int WINDOW_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width;
//	int WINDOW_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height;
	int WINDOW_WIDTH = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().width;
	int WINDOW_HEIGHT = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().height;
	
	int ASSET_SIZE = 21;
	int GK = 1;
	
	Color BACKGROUND_COLOR = new Color(94, 129, 162);
	
	int FIRST_DIVER_INDEX = 2;
	
	int Q_CLOUDS = WINDOW_WIDTH/100;
}
