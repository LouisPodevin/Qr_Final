package qr;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamResolution;
import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;

import java.awt.image.BufferedImage;

public class main {
	
	static String decodeQRCode(File qrCodeimage) throws IOException {
        BufferedImage bufferedImage = ImageIO.read(qrCodeimage);
        LuminanceSource source = new BufferedImageLuminanceSource(bufferedImage);
        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

        try {
            Result result = new MultiFormatReader().decode(bitmap);
            return result.getText();
        } catch (NotFoundException e) {
           
            return null;
        }
    }

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		System.out.println(Webcam.getWebcams());
		Webcam webcam =Webcam.getWebcams().get(0);
		

		
		
//		webcam.addWebcamListener(new WebcamListener()
//		{
//			
//			public void webcamOpen(WebcamEvent arg0) {
//				System.out.println("WebcamOpen");
//			}
//
//			@Override
//			public void webcamClosed(WebcamEvent arg0) {
//				// TODO Auto-generated method stub
//				System.out.println("WebcamClose");
//				
//			}
//
//			@Override
//			public void webcamDisposed(WebcamEvent arg0) {
//				// TODO Auto-generated method stub
//				System.out.println("Webcam disposed");
//			}
//
//			@Override
//			public void webcamImageObtained(WebcamEvent arg0) {
//				// TODO Auto-generated method stub
//				System.out.println("Image Taken");
//			}
//		});
		
		webcam.setViewSize(WebcamResolution.VGA.getSize());
		webcam.open();	
		webcam.close();
		
		window fenetre = new window();
		
//		 try {
//	            File file = new File("test.png");
//	            String decodedText = decodeQRCode(file);
//	            if(decodedText == null) {
//	                System.out.println("No QR Code found in the image");
//	            } else {
//	                System.out.println("Decoded text = " + decodedText);
//	            }
//	        } catch (IOException e) {
//	            System.out.println("Could not decode QR Code, IOException :: " + e.getMessage());
//	        }
	    }
		
	}


