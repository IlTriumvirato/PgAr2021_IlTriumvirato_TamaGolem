package it.unibs.fp.mylib;
/*
Questa classe rappresenta un menu testuale generico a piu' voci
Si suppone che la voce per uscire sia sempre associata alla scelta 0 
e sia presentata in fondo al menu

*/
public class MyMenu
{
  final private static int EXIT_NUMBER=0;
  final private static String FRAME = "--------------------------------";
  final private static String EXIT_OPTION_LINE = EXIT_NUMBER+"\tEsci";
  final private static String INPUT_REQUEST = "Digita il numero dell'opzione desiderata > ";
  final private static String DEFAULT_EXIT_MESSAGGE="chiusura effettuata";
  private String title;
  private String [] options;
  protected String exitMessage;
	
  public MyMenu (String title, String [] options)
  {
	this.title = title;
	this.options = options;
	exitMessage=DEFAULT_EXIT_MESSAGGE;
  }
  
  public MyMenu (String title, String [] options, String exitMessage)
  {
	this.title = title;
	this.options = options;
	this.exitMessage=exitMessage;
  }
  

  public int choose ()
  {
	printMenu();
	return InputDati.leggiIntero(INPUT_REQUEST, 0, options.length);	 
  }

  public void printMenu ()
  {
	System.out.println(FRAME);
	System.out.println(title);
	System.out.println(FRAME);
    for (int i=0; i<options.length; i++)
	 {
	  System.out.println( (i+1) + "\t" + options[i]);
	 }
    System.out.println();
	System.out.println(EXIT_OPTION_LINE);
    System.out.println();
  }
  
  public void loopMenu() {
		int scelta;
		do {
			scelta=choose();
			runSelection(scelta);
			
		}while(scelta!=EXIT_NUMBER);
  }
		
  /** This method will be overridden
   * 
   */
  public void runSelection(int selection) {
		switch(selection) {
			case EXIT_NUMBER:
				System.out.println(exitMessage);
				break;
			default:
				//undefined
			break;
		
		}
		
  }
}

