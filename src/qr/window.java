package qr;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;

import uk.co.caprica.vlcj.binding.LibVlc;
import uk.co.caprica.vlcj.player.MediaPlayer;
import uk.co.caprica.vlcj.player.MediaPlayerFactory;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;



public class window extends JFrame {
	
	public final int SOUND1 = 0;
	private boolean soundPlayed = false;
	public static String ID ;
	public static EmbeddedMediaPlayer emp;
	
	
	public window() throws IOException {
		
	   
		 
		Webcam webcam = Webcam.getDefault();
        webcam.setViewSize(WebcamResolution.VGA.getSize());
        
		
        
        WebcamPanel webcamPanel = new WebcamPanel(webcam);
        webcamPanel.setSize( 768, 480);
       
        
        
        Font font = new Font("Arial",Font.BOLD,40);
        Font font1 = new Font("Arial",Font.ITALIC,60);
        Font font2 = new Font("Arial",Font.ITALIC,40);
 
        JPanel WEBCAM = new JPanel();
        WEBCAM.setBackground(Color.white);
        WEBCAM.setBounds(0,5,690,500); 
        WEBCAM.add(webcamPanel);
                      
        JPanel TEXTE = new JPanel();
        TEXTE.setBackground(Color.white);
        TEXTE.setBounds(690,5, 1255, 500);
        JLabel texte = new JLabel();
        TEXTE.add(texte);
        texte.setFont(font1);
        texte.setForeground(new Color(0,105,144));
      
        JPanel TEXTE2 = new JPanel();
        TEXTE2.setLayout(null);
        TEXTE2.setBackground(new Color(0,105,144));
        TEXTE2.setBounds(0, 505, 670, 700);
        JLabel label2 = new JLabel("SHOW A QR CODE!");
        label2.setBounds(50, 200, 700, 100);
        label2.setForeground(Color.white);
        label2.setFont(font1);
        TEXTE2.add(label2);
        
        
        JPanel VIDEO = new JPanel();
        VIDEO.setBounds(200, 50, 350, 350);
        VIDEO.setOpaque(false);
        Canvas c = new Canvas();
        VIDEO.add(c);
        c.setBounds(800, 500, 350, 350);
        NativeLibrary.addSearchPath(RuntimeUtil.getLibVlcLibraryName(),"C:/Program Files/VideoLAN/VLC");
        Native.loadLibrary(RuntimeUtil.getLibVlcLibraryName(), LibVlc.class);
        MediaPlayerFactory mpf = new MediaPlayerFactory();
        emp =mpf.newEmbeddedMediaPlayer();
        emp.setVideoSurface(mpf.newVideoSurface(c));
        emp.setEnableKeyInputHandling(false);
        emp.setEnableMouseInputHandling(false);
        emp.setVolume(0);
        
        
        
        ImagePanel IMAGE = new ImagePanel(new ImageIcon("fish.jpg").getImage());
        IMAGE.setLayout(null);
        IMAGE.setBounds(670, 505, 1400, 600);
        IMAGE.add(VIDEO);
        
        
        ImageIcon icon = new ImageIcon("play.png");
        JButton SOUND = new JButton(icon);
        IMAGE.add(SOUND);
        SOUND.setBounds(700, 50, 350, 350);
        SOUND.setBackground(new Color(0,105,144));
        SOUND.setBorderPainted(false);
        SOUND.setFont(font2);
        SOUND.setFocusPainted( false );
        
        
       
        JFrame frame = new JFrame();
        frame.getContentPane().setBackground(Color.white);
        frame.setExtendedState(MAXIMIZED_BOTH);
        frame.setTitle("QUICK TIMETABLE");
        frame.add(WEBCAM); 
        frame.add(TEXTE);
        frame.add(TEXTE2);
        frame.add(IMAGE);
        
        frame.setLocationRelativeTo(null);
        frame.setLayout(null); 
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       
        frame.setVisible(true);
        
        
        new webcamthread("t1",webcam,texte,SOUND);
            	
	
 } 

	
	class ImagePanel extends JPanel {

		  private Image img;

		  public ImagePanel(String img) {
		    this(new ImageIcon(img).getImage());
		  }

		public ImagePanel(Image img) {
		    this.img = img;
		    Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
		    setPreferredSize(size);
		    setMinimumSize(size);
		    setMaximumSize(size);
		    setSize(size);
		    setLayout(null);
		  }

		  public void paintComponent(Graphics g) {
		    g.drawImage(img, 0, 0, null);
		  
		  }
		  
}
            	 
class  webcamthread extends Thread {
	
	public webcamthread(String name, Webcam webcam,JLabel texte,JButton SOUND) {
		
		super(name);
		this.start();
		try {
    		
    		while(true) {
			ImageIO.write(webcam.getImage(),"PNG",new File("test.png"));
			File file = new File("test.png");
            String decodedText = main.decodeQRCode(file);
            String file1 = "renoir.mp4";
            if(decodedText == null) {
                
                texte.setText("<html>No QR <br> Code found in the image</html>");
                
            }else {
            	
                System.out.println("Decoded text = " + decodedText);
                texte.setText(decodedText);
                window.ID =decodedText.split("<br>")[decodedText.split("<br>").length-1].substring(0,4);
                if (window.ID.equals("S001")) {
                	window.emp.prepareMedia(file1);
                	window.emp.play();
                	window.emp.setRepeat(true);
                	 SOUND.addActionListener(new ActionListener(){
                	      public void actionPerformed(ActionEvent event){
                	            	playit(SOUND1);  
                	      }
                	    });
                	
                }
                try {
				this.sleep(10000);
				} catch (InterruptedException e) {
					
					e.printStackTrace();
				}
            } }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.stop();
		
		
	}

	public void playit(int soundRequired) {
		String fn = null;
		File sound; 

		switch (soundRequired) {
		case SOUND1:
			fn = "sonTEST.wav";
			break;    
		default:
            break;
			

		}

		try {
			// Open an audio input stream.
			sound = new File(fn);
			AudioInputStream audioIn = AudioSystem.getAudioInputStream(sound);
			Clip clip = AudioSystem.getClip();
			// Open audio clip and load samples from the audio input stream.
			clip.open(audioIn);
			clip.start();
			// plays
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
}

}

