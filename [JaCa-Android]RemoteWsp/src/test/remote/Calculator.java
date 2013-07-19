package test.remote;

import cartago.Artifact;
import cartago.OPERATION;
import cartago.OpFeedbackParam;

public class Calculator extends Artifact {

	public void init(){
	}
	
	@OPERATION public void sum(int a, int b, OpFeedbackParam<Integer> res) throws Exception{
		res.set(a+b);
		Thread.sleep(1000);
	}
	
	@OPERATION public void sub(int a, int b, OpFeedbackParam<Integer> res){
		res.set(a-b);
	}	

	@OPERATION public void division(int a, int b, OpFeedbackParam<Integer> res){
		res.set(a/b);
	}	
}