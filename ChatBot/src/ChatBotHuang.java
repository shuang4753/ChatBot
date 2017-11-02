 import java.util.Random;
 import java.util.Scanner;
//aaa
/**
 * A program to carry on conversations with a human user.
 * This version:
 * @author Simon Huang
 * @version September 2017
 * Topic: Good Relationship Advice
 */
public class ChatBotHuang
{
	public String status;
	public String name;
	public String health;
	//as user's emotion goes down, bot becomes more uplifting.
	int emotion = 0;
	int breakupvars = 0;
	/**
	 * Get a default greeting 	
	 * @return a greeting
	 */	
	public String getGreeting()
	{	
		String greeting= "Hi, my name is Simon! I give positive relationship advice. What is your name?"+ 
				"\nAfter your name, please answer in COMPLETE SENTENCES and GRAMMAR. Thx :)"
				+ "\nIf stuck just press ENTER";
		System.out.println(greeting);
		Scanner in = new Scanner (System.in);
		name= in.nextLine();
		String hiUser = "Hi " + name + " \nWhat do you want to talk about first? "
				+ "\nLet's start talking about either breakups or how to keep a relationship healthy?"
				+ "\nIf stuck just press ENTER\n";
		return hiUser;
		
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
			response = "\nWow! No problems heh? You must have things on LOCKDOWN."+ "\nAre you sure though? " + getRandomResponse();
			emotion++; }
		
		else if (findKeyword(statement, "relationship is healthy") >= 0)
		{
			response = "Oh really? " + getRandomResponse();
			emotion++; 
			}
		
		else if (findKeyword(statement, "relationship is not healthy") >= 0)
		{
			response = "Oh really? Can you tell me what you want to change?";
			emotion--; }
		
		else if (findKeyword(statement, "aware") >= 0)
		{
			response = "You must realize how all your actions affect the other. If you do something good and he/she is grateful, then continue!"
					+ "\nIf he/she becomes sad or angry :( Then stop!"
					+ "\nDo you understand?" ;	}
		
		
		else if (findKeyword(statement, "breakups") >= 0)
		{
			response = transformBreakupStatement(statement);
			emotion--; }
		
		
	
		
			else if (findKeyword(statement, "I understand") >= 0) 
			{
					response = transformGoodJobStatement(statement);
					emotion++; }

			else if (findKeyword(statement, "I get it") >= 0) 
				{
					response = transformGoodJobStatement(statement);
					emotion++; }
		
			else if (findKeyword(statement, "I see") >= 0) 
				{
					response = transformGoodJobStatement(statement);
					emotion++; }
			else if (findKeyword(statement, "I think so") >= 0) 
			{
				response = transformGoodJobStatement(statement);
				emotion++; }
		
		else if (findKeyword(statement, "years") >= 0)
				{ 
			Scanner Years = new Scanner  (System.in);
			
			response = "Wow that is a long time!\nSo what problems are you having in this relationship?";
                	emotion++; }
                	
        else if (findKeyword(statement, "ugly") >= 0) 
		{
			response = "No one is ugly. If you call someone ugly, the only one ugly is you.";
			emotion--; }
                	
		else if (findKeyword(statement, "cheating") >= 0) 
		{
			response = "";
			emotion--; }
		
		// Response transforming I want to statement
		else if (findKeyword(statement, "I don't want to talk to you") >= 0)
		{
			response = transformRecoveryStatement(statement);
			emotion--; }
		
		else if (findKeyword(statement, "No, I don't want to breakup") >= 0)
		{ 
			response = transformNoBreakupStatement(statement); }
                	
		else if (findKeyword(statement, "keep") >= 0)
		{
			response = transformKeepStatement(statement);
			emotion++; }
                	
		else if (findKeyword(statement, "What do I do", 0) >= 0)
		{
			response = transformWhatDoIDoStatement(statement); }
                	
		else if (findKeyword(statement, "don't want to", 0) >= 0)
		{
			response = transformDontWantToStatement(statement); }
                	
		else if (findKeyword(statement, "is really",0) >= 0)
		{
			response = transformIsReallyStatement(statement); }
                	
		else if (findKeyword(statement, "is very",0) >= 0)
		{
			response = transformIsReallyStatement(statement); }
		
		else if (findKeyword(statement, "I think my partner",0) >= 0)
		{
			response = transformIThinkMyPartnerStatement(statement); }
                	
		else if (findKeyword(statement, "grateful ",0) >= 0)
		{
			response = transformGratefulGestureStatement(statement); }
                	
		else
		{
			response = getRandomResponse(); }
		
		return response;
	}
	
	/**
	 * Take a statement with "is really <something>." and transform it into 
	 * "If that person is really <something>. I suggest talking to that person"
	 * @param statement the user statement, assumed to contain "I want to"
	 * @return the transformed statement
	 */
	private String transformIsReallyStatement(String statement)
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
		
		if (lastChar.equals("?"))
		{
			statement = statement.substring(0, statement
					.length() - 1);
		}
		
		
		int psn = findKeyword (statement, "is really", 0);
		String restOfStatement = statement.substring(psn+9).trim();
		return "If that person is really " + restOfStatement+ ". I suggest talking to that person.";
	}

	private String transformWhatDoIDoStatement(String statement)
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
		
		if (lastChar.equals("?"))
		{
			statement = statement.substring(0, statement
					.length() - 1);
		}

		return "Are you certain?";
	}
	private String transformBreakupStatement(String statement)
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
		
		if (lastChar.equals("?"))
		{
			statement = statement.substring(0, statement
					.length() - 1);
		}
		
		return getRandomResponse();
	}

	private String transformDontWantToStatement(String statement)
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
		int psn = findKeyword (statement, "don't want to", 0);
		String restOfStatement = statement.substring(psn+12).trim();
		return "Why don't you want to " + restOfStatement + "?";
	}
	
	private String transformIThinkMyPartnerStatement(String statement)
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
		int psn = findKeyword (statement, "I think my partner", 0);
		String restOfStatement = statement.substring(psn+17).trim();
		return "If your partner" + restOfStatement + ", it is likely you are also "+ restOfStatement;
	}
	
	private String transformRecoveryStatement(String statement)
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
		System.out.println();
		return "I know talking about personal problems are a hard thing. However, the first step is always the hardest."
		+ "\nWhat would you like to talk about?" ; 
	}
	
	private String transformGoodJobStatement(String statement)
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
		
		return "Good Job "+ name +"! That's another step to a better relationship :)"; 
	}



	private String transformKeepStatement(String statement)
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
		if (lastChar.equals("?"))
		{
			statement = statement.substring(0, statement
					.length() - 1);
		}
		int psn = findKeyword (statement, "keep", 0);
		String restOfStatement = statement.substring(psn + 7).trim();
		return "In order to keep a " + restOfStatement + ", you must be aware.";
	}
	/*private String transformIsVeryStatement(String statement)
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
	
		int psn = findKeyword (statement, "is", 0);
		String restOfStatement = statement.substring(psn ).trim();
		return "If your partner " + restOfStatement + ", it is likely you are also" + restOfStatement.substring(2) +".";
	}*/
	
		private String transformGratefulGestureStatement(String statement)
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
			
			if (lastChar.equals("?"))
			{
				statement = statement.substring(0, statement
						.length() - 1);
			}
			
			
			int psn = findKeyword (statement, "grateful", 0);
			String restOfStatement = statement.substring(3,psn).trim();
			return "Yes!" + restOfStatement +" Is a grateful gesture!";
		}
		private String transformNoBreakupStatement(String statement)
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
			
			if (lastChar.equals("?"))
			{
				statement = statement.substring(0, statement
						.length() - 1);
			}
			
			
			int psn = findKeyword (statement, "is really", 0);
			String restOfStatement = statement.substring(psn+9).trim();
			return "If that person is really " + restOfStatement+ ". I suggest talking to that person.";
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
		if (breakupvars ==0)
		{
			return randomBreakupResponse[r.nextInt(randomBreakupResponse.length)]; 		}
		if (emotion > 2)
		{	
			return randomHappyResponses [r.nextInt(randomAngryResponses.length)];	 }	
		
		if (emotion < 0 )
		{
		return randomAngryResponses [r.nextInt(randomHappyResponses.length)];	}
		
		return randomNeutralResponses [r.nextInt(randomNeutralResponses.length)];
	}
	
	private String [] randomNeutralResponses = {"How long has this relationship been going?",
			"What do you think about the other's appearences?",
			"Do you have any other problems?",
			"Reflection time! Do you think this relationship is healthy?"
	};
	private String [] randomAngryResponses = {"Honey, you are something special! I recommend talking to me more!"};
	private String [] randomHappyResponses = {"Buddy, you are on a roll! I think you are prepared to have long-term relationships!"};
	private String [] randomBreakupResponse = {"Is your partner hot?", "Do you think this relationship should end?"};
}
