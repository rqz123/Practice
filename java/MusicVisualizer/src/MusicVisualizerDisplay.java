import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

public class MusicVisualizerDisplay extends JPanel implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	enum VisualObjShape {
		RECT, RECT_FILL,
		CYCLE, CYCLE_FILL,
	}
	
	private int dispWidth, dispHeight;
	private BufferedImage dispBuffer;
	
	private Image imageBG = null;
	
	private final long timeSegment = 1000;	// ms (change this with Python code)
	private final long winTime = timeSegment * 6;	// using *N to change speed
	private float generalSpeed = 0;			// pixel/ms	// this is calculated, don't change here
	
	private final int timeFetching = 500;	// ms	fetching freq. data timer
	private final int timeFlashing = 10;	// ms	display flashing timer
	private Timer timerFetching = null;
	private Timer timerFlashing = null;
	
	private VisualFreqChannel freqChannel = new VisualFreqChannel();
	private TimeManager timeManager = new TimeManager();
	
	private int dispFreqWidth;
	private int dispFreqHeight;
	private List<Color> dispFreqColor = new ArrayList<Color>();
	
	private int curTimeSegment = -1;
	private int keyStrick[];

	public Feedback mainCallback = null;
	
	MusicVisualizerDisplay(int width, int height) {
		// Display size
		dispWidth = width;
		dispHeight = height;
		
		// Display buffer 
		dispBuffer = new BufferedImage(dispWidth, dispHeight, BufferedImage.TYPE_INT_ARGB);
		setBackground(Color.BLACK); 
        setPreferredSize(new Dimension(dispWidth, dispHeight));
        //setMaximumSize(new Dimension(dispWidth, dispHeight));
        //setMinimumSize(new Dimension(dispWidth, dispHeight));
        setDoubleBuffered(true);
        
        // Set channel color
        float colour_f = (float)0.5;
        for (int i = 0; i < 256; i ++) {
        	int green = (int)(Math.sin(colour_f * i + 0) * 127 + 128);
        	int blue = (int)(Math.sin(colour_f * i + 2) * 127 + 128);
        	int red = (int)(Math.sin(colour_f * i + 4) * 127 + 128);
        	
        	dispFreqColor.add(new Color(red, green, blue));
        }

        // Set keyboard handler
        setFocusable(true);
        requestFocusInWindow();

        addKeyListener(new KeyHandler());
	}
	
	public void RegisterFeedback(Feedback callback) {
		mainCallback = callback;
	}
	
	public void SetBackgroundImage(String file) {
        // Display background
        try {
            File imgFile = new File(file);
            imageBG = ImageIO.read(imgFile);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public void Start() {
        // Music data
        int freq_segment = mainCallback.FeedbackFreqSegmentSize();
        int time_segment = mainCallback.FeedbackTimeSegmentSize();
        
        dispFreqWidth = dispWidth / freq_segment;
        dispFreqHeight = dispFreqWidth;
		
		generalSpeed = (float)(dispHeight - dispFreqHeight) / (float)winTime;
		
        keyStrick = new int[freq_segment];
        //Arrays.fill(keyStrick, 0);        

		// Get the first
		FetchFreqData(false);
          
        // Initial time and frequency channel
        timeManager.Init();
        freqChannel.Init(freq_segment, time_segment);

		// Set timer for data fetching
		timerFetching = new Timer(timeFetching, new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	FetchFreqData(true);
		    }
		});
		timerFetching.start();
		
		// Set timer for display flashing
		timerFlashing = new Timer(timeFlashing, new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        FlipDisplay(dispBuffer.getGraphics());
		    }
		});
		timerFlashing.start();
		
		if (mainCallback != null)
			mainCallback.FeedbackStartDisplay();
	}
	
	public void Stop() {
		timerFetching.stop();
		timerFlashing.stop();
		
		if (mainCallback != null)
			mainCallback.FeedbackStopDisplay();
	}
	
	public void SyncTimePosition(long pos) {
		timeManager.SyncTime(System.currentTimeMillis(), pos);
	}
	
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        g.drawImage(dispBuffer, 0, 0, this);
    }
    
    public void FetchFreqData(boolean bPrefetch) {
    	timeManager.CheckTime();
    	
    	int segment = (int)(timeManager.futureTime/timeSegment);
    	if (!bPrefetch)
    		segment = (int)(timeManager.currentTime/timeSegment);
    	
    	if (segment <= curTimeSegment)
    		return;
    	
    	if (segment < freqChannel.numTimeSegment) {
	        curTimeSegment = segment;
	        freqChannel.AddFreqObject(mainCallback.FeedbackFreqData(segment));
    	}
    }
    
    public void FlipDisplay(Graphics g) {
    	timeManager.CheckTime();
    	freqChannel.UpdateFreqObject(timeManager.currentTime);
    	
    	// Draw the background image to Component.
    	if (imageBG != null) {
    		g.drawImage(imageBG, 0, 0, null);
    	}
    	else {
    		g.setColor(Color.BLACK);
    		g.fillRect(0, 0, dispWidth, dispHeight);
    	}

        for (int i = 0; i < freqChannel.numFreqSegment; i ++) {
        	boolean hit = false;
        	
        	// Draw object on each frequency channel
        	List<VisualFreqObject> list = freqChannel.GetFreqChannel(i);
        	
			Iterator<VisualFreqObject> iter = list.iterator();
			 while (iter.hasNext()) {
				 VisualFreqObject obj = iter.next();
	        
				 // Draw areas by different music frequencies 
				 float y = generalSpeed * (float)timeManager.GetTimeByFuture(obj.lifeTime);
				 
				 if (y >= 0 && y < dispHeight && obj.score == 0) {
					 int y1 = (int) (y + dispFreqHeight);
					 int y2 = dispHeight - dispFreqHeight;
					 
					 if (y1 > y2 && keyStrick[i] == 1) {
						 obj.score = y1 - y2;
						 hit = true;
						 //System.out.println("y1=" + y1 + ", y2=" + y2);
					 }
				 }
					 
				 obj.Draw(g, dispFreqWidth * i, (int)(y), dispFreqWidth, dispFreqHeight);
			 }
			 
			 // Draw key strick
			 g.setColor(dispFreqColor.get(i));
			 
			 if (keyStrick[i] == 1) {
				 g.fillRect(dispFreqWidth * i + 1, dispHeight - dispFreqHeight + 1, dispFreqWidth - 2, dispFreqHeight - 2);
				 
				 if (hit == false) {
					g.setColor(Color.WHITE);
					//g.drawString("Missed!", dispFreqWidth * i + 1, dispHeight - dispFreqHeight + 1);
				 }
			 }
			 else if (keyStrick[i] == 0) {
				 g.drawRect(dispFreqWidth * i + 1, dispHeight - dispFreqHeight + 1, dispFreqWidth - 2, dispFreqHeight - 2);
			 }
        }
        
        // Show time information
    	//g.setColor(Color.WHITE);
    	//g.drawString(Long.toString(timeManager.checkTime)+"/"+Long.toString(timeManager.currentTime), 10, dispHeight - 40); 
    	 
    	repaint();
    }

    public void KeyReleased(KeyEvent e) {
    	int key = e.getKeyCode();
    	//System.out.println("Key released - " + key);
    	
    	switch (key) {
    	case KeyEvent.VK_A:
    		keyStrick[0] = 0;
    		break;
    	case KeyEvent.VK_S:
    		keyStrick[1] = 0;
    		break;
    	case KeyEvent.VK_D:
    		keyStrick[2] = 0;
    		break;
    	case KeyEvent.VK_F:
    		keyStrick[3] = 0;
    		break;
    	case KeyEvent.VK_G:
    		keyStrick[4] = 0;
    		break;
    	case KeyEvent.VK_H:
    		keyStrick[5] = 0;
    		break;
    	case KeyEvent.VK_J:
    		keyStrick[6] = 0;
    		break;
    	case KeyEvent.VK_K:
    		keyStrick[7] = 0;
    		break;
    	case KeyEvent.VK_L:
    		keyStrick[8] = 0;
    		break;
    	case KeyEvent.VK_SEMICOLON:
    		keyStrick[9] = 0;
    		break;
    	}
	}
    
    public void KeyPressed(KeyEvent e) {
    	int key = e.getKeyCode();
    	//System.out.println("Key pressed - " + key);

    	switch (key) {
    	case KeyEvent.VK_A:
    		keyStrick[0] = 1;
    		break;
    	case KeyEvent.VK_S:
    		keyStrick[1] = 1;
    		break;
    	case KeyEvent.VK_D:
    		keyStrick[2] = 1;
    		break;
    	case KeyEvent.VK_F:
    		keyStrick[3] = 1;
    		break;
    	case KeyEvent.VK_G:
    		keyStrick[4] = 1;
    		break;
    	case KeyEvent.VK_H:
    		keyStrick[5] = 1;
    		break;
    	case KeyEvent.VK_J:
    		keyStrick[6] = 1;
    		break;
    	case KeyEvent.VK_K:
    		keyStrick[7] = 1;
    		break;
    	case KeyEvent.VK_L:
    		keyStrick[8] = 1;
    		break;
    	case KeyEvent.VK_SEMICOLON:
    		keyStrick[9] = 1;
    		break;
    	}
    }

	private class TimeManager {
		public long startTime;		// real time for start
		public long currentTime;	// relative time for current
		public long futureTime;		// relative time for future
		public long correction;
		
		public long lastTime;		// real time for last check
		public long checkTime;		// relative time for last check

		TimeManager() {
			startTime = 0;
			lastTime = 0;
		}
		
		public void Init() {
	        startTime = System.currentTimeMillis();
	        lastTime = startTime;
		}
		
		public void CheckTime() {
	    	long time = System.currentTimeMillis();
	    	
	    	currentTime = time - startTime;
	    	futureTime = currentTime + winTime;
	    	
	    	checkTime = time - lastTime;
	    	lastTime = time;
		}
		
		public long GetTimeByFuture(long time) {
			return (futureTime - time);
		}
		
		public void SyncTime(long time, long pos) {
			long correction = time - startTime - pos;
			startTime = startTime + correction;
			//System.out.println(Long.toString(correction));
		}
	}
	
	private class VisualFreqObject {
		public long lifeTime = 0;
		public int shape;	//public VisualObjShape shape;
		public Color objColor;
		public int score;
		
		VisualFreqObject(long time) {
			lifeTime = time;
			
			int random = (int)(Math.random() * 256);
			objColor = dispFreqColor.get(random);

			//shape = VisualObjShape.RECT;
			shape = random % 4;
			
			score = 0;
		}
		
		public void Draw(Graphics g, int x, int y, int width, int height) {
			g.setColor(objColor);
			 
			switch(shape)
			{
			case 0:	//case RECT_FILL:
				g.fillRect(x + 2, y + 2, width - 4, height - 4);
				break;
			case 1:	//case RECT:
				g.drawRect(x + 2, y + 2, width - 4, height - 4);
				break;
			case 2:	//case CYCLE:
				g.drawOval(x + 2, y + 2, width - 4, height - 4);
				break;
			case 3:	//case CYCLE_FILL:
				g.fillOval(x + 2, y + 2, width - 4, height - 4);
				break;
			default:
				g.drawString(Long.toString(lifeTime), x + width / 2, y + height / 2);
				break;
			}
			
			if (score != 0) {
				g.setColor(Color.WHITE);
				g.drawString("Hit:"+score, x + 4, y + height / 2);
			}
		}
	}
	
	private class VisualFreqChannel {
		public int numFreqSegment = 0;
		public int numTimeSegment = 0;
		
	    private List<List<VisualFreqObject>> freqChannel = new ArrayList<List<VisualFreqObject>>();
		
		VisualFreqChannel() {
		}
		
		public void Init(int freq_segment, int time_segment) {
			numFreqSegment = freq_segment;
			numTimeSegment = time_segment;
			
			for (int i = 0; i < numFreqSegment; i ++) {
				List<VisualFreqObject> obj_lst = new ArrayList<VisualFreqObject>();
				freqChannel.add(obj_lst);
			}
		}
		
		public void AddFreqObject(List<Float> list) {
	        for (int i = 0; i < numFreqSegment; i ++) {
	        	Float height = list.get(i);
	        	
	        	int num = (int)(height / (float)dispFreqHeight + 0.5);
	        	long time = curTimeSegment * timeSegment;
	        	
	        	for (int j = 0; j < num; j ++) {
	        		VisualFreqObject obj = new VisualFreqObject(time + j * 600);
	        		freqChannel.get(i).add(obj);
	        		// System.out.println("Add a obj to channel " + i);
	        	}
	        }
		}
		
		public void UpdateFreqObject(long time) {
			for (int i = 0; i < numFreqSegment; i ++) {
				Iterator<VisualFreqObject> iter = freqChannel.get(i).iterator();
				while (iter.hasNext()) {
					VisualFreqObject obj = iter.next();
					 
					//long delta = (long) (dispFreqHeight / generalSpeed);
					if (obj.lifeTime < time) {
						// Update score
						if (obj.score != 0) {
							mainCallback.FeedbackScore(obj.shape, obj.score);
						}
						
						// Remove object
						iter.remove();
						// System.out.println("Remove a obj from channel " + i);
					}
				}
			}
		}
		
		public List<VisualFreqObject> GetFreqChannel(int channel) {
			return freqChannel.get(channel);
		}
	}
	
	private class KeyHandler extends KeyAdapter {
        @Override
        public void keyReleased(KeyEvent e) {
        	KeyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {
        	KeyPressed(e);
        }
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
