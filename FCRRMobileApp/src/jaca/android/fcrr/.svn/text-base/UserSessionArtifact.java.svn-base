package jaca.android.fcrr;

import jaca.android.dev.JaCaArtifact;
import cartago.LINK;
import cartago.OpFeedbackParam;

public class UserSessionArtifact extends JaCaArtifact {
	
	private Integer userID;
	
	protected void init(){
		this.userID = 0;
	}

	@LINK void setUserID(Integer userID){
		this.userID = userID;
	}

	@LINK void getUserID(OpFeedbackParam<Integer> userID){
		userID.set(this.userID);
	}
}
