package cartago.tools;

import cartago.*;

public class TupleSpace extends Artifact {
	
	TupleSet tset;
	
	void init(){
		tset = new TupleSet();
	}
	
	@OPERATION void out(String name, Object... args){
		tset.add(new Tuple(name,args));
	}
	
	@OPERATION void in(String name, Object... params){
		TupleTemplate tt = new TupleTemplate(name,params);
		await("foundMatch",tt);
		Tuple t = tset.removeMatching(tt);
		bind(tt,t);
	}

	@OPERATION void rd(String name, Object... params){
		TupleTemplate tt = new TupleTemplate(name,params);
		await("foundMatch",tt);
		Tuple t = tset.readMatching(tt);
		bind(tt,t);
	}
	
	private void bind(TupleTemplate tt, Tuple t){
		Object[] tparams = t.getContents();
		int index = 0;
		for (Object p: tt.getContents()){
			if (p instanceof OpFeedbackParam<?>){
				((OpFeedbackParam) p).set(tparams[index]);
			}
			index++;
		}
	}
	
	@GUARD boolean foundMatch(TupleTemplate tt){
		return tset.hasTupleMatching(tt);
	}
}
