import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//Practice Test #4 Problem #3b
public class TeachersPet extends StudentBot
{
	public TeachersPet(String nm) 
	{
		super(nm);
	}
	
	public String respondToGreeting(String greeting) 
	{
		String message = "";
		
		if(greeting.indexOf("morning") != -1)
			message += "Good morning";
		
		int index = greeting.indexOf("name is");
		if(index != -1)
			message += message.substring(index + 8);
		else
			message += ".";
		
		message += "\nWhat are we going to do in class today?";
			
		return message;
	}
}

class ClientClass
{
	//3c
	public static void greetAll(List<ChatBot> bots)
	{
		//RZ: ChatBot.readInput??
		Scanner in = new Scanner(System.in);
		System.out.print("Enter greeting: ");
		String text = in.nextLine();
		in.close();
		
		System.out.println();
		for(ChatBot currentBot: bots)
			System.out.println(currentBot.getName() + ": " + currentBot.respondToGreeting(text));
	}
}