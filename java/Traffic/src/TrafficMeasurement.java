class Traffic {
	boolean already_on;
	boolean already_off;
	
	int num_none = 0;
	int none[] = new int[2];	// min and max
	
	int on[] = new int[2];		// min and max
	int off[] = new int[2];		// min and max
	
	int input[] = new int[2];
	int output[] = new int[2];
	
	Traffic() {
		num_none 	= 0;
		none[0] 	= 0;
		none[1] 	= 0;
		on[0] 		= 0;
		on[1] 		= 0;
		off[0]		= 0;
		off[1]		= 0;
		input[0]	= 0;
		input[1]	= 0;
		output[0]	= 0;
		output[1]	= 0;
		
		already_on = false;
		already_off	= false;
	}
	
	void Add_on(int min, int max)
	{
		if (already_on == false)
		{
			on[0] = min;
			on[1] = max;
			
			already_on = true;
		}
		else
		{
			none[0] += min;
			none[1] += max;
			
			on[0] = 0;
			on[1] = 0;
			
			already_on = false;
		}
	}
	void Add_off(int min, int max)
	{
		if (already_off == false)
		{
			off[0] = min;
			off[1] = max;
			
			already_off = true;
		}
		else
		{
			none[0] = output[0];
			none[1] = output[1];
			
			already_off = false;
		}
	}
	void Add_none(int min, int max)
	{
		if (num_none > 0)
		{
			none[0] = Math.max(none[0], min);
			none[1] = Math.min(none[1], max);
		}
		else
		{
			none[0] = min;
			none[1] = max;
		}
		
		num_none ++;
		
		System.out.println(none[0] + "," + none[1]);
	}
	void Get_input()
	{
		input[0] = none[0] - on[1];
		input[1] = none[1] - on[0];
		
		System.out.println(input[0] + "," + input[1]);
	}
	void Get_output()
	{
		output[0] = none[0] - off[1];
		output[1] = none[1] - off[0];
		
		System.out.println(output[0] + "," + output[1]);
	}
}

public class TrafficMeasurement {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Traffic tr = new Traffic();
		
		System.out.println("add");
		tr.Add_on(1,2);
		System.out.println("none");
		tr.Add_none(10,14);
		tr.Add_none(11,15);
		System.out.println("off");
		tr.Add_off(2,3);
		
		tr.Get_input();
		tr.Get_output();
		
		System.out.println("add again");
		tr.Add_none(1,1);
	}
}
