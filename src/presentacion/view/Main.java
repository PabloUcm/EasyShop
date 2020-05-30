package presentacion.view;

import java.awt.Image;
import java.awt.Toolkit;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;


import javax.swing.JFrame;
import javax.swing.SwingUtilities;


public class Main {
	
	public static void main(String[] args) throws InvocationTargetException, InterruptedException  {
		startGUIMode();
	}
	
	
	private static void startGUIMode() throws InvocationTargetException, InterruptedException {
		
		SwingUtilities.invokeAndWait(new Runnable() {
			@Override
			public void run() {
				
				List<Image> logo = new ArrayList<>();
				Toolkit toolkit = Toolkit.getDefaultToolkit();
				
				MainWindow mainWindow = new MainWindow();
				
				JFrame frame = new JFrame("EasyShop");
				
				frame.setContentPane(mainWindow.getDefaultLayout());
				
				logo.add(toolkit.getImage("icons/logo/16x16.png"));
		        logo.add(toolkit.getImage("icons/logo/32x32.png"));
		        logo.add(toolkit.getImage("icons/logo/64x64.png"));
		        logo.add(toolkit.getImage("icons/logo/128x128.png"));
				frame.setIconImages(logo);
				
				frame.pack();
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			}
		});
	}
}
