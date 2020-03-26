import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.Timer;

interface Feedback {
	// Feedback from display
	void FeedbackStartDisplay();
	void FeedbackStopDisplay();
	
	// Feedback from music
	void FeedbackStartMusic();
	void FeedbackStopMusic();
	void FeedbackPositionMusic(long pos);
	
	// Feedback from data
    int FeedbackTimeSegmentSize();
    int FeedbackFreqSegmentSize();
    List<Float> FeedbackFreqData(int segment);
	
	// Feedback from score card
    void FeedbackScore(int type, int score);
}

public class MusicVisualizerMain extends JFrame implements Feedback {
	private static final long serialVersionUID = 1L;

	// Resource files
    private String MUSIC_FILE = "Dragon_Force.wav";
    private String FREQ_FILE = "Dragon_Force.csv";

    private String BG_FILE = "Music-Background-640x640-0.jpg";
	private Timer timerBG = null;
	private int seqBG_FILE = 0;
    
	// Display size
    private static final int winTile = 64;
    private static final int winWidth = winTile * (10 + 2);
    private static final int winHeight = winTile * (10 + 1);

    // Data
    private ReadMusicFreqData freqData = null;
    private boolean bShuffle = false;
    
    // Music
    private MusicPlayer mPlayer = null;
    
    // Music display
    MusicVisualizerDisplay mvDisplay = null;
    
    // Score card display
    ScoreCardDisplay scDisplay = null;
    
    // Message display
    JLabel msgDisplay = new JLabel("Message:");
    
    // Top layout
    JPanel topLayoutPanel = new JPanel();
    
    MusicVisualizerMain() {
        super("Music Visualizer Game");
        
        // Read music data
        freqData = new ReadMusicFreqData(FREQ_FILE);

		// Setup music display
        mvDisplay = new MusicVisualizerDisplay(winTile*10, winHeight - winTile);
        mvDisplay.RegisterFeedback(this);
        mvDisplay.SetBackgroundImage(BG_FILE);

        // Setup scorecard display
        scDisplay = new ScoreCardDisplay(winTile*2, winHeight - winTile);
        scDisplay.RegisterFeedback(this);
        scDisplay.Init();
        
        // Setup display layout
        setSize(winWidth+16, winHeight);
        
	    topLayoutPanel.setLayout(new BorderLayout());
	    setContentPane(topLayoutPanel);

	    topLayoutPanel.add(mvDisplay, BorderLayout.WEST);
	    topLayoutPanel.add(scDisplay, BorderLayout.EAST);
	    //topLayoutPanel.add(new JSeparator(), BorderLayout.CENTER);
	    topLayoutPanel.add(msgDisplay, BorderLayout.SOUTH);
	    
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);   
        // setResizable(false);	// Strange!

        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension screenSize = tk.getScreenSize();
        int winX = (screenSize.width - winWidth) / 2;
        int winY = (screenSize.height - winHeight) / 2;
        setLocation(winX, winY);
        
        // Setup listener for window close
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
            	mPlayer.Stop();
            	
            	try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            	
            	System.out.println("Thank you for playing!");
            	
            	System.exit(0);
            }
        });

		timerBG = new Timer(freqData.GetTimeSegmentSize()*100, new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	seqBG_FILE = (seqBG_FILE + 1) % 10;
		    	BG_FILE = "Music-Background-640x640-" + seqBG_FILE + ".jpg";
		        mvDisplay.SetBackgroundImage(BG_FILE);
		    }
		});

/* Debug only
        // Add mouse listener
        addMouseMotionListener(new MouseMotionListener() {
			@Override
			public void mouseDragged(MouseEvent e) {
                // update the label to show the point to which the cursor moved 
            	System.out.println("Mouse: " + e.getX() + "," + e.getY()); 
			}

			@Override
			public void mouseMoved(MouseEvent e) {
                // update the label to show the point to which the cursor moved 
            	System.out.println("Mouse: " + e.getX() + "," + e.getY()); 
			}
        });
*/
        // Play music
        mPlayer = new MusicPlayer(MUSIC_FILE);
        mPlayer.RegisterFeedback(this);

        setVisible(true);
        
        // Start from display
        mvDisplay.Start();
    }

	public static void main(String[] args) {
		System.out.println("Working Directory = "+System.getProperty("user.dir"));
		
		// Getting reference to Main thread 
        Thread mainThread = Thread.currentThread(); 
        System.out.println("Current thread: " + mainThread.getName()); 
        
		new MusicVisualizerMain();
	}

	public void FeedbackStartDisplay() {
		timerBG.start();
        mPlayer.start();
        
		System.out.println("Display started");
	}
	
	public void FeedbackStopDisplay() {
		timerBG.stop();
		System.out.println("Display stopped");
	}
	
	public void FeedbackStartMusic() {
		System.out.println("Music started");
	}
	
	public void FeedbackStopMusic() {
		mvDisplay.Stop();
		
		System.out.println("Music stopped");
	}
	
	public void FeedbackPositionMusic(long pos) {
		mvDisplay.SyncTimePosition(pos);
		
		msgDisplay.setText("Music playback: " + Long.toString(pos));
	}

    public int FeedbackTimeSegmentSize() { 
		System.out.println("Number of time segment is " + freqData.GetTimeSegmentSize());
    	return freqData.GetTimeSegmentSize();
    }
    
    public int FeedbackFreqSegmentSize() { 
		System.out.println("Number of freq segment is " + freqData.GetFreqSegmentSize());
    	return freqData.GetFreqSegmentSize();
    }
    
    public List<Float> FeedbackFreqData(int segment) { 
    	if (bShuffle) {
    		List<Float> list = freqData.GetFreqData(segment);
    		Collections.shuffle(list);
    		return list;
    	}
    	
    	return freqData.GetFreqData(segment);
    }
    
    public void FeedbackScore(int type, int score) {
    	scDisplay.UpdateScore(type, score);
    }
}
