package jaca.android.dev;

/**
 * Developers must extend this class to define an artifact usable from 
 * agents for observe and control Android ListActivity GUI
 * 
 * <table border="0" width="100%">
 * <tr colspan="2"><td>#### Observable Property #####</td></tr>
 * <tr><td width="50%">state(State)</td><td width="50%">State = {"running","paused","stopped"}</td></tr>
 * </table>
 * 
 * @author mguidi
 *
 */
public abstract class ListActivityArtifact extends GUIArtifact {
	
	
    /**
     * Link onDestroy event to a specific operation of the artifact
     * <br/><br/>
	 * <b>Operation</b> prototype like: @INTERNAL_OPERATION void onListItemClick(ListView l, View v, int position, long id)
     * 
     * @param opName name of the operation to trigger
     */
    protected void linkOnListItemClickEventToOp(String opName) {
    	linkEventToOp(mActivity, ActivityEventsFetcher.ON_LIST_ITEM_CLICK, opName);
    }
}