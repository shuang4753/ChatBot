import java.util.Scanner;

public class ChatBotMenu {
	public static void option() 
	{
		System.out.println("Who do you want to talk to?");
		System.out.println("1 - Relationship advice bot");
		System.out.println("2 - Cheating partner");
		System.out.println("3 - exit");
		Scanner scanchoice = new Scanner(System.in);
		System.out.println();
		System.out.println("Enter 1, 2, or 3"");
		int choiceentry = scanchoice.nextInt();

		while (option != 3) {

		    if option < 1 || option > 3) {

		        System.out.println("Enter 1, 2, or 3"");
		        option = scanchoice.nextInt();

		    }

		    else if(option == 1) {
		        
		    	ChatbotHuang chatbot1 = new ChatBotHuang();
		   
		    }
		    else if(option == 2) {

		    	ChatbotHuynh chatbot2 = new ChatBotHuynh();
		    }
		    else if(option == 3) {
		    	ChatbotHuang chatbot1 = new ChatBotHuang();
		    }

		}   
	}
}