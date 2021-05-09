package arnaldo.anno2021.triumvirato.tamagolem;

public class TamaGolemMain {
	
	public static void main(String args[]) {
		MenuScreen main_menu=new MenuScreen(Constants.GAME_NAME,Constants.MENU_SELECTION_OPTIONS);
		main_menu.runMenu();
	}
}
