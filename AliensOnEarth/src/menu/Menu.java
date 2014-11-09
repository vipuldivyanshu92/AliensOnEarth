package menu;

import java.util.ArrayList;
import java.util.Scanner;
import javaConsole.JavaConsole;


public class Menu {

	private JavaConsole console;
	
	/**
	 * @brief Class Constructor
	 * @param c an instance of the JavaConsole UI
	 */
	public Menu(JavaConsole c) {
		console = c;
	}

	/**
	 * @brief A menu item object.
	 *
	 * @date 02-07-2012
	 * 
	 * @bug
	 */
	private class MenuItem {

		private MenuCallback _mc;
		private String _text;

		/**
		 * @brief Class Constructor
		 * @param text The text to display
		 * @param mc an MenuCallback object
		 */
		public MenuItem(String text, MenuCallback mc) {
			_mc = mc;
			_text = text;
		}

		/**
		 * @return the MenuCallback object
		 */
		public MenuCallback get_mc() {
			return _mc;
		}

		/**
		 * @return the display text
		 */
		public String get_text() {
			return _text;
		}

	}

	private ArrayList<MenuItem> Items =
		new ArrayList<MenuItem>();
	
	/**
	 * @param text The text to display
	 * @param mc an MenuCallback object
	 * @return boolean true if successful.
	 */
	public boolean add(String text, MenuCallback mc) {
		return Items.add(new MenuItem(text, mc));
	}

	/**
	 * @brief Display the list of menu item choices
	 */
	public void show() {
		int choosen = 0;
		Scanner in = new Scanner(System.in);

		for (int i = 0; i < Items.size(); ++i) {
			MenuItem mi = Items.get(i);
			System.out.printf(" [%d] %s \n", i + 1, mi.get_text());
		}

		System.out.println(); // add a line

		try {
			choosen = in.nextInt();
		} catch (Exception e1) { /* Ignore non numeric and mixed */ }

		console.clear();

		if (choosen > Items.size() || choosen < 1) {
			System.out.println("Invalid option.\nPress enter to continue...");
			in.nextLine();
			in.nextLine();
		} else {
			MenuItem mi = Items.get(choosen - 1);
			MenuCallback mc = mi.get_mc();
			mc.Invoke();
		}
	}
}
