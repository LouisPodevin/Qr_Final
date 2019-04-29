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
import javax.swing.ButtonModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

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
	
	
	public static JLabel texte = new JLabel();
	public final int SOUND1 = 0;
	public final int SOUND2 = 1;
	public final int SOUND3 = 2;
	private boolean soundPlayed = false;
	public static String ID ;
	public static EmbeddedMediaPlayer emp;
	
	public static Webcam webcam = Webcam.getDefault();
	
	
	

	public ImageIcon iconSON = new ImageIcon("icon_son.png");
	public JButton SON = new JButton(iconSON);
	
	public ImageIcon iconVIDEO = new ImageIcon("icon_video.png");
	public JButton VIDEO = new JButton(iconVIDEO);
	
	public ImageIcon iconCAMERA = new ImageIcon("icon_camera.png");
	public JButton CAMERA = new JButton(iconCAMERA);
	private JPanel MOVIE = new JPanel();
	
	public static int sound_required;
	public static JFrame frameCamera ;
	protected static JFrame frameVideo;
	public static String pathvideo;
	
	
	public ImageIcon iconFRENCH = new ImageIcon("bouton_french.png");
	public ImageIcon iconENGLISH = new ImageIcon("bouton_english.png");
	public JButton FRENCH = new JButton(iconFRENCH);
	public JButton ENGLISH = new JButton(iconENGLISH);
	
	
	
	
	public window() throws IOException {
		
	   
		
        webcam.setViewSize(WebcamResolution.VGA.getSize());
       
        WebcamPanel webcamPanel = new WebcamPanel(webcam);
        webcamPanel.setSize( 768, 480);
       
      
        
        
      
        Font font = new Font("Arial",Font.ITALIC,60);
        
 ////////////////////JPANEL///////////////////////////////////////////
                              
        JPanel TEXTE = new JPanel();
        TEXTE.setBackground(new Color(41,151,204));
        TEXTE.setBounds(690,10, 1215, 500);
        TEXTE.setBorder(BorderFactory.createTitledBorder(null, "Informations" ,SOUND1, SOUND1, new Font("Arial", Font.PLAIN , 40), Color.white));
       
                
        JPanel LANGUE = new JPanel();
        LANGUE.setLayout(null);
        LANGUE.setBackground(new Color(25,151,156));
        LANGUE.setBounds(10, 520, 670, 455);
        LANGUE.setBorder(BorderFactory.createTitledBorder(null, "Languages" ,SOUND1, SOUND1, new Font("Arial", Font.PLAIN , 40), Color.white));
        
        
        
        texte.setBounds(800,10,1215,500);
        texte.setForeground(Color.white);
        texte.setFont(font);
        
        TEXTE.add(texte);
       
       
                
 //////////////////////////////////////////////////////////////////
        
 /////////////////JBUTTON//////////////////////////////////////////
        
        SON.setBounds(690,520, 630, 455);
        SON.setBackground(new Color(236,137,35));
        SON.setFocusPainted(false);
        
       
        VIDEO.setBounds(1330, 520, 575, 455);
        VIDEO.setBackground(new Color(100,23,144));
        VIDEO.setFocusPainted(false);
        
        
        CAMERA.setBounds(10,10,670, 500);
        CAMERA.setBackground(new Color(153,28,63));
        CAMERA.setFocusPainted(false);
        
        FRENCH.setBounds(100,100,200,200);
        FRENCH.setFocusPainted(false);
        //FRENCH.setBorder(null);
        FRENCH.setContentAreaFilled(false);
        LANGUE.add(FRENCH);
      
        ENGLISH.setBounds(350,100,200,200);
        ENGLISH.setFocusPainted(false);
        //FRENCH.setBorder(null);
        ENGLISH.setContentAreaFilled(false);
        LANGUE.add(ENGLISH);
        
        
        Canvas c = new Canvas();
        MOVIE.setBounds(1330,520,575,455);
        MOVIE.setOpaque(false);
        MOVIE.add(c);
        c.setBounds(800, 500, 350, 350);
        NativeLibrary.addSearchPath(RuntimeUtil.getLibVlcLibraryName(),"C:/Program Files/VideoLAN/VLC");
        Native.loadLibrary(RuntimeUtil.getLibVlcLibraryName(), LibVlc.class);
        MediaPlayerFactory mpf = new MediaPlayerFactory();
   
        

        JFrame frame = new JFrame();
        frame.getContentPane().setBackground(new Color(30,30,30));
        frame.setExtendedState(MAXIMIZED_BOTH);
        frame.setTitle("QUICK TIMETABLE");
        
        
        frame.add(TEXTE);
        frame.add(LANGUE);
        frame.add(SON);
        frame.add(VIDEO);
        frame.add(CAMERA);
  
        frame.setLocationRelativeTo(null);
        frame.setLayout(null); 
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        
        
	CAMERA.addActionListener(new ActionListener(){
	      public void actionPerformed(ActionEvent event){
	    	 
	    	  frameCamera = new JFrame();
	    	  frameCamera.setUndecorated(true);
	          frameCamera.getContentPane().setBackground(Color.white);
	          frameCamera.add(webcamPanel);
	          frameCamera.pack();
	          frameCamera.setLocation(25,55);
	          frameCamera.setLayout(null); 
	          frameCamera.setVisible(true);
 synchronized(main.QR) {
	    		  
	    		  main.QR.notify();
	    		  System.out.println("notification de QRthreads");
	    	  }
	      }
	  
	
	
	});
	
	VIDEO.addActionListener(new ActionListener(){
	      public void actionPerformed(ActionEvent event){
	    	 
	    	  frameVideo = new JFrame();
	    	  frameVideo.setBounds(1330,520,575,455);
	          frameVideo.add(MOVIE);
	          frameVideo.pack();
	          frameVideo.setLocationRelativeTo(null);
	          frameVideo.setLayout(null); 
	          frameVideo.setVisible(true);
	          emp =mpf.newEmbeddedMediaPlayer();
	          emp.setVideoSurface(mpf.newVideoSurface(c));
	          emp.setEnableKeyInputHandling(false);
	          emp.setEnableMouseInputHandling(false);
	          emp.setVolume(0);
	          emp.prepareMedia(pathvideo);
	          emp.play();
	      }
	
	});
	
	SON.addActionListener(new ActionListener(){
	      public void actionPerformed(ActionEvent event){
	    	  
	    	  if(ID.contentEquals("undifined") || ID == null) {
	    		  
	    	  }else {
	    		  playit(sound_required);
	    	  }
	    	 
	    	  
	      }
	
	});
	
	FRENCH.addActionListener(new ActionListener(){
	      public void actionPerformed(ActionEvent event){
	    	  
	    	  Object source = event.getSource();
	    	  
	  		if(source == FRENCH){
	  			System.out.println("Vous avez cliqu� ici.");
	    	 
	    	  
	      }
	      }
	
	});
	
	

}
	
	
            	 
	public void playit(int soundRequired) {
		String fn = null;
		File sound; 

		switch (soundRequired) {
		case SOUND1:
			fn = "son1.wav";
			break;
		case SOUND2:
			fn ="son2.wav";
			break;
		case SOUND3:
			fn ="son3.wav";
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





