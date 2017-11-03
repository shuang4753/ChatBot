 import java.util.Random;

/**
 * A program to carry on conversations with a human user.
 * This version:
 * @author Kewu Sze
 * @version September 2017
 **/

public class ChatBotSze
{
	//emotion can alter the way our bot responds. Emotion can become more negative or positive over time.
	int emotion = 0;
	/**
	 * Get a default greeting 	
	 * @return a greeting
	 */	
	public String getGreeting()
	{
		return "Hey buddy, how's your relationship going?";
	}
	
	/**
	 * Gives a response to a user statement
	 * 
	 * @param statement
	 *            the user statement
	 * @return a response based on the rules given
	 */
	public String getResponse(String statement)
	{
		String response = "";
		
		if (statement.length() == 0)
		{
			response = "You know I am a chatbot." +"\nThe whole point of this is to chat";
		}
		else if(findKeyword(statement, "ok") >=0)
		{
			response = "Let's fix that shall we?";
		}
		else if (findKeyword(statement, "cheated") >= 0)
		{
			response = "Do you want revenge on them?";
                	emotion--;
		}
		else if( findKeyword(statement, "kill myself")>=0)
		{
			response = "Suicide is the easy way out. At least get some revenge before you attempt it. Who knows, maybe you won't want to kill your self afterwards.";
			emotion--;
		}
		else if (findKeyword(statement, "great") >= 0)
		{
			response = "That's nice I guess.";
			emotion++;
		}
		else if (findKeyword(statement, "well") >= 0)
		{
			response = "It's just going well? Anything we can do to change that?";
			emotion++;
		}
		else if (findKeyword(statement, "good") >= 0)
		{
			response = "That's nice I guess.";
			emotion++;
		}
		else if (emotion >= 3)
		{
			System.out.println("It sounds like your relationship is going great. Unless you want to break up, I'd suggesst talking to another chatbot.");
		}

		else if (findKeyword(statement, "I want to", 0) >= 0)
		{
			response = transformIWantToStatement(statement);
		}
		else if (findKeyword(statement, "I want",0) >= 0)
		{
			response = transformIWantStatement(statement);
		}	
		else
		{
			response = getRandomResponse();
		}
		
		return response;
	}
	// fight/argument response
	public String respondTofights(String statement)
	{
		String response = "";
		
		if(findKeyword(statement, "no fights") >=0)
		{
			response = "Nothing at all?";
		}
		
		else if (findKeyword(statement, "yes fight") >=0)
		{
			response = "What were you fighting about??";
		}
		else if(findKeyword(statement, "nothing")>=0)
		{
			response = "Hmmmm";
			emotion++;
		}
		return response;
	}
	public String discoverRevenge(String statement)
	{
		String response = "";
		if(findKeyword(statement, "revenge") >=0);
		{
			response = "So you want revenge? Tell me what they did to you.";
			emotion--;
		}
		return response;
	}
	public String intiateRevenge(String statement)
	{
		String response = "";
		if(findKeyword(statement, "cheated with on me with my friend") >=0);
		{
			response = "Have you told your friend? they might join in on this as well.";
			emotion--;
		}
		return response;
	}
	public String sassBot(String statement)
	{
		String response = "";
		if (findKeyword(statement, "bad advice") >=0)
		{
			response = "Now who would've thought that I gave bad advice?" + 
		"\nMaybe because the menu tipped you off and said I was the guy who gives bad advice??" + 
		"\nWhat a snitch";
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
		return "Will that make you happier if" + restOfStatement + "?";
	}
	
	
	/**
	 * Take a statement with "I <something> you" and transform it into 
	 * "Why do you <something> me?"
	 * @param statement the user statement, assumed to contain "I" followed by "you"
	 * @return the transformed statement
	 */
	@SuppressWarnings("unused")
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
	


	/**
	 * Pick a default response to use if nothing else fits.
	 * @return a non-committal string
	 */
	private String getRandomResponse ()
	{
		Random r = new Random ();
		if (emotion == 0)
		{	
			return randomNeutralResponses [r.nextInt(randomNeutralResponses.length)];
		}
		if (emotion < 0)
		{	
			return randomAngryResponses [r.nextInt(randomAngryResponses.length)];
		}	
		return randomHappyResponses [r.nextInt(randomHappyResponses.length)];
	}
	
	private String [] randomNeutralResponses = {"Is that so?",
			"That's nice",
			"What's that going to do?",
			"Have you had a fight recently?",
			"Do you have any plans with them?",
			"What happend the last time you saw each other?",
			"I'm sorry, could you say that again?"
	};
	private String [] randomAngryResponses = {
			"Why not just dump them?", 
			"You should release revealing picture of them they sent you to the internet.", 
			"You should smash the windows of their car. That'll prove a point.", 
			"Do whatever you can to get back at them.",
			"Throw some pie at them.",
			"Expose them on the internet and let the internet do the rest."};
	private String [] randomHappyResponses = {"If you really love them, you should let them go", "Don't let them get away.", "You know it's very likely that you two will break up statisticallly anyway.", "If you really think they're the one, put a ring on it."};
	
}
