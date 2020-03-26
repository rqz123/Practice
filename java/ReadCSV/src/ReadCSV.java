import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * https://stackoverflow.com/questions/10866205/2-dimensional-array-list/10866208
 */
public class ReadCSV 
{
    private static final String SAMPLE_CSV = "C:\\Users\\RichardZhang\\OneDrive\\Work\\python\\AV\\PyAudioVisualiser\\sample.csv";

    /**
	 * @param args
     * @throws IOException 
     * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException, IOException {
	    List<List<Float>> freqArray = new ArrayList<List<Float>>();
		BufferedReader br = null;
		
		try {
			FileReader file = new FileReader(SAMPLE_CSV);
			br = new BufferedReader(file);

			String line;
	        while ((line = br.readLine()) != null) {
				String[] freqs_str = line.split(",");
				
				List<Float> freqs_lst = new ArrayList<Float>();

				for (String freq : freqs_str)
					freqs_lst.add(Float.parseFloat(freq));
				
				System.out.println(freqs_lst.size());
				freqArray.add(freqs_lst);
				System.out.println(freqArray.size());
			}
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}		
		}
	}
}
