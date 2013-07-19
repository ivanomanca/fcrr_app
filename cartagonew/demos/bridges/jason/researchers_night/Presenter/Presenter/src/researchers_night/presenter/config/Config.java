package researchers_night.presenter.config;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import cartago.LINK;
import cartago.OPERATION;
import cartago.OpFeedbackParam;

import android.content.Context;

import jaca.android.dev.JaCaArtifact;

/**
 * 
 * @author mguidi
 *
 */
public class Config extends JaCaArtifact {

	private static final String FILENAME = "config.conf";
	
	private String mAddress;
	
	
	protected void init() {
		try {
			FileInputStream fis = getApplicationContext().openFileInput(FILENAME);
			DataInputStream dis = new DataInputStream(fis);
			
			mAddress = dis.readUTF();
			
			dis.close();
			fis.close();
			
			
		} catch (FileNotFoundException e) {
			mAddress = "";
		} catch (IOException e) {
			mAddress = "";
		}
	}
	
	@OPERATION void getAddress(OpFeedbackParam<String> address) {
		address.set(mAddress);
	}
	
	
	@LINK void resetAddress() {
		mAddress = "";
		try {
			FileOutputStream fos = getApplicationContext().openFileOutput(FILENAME, Context.MODE_PRIVATE);
			fos.close();
		} catch (FileNotFoundException e) {
			
		} catch (IOException e) {
			
		}
		
	}
	
	@LINK void setAddressConfig(String address, boolean remember) {
		mAddress = address;
		
		try {
			FileOutputStream fos = getApplicationContext().openFileOutput(FILENAME, Context.MODE_PRIVATE);
			DataOutputStream dos = new DataOutputStream(fos);
			
			if(remember) {
				dos.writeUTF(mAddress);
			} else {
				dos.writeUTF("");
			}
			
			dos.close();
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
}
