package cn.com.hyun.generator.tools;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;

/**
 *
 */
public class IndentWriter extends PrintWriter {
	
	private int indent = 0;

	/**
	 * Constructor for IndentWriter.
	 * @param out
	 */
	public IndentWriter(Writer out) {
		super(out);
	}

	/**
	 * Constructor for IndentWriter.
	 * @param out
	 * @param autoFlush
	 */
	public IndentWriter(Writer out, boolean autoFlush) {
		super(out, autoFlush);
	}

	/**
	 * Constructor for IndentWriter.
	 * @param out
	 */
	public IndentWriter(OutputStream out) {
		super(out);
	}

	/**
	 * Constructor for IndentWriter.
	 * @param out
	 * @param autoFlush
	 */
	public IndentWriter(OutputStream out, boolean autoFlush) {
		super(out, autoFlush);
	}
	
	/**
	 * 
	 */
	public void addIndent(){
		indent++;
	}
	
	/**
	 * 
	 */
	public void delIndent(){
		indent--;
	}
	
	/**
	 * 

	 */
	public void addIndent(int arg){
		indent += arg;
	}
	/**

	 */
	public void printjpln(String arg){
		StringBuffer buf = new StringBuffer(arg);
		
		for(int i = 0; i < indent; i++){
			buf.insert(0, "    ");
		}
		buf.append("\r\n");
		super.print(buf);

	}
	/**

	 */
	public void println(String arg){
		StringBuffer buf = new StringBuffer(arg);
		
		for(int i = 0; i < indent; i++){
			buf.insert(0, "    ");
		}
		buf.append("\n");
		//super.println(buf);
		
		try {
			String temp = new String(buf.toString().getBytes("UTF-8"), "UTF-8");
			super.print(temp);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**

	 */
	public void println(){
		StringBuffer buf = new StringBuffer();
		
		for(int i = 0; i < indent; i++){
			buf.insert(0, "    ");
		}
		buf.append("\n");
		//super.println(buf);
		
		try {
			String temp = new String(buf.toString().getBytes("UTF-8"), "UTF-8");
			super.print(temp);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 *
	 * @param arg
	 */
	public void print(String arg){
		StringBuffer buf = new StringBuffer(arg);
		
		for(int i = 0; i < indent; i++){
			buf.insert(0, "    ");
		}
		
		try {
			String temp = new String(buf.toString().getBytes("UTF-8"), "UTF-8");
			super.print(temp);
		} catch (UnsupportedEncodingException e) {
		}
	}
	/**
	 * 
	 */
	public void printIndent(){
		StringBuffer buf = new StringBuffer();
		
		for(int i = 0; i < indent; i++){
			buf.insert(0, "    ");
		}
		
		super.print(buf);
	}
		

}
