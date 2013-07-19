/**
 * CARTAGO-JASON-BRIDGE - Developed by aliCE team at deis.unibo.it
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */
package c4jason;

import jason.asSyntax.ASSyntax;
import jason.asSyntax.Atom;
import jason.asSyntax.ListTerm;
import jason.asSyntax.ListTermImpl;
import jason.asSyntax.Literal;
import jason.asSyntax.NumberTerm;
import jason.asSyntax.StringTerm;
import jason.asSyntax.Structure;
import jason.asSyntax.Term;
import jason.asSyntax.VarTerm;
import jason.bb.BeliefBase;
import java.util.Collection;
import java.util.Map;
import cartago.*;
import cartago.events.*;

public class TermConversionUtils {

	static private final Term OBS_PROP_PERCEPT = ASSyntax.createStructure("percept_type",ASSyntax.createString("obs_prop"));
	static private final Term OBS_EV_PERCEPT = ASSyntax.createStructure("percept_type",ASSyntax.createString("obs_ev"));	

	/**
	 * Convert a Jason term into a CArtAgO/Java Object
	 * 
	 * @param t Jason term
	 * @param lib Java library - each agent has its own one
	 * @return
	 */
	static public Object termToObject(Term t, JavaLibrary lib){
	    if (t instanceof VarTerm){
			VarTerm var = (VarTerm)t;
			if (var.isGround()){
				return termToObject(var.getValue(),lib);
			} else {
				return new OpFeedbackParam<Object>();
			}
	    } else if (t.isAtom()){
			Atom t2 = (Atom)t;
			if (t2.equals(Atom.LTrue)){
				return Boolean.TRUE;
			} else if (t2.equals(Atom.LFalse)){
				return Boolean.FALSE;
			} else {
				Object obj = lib.getObject(t2);
				if (obj != null){
					return obj;
				} else {
					return t2.getFunctor();
				}
			}
	    } else if (t.isNumeric()){
			NumberTerm nt = (NumberTerm)t;
			double d = nt.solve();
			if (((byte)d)==d){
				return (byte)d; 
			} else if (((int)d)==d){
				return (int)d;
			} else if (((float)d)==d){
				return (float)d;
			} else if (((long)d)==d){
				return (long)d;
			} else {
				return d;
			}
		} else if (t.isString()){
			return ((StringTerm)t).getString(); //(t.toString()).substring(1,t.toString().length()-1);
		} else if (t.isList()){
			ListTerm lt = (ListTerm)t;
			int i = 0;
			Object[] list = new Object[lt.size()];
			for (Term t1: lt){
				list[i++] = termToObject(t1,lib);
			}
			return list;
		} else {
			return t.toString();
		}
	}

	
	/**
	 * Convert Java Object into a Jason term
	 */
    static public Term objectToTerm(Object value, JavaLibrary lib) throws Exception {
		if (value == null){
			return ASSyntax.createVar("_");
		} else if (value instanceof OpFeedbackParam<?>){
			return objectToTerm(((OpFeedbackParam<?>)value).get(),lib);
		} else if (value instanceof Number) {
            try {
                return ASSyntax.parseNumber(value.toString());
            } catch (Exception ex){
                return ASSyntax.createString(value.toString());
            }
        } else if (value instanceof String) {
            return ASSyntax.createString(value.toString());
        } else if (value instanceof Boolean) {
            return ((Boolean)value).booleanValue() ? Literal.LTrue : Literal.LFalse;
        } else if (value instanceof java.lang.reflect.Array) { 
        	java.lang.reflect.Array array = (java.lang.reflect.Array)value;
        	ListTerm l = new ListTermImpl();
            ListTerm tail = l;
            for (Object e: (Collection)value)
                tail = tail.append(objectToTerm(e,lib));
            return l;             
		} else if (value instanceof Map) {
            ListTerm l = new ListTermImpl();
            ListTerm tail = l;
            Map m = (Map)value;
            for (Object k: m.keySet()) {
                Term pair = ASSyntax.createStructure("map",
                        objectToTerm(k, lib),
                        objectToTerm(m.get(k), lib));
                tail = tail.append(pair);
            }
            return l;             
		} else if (value instanceof ToProlog) {
	        return ASSyntax.parseTerm(((ToProlog)value).getAsPrologStr());
		} /* else if (value instanceof Collection) {
            ListTerm l = new ListTermImpl();
            ListTerm tail = l;
            for (Object e: (Collection)value)
                tail = tail.append(object2term(e,lib));
            return l;             
        } */ else if (value instanceof Tuple) {
        	Tuple t = (Tuple)value;
        	Structure st =  ASSyntax.createStructure(t.getLabel()); 
        	for (int i = 0; i < t.getNArgs(); i++){
        		st.addTerm(objectToTerm(t.getContent(i),lib));		
        	}
        	return st;
        }
	    return lib.registerDynamic(value);
	}
	
	public static Term[] objectArray2termArray(Object[] values, JavaLibrary lib) throws Exception {
        Term[] result = new Term[values.length];
        for (int i=0; i<values.length; i++)
            result[i] = objectToTerm(values[i], lib);
        return result;
    }

	//	
	
	/**
	 * Convert an observable event into a literal
	 */
	static public Literal obsEventToLiteral(ArtifactObsEvent p, JavaLibrary lib) {
		Tuple signal = p.getSignal();
		Object[] contents = signal.getContents();
		try {
		    Literal struct = ASSyntax.createLiteral(signal.getLabel(), objectArray2termArray(contents, lib));		    
	        struct.addAnnot(OBS_EV_PERCEPT);
	        ArtifactId source = p.getArtifactId();
	        Term id = objectToTerm(source, lib);  
	        struct.addAnnot(ASSyntax.createStructure("source", id));
	        struct.addAnnot(ASSyntax.createStructure("artifact_id", id));
	        struct.addAnnot(ASSyntax.createStructure("artifact_name", id, ASSyntax.createString(source.getName())));
	        struct.addAnnot(ASSyntax.createStructure("artifact_type", id, ASSyntax.createString(source.getArtifactType())));
	        struct.addAnnot(ASSyntax.createStructure("workspace", id, ASSyntax.createString(source.getWorkspaceId().getName())));
	        //struct.addAnnot(ASSyntax.createStructure("time", id, ASSyntax.createNumber(p.getId())));
			return struct;
		} catch (Exception ex){
			ex.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Convert an observable property into a literal
	 */
	static public Literal obsPropToLiteral(String propId, ArtifactId source, String propName, Object[] args , JavaLibrary lib) throws Exception {
    	Literal struct = ASSyntax.createLiteral(propName);
    	for (Object obj: args){
    		struct.addTerm(objectToTerm(obj,lib));
    	}
        struct.addAnnot(BeliefBase.TPercept);
        struct.addAnnot(OBS_PROP_PERCEPT);
        struct.addAnnot(ASSyntax.createStructure("obs_prop_id", ASSyntax.createString(propId)));
        Term id = objectToTerm(source, lib);  
        struct.addAnnot(ASSyntax.createStructure("artifact_id", id));
        struct.addAnnot(ASSyntax.createStructure("artifact_name", id, ASSyntax.createString(source.getName())));
        struct.addAnnot(ASSyntax.createStructure("artifact_type", id, ASSyntax.createString(source.getArtifactType())));
        struct.addAnnot(ASSyntax.createStructure("workspace", id, ASSyntax.createString(source.getWorkspaceId().getName())));
		return struct; 
	}
	
    
	// aux
	
	
	public static Tuple termToTuple(Term t, JavaLibrary lib) {
    	if (t.isAtom()){
    		return new Tuple(((Atom)t).getFunctor());
    	} else if (t.isStructure()){
    		Structure st = (Structure)t;
    		Term[] ta = st.getTermsArray();
    		Object[] objs = new Object[ta.length];
    		for (int i = 0; i < ta.length; i++){
    			objs[i] = termToObject(ta[i],lib);
    		}
    		return new Tuple(st.getFunctor(),objs);
    	} else throw new IllegalArgumentException();
    }
        
    public static Term tupleToTerm(Tuple t, JavaLibrary lib) throws Exception {
    	Structure st = new Structure(t.getLabel());
    	for (Object obj: t.getContents()){
    		st.addTerm(objectToTerm(obj,lib));
    	}
    	return st;
    }

    public static Literal tupleToLiteral(Tuple t, JavaLibrary lib) throws Exception {
    	Structure st = new Structure(t.getLabel());
    	for (Object obj: t.getContents()){
    		st.addTerm(objectToTerm(obj,lib));
    	}
    	return st;
    }

}
