package cn.com.hyun.generator.tools;


public class CacheService {
	
	private String serviceName;
	private String threadcount;
	private String partitioncount;
	private String backupcount;
	private String backupcountafterwritebehind= "0";
	private String taskhungthreshold;
	private String tasktimeout;
	private String requesttimeout;
	private boolean cacheindexflag = false;
	
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public String getThreadcount() {
		return threadcount;
	}
	public void setThreadcount(String threadcount) {
		this.threadcount = threadcount;
	}
	public String getPartitioncount() {
		return partitioncount;
	}
	public void setPartitioncount(String partitioncount) {
		this.partitioncount = partitioncount;
	}
	public String getBackupcount() {
		return backupcount;
	}
	public void setBackupcount(String backupcount) {
		this.backupcount = backupcount;
	}
	public String getTaskhungthreshold() {
		return taskhungthreshold;
	}
	public void setTaskhungthreshold(String taskhungthreshold) {
		this.taskhungthreshold = taskhungthreshold;
	}
	public String getTasktimeout() {
		return tasktimeout;
	}
	public void setTasktimeout(String tasktimeout) {
		this.tasktimeout = tasktimeout;
	}
	public String getRequesttimeout() {
		return requesttimeout;
	}
	public void setRequesttimeout(String requesttimeout) {
		this.requesttimeout = requesttimeout;
	}
	public String getBackupcountafterwritebehind() {
		return backupcountafterwritebehind;
	}
	public void setBackupcountafterwritebehind(String backupcountafterwritebehind) {
		this.backupcountafterwritebehind = backupcountafterwritebehind;
	}
	public boolean isCacheindexflag() {
		return cacheindexflag;
	}
	public void setCacheindexflag(boolean cacheindexflag) {
		this.cacheindexflag = cacheindexflag;
	}


}
