package cn.com.hyun.generator.tools;

import java.util.ArrayList;
import java.util.List;


public class ExcelColum {


	private String fieldEmpty;

	public String getFieldEmpty() {
		return fieldEmpty;
	}

	public void setFieldEmpty(String fieldEmpty) {
		this.fieldEmpty = fieldEmpty;
	}

	private String regex;

	public String getRegex() {
		return regex;
	}

	public void setRegex(String regex) {
		this.regex = regex;
	}

	private String eventId;

	public boolean isHaveDbencolumn() {
		return haveDbencolumn;
	}

	public void setHaveDbencolumn(boolean haveDbencolumn) {
		this.haveDbencolumn = haveDbencolumn;
	}

	private  boolean haveDbencolumn;

 private String tableJpname;


 private String tableEnname;

 private boolean dbflag = false;
 	

 private boolean msflag = false;
 

 private boolean pofflag = false;
 

 private String dbjpcolumn;

 private String dbencolumn;

 private boolean dbpkey = false;

 private String dbColumType;


 private String dbColumFrontLength;

 private String dbColumFixLength;

 private String msEventName;

 private String msAttributeName;
 private String msAttributeType;

 private boolean msIndex = false;

 private boolean msPkey = false;

 private boolean msSubKey = false;

 private boolean msSubKey2 = false;

 private boolean msTimeKey = false;

 private boolean msSortKey = false;
 private boolean hasJmonCollObject = false;
 private boolean mscollObject = false;

 private boolean  msJmonflag = false;

 private String eventCollectionName;

 private String patternId;

 private String pofColumType;

 private String cacheName;

 private String lostCacheName;
 private String conditon;

 private boolean commonColumnflag = false;
 private String cacheservicepattern;
 private String cacheparampattern;

 private boolean initmstLoadflag = false;

 private boolean initLoadflag = false;
 
 private String phtype = "PH";
 
 private boolean generatorflag = true;
 
 private String cohepattern;
 
 private boolean dumpflag = false;
 
 private boolean transIdflag = false;
 
 private boolean measurementIdflag = false;
 
 private String doublepointtable = "";
 
 private String doublepointindex = "";
 
 private boolean arrivedMonitorObject = false;
 private boolean indexflag = false;
 private boolean bpkeyflag = false;
 private int cacheindexseq = -1;
 private String pertation = "";
 private String msAttrMethodName = "";
 private String domain = "";
 private String length = "";
 private String mysqlType = "";
 private String fieldvalue = "";
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

public String getDbjpcolumn() {
	return dbjpcolumn;
}
public void setDbjpcolumn(String dbjpcolumn) {
	this.dbjpcolumn = dbjpcolumn;
}
public String getDbencolumn() {
	return dbencolumn;
}
public void setDbencolumn(String dbencolumn) {
	this.dbencolumn = dbencolumn;
}
public boolean isDbpkey() {
	return dbpkey;
}
public void setDbpkey(boolean dbpkey) {
	this.dbpkey = dbpkey;
}
public String getDbColumType() {
	return dbColumType;
}
public void setDbColumType(String dbColumType) {
	this.dbColumType = dbColumType;
}
public String getDbColumFrontLength() {
	return dbColumFrontLength;
}
public void setDbColumFrontLength(String dbColumFrontLength) {
	this.dbColumFrontLength = dbColumFrontLength;
}
public String getDbColumFixLength() {
	return dbColumFixLength;
}
public void setDbColumFixLength(String dbColumFixLength) {
	this.dbColumFixLength = dbColumFixLength;
}
public String getMsEventName() {
	return msEventName;
}
public void setMsEventName(String msEventName) {
	this.msEventName = msEventName;
}
public String getMsAttributeName() {
	return msAttributeName;
}
public void setMsAttributeName(String msAttributeName) {
	this.msAttributeName = msAttributeName;
}

public boolean isMsPkey() {
	return msPkey;
}
public void setMsPkey(boolean msPkey) {
	this.msPkey = msPkey;
}
public boolean isMsSubKey() {
	return msSubKey;
}
public void setMsSubKey(boolean msSubKey) {
	this.msSubKey = msSubKey;
}
public boolean isMsSubKey2() {
	return msSubKey2;
}
public void setMsSubKey2(boolean msSubKey2) {
	this.msSubKey2 = msSubKey2;
}
public boolean isMsTimeKey() {
	return msTimeKey;
}
public void setMsTimeKey(boolean msTimeKey) {
	this.msTimeKey = msTimeKey;
}
public boolean isMsSortKey() {
	return msSortKey;
}
public void setMsSortKey(boolean msSortKey) {
	this.msSortKey = msSortKey;
}

public String getEventCollectionName() {
	return eventCollectionName;
}
public void setEventCollectionName(String eventCollectionName) {
	this.eventCollectionName = eventCollectionName;
}
public String getPatternId() {
	return patternId;
}
public void setPatternId(String patternId) {
	this.patternId = patternId;
}
public String getPofColumType() {
	return pofColumType;
}
public void setPofColumType(String pofColumType) {
	this.pofColumType = pofColumType;
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
public boolean isCommonColumnflag() {
	return commonColumnflag;
}
public void setCommonColumnflag(boolean commonColumnflag) {
	this.commonColumnflag = commonColumnflag;
}
public boolean isInitLoadflag() {
	return initLoadflag;
}
public void setInitLoadflag(boolean initLoadflag) {
	this.initLoadflag = initLoadflag;
}

public boolean isMsflag() {
	return msflag;
}
public void setMsflag(boolean msflag) {
	this.msflag = msflag;
}
public boolean isMsIndex() {
	return msIndex;
}
public void setMsIndex(boolean msIndex) {
	this.msIndex = msIndex;
}
public String getPhtype() {
	return phtype;
}
public void setPhtype(String phtype) {
	this.phtype = phtype;
}

public String getMsAttributeType() {
	return msAttributeType;
}
public void setMsAttributeType(String msAttributeType) {
	this.msAttributeType = msAttributeType;
}

public boolean hasJmonCollObject() {
	return hasJmonCollObject;
}
public void setJmonCollObject(boolean hasJmonCollObject) {
	this.hasJmonCollObject = hasJmonCollObject;
}

public boolean isMsJmonflag() {
	return msJmonflag;
}
public void setMsJmonflag(boolean msJmonflag) {
	this.msJmonflag = msJmonflag;
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
public List<String> getEventAccessType() {
	List<String> eventType = new ArrayList<String>();
	//if (initLoadflag) {
	if (Constants.PUT_HANDLE_TYPE.equals(phtype)) {
	    	
	    
		eventType.add(Constants.GET_ACCESSOR);
	//}
	//if (Constants.PUT_HANDLE_TYPE.equals(phtype)) {
		eventType.add(Constants.PUT_ACCESSOR);
		eventType.add(Constants.HANDLE_ACCESSOR);
//	} else if (Constants.PUT_TYPE.equals(phtype)) {
//		eventType.add(Constants.PUT_ACCESSOR);
//	} else if (Constants.HANDLE_TYPE.equals(phtype)) {
//		eventType.add(Constants.HANDLE_ACCESSOR);
	}
	return eventType;
}

public String getPatternValue() {
	String patternValue = "";
	if (Constants.PATTERN_A_NAME.equals(patternId)) {
		patternValue = Constants.PATTERN_A;
	} else if (Constants.PATTERN_B_NAME.equals(patternId)) {
		patternValue = Constants.PATTERN_B;
	} else if (Constants.PATTERN_C_NAME.equals(patternId)) {
		patternValue = Constants.PATTERN_C;
	} else if (Constants.PATTERN_D_NAME.equals(patternId)) {
		patternValue = Constants.PATTERN_D;
	}
	return patternValue;
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
public String getDoublepointtable() {
	return doublepointtable;
}
public void setDoublepointtable(String doublepointtable) {
	this.doublepointtable = doublepointtable;
}
public String getDoublepointindex() {
	return doublepointindex;
}
public void setDoublepointindex(String doublepointindex) {
	this.doublepointindex = doublepointindex;
}
public boolean isPofflag() {
	return pofflag;
}
public void setPofflag(boolean pofflag) {
	this.pofflag = pofflag;
}
public String getConditon() {
	return conditon;
}
public void setConditon(String conditon) {
	this.conditon = conditon;
}
public boolean isArrivedMonitorObject() {
	return arrivedMonitorObject;
}
public void setArrivedMonitorObject(boolean arrivedMonitorObject) {
	this.arrivedMonitorObject = arrivedMonitorObject;
}
public boolean isIndexflag() {
	return indexflag;
}
public void setIndexflag(boolean indexflag) {
	this.indexflag = indexflag;
}
public int getCacheindexseq() {
	return cacheindexseq;
}
public void setCacheindexseq(int cacheindexseq) {
	this.cacheindexseq = cacheindexseq;
}
public String getPertation() {
	return pertation;
}
public void setPertation(String pertation) {
	this.pertation = pertation;
}
public String getMsAttrMethodName() {
	return msAttrMethodName;
}
public void setMsAttrMethodName(String msAttrMethodName) {
	this.msAttrMethodName = msAttrMethodName;
}
public String getDomain() {
	return domain;
}
public void setDomain(String domain) {
	this.domain = domain;
}
public String getLength() {
	return length;
}
public void setLength(String length) {
	this.length = length;
}
public String getMysqlType() {
	return mysqlType;
}
public void setMysqlType(String mysqlType) {
	this.mysqlType = mysqlType;
}
public String getFieldvalue() {
	return fieldvalue;
}
public void setFieldvalue(String fieldvalue) {
	this.fieldvalue = fieldvalue;
}
public boolean isBpkeyflag() {
	return bpkeyflag;
}
public void setBpkeyflag(boolean bpkeyflag) {
	this.bpkeyflag = bpkeyflag;
}

}
