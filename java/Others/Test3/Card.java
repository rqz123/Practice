//Extra class for Practice Test #3 Problem #3
public class Card 
{
	private String content;
	
	public Card(String s)
	{
		content = s;
	}
	
	public boolean equals(Card other)
	{
		if(this.content.equals(other.content))
			return true;
		return false;
	}
}
