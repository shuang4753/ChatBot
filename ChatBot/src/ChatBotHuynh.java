 import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

/**
 * A program to carry on conversations with a human user.
 * This version:
 * @author Nicholas Huynh
 * @version September 2017
 */
public class ChatBotHuynh
{
	//emotion can alter the way our bot responds. Emotion can become more negative or positive over time.
	
	int feels = 0;
	/**
	 * Get a default greeting 	
	 * @return a greeting
	 */	
	public String getGreeting()
	{
		String greeting= "Hey, it's Nick...So I heard your partner was cheating on you...Whats your name?";
		System.out.println(greeting);
		Scanner Username = new Scanner (System.in);
		String name = Username.nextLine();
		String hiUser = "So, " + name + ", What do you want to talk about?";
		return hiUser;
	}
	
	/**
	 * Gives a response to a user statement
	 * 
	 * @param whatTheySay
	 *            the user statement
	 * @return a response based on the rules given
	 */
	public String getResponse(String whatTheySay)
	{
		String response = "";
		
		if (whatTheySay.length() == 0)
		{
			response = "Baby come back to me";
		}

		else if (findKeyword(whatTheySay, "no") >= 0)
		{
			response = "THEN WHY ARE YOU TALKING TO ME!!!!";
                	feels--;
		}
		else if (findKeyword(whatTheySay,"kill") >= 0)
		{
			response = "That's not a good way to think";
		
		}
		else if (findKeyword(whatTheySay, "die") >= 0)
		{
			
			feels--;
			System.out.println("Remember, cheaters are cowards that are tempted to chase the fantasy of what COULD BE...");
			System.out.println("Instead of courageously addressing their own self-destructive behavior and cultivating WHAT IS");
		}
		else if (findKeyword(whatTheySay,"hide") >= 0)
		{
			response = getRandomQuote();
			feels++;
			
		}
		else if (findKeyword(whatTheySay,"hurt") >= 0)
		{
			response = "how about we take a look at the hurt-o-meter and you tell me how you feel";
			hurtMeter(response);
			feels++;
		}
		// Response transforming I want to statement
		else if (findKeyword(whatTheySay, "I want to", 0) >= 0)
		{
			response = transformIWantToStatement(whatTheySay);
		}
		else if (findKeyword(whatTheySay, "I want",0) >= 0)
		{
			response = transformIWantStatement(whatTheySay);
		}	
		else if (findKeyword(whatTheySay,"gift") >= 0)
		{
			ChatBotNi chatbot3 = new ChatBotNi();
			System.out.print(chatbot3.getGreeting());
			Scanner in = new Scanner(System.in);
			
			String statement = in.nextLine();
			
				while (!statement.equals("Bye"))
				{
					//System.out.println (chatbot3.getResponse(statement));
					System.out.println(chatbot3.getResponse(statement));
					statement = in.nextLine();
				}
			
		}
		
		else
		{
			response = getRandomResponse();
		}
		
		return response;
	}
	//asks user how hurt they are and gives them advice based on number
	public static String hurtMeter(String hurtScale)
	{
		
		System.out.println("from a scale from 1 - 10, how badly are you hurt?");
		Scanner hurtMyFeels = new Scanner (System.in);
		String scale = hurtMyFeels.nextLine();
		
		String response = "";
		if(hurtMyFeels.equals("0"))
		{
			response = "If you aren't hurt, why are you talking to me?";
			
		}
		else if(hurtMyFeels.equals("1"))
		{
			response = "I think you should have a calm conversation with your partner first";
			
		}
		else if(hurtMyFeels.equals("2"))
		{
			response = "You should confront your partner in a professional manner";
			
		}
		else if(hurtMyFeels.equals("3"))
		{
			response = "I personally suggest giving them a good scolding";
			
		}
		else if(hurtMyFeels.equals("4"))
		{
			response = "Confront your partner and listen to some sad songs to deal with your pain"; 
			
		}
		else if(hurtMyFeels.equals("5"))
		{
			response = "I'd suggest you realy sort this ordeal with your partner";
			
		}
		//always toes to the last else statement no matter what input?
		else 
			{
				System.out.println("It's time to look for someone new, they clearly don't deserve you if you are in this much pain");
				String googleURL = "https://www.google.com/search";
				String searchURL = googleURL + "?q=" + " dating websites";
				String searchURLSFinal = searchURL.replaceAll(" ", "%20");
				String finalURL = "Now look for someone else to be with"
									+"I'm single btw)";
			try {
				Process po = Runtime.getRuntime().exec("C:\\Program Files\\Internet Explorer\\iexplore.exe " + searchURLSFinal);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return finalURL;
			}
		return response;
	}
	
	
	
	/**
	 * Take a statement with "I want to <something>." and transform it into 
	 * "Why do you want to <something>?"
	 * @param statement the user statement, assumed to contain "I want to"
	 * @return the transformed statement
	 */
	private String transformIWantToStatement(String statement)
	{
		//  Remove the final period, if there is one
		statement = statement.trim();
		String lastChar = statement.substring(statement
				.length() - 1);
		if (lastChar.equals("."))
		{
			statement = statement.substring(0, statement
					.length() - 1);
		}
		int psn = findKeyword (statement, "I want to", 0);
		String restOfStatement = statement.substring(psn + 9).trim();
		return "Why do you want to " + restOfStatement + "?";
	}

	
	/**
	 * Take a statement with "I want <something>." and transform it into 
	 * "Would you really be happy if you had <something>?"
	 * @param statement the user statement, assumed to contain "I want"
	 * @return the transformed statement
	 */
	private String transformIWantStatement(String statement)
	{
		//  Remove the final period, if there is one
		statement = statement.trim();
		String lastChar = statement.substring(statement
				.length() - 1);
		if (lastChar.equals("."))
		{
			statement = statement.substring(0, statement
					.length() - 1);
		}
		int psn = findKeyword (statement, "I want", 0);
		String restOfStatement = statement.substring(psn + 6).trim();
		return "Would you really be happy if you had " + restOfStatement + "?";
	}
	
	
	/**
	 * Take a statement with "I <something> you" and transform it into 
	 * "Why do you <something> me?"
	 * @param statement the user statement, assumed to contain "I" followed by "you"
	 * @return the transformed statement
	 */
	private String transformIYouStatement(String statement)
	{
		//  Remove the final period, if there is one
		statement = statement.trim();
		String lastChar = statement.substring(statement
				.length() - 1);
		if (lastChar.equals("."))
		{
			statement = statement.substring(0, statement
					.length() - 1);
		}
		
		int psnOfI = findKeyword (statement, "I", 0);
		int psnOfYou = findKeyword (statement, "you", psnOfI);
		
		String restOfStatement = statement.substring(psnOfI + 1, psnOfYou).trim();
		return "Why do you " + restOfStatement + " me?";
	}
	

	
	
	/**
	 * Search for one word in phrase. The search is not case
	 * sensitive. This method will check that the given goal
	 * is not a substring of a longer string (so, for
	 * example, "I know" does not contain "no").
	 *
	 * @param statement
	 *            the string to search
	 * @param goal
	 *            the string to search for
	 * @param startPos
	 *            the character of the string to begin the
	 *            search at
	 * @return the index of the first occurrence of goal in
	 *         statement or -1 if it's not found
	 */
	private int findKeyword(String statement, String goal,
			int startPos)
	{
		String phrase = statement.trim().toLowerCase();
		goal = goal.toLowerCase();

		// The only change to incorporate the startPos is in
		// the line below
		int psn = phrase.indexOf(goal, startPos);

		// Refinement--make sure the goal isn't part of a
		// word
		while (psn >= 0)
		{
			// Find the string of length 1 before and after
			// the word
			String before = " ", after = " ";
			if (psn > 0)
			{
				before = phrase.substring(psn - 1, psn);
			}
			if (psn + goal.length() < phrase.length())
			{
				after = phrase.substring(
						psn + goal.length(),
						psn + goal.length() + 1);
			}

			// If before and after aren't letters, we've
			// found the word
			if (((before.compareTo("a") < 0) || (before
					.compareTo("z") > 0)) // before is not a
											// letter
					&& ((after.compareTo("a") < 0) || (after
							.compareTo("z") > 0)))
			{
				return psn;
			}

			// The last position didn't work, so let's find
			// the next, if there is one.
			psn = phrase.indexOf(goal, psn + 1);

		}

		return -1;
	}
	
	/**
	 * Search for one word in phrase.  The search is not case sensitive.
	 * This method will check that the given goal is not a substring of a longer string
	 * (so, for example, "I know" does not contain "no").  The search begins at the beginning of the string.  
	 * @param statement the string to search
	 * @param goal the string to search for
	 * @return the index of the first occurrence of goal in statement or -1 if it's not found
	 */
	private int findKeyword(String statement, String goal)
	{
		return findKeyword (statement, goal, 0);
	}
	
	private String getRandomQuote()
	{
		Random r = new Random ();
		return randomQuote [r.nextInt(randomQuote.length)];
	}
	
	private String [] randomQuote = {"there are plenty of fish in the sea",
			"Cheating on a good person is like throwing away a diamond and picking up a rock"
	};

	private String googleSearch(String statement) {
		{
			String googleURL = "https://www.google.com/search";
			String searchURL = googleURL + "?q=" + " dating websites";
			String searchURLSFinal = searchURL.replaceAll(" ", "%20");
			String finalURL = "Now look for someone else to be with"
				+"I'm single btw)";
			
		try {
			Process po = Runtime.getRuntime().exec("C:\\Program Files\\Internet Explorer\\iexplore.exe " + searchURLSFinal);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return finalURL;
		}
	}
		
	
	/**
	 * Pick a default response to use if nothing else fits.
	 * @return a non-committal string
	 */
	private String getRandomResponse ()
	{
		Random r = new Random ();
		if (feels == 0)
		{	
			return randomNeutralResponses [r.nextInt(randomNeutralResponses.length)];
		}
		if (feels < 0)
		{	
			return randomSadResponses [r.nextInt(randomSadResponses.length)];
		}	
		return randomHappyResponses [r.nextInt(randomHappyResponses.length)];
	}
	
	private String [] randomNeutralResponses = {"Interesting, tell me more",
			"I don't really know what to say...",
			"Dont blame yourself",
			"You don't say.",
			"What else do you want to talk about?",
			"One lie is enough to question all truths ",
			"Could you say that again?"
	};
	private String [] randomSadResponses = {"Don't feel bad about yourself, they don't deserve you anyways", "OMG", "HARDY HAR HAR"};
	private String [] randomHappyResponses = {"Awww, thats amazing", "Today is a good day", "you're so cute ;)"};
	
}
	