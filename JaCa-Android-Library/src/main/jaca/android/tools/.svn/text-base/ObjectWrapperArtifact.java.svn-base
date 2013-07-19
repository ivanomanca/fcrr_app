package jaca.android.tools;

import jaca.android.dev.JaCaArtifact;

import java.lang.reflect.Method;

import cartago.IArtifactGuard;
import cartago.IArtifactOp;
import cartago.OpFeedbackParam;


/**
 * An artifat that wraps an object with a given interface. 
 * All interface methods are map into artifact operation.
 * 
 * @author asanti
 *
 */
public class ObjectWrapperArtifact extends JaCaArtifact {
	
	/**
	 * Wrap object with a given interface to an artifact.
	 * @param objInterface object interface class
	 * @param object object instance
	 */
	protected void init(Class<?> objInterface, Object object) {
		Method[] methods = objInterface.getMethods();
		IArtifactGuard guard = new TrueArtifactGuard();
		
		for (int i=0; i<methods.length; i++) {
			IArtifactOp op = new InterfaceOpMethod(object, methods[i]);
			defineOp(op, guard);
		}
	}
	
	class InterfaceOpMethod implements IArtifactOp {
		
		private Method mMethod;
		private Object mObject;

		public InterfaceOpMethod(Object object, Method method) {
			mObject = object;
			mMethod = method;
		}
		
		@SuppressWarnings("unchecked")
		@Override
		public void exec(Object[] actualParams) throws Exception {
			try {
				Object[] param = new Object[(actualParams.length-1 <0) ? 0 : actualParams.length-1];
				for (int i=0; i<param.length; i++) {
					param[i] = actualParams[i];
				}
				Object result = mMethod.invoke(mObject, param);
				if (actualParams.length>0 && actualParams[actualParams.length-1] instanceof OpFeedbackParam<?>) {
					OpFeedbackParam<Object> feedBack = (OpFeedbackParam<Object>) actualParams[actualParams.length-1];
					feedBack.set(result);
				}
			} catch(Exception e){
				failed(e.getLocalizedMessage());
			}
		}

		@Override
		public int getNumParameters(){
			if (mMethod.getReturnType() == Void.TYPE) {
				return mMethod.getParameterTypes().length;
			} else {
				return mMethod.getParameterTypes().length + 1;
			}
		}

		@Override
		public String getName(){
			return mMethod.getName();
		}
		
		@Override
		public boolean isVarArgs(){
			return mMethod.isVarArgs();
		}
		
	}

	class TrueArtifactGuard implements IArtifactGuard {

		@Override
		public boolean eval(Object[] actualParams) throws Exception {
			return true;
		}

		@Override
		public String getName() {
			return null;
		}

		@Override
		public int getNumParameters() {
			return 0;
		}
		
	}
}
