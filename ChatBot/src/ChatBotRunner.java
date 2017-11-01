import java.util.Scanner;

/**
 * A simple class to run our chatbot teams.
 * @author Simon Huang, Nicholas Huynh, Kewu Sze, Handy Ni
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
		ChatBotHuang chatbot1 = new ChatBotHuang();
		ChatBotHuynh chatbot2 = new ChatBotHuynh();
		ChatBotNi chatbot3 = new ChatBotNi();
		ChatBotSze chatbot4 = new ChatBotSze();
		
		System.out.println("Who do you want advice from?");
		System.out.println("____________________________");
		System.out.println("1 - The guy with decent advice");
		System.out.println("2 - The guy who talks about your partner cheating with you");
		System.out.println("3 - The guy who tells you what to get for your significant other");
		System.out.println("4 - The guy who gives bad advice");
		System.out.println("pick your guy");
		
		Scanner input = new Scanner(System.in);
		String guy = input.nextLine();
	
		if(guy.equals("1"))
			{
			System.out.print(chatbot1.getGreeting());
			Scanner in = new Scanner(System.in);
			String statement = in.nextLine();
			
				while (!statement.equals("Bye"))
				{
					//System.out.println (chatbot1.getResponse(statement));
					System.out.println(chatbot1.getResponse(statement));
					statement = in.nextLine();
				}
				main(null);
			}
	
		else if(guy.equals("2"))
		{
		System.out.print(chatbot2.getGreeting());
		Scanner in = new Scanner(System.in);
		String statement = in.nextLine();
		
			while (!statement.equals("Bye"))
			{
				//System.out.println (chatbot2.getResponse(statement));
				System.out.println(chatbot2.getResponse(statement));
				statement = in.nextLine();
			}
			main(null);
		}
		
		else if(guy.equals("3"))
		{
		System.out.print(chatbot3.getGreeting());
		Scanner in = new Scanner(System.in);
		String statement = in.nextLine();
		
			while (!statement.equals("Bye"))
			{
				//System.out.println (chatbot3.getResponse(statement));
				System.out.println(chatbot3.getResponse(statement));
				statement = in.nextLine();
			}
			main(null);
		}
	
		else if(guy.equals("4"))
		{
		System.out.print(chatbot4.getGreeting());
		Scanner in = new Scanner(System.in);
		String statement = in.nextLine();
		
			while (!statement.equals("Bye"))
			{
				//System.out.println (chatbot4.getResponse(statement));
				System.out.println(chatbot4.getResponse(statement));
				statement = in.nextLine();
			}
			main(null);
		}
		else
			{
			System.out.println("please enter a valid input");
			//how do we make it re-prompt the user if it comes to this??
			//breaks if the input isn't valid
			}
	}
}
