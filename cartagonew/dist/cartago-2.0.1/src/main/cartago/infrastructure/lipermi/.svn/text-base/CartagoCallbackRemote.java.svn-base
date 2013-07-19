package cartago.infrastructure.lipermi;

import cartago.CartagoEvent;
import cartago.ICartagoCallback;

/**
 * 
 * @author mguidi
 *
 */
public class CartagoCallbackRemote implements ICartagoCallbackRemote {

	private static final long serialVersionUID = 1L;
	private ICartagoCallback mCallback;
	
	public CartagoCallbackRemote(ICartagoCallback callback) {
		mCallback = callback;
	}
	
	@Override
	public void notifyCartagoEvent(CartagoEvent ev) {
		mCallback.notifyCartagoEvent(ev);
	}

}
