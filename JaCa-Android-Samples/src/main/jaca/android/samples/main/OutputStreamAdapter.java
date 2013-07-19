package jaca.android.samples.main;

import java.io.PrintStream;


/**
 * Utility class for re-directing standard output and 
 * standard error in the console showed in the {@link BaseMainActivity}
 * @author mguidi
 *
 */
public class OutputStreamAdapter extends PrintStream {

	public PrintStream originalOut = null;
    public PrintStream originalErr = null;
    private BaseMainActivity mView;
    
    
    public OutputStreamAdapter(BaseMainActivity view) {
        super(System.out);
        mView = view;
    }
    
    public void setAsDefaultOut() {
        originalOut = System.out;
        originalErr = System.err;
        System.setOut(this);
        System.setErr(this);
    }
    
    public void restoreOriginalOut() {
        if (originalOut != null) {
            System.setOut(originalOut);
        }
        if (originalErr != null) {
            System.setErr(originalErr);
        }
    }
        
    void append(String s) {
    	mView.append(s);
    }
    
    public void print(Object s) {
        append(s.toString());
    }
    public void println(Object s) {
        append(s+"\n");
    }

    public void print(String s) {
        append(s.toString());
    }
    public void println(String s) {
        append(s+"\n");
    }
    
    public void print(boolean arg) {
        append(arg+"");
    }
    public void print(char arg0) {
        append(arg0+"");
    }
    public void print(double arg0) {
        append(arg0+"");
    }
    public void print(float arg0) {
        append(arg0+"");
    }
    public void print(int arg0) {
        append(arg0+"");
    }
    public void print(long arg0) {
        append(arg0+"");
    }
    public void println(boolean arg0) {
        append(arg0+"\n");
    }
    public void println(char arg0) {
        append(arg0+"\n");
    }
    public void println(double arg0) {
        append(arg0+"\n");
    }
    public void println(float arg0) {
        append(arg0+"\n");
    }
    public void println(int arg0) {
        append(arg0+"\n");
    }
    public void println(long arg0) {
        append(arg0+"\n");
    }
    public void println() {
        append("\n");
    }
    
    public String toString() {
        String s = "masConsole";
        return "OutputAdapter("+s+")";
    }   
}