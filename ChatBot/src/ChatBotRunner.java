import java.util.Scanner;

/**
 * A simple class to run our chatbot teams.
 * @author Simon Huang, Nicholas Huynh, Kewu Sze
 * @version September 2017
 * Theme: Relationship Advice
 */
public class ChatBotRunner
{

	/**
	 * Create instances of each chatbot, give it user input, and print its replies. Switch chatbot responses based on which chatbot the user is speaking too.
	 */
	public static void main(String[] args)
	{
		ChatBotHuynh chatbot1 = new ChatBotHuynh();
		
		System.out.println (chatbot1.getGreeting());
		Scanner in = new Scanner (System.in);
		String statement = in.nextLine();
		


		while (!statement.equals("Bye"))
		{
			System.out.println (chatbot1.getResponse(statement));
			statement = in.nextLine();
		}
	}

}
