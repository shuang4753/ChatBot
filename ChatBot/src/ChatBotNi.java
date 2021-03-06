import java.util.Random ;
import java.util.Scanner;
import java.net.URL;
import java.net.URLConnection;
import java.io.*;
/**
 * A program to carry on conversations with a human user. This version:
 * 
 * @author Handy Ni
 * @version September 2017
 */
public class ChatBotNi {

	// friendliness can alter the way our bot responds. Emotion can become more negative
	// or positive over time.
	int friendliness = 0;
    
	// deepnessOfLove changes some responses based on how deep in love
	// you are with your significant other.
	int deepnessOfLove = 0;
	/**
	 * Get a default greeting
	 * 
	 * @return a greeting
	 */
	private String newName;
	
	public String getGreeting() {
		
		System.out.println("Hi! This is [Gift Advice Bot 3000]!" 
				+ "\nLet us start with your name! What is your name?");
		Scanner name = new Scanner (System.in);
		newName = name.nextLine();
		String textLine = "Hi " + newName + "! How may I help"
				+ "\nyou today? [Remember this is a gift suggestions chatbot!]";
		return textLine;
			
	}

	/**
	 * Gives a response to a user statement
	 * 
	 * @param statement
	 *            the user statement
	 * @return a response based on the rules given
	 */
	public String getResponse(String statement) {
		String response = "";
		
		// After your friendliness with the chatbot drops below -5 you will no longer get a response. 
		if (friendliness <= -5) {
			return "Leave me alone" + newName + ", I don't want to talk to you.";
		
			// Easter Egg
		} else if ((friendliness >= 10) && (findKeyword(statement, "you're cute", 0) >= 0)) {
			response = "You're the cutest thing I've seen! You forget about your gift I'll be a better partner!";
		
			// Response to a empty response.
		} else if (statement.length() == 0) {
			response = "It's okay to talk to me.";
		}
		
		else if (findKeyword(statement, "no") >= 0) {
			response =  newName + "! You're not trying hard enough! Think beyond the obvious!";
			friendliness--;
			deepnessOfLove--;
		}
		
		else if (findKeyword(statement, "thank") >= 0) {
			response = "You're so nice! Anything for a cutie like you.";
			} else if (findKeyword(statement, "thanks") >= 0) {
				response = "You're so nice! Anything for a cutie like you.";
		}

		else if (findKeyword(statement, "nice") >= 0) {
			response = "Awww" + newName + ", you're so kind.";
			friendliness++;
		}
		
		else if (findKeyword(statement, "years") >= 0) {
			response = "Wow that's a long time! Does she have any interests?";
			friendliness++;
			deepnessOfLove++;
			} else if (findKeyword(statement, "year") >= 0) {
				response = "Wow that's a long time! Does she have any interests?";
				friendliness++;
				deepnessOfLove++;
			}
		
		else if ((statement.equals("I love her") || statement.equals("I love him"))) {
			response = "That's adorable!";
			deepnessOfLove+=2;
		
		
		} else if (findKeyword(statement, "yeah") >= 0) {
			response = interestedIn(statement); 
				friendliness++;
				deepnessOfLove++;
			} else if (findKeyword(statement, "interested") >= 0) {
				response = interestedIn(statement);
					friendliness++;
					deepnessOfLove++;
				} else if (findKeyword(statement, "enjoys") >= 0) {
					response = interestedIn(statement);
					 friendliness++;
					 deepnessOfLove++;
					} else if (findKeyword(statement, "loves") >= 0) {
						response = interestedIn(statement);
							friendliness++;
							deepnessOfLove++;
						} else if (findKeyword(statement, "likes") >= 0) {
							response = interestedIn(statement);
								friendliness++;
								deepnessOfLove++;

		}					
		
		else if (findKeyword(statement, "Ok") >= 0) {
			response = googleSearch(statement);
		}	
		
		// Response transforming I want to statement
		
		else if (findKeyword(statement, "I want to", 0) >= 0) {
			response = transformIWantToStatement(statement);
			
		} else if (findKeyword(statement, "I want", 0) >= 0) {
			response = transformIWantStatement(statement);
			
		} else if ((findKeyword(statement, "gift", 0) >= 0) && (deepnessOfLove >= 10)) {
			response = "You guys are close enough to be married! "
					+ "\nThere no better gift than a surprise proposal!"; 
			} else if ((findKeyword(statement, "gift", 0) >= 0) && (deepnessOfLove < 10)) {
			response = "Do you perhaps need help picking out a gift for your significant other?";
				deepnessOfLove++;
		
		} else if (findKeyword(statement, "gifts", 0) >= 0) {
				response = "Do you perhaps need help picking out a gift for your significant other?";
					deepnessOfLove++;
				} else if (findKeyword(statement, "present", 0) >= 0) {
					response = "Do you perhaps need help picking out a gift for your significant other?";
						deepnessOfLove++;
					} else if (findKeyword(statement, "presents", 0) >= 0) {
						response = "Do you perhaps need help picking out a gift for your significant other?";
							deepnessOfLove++;
						} else if (findKeyword(statement, "get", 0) >= 0) {
							response = "Do you perhaps need help picking out a gift for your significant other?";
								deepnessOfLove++;
							} else if (findKeyword(statement, "give", 0) >= 0) {
								response = "Do you perhaps need help picking out a gift for your significant other?";
									deepnessOfLove++;
								} else if (findKeyword(statement, "send", 0) >= 0) {
									response = "Do you perhaps need help picking out a gift for your significant other?";
										deepnessOfLove++;
		
		
			} else if ((findKeyword(statement, "yes", 0) >= 0) && (yesChecker == "true")) {
			response = "That's awesome! If you need anymore more help with gifts try entering something else she likes! "
					+ "\nOr you can try to find all my hidden features!";
			
			} else if (findKeyword(statement, "yes", 0) >= 0) {
				response = giftCheck(statement);

			} else if (findKeyword(statement, "no", 0) >= 0) {
				response = "Think harder!";
				--friendliness;
				--deepnessOfLove;
				
		} else {
			
			response = getRandomResponse();
		}

		return response;
	}
		//base string to record interests
		private String capInterested;
		
		//Checks if yes was used already
		private String yesChecker;
		
		//Method to include yesChecker
		private String giftCheck(String statement) {
		yesChecker = "true";
			return "This is a great start " + newName + "! Lets start with a few questions about your relationship. " + 
				"\nHow many years have you two been together?";
	
		}
		
		// Method used to pull the targeted word out of the statement.
		private String interestedIn(String statement) {
		String [] list = statement.split(" ");
		String interest = list[list.length - 1];
		capInterested = interest.substring(0,1).toUpperCase() + interest.substring(1);
		String newStatement = "Wow!" + " " + capInterested + "?" + " " + "That's amazing! Now lets find a gift! [Type 'Ok' to continue]";
			return newStatement;
		
	}
		
		// Automatically opens browser and searches a topic. 
		// Uses google formatting of search and Runtime method to execute files on the desktop
		// searchURLSFinal is the formatted google link.
		private String googleSearch(String statement) {
			String googleURL = "https://www.google.com/search";
			String searchURL = googleURL + "?q=" + capInterested + " gifts for your significant other";
			String searchURLSFinal = searchURL.replaceAll(" ", "%20");
			String finalURL = "Great! Lets find your cutie pie a gift!"
					+" \nPersonally, I always recommend the first website as a recommendation!"
					+" \nAnyways, do you like my reccomendation?";
			try {
				Process po = Runtime.getRuntime().exec("C:\\Program Files\\Internet Explorer\\iexplore.exe " + searchURLSFinal);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return finalURL;
		}

	/**
	 * Take a statement with "I want to <something>." and transform it into "Why do
	 * you want to <something>?"
	 * 
	 * @param statement
	 *            the user statement, assumed to contain "I want to"
	 * @return the transformed statement
	 */
	private String transformIWantToStatement(String statement) {
		// Remove the final period, if there is one
		statement = statement.trim();
		String lastChar = statement.substring(statement.length() - 1);
		if (lastChar.equals(".")) {
			statement = statement.substring(0, statement.length() - 1);
		}
		int psn = findKeyword(statement, "I want to", 0);
		String restOfStatement = statement.substring(psn + 9).trim();
		return "Why do you want to " + restOfStatement + "?";
	}
	/**
	 * Take a statement with "I want <something>." and transform it into "Would you
	 * really be happy if you had <something>?"
	 * 
	 * @param statement
	 *            the user statement, assumed to contain "I want"
	 * @return the transformed statement
	 */
	

	private String transformIWantStatement(String statement) {
		// Remove the final period, if there is one
		statement = statement.trim();
		String lastChar = statement.substring(statement.length() - 1);
		if (lastChar.equals(".")) {
			statement = statement.substring(0, statement.length() - 1);
		}
		int psn = findKeyword(statement, "I want", 0);
		String restOfStatement = statement.substring(psn + 6).trim();
		return "Would you really be happy if you had " + restOfStatement + "?";
	}

	/**
	 * Take a statement with "I <something> you" and transform it into "Why do you
	 * <something> me?"
	 * 
	 * @param statement
	 *            the user statement, assumed to contain "I" followed by "you"
	 * @return the transformed statement
	 */
	private String transformIYouStatement(String statement) {
		// Remove the final period, if there is one
		statement = statement.trim();
		String lastChar = statement.substring(statement.length() - 1);
		if (lastChar.equals(".")) {
			statement = statement.substring(0, statement.length() - 1);
		}

		int psnOfI = findKeyword(statement, "I", 0);
		int psnOfYou = findKeyword(statement, "you", psnOfI);

		String restOfStatement = statement.substring(psnOfI + 1, psnOfYou).trim();
		return "Why do you " + restOfStatement + " me?";
	}

	/**
	 * Search for one word in phrase. The search is not case sensitive. This method
	 * will check that the given goal is not a substring of a longer string (so, for
	 * example, "I know" does not contain "no").
	 *
	 * @param statement
	 *            the string to search
	 * @param goal
	 *            the string to search for
	 * @param startPos
	 *            the character of the string to begin the search at
	 * @return the index of the first occurrence of goal in statement or -1 if it's
	 *         not found
	 */

	
	private int findKeyword(String statement, String goal, int startPos) {
		String phrase = statement.trim().toLowerCase();
		goal = goal.toLowerCase();

		// The only change to incorporate the startPos is in
		// the line below
		int psn = phrase.indexOf(goal, startPos);

		// Refinement--make sure the goal isn't part of a
		// word
		while (psn >= 0) {
			// Find the string of length 1 before and after
			// the word
			String before = " ", after = " ";
			if (psn > 0) {
				before = phrase.substring(psn - 1, psn);
			}
			if (psn + goal.length() < phrase.length()) {
				after = phrase.substring(psn + goal.length(), psn + goal.length() + 1);
			}

			// If before and after aren't letters, we've
			// found the word
			if (((before.compareTo("a") < 0) || (before.compareTo("z") > 0)) // before is not a
																				// letter
					&& ((after.compareTo("a") < 0) || (after.compareTo("z") > 0))) {
				return psn;
			}

			// The last position didn't work, so let's find
			// the next, if there is one.
			psn = phrase.indexOf(goal, psn + 1);

		}

		return -1;
	}
	
	/**
	 * Search for one word in phrase. The search is not case sensitive. This method
	 * will check that the given goal is not a substring of a longer string (so, for
	 * example, "I know" does not contain "no"). The search begins at the beginning
	 * of the string.
	 * 
	 * @param statement
	 *            the string to search
	 * @param goal
	 *            the string to search for
	 * @return the index of the first occurrence of goal in statement or -1 if it's
	 *         not found
	 */
	private int findKeyword(String statement, String goal) {
		return findKeyword(statement, goal, 0);
	}

	/**
	 * Pick a default response to use if nothing else fits.
	 * 
	 * @return a non-committal string
	 */
	private String getRandomResponse() {
		Random r = new Random();
		if (friendliness == 0) {
			return randomNeutralResponses[r.nextInt(randomNeutralResponses.length)];
		}
		if ((friendliness < 0) && (friendliness > -5)) {
			return randomAngryResponses[r.nextInt(randomAngryResponses.length)];
		} 
		if ((friendliness > 0) && (friendliness < 5)) {
		return randomHappyResponses[r.nextInt(randomHappyResponses.length)];
		}
		return "Uhmm..";
	}
		
	private String[] randomNeutralResponses = 
			{ "Tell me about it.", "Don't know what to think about that.",
			"That's interesting!", "That's intriguing!", "Lets not talk about that. It's confusing.",
			"Sorry I don't know how to respond to something like that." };
	private String[] randomAngryResponses = { "You're really annoying you know.", "What do you want now?", newName + ", you're disgusting!" };
	private String[] randomHappyResponses = {  newName + ", you're so cute!!", "You should talk to me more often." };

}