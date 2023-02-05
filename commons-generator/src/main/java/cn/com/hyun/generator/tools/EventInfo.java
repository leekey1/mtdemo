package cn.com.hyun.generator.tools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class EventInfo extends InputInfo implements Comparable<Object> {
	private String eventId;
	
	private String domain;

	 private String tableJpname;

	 private String tableEnname;

	 private boolean dbflag = false;

	 private String eventName;
	 
	 private String parentEventName;
	 private String patternId;

	 private String cacheName;

	 private String lostCacheName;
	 private String eventNameVar;
	 private boolean hasJmonCollObject = false;

	 private boolean  msJmonflag = false;
	 private boolean pofflag = false;
	 private boolean  initLoadflag = false;
	 
	 private boolean commonColumnflag = false;
	 private boolean mscollObject = false;
	 
	 private boolean generatorflag = true;
	 
	 private boolean transIdflag = false;
	 
	 private boolean measurementIdflag = false;
	 private String cohepattern;
	 
	 private String cacheservicepattern;
	 private String cacheparampattern;

	 private boolean initmstLoadflag = false;
	 private boolean dumpflag = false;
	 private String condition = "";
	 private boolean arrivedMonitorObject = false;
	 
	 private boolean indexflag = false;
	 private List<String> eventAccessType = new ArrayList<String>();
	 
     private List<Attribute> attrList = new ArrayList<Attribute>();
     
     private Map<String, CacheParamter> patternCacheParam = new HashMap<String, CacheParamter>();
     private Map<String, CacheService> patternCacheService = new HashMap<String, CacheService>();
     
     private String fullCalssName = "";
     
     private String pertation = "";
     
     private String keyDbAttr = "";
     
     private String keyAttr = "";
     
     private String bpkeyAttr = "";
     private String bpkeyDbAttr = "";
     private String bpkeyMeAttr = "";
     private String fieldLength = "";
     
     private String fieldType = "";
     
     public void setPatternCacheParam(Map<String, CacheParamter> patternCacheParam) {
		this.patternCacheParam = patternCacheParam;
	}
	public void setPatternCacheService(Map<String, CacheService> patternCacheService) {
		this.patternCacheService = patternCacheService;
	}
	public void addCacheParam(Map<String, CacheParamter> param) {
    	 patternCacheParam.putAll(param);
     }
     public void addCacheService(Map<String, CacheService> service) {
    	 patternCacheService.putAll(service);
     }
     
     public CacheParamter getCacheParam(String pattern) {
    	 return patternCacheParam.get(pattern);
     }
     public CacheService getCacheService(String pattern) {
    	 return patternCacheService.get(pattern);
     }
     public Map<String, CacheService> getCacheService() {
    	 return patternCacheService;
     }
	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	public String getTableJpname() {
		return tableJpname;
	}

	public void setTableJpname(String tableJpname) {
		this.tableJpname = tableJpname;
	}

	public String getTableEnname() {
		return tableEnname;
	}

	public void setTableEnname(String tableEnname) {
		this.tableEnname = tableEnname;
	}

	public boolean isDbflag() {
		return dbflag;
	}

	public void setDbflag(boolean dbflag) {
		this.dbflag = dbflag;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public String getParentEventName() {
		return parentEventName;
	}

	public void setParentEventName(String parentEventName) {
		this.parentEventName = parentEventName;
	}

	public String getPatternId() {
		return patternId;
	}

	public void setPatternId(String patternId) {
		this.patternId = patternId;
	}

	public String getCacheName() {
		return cacheName;
	}

	public void setCacheName(String cacheName) {
		this.cacheName = cacheName;
	}

	public String getLostCacheName() {
		return lostCacheName;
	}

	public void setLostCacheName(String lostCacheName) {
		this.lostCacheName = lostCacheName;
	}

	public String getEventNameVar() {
		return eventNameVar;
	}

	public void setEventNameVar(String eventNameVar) {
		this.eventNameVar = eventNameVar;
	}

	public List<String> getEventAccessType() {
		return eventAccessType;
	}

	public void setEventAccessType(List<String> eventAccessType) {
		this.eventAccessType.addAll(eventAccessType);
	}

	public List<Attribute> getAttrList() {
		return attrList;
	}

	public void setAttrList(List<Attribute> attrList) {
		this.attrList.addAll(attrList);
	}

	public boolean hasJmonCollObject() {
		return hasJmonCollObject;
	}

	public void setHasJmonCollObject(boolean hasJmonCollObject) {
		this.hasJmonCollObject = hasJmonCollObject;
	}

	public boolean isMsJmonflag() {
		return msJmonflag;
	}

	public void setMsJmonflag(boolean msJmonflag) {
		this.msJmonflag = msJmonflag;
	}

	public boolean isInitLoadflag() {
		return initLoadflag;
	}

	public void setInitLoadflag(boolean initLoadflag) {
		this.initLoadflag = initLoadflag;
	}

	public boolean isCommonColumnflag() {
		return commonColumnflag;
	}

	public void setCommonColumnflag(boolean commonColumnflag) {
		this.commonColumnflag = commonColumnflag;
	}

	public String getCohepattern() {
		return cohepattern;
	}

	public void setCohepattern(String cohepattern) {
		this.cohepattern = cohepattern;
	}

	public boolean isMscollObject() {
		return mscollObject;
	}

	public void setMscollObject(boolean mscollObject) {
		this.mscollObject = mscollObject;
	}

	public boolean isGeneratorflag() {
		return generatorflag;
	}

	public void setGeneratorflag(boolean generatorflag) {
		this.generatorflag = generatorflag;
	}

	public String getCacheservicepattern() {
		return cacheservicepattern;
	}

	public void setCacheservicepattern(String cacheservicepattern) {
		this.cacheservicepattern = cacheservicepattern;
	}

	public String getCacheparampattern() {
		return cacheparampattern;
	}

	public void setCacheparampattern(String cacheparampattern) {
		this.cacheparampattern = cacheparampattern;
	}

	public boolean isInitmstLoadflag() {
		return initmstLoadflag;
	}

	public void setInitmstLoadflag(boolean initmstLoadflag) {
		this.initmstLoadflag = initmstLoadflag;
	}
	public boolean isDumpflag() {
		return dumpflag;
	}
	public void setDumpflag(boolean dumpflag) {
		this.dumpflag = dumpflag;
	}
	public boolean isTransIdflag() {
		return transIdflag;
	}
	public void setTransIdflag(boolean transIdflag) {
		this.transIdflag = transIdflag;
	}
	public boolean isMeasurementIdflag() {
		return measurementIdflag;
	}
	public void setMeasurementIdflag(boolean measurementIdflag) {
		this.measurementIdflag = measurementIdflag;
	}
	
	
    public boolean isPofflag() {
		return pofflag;
	}
	public void setPofflag(boolean pofflag) {
		this.pofflag = pofflag;
	}
	
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		this.condition = condition;
	}
	
	public String getFullCalssName() {
		return fullCalssName;
	}
	public void setFullCalssName(String fullCalssName) {
		this.fullCalssName = fullCalssName;
	}
	
	
	public String getPertation() {
		return pertation;
	}
	public void setPertation(String pertation) {
		this.pertation = pertation;
	}
	public boolean isArrivedMonitorObject() {
		return arrivedMonitorObject;
	}
	public void setArrivedMonitorObject(boolean arrivedMonitorObject) {
		this.arrivedMonitorObject = arrivedMonitorObject;
	}


	
	public String getKeyAttr() {
		return keyAttr;
	}
	public void setKeyAttr(String keyAttr) {
		this.keyAttr = keyAttr;
	}
	
	
	public String getKeyDbAttr() {
		return keyDbAttr;
	}
	public void setKeyDbAttr(String keyDbAttr) {
		this.keyDbAttr = keyDbAttr;
	}
	public void copy(EventInfo evtinfo) {

    	evtinfo.setEventId(eventId);

    	evtinfo.setTableJpname(tableJpname);

    	evtinfo.setTableEnname(tableEnname);

    	evtinfo.setDbflag(dbflag);

    	evtinfo.setEventName(eventName);
   	 
    	evtinfo.setParentEventName(parentEventName);
    	evtinfo.setPatternId(patternId);

    	evtinfo.setCacheName(cacheName);

    	evtinfo.setLostCacheName(lostCacheName);
    	evtinfo.setEventNameVar(eventNameVar);
    	evtinfo.setHasJmonCollObject(hasJmonCollObject);

    	evtinfo.setMsJmonflag(msJmonflag);
   	 
    	evtinfo.setInitLoadflag(initLoadflag);
   	 
    	evtinfo.setCommonColumnflag(commonColumnflag);
    	evtinfo.setMscollObject(mscollObject);
   	 
    	evtinfo.setGeneratorflag(generatorflag);
   	 
    	evtinfo.setTransIdflag(transIdflag);
   	 
    	evtinfo.setMeasurementIdflag(measurementIdflag);
    	evtinfo.setCohepattern(cohepattern);
   	 
    	evtinfo.setCacheservicepattern(cacheservicepattern);
    	evtinfo.setCacheparampattern(cacheparampattern);

    	evtinfo.setInitmstLoadflag(initmstLoadflag);
    	evtinfo.setDumpflag(dumpflag);
    	evtinfo.setEventAccessType(eventAccessType);
   	 
    	evtinfo.setAttrList(attrList);
        
    	evtinfo.setPatternCacheParam(patternCacheParam);
    	evtinfo.setPatternCacheService(patternCacheService);
    	evtinfo.setArrivedMonitorObject(arrivedMonitorObject);
    }
	
    public boolean isIndexflag() {
		return indexflag;
	}
	public void setIndexflag(boolean indexflag) {
		this.indexflag = indexflag;
	}

	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getFieldLength() {
		return fieldLength;
	}
	public void setFieldLength(String fieldLength) {
		this.fieldLength = fieldLength;
	}
	
	public String getFieldType() {
		return fieldType;
	}
	public void setFieldType(String fieldType) {
		this.fieldType = fieldType;
	}
	
	public String getBpkeyAttr() {
		return bpkeyAttr;
	}
	public void setBpkeyAttr(String bpkeyAttr) {
		this.bpkeyAttr = bpkeyAttr;
	}

	public String getBpkeyDbAttr() {
		return bpkeyDbAttr;
	}
	public void setBpkeyDbAttr(String bpkeyDbAttr) {
		this.bpkeyDbAttr = bpkeyDbAttr;
	}
	
	public String getBpkeyMeAttr() {
		return bpkeyMeAttr;
	}
	public void setBpkeyMeAttr(String bpkeyMeAttr) {
		this.bpkeyMeAttr = bpkeyMeAttr;
	}
	@Override
    public int compareTo(Object arg0) {
    	EventInfo eventInfo = (EventInfo) arg0;
        int result = 0;
        
        if (this.cacheparampattern.compareTo(eventInfo.getCacheparampattern()) != 0) {
            result = this.cacheparampattern.compareTo(eventInfo.getCacheparampattern());
        }
        return result;
    }
}
