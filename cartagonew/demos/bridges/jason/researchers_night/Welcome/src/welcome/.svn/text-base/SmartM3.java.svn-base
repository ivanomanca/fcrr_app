package welcome;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;

import sofia_kp.KPICore;
import sofia_kp.SSAP_XMLTools;
import sofia_kp.iKPIC_subscribeHandler;

import cartago.Artifact;
import cartago.OPERATION;
import cartago.OpFeedbackParam;

public class SmartM3 extends Artifact implements iKPIC_subscribeHandler {

	//Useful constants
	private static final String DEFAULT_HOST = "127.0.0.1";
	private static final String DEFAULT_PORT = "10010";
	private static final String DEFAULT_SPACENAME = "X";
	
	// smart-m3 references
	private KPICore kp = null;
	private SSAP_XMLTools xmlTools=null;
	
	// space address
	private String host; 
	private String port; 
	private String spaceName;
	
	// state info 
	private boolean joined = false;
	private int counter = 0;
	
	// debug info
	private boolean verbose = false;
	
	public void init(String host, String port, String spaceName) {
		if (host == null || host.equals("")) 
			this.host = DEFAULT_HOST;
		else
			this.host = host;
		
		if (port == null || port.equals("")) 
			this.port = DEFAULT_PORT;
		else
			this.port = port;
		
		if (spaceName == null || spaceName.equals("")) 
			this.spaceName = DEFAULT_SPACENAME;
		else
			this.spaceName = spaceName;
		
		this.kp = new KPICore(this.host,Integer.parseInt(this.port),this.spaceName);
		this.xmlTools = new SSAP_XMLTools(null,null,null);
		this.kp.setEventHandler(this);
		
		// this.kp.enable_debug_message();
		// this.kp.enable_error_message();
	}
	
	/* TO BE IMPLEMENTED ... (if needed)
	 *     	public String queryRDF( Vector<Vector<String>> queryList );
	 *     	public String insert( Vector<Vector<String>> queryList );
	 *     	public String remove( Vector<Vector<String>> queryList );
 	 *		public String update( Vector<Vector<String>>  newTripleVector, Vector<Vector<String>>  oldTripleVector);
	 */
	
	
	@OPERATION public void join(OpFeedbackParam<Boolean> res) {
		if (joined) {
			res.set(true);
			counter ++ ;
			return ;
		}
		
		String xml = kp.join();
	    boolean ack = xmlTools.isJoinConfirmed(xml);
	    print("Join confirmed: " + (ack?"YES":"NO")+"\n");
	    
	    if (!ack) {
	    	print("XML message: " + xml); 
	    	res.set(false);
	    	return ;
	    }
	    res.set(true);
	    joined = true;
	    counter ++ ;
	}
	
	@OPERATION public void leave(OpFeedbackParam<Boolean> res) {
		if (counter > 0) {
			res.set(true);
			counter --;
			return ;
		}
		
	     String xml = kp.leave();
	     boolean ack = xmlTools.isLeaveConfirmed(xml);
	     print("Leave confirmed: " + (ack?"YES":"NO"));
	     
	     if (!ack) {
	    	 print("XML message: " + xml); 
	    	 res.set(false);
	    	 return ;
	     }
		 res.set(true);
		 joined = false;
	}
	
	@OPERATION public void queryRDF(String s, String p, String o, String s_type, String o_type, OpFeedbackParam<Vector<Vector<String>>> res) {
		if (!joined) {
			print("space not joined yet");
			res.set(null);
			return ;
		}
		
		// due to binding issues with null values
		if (s.equals("null"))
			s = null;
		if (o.equals("null"))
			o = null;
		if (p.equals("null"))
			p = null;
		
		String xml = kp.queryRDF(s,p,o,s_type,o_type);
		boolean ack=xmlTools.isQueryConfirmed(xml);
		print("Query confirmed: " + (ack?"YES":"NO"));
		
		// stampa di prova
		print(" PARAMETERS: " + s + " " + p + " " + o);
		
		if (!ack) {
			print("XML message: " + xml);
			res.set(null);
			return ;
		}

		res.set(xmlTools.getQueryTriple(xml));
		
		print("RESULT: " + res.get().size());
		Iterator<Vector<String>> it = res.get().iterator();
		while(it.hasNext()) {
			Vector<String> tmp = it.next();
			print(tmp.get(0) + " " + tmp.get(1) + " " + tmp.get(2));
		}
		
	}
	
	/*@OPERATION public Vector<Vector<String>> queryRDF( Vector<Vector<String>> queryList ) {
		String xml = kp.queryRDF(queryList);
		boolean ack=xmlTools.isQueryConfirmed(xml);
		print("Query confirmed: " + (ack?"YES":"NO"));
		
		if (!ack) {
			print("XML message: " + xml);
			return null;
		}
		
		return xmlTools.getQueryTriple(xml);
	}*/
	
	@OPERATION public void insert(String s, String p, String o, String s_type, String o_type, OpFeedbackParam<Boolean> res) {
		if (!joined) {
			print("space not joined yet");
			res.set(false);
			return ;
		}
		
		String xml = kp.insert(s,p,o,s_type,o_type);
		boolean ack = xmlTools.isInsertConfirmed(xml);
		print("Insert confirmed: " + (ack?"YES":"NO"));
		
		if (!ack) {
			print("XML message: " + xml);
			res.set(false);
			return ;
		}
		res.set(true);
	}
	
	@OPERATION public void remove(String s, String p, String o, String s_type, String o_type, OpFeedbackParam<Boolean> res) {
		if (!joined) {
			print("space not joined yet");
			res.set(false);
			return ;
		}
		
		String xml = kp.remove(s,p,o,s_type,o_type);
		boolean ack = xmlTools.isRemoveConfirmed(xml);
		print("Remove confirmed: " + (ack?"YES":"NO"));	 
		
		if(!ack) { 
			print("XML message: " + xml);
			res.set(false);
			return ;
		}
		res.set(true); // dovrei ritornare la tupla/e rimossa/e
	}
	
	@OPERATION public void update(String sn, String pn, String on, String sn_type, String on_type, String so, String po, String oo, String so_type, String oo_type, OpFeedbackParam<Boolean> res) {
		if (!joined) {
			print("space not joined yet");
			res.set(false);
			return ;
		}
		
		String xml = kp.update(sn,pn,on,sn_type,on_type,so,po,oo,so_type,oo_type);
		boolean ack = xmlTools.isUpdateConfirmed(xml);
		print("Update confirmed: " + (ack?"YES":"NO"));
		
		if(!ack) { 
			print("XML message: " + xml);
			res.set(false);
			return ;
		}
		res.set(true);
	}
	
	@OPERATION public void subscribeRDF(String s, String p, String o, String o_type, OpFeedbackParam<String> res) {
		if (!joined) {
			print("space not joined yet");
			res.set(null);
			return ;
		}
		
		// due to binding issues with null values
		if (s.equals("null"))
			s = null;
		if (o.equals("null"))
			o = null;
		if (p.equals("null"))
			p = null;
		
		String xml = kp.subscribeRDF(s,p,o,o_type);
		boolean ack = xmlTools.isSubscriptionConfirmed(xml);
		
		if (!ack) {
			res.set(null);
			print("XML message: " + xml);
			return ;
		}
		res.set(xmlTools.getSubscriptionID(xml));
	}
	
	@OPERATION public void unsubscribe(String subscriptionID, OpFeedbackParam<Boolean> res) {
		if (!joined) {
			print("space not joined yet");
			res.set(false);
			return ;
		}
		
		String xml = kp.unsubscribe(subscriptionID); // kp.unsubscribe() unsub the last one
		boolean ack = true;//xmlTools.isUnSubscriptionConfirmed(xml); // cannot check due to malformed resp msg 
		
		if(!ack) { 
			print("XML message: " + xml);
			res.set(false);
			return ;
		}
		res.set(true);
	}
	
	@OPERATION public void getHost(OpFeedbackParam<String> res) {
		res.set(host);
	}
	
	@OPERATION public void getPort(OpFeedbackParam<String> res) {
		res.set(port);
	}
	
	@OPERATION public void getSpaceName(OpFeedbackParam<String> res) {
		res.set(spaceName);
	}
	
	@OPERATION public void verbose(boolean verbose) {
		this.verbose = verbose;
	}
	
	private void print(String msg) {
		if (verbose)
			System.out.print("[" + spaceName + "]: " + msg + "\n");
	}

	// subscriptions event handler
	public void kpic_SIBEventHandler(String xml) { // call-back method called by the api's event-handler
		
		SSAP_XMLTools tmpTool = new SSAP_XMLTools();
		Vector<Vector<String>> new_triples = tmpTool.getNewResultEventTriple(xml);
		Vector<Vector<String>> old_triples = tmpTool.getObsoleteResultEventTriple(xml);
		
		String op_type = "";
		if (new_triples.size() != 0 && old_triples.size() == 0)
			op_type = "insert";
		else if (new_triples.size() == 0 && old_triples.size() != 0)
			op_type = "remove";
		else if (new_triples.size() != 0 && old_triples.size() != 0)
			op_type = "update";
		
		print("tuple has been " + (op_type.equals("insert")?"inserted ":op_type.equals("remove")?"removed ":"updated ") + "from the space \nOLD: " + old_triples + " NEW: " + new_triples);
		this.execInternalOp("signalEvent", tmpTool.getSubscriptionID(xml),op_type,old_triples,new_triples); 
		print("internal op executed?!");
	}
	
	
	@OPERATION private void signalEvent(String subID,String op_type, Vector<Vector<String>> old_triples, Vector<Vector<String>> new_triples) {
		if (op_type.equals("update")) {
			ArrayList<String> o = new ArrayList<String>();
			o.addAll(old_triples.get(0));
			ArrayList<String> n = new ArrayList<String>();
			n.addAll(new_triples.get(0));
			this.signal("smartM3_event",subID,op_type,o,n);
			print("signal smartM3_event(" + subID + "," + op_type + ", " + old_triples + ", " + new_triples + ") generated");
		}
		else {
			Vector<Vector<String>> triples = (op_type.equals("insert")?new_triples:old_triples);
			ArrayList<String> l = new ArrayList<String>();
			l.addAll(triples.get(0));
			print(l + "");
			this.signal("smartM3_event",subID,op_type,l);
			print("signal smartM3_event(" + subID + "," + op_type + ", " + triples + ") generated");
		}
	}
}
