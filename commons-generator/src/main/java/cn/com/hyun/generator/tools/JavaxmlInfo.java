package cn.com.hyun.generator.tools;

//import jdk.jfr.EventInfo;
import com.oracle.jrockit.jfr.EventInfo;

import java.util.ArrayList;
import java.util.List;

public class JavaxmlInfo extends ConfigInfo {
    List<String> eventlist = new ArrayList<String>();
    List<String> monitorlist = new ArrayList<String>();
    List<String> handlerlist = new ArrayList<String>();
    List<String> accessorlist = new ArrayList<String>();
    List<EventInfo> poflist = new ArrayList<EventInfo>();
    List<String> repositorylist = new ArrayList<String>();
    List<EventInfo> evtlist = new ArrayList<EventInfo>();
    public List<String> getEventlist() {
		return eventlist;
	}
    public List<String> getMonitorlist() {
		return monitorlist;
	}
	public List<EventInfo> getPoflist() {
		return poflist;
	}
	public List<String> getRepositorylist() {
		return repositorylist;
	}
	public List<EventInfo> getEvtlist() {
		return evtlist;
	}
	public List<String> getHandlerlist() {
		return handlerlist;
	}

	public List<String> getAccessorlist() {
		return accessorlist;
	}

}
