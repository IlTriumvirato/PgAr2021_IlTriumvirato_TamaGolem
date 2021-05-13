package arnaldo.anno2021.triumvirato.tamagolem;

public class TamaGolemMain {
	
	public static void defineDefaultElements() {
		GlobalValues.generateElementsList(Constants.DEFAULT_ELEMENTS_NAMES);
	}
	
	public static void activateMenuScreen() {
		MenuScreen main_menu=new MenuScreen(Constants.GAME_NAME,Constants.MENU_SELECTION_OPTIONS);
		main_menu.loopMenu();
	}
	
	public static void main(String args[]) {
		defineDefaultElements();
		
		activateMenuScreen();
	}
}
