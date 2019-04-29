
package qr;

import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

import javax.imageio.ImageIO;
import javax.swing.JLabel;

import com.github.sarxos.webcam.Webcam;

class  webcamphoto extends Thread {
	
	private IDcase executeid;
	public static String decodedText;
		public webcamphoto(String name)  {
		super(name);
		executeid = new IDcase();
		
		
	}
	public void run()    {
		
		
		
			
		
				synchronized(this){try {
					this.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			
		}
		
		try {
    		
    		
    			while(true){
    				File file = new File("test.png");
			ImageIO.write(window.webcam.getImage(),"PNG",file);
			
             decodedText = main.decodeQRCode(file);
            if(decodedText == null) {
            	switch(window.Mode) {
            	
            	case "EN":
                
                window.texte.setText("<html>No QR <br> Code found in the image</html>");
                break;
                
            	case "FR":
            		
            		window.texte.setText("<html>Aucun QrCode <br> Trouvé dans cette image</html>");
            	break;
                
            	}
                
            }else {
            	
            	
            	
            	//System.out.println("datetab:"+datetab[1]+datetab[0].subSequence(1, 2)+datetab[2]+"timedu:"+timedu+"NEh:"+NEh);
            	
                System.out.println("Decoded text = " + decodedText);
              
                window.ID =decodedText.split("<br>")[decodedText.split("<br>").length-1].substring(6,11);
                System.out.println(window.ID);
                window.frameCamera.dispatchEvent(new WindowEvent(window.frameCamera, WindowEvent.WINDOW_CLOSING));
                
               
                
               
                
               
                executeid.start();
                
               
               
                synchronized(this){
                try {
                	System.out.println("attente de du prochain lancement du QRreader");
					this.wait();
					
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}}
                
              
           
            
            } }
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	
		
		
	}
  }




class IDcase implements Runnable {
	private Thread tid;
	
	
	public void run() {
		
		System.out.println("L'ID trouvé est :"+window.ID);
		
		switch(window.ID) {
		
		case "E2004":
			window.sound_required = 0;
			window.pathvideo = "E2004.mp4";
			time();
			break;
		case "E1007":
			window.sound_required = 1;
			window.pathvideo = "E1007.mp4";
			time();
			break;
		case "E2003":
			window.sound_required = 2;
			window.pathvideo = "E2003.mp4";
			time();
			break;
		default :
			window.texte.setText("<html>This QrCODE is desable<br>please go to reception<br>for more informations</html>");
			
		
		}
		synchronized(tid) {
			
			try {

				tid.wait(30000);

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		window.ID="undifined";
		window.texte.setText(null);
	
		
		
		
		
		
		
	}
	public void start() {
		
		tid= new Thread(this,"execution de l'id");
		tid.start();
	}
	
	public void time() {
		LocalDateTime currentime = LocalDateTime.now();
		String[] datetab =webcamphoto.decodedText.split("</html>")[1].split("/");
    	int timedu = Integer.parseInt(webcamphoto.decodedText.split("<br>")[1].substring(7, 8));
    	String NEh = webcamphoto.decodedText.split("<br>")[1].substring(webcamphoto.decodedText.split("<br>")[1].length()-2);
    	if(NEh.equals("pm")) {
    		timedu +=12;
    	}
    	 LocalDateTime dateqr = LocalDateTime.of(Integer.parseInt(datetab[2]),Integer.parseInt(datetab[1]),Integer.parseInt(datetab[0]),timedu,0,0);
         System.out.println(dateqr.toString());
          
          if(dateqr.isAfter(currentime)) {
          	window.texte.setText(webcamphoto.decodedText);
               
          }else {window.texte.setText("<html>Hello, I’m sorry,<br> class you are attending is over<br> (or has started).</html>");}
		
	}
	
	
	
	
	
}
    



