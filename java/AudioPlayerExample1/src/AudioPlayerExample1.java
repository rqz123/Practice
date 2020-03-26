

/**
  * This is an example program that demonstrates how to play back an audio file
  * using the Clip in Java Sound API.
  * @author www.codejava.net
  *
  */

public class AudioPlayerExample1 {
    public static void main(String[] args) {
        String audioFilePath = "C:/Users/RichardZhang/Music/strings.wav";
        //String audioFilePath = "C:/Users/RichardZhang/Music/MP3_700KB.mp3";
        //String audioFilePath = "C:/Users/RichardZhang/OneDrive/Work/python/AV/PyAudioVisualiser/sample.wav";
        AudioPlayer player = new AudioPlayer(audioFilePath);
        player.start();
        player.Stop();
    }
}
