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
	
	
	public JLabel texte = new JLabel();
	public final int SOUND1 = 0;
	private boolean soundPlayed = false;
	public static String ID ;
	public static EmbeddedMediaPlayer emp;
	
	public static Webcam webcam = Webcam.getDefault();
	
	
	

	public ImageIcon iconSON = new ImageIcon("icon_son.png");
	public ImageIcon iconSON2 = new ImageIcon("icon_son2.png");
	public JButton SON = new JButton(iconSON);
	
	public ImageIcon iconVIDEO = new ImageIcon("icon_video.png");
	public ImageIcon iconVIDEO2 = new ImageIcon("icon_video2.png");
	public JButton VIDEO = new JButton(iconVIDEO);
	
	public ImageIcon iconCAMERA = new ImageIcon("icon_camera.png");
	public ImageIcon iconCAMERA2= new ImageIcon("icon_camera2.png");
	public JButton CAMERA = new JButton(iconCAMERA);
	
	
	
	public window() throws IOException {
		
	   
		
        webcam.setViewSize(WebcamResolution.VGA.getSize());
       
        WebcamPanel webcamPanel = new WebcamPanel(webcam);
        webcamPanel.setSize( 768, 480);
       
        ImagePanel IMAGE = new ImagePanel(new ImageIcon("fond.jpg").getImage());
        IMAGE.setLayout(null);
        IMAGE.setBounds(0, 0, 1920, 1080);
        
        
      
        Font font = new Font("Arial",Font.ITALIC,60);
        
 ////////////////////JPANEL///////////////////////////////////////////
                              
        JPanel TEXTE = new JPanel();
        TEXTE.setBackground(new Color(0,0,0,100));
        TEXTE.setBounds(690,10, 1215, 500);
       
                
        JPanel LANGUE = new JPanel();
        LANGUE.setLayout(null);
        LANGUE.setBackground(new Color(0,0,0,100));
        LANGUE.setBounds(10, 520, 670, 455);
        
        
        
        texte.setBounds(800,10,1215,500);
        texte.setForeground(Color.white);
        texte.setFont(font);
       
       
                
 //////////////////////////////////////////////////////////////////
        
 /////////////////JBUTTON//////////////////////////////////////////
        
        SON.setBounds(690,520, 630, 455);
        SON.setOpaque(false);
        SON.setFocusPainted(false);
        SON.setContentAreaFilled(false);
        SON.setBorderPainted(false);
       
        VIDEO.setBounds(1330, 520, 575, 455);
        VIDEO.setOpaque(false);
        VIDEO.setFocusPainted(false);
        VIDEO.setContentAreaFilled(false);
        VIDEO.setBorderPainted(false);
        
        CAMERA.setBounds(10,10,670, 500);
        CAMERA.setOpaque(false);
        CAMERA.setFocusPainted(false);
        CAMERA.setContentAreaFilled(false);
        CAMERA.setBorderPainted(false);
        
        
        
//        MOVIE.setBounds(1330, 520, 575, 455);
//        MOVIE.setOpaque(false);
//        MOVIE.add(c);
//        c.setBounds(800, 500, 350, 350);
//        NativeLibrary.addSearchPath(RuntimeUtil.getLibVlcLibraryName(),"C:/Program Files/VideoLAN/VLC");
//        Native.loadLibrary(RuntimeUtil.getLibVlcLibraryName(), LibVlc.class);
//        MediaPlayerFactory mpf = new MediaPlayerFactory();
//        emp =mpf.newEmbeddedMediaPlayer();
//        emp.setVideoSurface(mpf.newVideoSurface(c));
//        emp.setEnableKeyInputHandling(false);
//        emp.setEnableMouseInputHandling(false);
//        emp.setVolume(0);
        

        JFrame frame = new JFrame();
        frame.getContentPane().setBackground(Color.white);
        frame.setExtendedState(MAXIMIZED_BOTH);
        frame.setTitle("QUICK TIMETABLE");
        
        
        frame.add(TEXTE);
        frame.add(LANGUE);
        frame.add(texte);
        frame.add(SON);
        frame.add(VIDEO);
        frame.add(CAMERA);
        frame.add(IMAGE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null); 
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        
        

	SON.getModel().addChangeListener(new ChangeListener() {
	  public void stateChanged(ChangeEvent e) {
        ButtonModel model = (ButtonModel) e.getSource();
        
        if (model.isRollover()) {
        	 SON.setIcon(iconSON);
        	 
        } if (model.isPressed()) {
            SON.setIcon(iconSON2);
            
        } else {
            SON.setIcon(iconSON);
            
        }
    }
});
	
	VIDEO.getModel().addChangeListener(new ChangeListener() {
		  public void stateChanged(ChangeEvent e) {
	        ButtonModel model = (ButtonModel) e.getSource();
	        
	        if (model.isRollover()) {
	        	 VIDEO.setIcon(iconVIDEO);
	        	 
	        } if (model.isPressed()) {
	            VIDEO.setIcon(iconVIDEO2);
	            
	        } else {
	            VIDEO.setIcon(iconVIDEO);
	            
	        }
	    }
	});
	
	CAMERA.getModel().addChangeListener(new ChangeListener() {
		  public void stateChanged(ChangeEvent e) {
	        ButtonModel model = (ButtonModel) e.getSource();
	        
	        if (model.isRollover()) {
	        	 CAMERA.setIcon(iconCAMERA);
	        	 
	        } if (model.isPressed()) {
	             CAMERA.setIcon(iconCAMERA2);
	            
	        } else {
	             CAMERA.setIcon(iconCAMERA);
	            
	        }
	    }
	});
	
	CAMERA.addActionListener(new ActionListener(){
	      public void actionPerformed(ActionEvent event){
	    	  JFrame frameCamera = new JFrame();
	          frameCamera.getContentPane().setBackground(Color.white);
	          frameCamera.add(webcamPanel);
	          frameCamera.pack();
	          frameCamera.setLocationRelativeTo(null);
	          frameCamera.setLayout(null); 
	          frameCamera.setVisible(true);
	      }
	    });
	
	new webcamthread("t1",webcam,texte);

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
	

	public webcamthread(String name, Webcam webcam,JLabel texte,JButton SON) {
		
		super(name);
		this.start();
		try {
    		
    		while(true) {
			ImageIO.write(webcam.getImage(),"PNG",new File("test.png"));
			File file = new File("test.png");
            String decodedText = main.decodeQRCode(file);
          
            if(decodedText == null) {
                texte.setText("No QR Code found in the image"); 
                
            }else 
                System.out.println("Decoded text = " + decodedText);
                texte.setText(decodedText);
                window.ID =decodedText.split("<br>")[decodedText.split("<br>").length-1].substring(0,4);

                try {
				this.sleep(10000);
				} catch (InterruptedException e) {
					
					e.printStackTrace();
				}
            } 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.stop();
		
		
	}

	public webcamthread(String string, Webcam webcam, JLabel texte) {
		// TODO Auto-generated constructor stub
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



