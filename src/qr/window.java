package qr;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
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
import uk.co.caprica.vlcj.player.MediaPlayerEventAdapter;
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
	
	
	private String[] englishday= {"Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"} ;
	private String[] frenchday = {"Lundi","Mardi","Mercredi","Jeudi","Vendredi","Samedi","Dimanche"};
	private String[] numbers = {"1","2","3","4","5","6","7","8","9","10","11","12"};
	
	
	
	private String frenchclassE1007 = "<html>Aller a gauche, continuer tout droit jusqu’à faire face a un escalier,<br> monter au 1er etage continuer tout droit, <br>la salle 1007 est la 2eme salle a droite, juste apres les bancs.</html>";
	private String frenchclassE2003 = "<html>Aller a gauche, continuer tout droit jusqu’à faire face a un escalier,<br> monter au 2e étage de cet escalier, une fois au 2e continuer de marcher tout droit,<br> passer par la dernière porte a droite, et la salle 2003 se trouve derrière cette nouvelle porte.</html>";
	private String frenchclassE2004 = "<html>Aller a gauche, continuer tout droit jusqu’à faire face a un escalier,<br> monter au 2e étage de cet escalier, une fois au 2e continuer de marcher tout droit,<br> la salle 2004 est l'avant dernière salle a droite.</html>";
	
	private String englishclassE1007 = "<html>Go left, continue straight ahead until you reach a staircase,<br> go up to the 1st floor, continue straight ahead,<br> room 1007 is the 2nd room on the right, just after the benches.</html>";
	private String englishclassE2003 = "<html>Go left, continue straight ahead until you reach a staircase,<br>go up to the 2nd floor of this staircase, once you reach the 2nd floor continue straight ahead,<br> go through the last door on the right, and the 2003 room is behind this new door.</html>";
	private String englishclassE2004 = "<html>Go left, continue straight ahead until you reach a staircase, <br>go up to the 2nd floor of this staircase, once at the 2nd continue walking straight ahead,<br> the 2004 room is the penultimate room on the right.</html>";

	
	private String HF_1007 = "<html>Aller a gauche, traverser la double porte juste après sur votre gauche se trouve<br> un ascenseur, allez au 1er étage, prendre a gauche en sortant de l’ascenseur,<br> continuer tout droit, longer les escaliers, la salle se trouve sur votre droite juste<br> après les bancs.</html>";
	private String HF_2003 = "<html>Aller a gauche, traverser la double porte juste après sur votre gauche se trouve<br> un ascenseur, allez au 2e étage, prendre a gauche en sortant de l’ascenseur,<br> continuer tout droit, jusqu'au fond du couloir, passez la porte sur votre droite<br> et votre salle se trouve derrière cette nouvelle porte.</html>";
	private String HF_2004 = "<html>Aller a gauche, traverser la double porte juste après sur votre gauche se trouve<br> un ascenseur, allez au 2e étage, prendre a gauche en sortant de l’ascenseur,<br> continuer tout droit, la salle se trouve sur votre droite juste après la barrière.</html>";
	
	private String HE_1007 = "<html>Go left, cross the double door just after on your left is an elevator,<br> go to the 1st floor, take a left when leaving the elevator,<br> continue straight ahead, walk along the stairs,<br> the room is on your right just after the benches.</html>";
	private String HE_2003 = "<html>Go left, cross the double door just after on your left is an elevator,<br> go to the 2nd floor, take a left when leaving the elevator,<br> continue straight ahead, to the end of the corridor,<br> pass the door on your right and your room is behind this new door.</html>";
	private String HE_2004 = "<html>Go left, cross the double door just after on your left is an elevator,<br> go to the 2nd floor, take a left when leaving the elevator,<br> continue straight ahead, the room is on your right just after the barrier.</html>";
	
	
	public ImageIcon iconSON = new ImageIcon("icon_son.png");
	public JButton SON = new JButton(iconSON);
	
	public ImageIcon iconVIDEO = new ImageIcon("icon_video.png");
	public JButton VIDEO = new JButton(iconVIDEO);
	
	public ImageIcon iconCAMERA = new ImageIcon("icon_camera.png");
	public JButton CAMERA = new JButton(iconCAMERA);

	protected JFrame framevideo;
	private JPanel MOVIE = new JPanel();
	public static String Mode ="EN";

	

	public static int sound_required;
	public static JFrame frameCamera ;



	protected static JFrame frameVideo;
	public static String pathvideo;
	private int modeinfo=0;
	
	public ImageIcon iconFRENCH = new ImageIcon("bouton_french.png");
	public ImageIcon iconENGLISH = new ImageIcon("bouton_english.png");
	public JButton FRENCH = new JButton(iconFRENCH);
	public JButton ENGLISH = new JButton(iconENGLISH);
	
    
	public ImageIcon iconChangeTexte = new ImageIcon("textechange.png");
	public ImageIcon iconHandicap = new ImageIcon("handicap.png");
	public JButton changeTexte = new JButton(iconChangeTexte);
	public JButton handicap = new JButton(iconHandicap);

	protected String decoded;
	
	
	public window() throws IOException {
		
	   
		
        webcam.setViewSize(WebcamResolution.VGA.getSize());
       
        WebcamPanel webcamPanel = new WebcamPanel(webcam);
        webcamPanel.setSize( 768, 480);
        webcamPanel.setLocation(25,55);
       
      
        
        
        
     
        
        
        
      
        Font font = new Font("Arial",Font.ITALIC,40);
        TextField t = new TextField();
        t.setFont(font);
 ////////////////////JPANEL///////////////////////////////////////////
                              
        JPanel TEXTE = new JPanel();
        TEXTE.setBackground(new Color(52,152,219));
        TEXTE.setBounds(690,10, 1215, 500);
        TEXTE.setLayout(null);
        TEXTE.setBorder(BorderFactory.createTitledBorder(null, "Informations" ,SOUND1, SOUND1, new Font("Arial", Font.PLAIN , 40), Color.white));
        
        
        
        
                
        JPanel LANGUE = new JPanel();
        LANGUE.setLayout(null);
        LANGUE.setBackground(new Color(25,151,156));
        LANGUE.setBounds(10, 520, 670, 455);
        LANGUE.setBorder(BorderFactory.createTitledBorder(null, "Languages" ,SOUND1, SOUND1, new Font("Arial", Font.PLAIN , 40), Color.white));
        
        
        
        texte.setBounds(30,0,1000,500);
        texte.setForeground(Color.white);
        texte.setFont(t.getFont());
        
        
        TEXTE.add(texte);
        TEXTE.add(changeTexte);
        TEXTE.add(handicap);
       
       
                
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
        FRENCH.setBorder(null);
        FRENCH.setContentAreaFilled(false);
        LANGUE.add(FRENCH);
      
        ENGLISH.setBounds(350,100,200,200);
        ENGLISH.setFocusPainted(false);
        ENGLISH.setBorder(null);
        ENGLISH.setContentAreaFilled(false);
        LANGUE.add(ENGLISH);
        
        changeTexte.setBounds(1065,25,150,150);
        changeTexte.setFocusPainted(false);
        changeTexte.setBorder(null);
        changeTexte.setContentAreaFilled(false);
        
        handicap.setBounds(1065,170,150,150);
        handicap.setFocusPainted(false);
        handicap.setBorder(null);
        handicap.setContentAreaFilled(false);
        
        
        Canvas c = new Canvas();
        MOVIE.setBounds(0, 0, 575, 455);
        MOVIE.setOpaque(false);
        MOVIE.add(c);
        c.setBounds(0, 0,575, 455);
        NativeLibrary.addSearchPath(RuntimeUtil.getLibVlcLibraryName(),"C:/Program Files/VideoLAN/VLC");
        Native.loadLibrary(RuntimeUtil.getLibVlcLibraryName(), LibVlc.class);
        MediaPlayerFactory mpf = new MediaPlayerFactory();
   

        
        
        ////////////font changement///////////////////
        

        JFrame frame = new JFrame();
        frame.getContentPane().setBackground(new Color(30,30,30));
        frame.setExtendedState(MAXIMIZED_BOTH);
        frame.setTitle("QUICK TIMETABLE");
        
        
        frame.add(TEXTE);
        frame.add(LANGUE);
       
        frame.add(SON);
        frame.add(VIDEO);
        frame.add(CAMERA);
    ;
  
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
	    	  frameVideo.setUndecorated(true);
	    	  frameVideo.getContentPane().setBackground(Color.black);
	    	  frameVideo.setBounds(1330, 553, 575, 458);
	          frameVideo.add(MOVIE);
	          //frameVideo.pack();
	          //frameVideo.setLocationRelativeTo(null);
	          //frameVideo.setLayout(null); 
	          frameVideo.setVisible(true);
	          emp =mpf.newEmbeddedMediaPlayer();
	          emp.setVideoSurface(mpf.newVideoSurface(c));
	          emp.setEnableKeyInputHandling(false);
	          emp.setEnableMouseInputHandling(false);
	          emp.setVolume(0);
	          emp.prepareMedia(pathvideo);
	          emp.play();
	          emp.addMediaPlayerEventListener(new MediaPlayerEventAdapter() {
	        	    @Override
	        	    public void finished(MediaPlayer mediaPlayer) {
	        	        frameVideo.dispatchEvent(new WindowEvent(frameVideo,WindowEvent.WINDOW_CLOSING));
	        	    }
	        	});
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
	
	
	changeTexte.addActionListener(new ActionListener() {
		
	

		@Override
		public void actionPerformed(ActionEvent e) {
			
			if(modeinfo ==0) {
				decoded=webcamphoto.decodedText;
				switch(ID) {
				
				case "E1007":
					texte.setText(englishclassE1007);
					break;
				case "E2004":
					texte.setText(englishclassE2004);
					break;
				case "E2003":
					texte.setText(englishclassE2003);
					break;
				}
				
				modeinfo=1;
			}else {
				
				texte.setText(decoded);
				modeinfo =0;
				
			}
			
		}
	});
	
		handicap.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(modeinfo ==0) {
					decoded=webcamphoto.decodedText;
					switch(ID) {
					
					case "E1007":
						texte.setText(HE_1007);
						break;
					case "E2004":
						texte.setText(HE_2004);
						break;
					case "E2003":
						texte.setText(HE_2003);
						break;
					}
					
					modeinfo=2;
				}else {
					
					texte.setText(decoded);
					modeinfo =0;
					
				}
					
					
				
				
			}
		});

	
	
	ENGLISH.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			Mode="EN";
			
			
			if(modeinfo== 0) {
				texte.setText(webcamphoto.decodedText);
				
			}else if(modeinfo==1) {
				switch(ID) {
				
				case "E1007":
					texte.setText(englishclassE1007);
					break;
				case "E2004":
					texte.setText(englishclassE2004);
					break;
				case "E2003":
					texte.setText(englishclassE2003);
					break;
				}
				
				
			}else if(modeinfo==2) {
switch(ID) {
				
				case "E1007":
					texte.setText(HE_1007);
					break;
				case "E2004":
					texte.setText(HE_2004);
					break;
				case "E2003":
					texte.setText(HE_2003);
					break;
				}
				
			}
			
		}
	} );
	
	
	
	
	FRENCH.addActionListener(new ActionListener(){
	      public void actionPerformed(ActionEvent event){
	    	  
	    	  Object source = event.getSource();
	    	  
	  		if(source == FRENCH && Mode.equals("EN")&& modeinfo==0){
	  			System.out.println("FR mode");
	  			
	  			
	  			texte.setText(texte.getText().replaceFirst("Day", "Jour" ).replaceFirst("Room","Salle").replaceFirst("Time", "Heure").replaceFirst("Subject", "Matière"));
	  			
	  			for(int i =0;i<7;i++) {
	  			if(texte.getText().contains(englishday[i])) {
	  				texte.setText(texte.getText().replaceFirst(englishday[i], frenchday[i]));
	  				
	  				
	  			}}
	  			if(texte.getText().contains("am")) {
	  				texte.setText(texte.getText().replaceFirst("to", "à").replaceFirst("am"," "));
	  				
	  			}else {
	  				texte.setText(texte.getText().replaceFirst("to", "à").replaceFirst("pm", " "));
	  				for(String num : numbers) {
	  					
	  					if(texte.getText().contains(num+":")) {
	  						
	  						if(texte.getText().contains("0"+num+":")) {
	  							System.out.println(num);
	  						texte.setText(texte.getText().replaceFirst("0"+num, Integer.toString(Integer.parseInt(num)+12)));}else if(texte.getText().contains(" "+num+":")) {
	  						
	  							System.out.println(num);
	  							texte.setText(texte.getText().replaceFirst(" "+num, Integer.toString(Integer.parseInt(num)+12)));}
	  					}
	  					
	  				}
	  				
	  				
	  			}
	  			
	  			
	    	 
	    	  
	      }
	  		if(modeinfo==1) {
	  			switch(ID) {
	  			
	  			case "E1007":
	  				texte.setText(frenchclassE1007);
	  				break;
	  			case "E2004":
					texte.setText(frenchclassE2004);
					break;
				case "E2003":
					texte.setText(frenchclassE2003);
					break;
	  			
	  			
	  			}
	  			
	  			
	  		}
	  		if(modeinfo==2) {
	  			switch(ID) {
	  			
	  			case "E1007":
	  				texte.setText(HF_1007);
	  				break;
	  			case "E2004":
					texte.setText(HF_2004);
					break;
				case "E2003":
					texte.setText(HF_2003);
					break;
	  			
	  			
	  			}
	  			
	  			
	  		}
	  		
	  		
	  		
	  		
	  		Mode = "FR";   }
	
	});
	
}
	
	
            	 
	public void playit(int soundRequired) {
		String fn = null;
		File sound; 

		switch (soundRequired) {
		case SOUND1:
			fn = "E2004.wav";
			break;
		case SOUND2:
			fn ="E1007.wav";
			break;
		case SOUND3:
			fn ="E2003.wav";
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





