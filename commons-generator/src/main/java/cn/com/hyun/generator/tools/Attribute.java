package cn.com.hyun.generator.tools;


public class Attribute implements Comparable<Object> {

	private String fieldEmpty;

	public String getFieldEmpty() {
		return fieldEmpty;
	}

	public void setFieldEmpty(String fieldEmpty) {
		this.fieldEmpty = fieldEmpty;
	}

	private boolean dbpkey = false;

	 private String dbColumType;

	private boolean msIndex = false;

	private boolean msPkey = false;

	private boolean msSubKey = false;

	private boolean msSubKey2 = false;

	private boolean msTimeKey = false;

	private boolean msSortKey = false;

	private String dbjpcolumn;

	public String getRegexcolumn() {
		return regexcolumn;
	}

	public void setRegexcolumn(String regexcolumn) {
		this.regexcolumn = regexcolumn;
	}

	private String regexcolumn;

	private String dbencolumn;

	private String msAttributeName;
	private String msAttributeType;
	private String attrMethodName;

	private String pofColumType;
	private String i1 = "";
	private String i2 = "";
	private String i3 = "";
	private String i4 = "";
	private String i5 = "";
	private String i6 = "";
	private String i7 = "";
	private String i8 = "";
	private String i9 = "";
	private String i10 = "";
	private String i11 = "";
	private String i12 = "";
	private String i13 = "";
	private String i14 = "";
	private String i15 = "";
	private String i16 = "";
	private String i17 = "";
	private String i18 = "";
	private String i19 = "";
	private String i20 = "";
	private String coltypestring;
	private int bytelength;
	private int collength;
	private String colfixlength;
	
	private int cacheIndexSeq = -1;
	
    private String fieldLength = "";
    
    private String fieldType = "";
    
    private String fieldNull = "";
	private boolean bpkeyflag = false;
	private String bpkeym = "";
	public String getColfixlength() {
		return colfixlength;
	}

	public void setColfixlength(String colfixlength) {
		this.colfixlength = colfixlength;
	}

	public String getAttrMethodName() {
		return attrMethodName;
	}

	public void setAttrMethodName(String attrMethodName) {
		this.attrMethodName = attrMethodName;
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

	public boolean isMsIndex() {
		return msIndex;
	}

	public void setMsIndex(boolean msIndex) {
		this.msIndex = msIndex;
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

	public String getMsAttributeName() {
		return msAttributeName;
	}

	public void setMsAttributeName(String msAttributeName) {
		this.msAttributeName = msAttributeName;
	}

	public String getPofColumType() {
		return pofColumType;
	}

	public void setPofColumType(String pofColumType) {
		this.pofColumType = pofColumType;
	}

	public String getMsAttributeType() {
		return msAttributeType;
	}

	public void setMsAttributeType(String msAttributeType) {
		this.msAttributeType = msAttributeType;
	}

	public String getI1() {
		return i1;
	}

	public void setI1(String i1) {
		this.i1 = i1;
	}

	public String getI2() {
		return i2;
	}

	public void setI2(String i2) {
		this.i2 = i2;
	}

	public String getI3() {
		return i3;
	}

	public void setI3(String i3) {
		this.i3 = i3;
	}

	public String getI4() {
		return i4;
	}

	public void setI4(String i4) {
		this.i4 = i4;
	}

	public String getI5() {
		return i5;
	}

	public void setI5(String i5) {
		this.i5 = i5;
	}

	public String getI6() {
		return i6;
	}

	public void setI6(String i6) {
		this.i6 = i6;
	}

	public String getI7() {
		return i7;
	}

	public void setI7(String i7) {
		this.i7 = i7;
	}

	public String getI8() {
		return i8;
	}

	public void setI8(String i8) {
		this.i8 = i8;
	}

	public String getI9() {
		return i9;
	}

	public void setI9(String i9) {
		this.i9 = i9;
	}

	public String getI10() {
		return i10;
	}

	public void setI10(String i10) {
		this.i10 = i10;
	}

	public String getI11() {
		return i11;
	}

	public void setI11(String i11) {
		this.i11 = i11;
	}

	public String getI12() {
		return i12;
	}

	public void setI12(String i12) {
		this.i12 = i12;
	}

	public String getI13() {
		return i13;
	}

	public void setI13(String i13) {
		this.i13 = i13;
	}

	public String getI14() {
		return i14;
	}

	public void setI14(String i14) {
		this.i14 = i14;
	}

	public String getI15() {
		return i15;
	}

	public void setI15(String i15) {
		this.i15 = i15;
	}

	public String getI16() {
		return i16;
	}

	public void setI16(String i16) {
		this.i16 = i16;
	}

	public String getI17() {
		return i17;
	}

	public void setI17(String i17) {
		this.i17 = i17;
	}

	public String getI18() {
		return i18;
	}

	public void setI18(String i18) {
		this.i18 = i18;
	}

	public String getI19() {
		return i19;
	}

	public void setI19(String i19) {
		this.i19 = i19;
	}

	public String getI20() {
		return i20;
	}

	public void setI20(String i20) {
		this.i20 = i20;
	}

	public String getColtypestring() {
		return coltypestring;
	}

	public void setColtypestring(String coltypestring) {
		this.coltypestring = coltypestring;
	}

	public int getBytelength() {
		return bytelength;
	}

	public void setBytelength(int bytelength) {
		this.bytelength = bytelength;
	}

	public int getCollength() {
		return collength;
	}

	public void setCollength(int collength) {
		this.collength = collength;
	}

	public int getCacheIndexSeq() {
		return cacheIndexSeq;
	}

	public void setCacheIndexSeq(int cacheIndexSeq) {
		this.cacheIndexSeq = cacheIndexSeq;
	}
	@Override
    public int compareTo(Object arg0) {
		Attribute attribute = (Attribute) arg0;
        int result = 0;
        
        if (this.cacheIndexSeq > attribute.getCacheIndexSeq()) {
            result = 1;
        } else if (this.cacheIndexSeq < attribute.getCacheIndexSeq()) {
        	result = -1;
        } else {
        	result = 0;
        }
        return result;
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

	public String getFieldNull() {
		return fieldNull;
	}

	public void setFieldNull(String fieldNull) {
		this.fieldNull = fieldNull;
	}

	public boolean isBpkeyflag() {
		return bpkeyflag;
	}

	public void setBpkeyflag(boolean bpkeyflag) {
		this.bpkeyflag = bpkeyflag;
	}

	public String getBpkeym() {
		return bpkeym;
	}

	public void setBpkeym(String bpkeym) {
		this.bpkeym = bpkeym;
	}

}
