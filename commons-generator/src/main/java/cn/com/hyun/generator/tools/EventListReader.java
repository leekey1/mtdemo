package cn.com.hyun.generator.tools;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.util.CellUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;

import static cn.com.hyun.generator.tools.Md5Utils.md5;


public class EventListReader {

    @Autowired
    private ResourcePatternResolver resourcePatternResolver;

    //	@Autowired
//	private RestOperations restOperations;
//	@Autowired
//	private RestTemplate restTemplate;
    // singleton instance.
    private static EventListReader _instance = null;
    private static final String sheetName = "log";
    private static final String searchInvestName = "searchInvestName";
    private static final String cacheSerSheetName = "cacheSerSheetName";
    private static final String cacheParamSheetName = "cacheParamSheetName";
    private static final String secondaryindexSheetName = "secondaryindexSheetName";
    private static final String sizingmonitorSheetName = "sizingmonitorSheetName";
    ExcelReader eclReader = null;
    Map<String, String> sedidxMap = null;
    List<TableListColum> tablelist = new ArrayList<TableListColum>();
    private Map<String, EventInfo> evtMap = new HashMap<String, EventInfo>();

    private EventListReader(String dbinfofile) throws Exception {
        eclReader = new ExcelReader(dbinfofile);
        eclReader.setSheet(sheetName);
    }

    public static EventListReader getInstance(String dbinfofile) throws Exception {
        if (_instance == null) {
            _instance = new EventListReader(dbinfofile);
        }
        return _instance;
    }

    public Map<String, CacheService> readercacheSer() {
        Map<String, CacheService> patternMap = new LinkedHashMap<String, CacheService>();
        try {
            eclReader.setSheet(cacheSerSheetName);
            int fristRowNum = 3;
            int lastRowNum = eclReader.getLastRowNum();
            int crrentRowNum = 0;
            for (int i = 0; i < lastRowNum; i++) {
                CacheService service = new CacheService();
                crrentRowNum = fristRowNum + i;
                String serverName = eclReader.getStringNumber(crrentRowNum, 3);
                service.setServiceName(serverName);
                String threadcount = eclReader.getStringNumber(crrentRowNum, 4);
                service.setThreadcount(threadcount);
                String partitioncount = eclReader.getStringNumber(crrentRowNum, 5);
                service.setPartitioncount(partitioncount);
                String backupcount = eclReader.getStringNumber(crrentRowNum, 6);
                service.setBackupcount(backupcount);
                String backupcountafterwritebehind = eclReader.getStringNumber(crrentRowNum, 7);
                service.setBackupcountafterwritebehind(backupcountafterwritebehind);
                String taskhungthreshold = eclReader.getStringNumber(crrentRowNum, 8);
                service.setTaskhungthreshold(taskhungthreshold);
                String tasktimeout = eclReader.getStringNumber(crrentRowNum, 9);
                service.setTasktimeout(tasktimeout);
                String requesttimeout = eclReader.getStringNumber(crrentRowNum, 10);
                service.setRequesttimeout(requesttimeout);

                // String lostserverName = eclReader.getString(crrentRowNum, 4);
                String patternName = eclReader.getString(crrentRowNum, 1);
                // String allservice = serverName + "@" + lostserverName;
                patternMap.put(patternName, service);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return patternMap;

    }

    public Map<String, CacheParamter> readercacheParam() {
        Map<String, CacheParamter> patternMap = new HashMap<String, CacheParamter>();
        try {
            eclReader.setSheet(cacheParamSheetName);
            int fristRowNum = 3;
            int lastRowNum = eclReader.getLastRowNum();
            int crrentRowNum = 0;
            for (int i = 0; i < lastRowNum; i++) {
                crrentRowNum = fristRowNum + i;
                CacheParamter param = new CacheParamter();
                String patternName = eclReader.getString(crrentRowNum, 1);
                String writemaxbatchsize = eclReader.getStringNumber(crrentRowNum, 2);
                // writemaxbatchsize = writemaxbatchsize.substring(0,
                // writemaxbatchsize.indexOf("."));
                String writeDelaySeconds = eclReader.getStringNumber(crrentRowNum, 3);
                // writeDelaySeconds = writeDelaySeconds.substring(0,
                // writeDelaySeconds.indexOf("."));
                String writeBatchFactor = eclReader.getString(crrentRowNum, 4);
                String writeRequeueThreshold = eclReader.getStringNumber(crrentRowNum, 5);
                // writeRequeueThreshold = writeRequeueThreshold.substring(0,
                // writeRequeueThreshold.indexOf("."));
                String expiryDelay = eclReader.getStringNumber(crrentRowNum, 6);
                // expiryDelay = expiryDelay.substring(0,
                // expiryDelay.indexOf("."));
                String evictionPolicy = eclReader.getString(crrentRowNum, 7);
                String highUnits = eclReader.getStringNumber(crrentRowNum, 8);
                // highUnits = highUnits.substring(0, highUnits.indexOf("."));
                String cachestoreTimeout = eclReader.getStringNumber(crrentRowNum, 9);
                // cachestoreTimeout = cachestoreTimeout.substring(0,
                // cachestoreTimeout.indexOf("."));
                String persistenceMode = eclReader.getString(crrentRowNum, 10);
                String batchwritingsize = eclReader.getStringNumber(crrentRowNum, 11);
                String cachestatementssize = eclReader.getStringNumber(crrentRowNum, 12);
                param.setWriteMaxBatchSize(writemaxbatchsize);
                param.setWriteDelaySeconds(writeDelaySeconds);
                param.setWriteBatchFactor(writeBatchFactor);
                param.setWriteRequeueThreshold(writeRequeueThreshold);
                param.setExpiryDelay(expiryDelay);
                param.setEvictionPolicy(evictionPolicy);
                param.setHighUnits(highUnits);
                param.setCachestoreTimeout(cachestoreTimeout);
                param.setPersistenceMode(persistenceMode);
                param.setBatchwritingsize(batchwritingsize);
                param.setCachestatementssize(cachestatementssize);
                patternMap.put(patternName, param);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return patternMap;

    }

    private Map<String, String> readSizing() {
        Map<String, String> sizingMap = new HashMap<String, String>();
        try {
            eclReader.setSheet(sizingmonitorSheetName);
            int fristRowNum = 3;
            int lastRowNum = eclReader.getLastRowNum();
            int crrentRowNum = 0;
            for (int i = 0; i < lastRowNum; i++) {
                crrentRowNum = fristRowNum + i;
                String tableName = eclReader.getString(crrentRowNum, 2);
                String sizing = eclReader.getStringNumber(crrentRowNum, 9);
                sizingMap.put(tableName, sizing);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sizingMap;
    }

    public List<EventInfo> readerRepositorys() {
        List<EventInfo> repositorys = new ArrayList<EventInfo>();
        try {
            eclReader.setSheet(sheetName);
            int fristRowNum = eclReader.getFirstRowNum() + 2;
            int lastRowNum = eclReader.getLastRowNum();
            int crrentRowNum = 0;
            for (int i = 0; i < lastRowNum; i++) {
                crrentRowNum = fristRowNum + i;
                ExcelColum clums = converExcelTableNmColumn(crrentRowNum, eclReader);
                EventInfo eventinfo = new EventInfo();
                eventinfo.setTableJpname(clums.getTableJpname());
                eventinfo.setTableEnname(clums.getTableEnname());
                eventinfo.setDomain(clums.getDomain());
                if (!"".equals(clums.getTableEnname()) && clums.getTableEnname() != null) {
                    String tempEventName = clums.getTableEnname();
                    String eventname = "";
                    if (tempEventName.indexOf("_") != -1) {
                        String[] tempsub = tempEventName.split("_");
                        for (String tempPre : tempsub) {
                            String preChar = tempPre.substring(0, 1).toUpperCase();
                            String fixChar = tempPre.substring(1).toLowerCase();
                            eventname = eventname + preChar + fixChar;
                        }
                    } else {
                        String preChar = tempEventName.substring(0, 1).toUpperCase();
                        String fixChar = tempEventName.substring(1).toLowerCase();
                        eventname = preChar + fixChar;
                    }
                    String preChar = eventname.substring(0, 1).toLowerCase();
                    String fixChar = eventname.substring(1);
                    eventinfo.setEventNameVar(preChar + fixChar);
                    eventinfo.setEventName(eventname);
                    repositorys.add(eventinfo);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return repositorys;
    }

    public void readerEntity(EventInfo eventEntity, String entity, String eventListFile, String fileName) {
        try {
            eclReader.setSheet(entity);
            int fristRowNum = eclReader.getFirstRowNum() + 8;
            int lastRowNum = eclReader.getLastRowNum();
            int crrentRowNum = 0;
            for (int i = 0; i < lastRowNum; i++) {
                crrentRowNum = fristRowNum + i;
                ExcelColum excelClm = this.converAttrExcelColumn(crrentRowNum, eclReader);

                if (excelClm.getDbencolumn() != null && !"".equals(excelClm.getDbencolumn())) {

                    Attribute attr = new Attribute();
                    attr.setDbencolumn(excelClm.getDbencolumn());
                    attr.setDbjpcolumn(excelClm.getDbjpcolumn());
                    attr.setDbpkey(excelClm.isDbpkey());
                    attr.setBpkeyflag(excelClm.isBpkeyflag());
                    attr.setMsAttributeName(excelClm.getMsAttributeName());
                    attr.setAttrMethodName(excelClm.getMsAttrMethodName());
                    attr.setMsAttributeType(excelClm.getMsAttributeType());
                    if (excelClm.isDbpkey()) {
                        eventEntity.setKeyAttr(excelClm.getMsAttributeName());
                        eventEntity.setKeyDbAttr(excelClm.getDbencolumn());
                    }
                    if (excelClm.isBpkeyflag()) {
                        eventEntity.setBpkeyAttr(excelClm.getMsAttributeName());
                        eventEntity.setBpkeyDbAttr(excelClm.getDbencolumn());
                        eventEntity.setBpkeyMeAttr(excelClm.getMsAttrMethodName());
                    }
                    attr.setDbColumType(excelClm.getDbColumType());
                    attr.setFieldLength(excelClm.getLength());
                    attr.setFieldType(excelClm.getMysqlType());
                    attr.setFieldNull(excelClm.getFieldvalue());
                    attr.setRegexcolumn(excelClm.getRegex());
                    attr.setFieldEmpty(excelClm.getFieldEmpty());

                    if (!excelClm.isHaveDbencolumn()){
                        for (Attribute attribute : eventEntity.getAttrList()) {


                            if (attribute.getDbencolumn().equals(attr.getDbencolumn())) {

                                attr.setDbencolumn(attr.getDbencolumn() + "_" + NumEnum.getByKey(String.valueOf(i)).getValue());
                                attr.setMsAttributeName(attr.getMsAttributeName() + toUpperCaseFirstOne(NumEnum.getByKey(String.valueOf(i)).getValue()));
                                attr.setAttrMethodName(attr.getAttrMethodName() + toUpperCaseFirstOne(NumEnum.getByKey(String.valueOf(i)).getValue()));
                            }
                        }
                    }


                    eventEntity.getAttrList().add(attr);
                }

            }
            writeExcel(eventEntity.getAttrList(), eventListFile, entity, fileName);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void writeExcel(List<Attribute> attrList, String eventListFile, String sheetName, String fileName) {
        try {
            for (int i = 0; i < attrList.size(); i++) {
                Attribute attribute = attrList.get(i);
                HSSFWorkbook workbook = getExcelTemplate(eventListFile);
                HSSFSheet sheet = workbook.getSheet(sheetName);
                HSSFCellStyle commonStyle = workbook.createCellStyle();
                commonStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
                commonStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
                commonStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
                commonStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
                int startRow = 8;
                HSSFRow row = sheet.createRow(startRow + i);

                String key = "";
                if (attribute.isDbpkey()) {
                    key = "PK";
                } else if (attribute.isBpkeyflag()) {
                    key = "UNIQUE";
                }
                CellUtil.createCell(row, 0, String.valueOf(i + 1), commonStyle);
                CellUtil.createCell(row, 1, attribute.getDbjpcolumn(), commonStyle);
                CellUtil.createCell(row, 2, attribute.getDbencolumn(), commonStyle);
                CellUtil.createCell(row, 3, attribute.getDbColumType(), commonStyle);
                CellUtil.createCell(row, 4, attribute.getFieldLength(), commonStyle);
                CellUtil.createCell(row, 5, key, commonStyle);
                CellUtil.createCell(row, 6, attribute.getFieldNull(), commonStyle);
                CellUtil.createCell(row, 7, attribute.getFieldEmpty(), commonStyle);
                CellUtil.createCell(row, 8, attribute.getRegexcolumn(), commonStyle);


                OutputStream out = new FileOutputStream(eventListFile);
                ;
                workbook.write(out);
                out.flush();
                out.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException(e.getMessage());
        }
    }


    public static void exportExcel(ExportDto workBook) throws IOException {
        HttpServletRequest request = getRequest();
        HttpServletResponse response = getResponse();
        exportExcel(request, response, workBook);
    }

    public static void exportExcel(HttpServletRequest request, HttpServletResponse response, ExportDto workBook) throws IOException {
        response.setContentType("application/msexcel;");
        String agent = request.getHeader("USER-AGENT").toLowerCase();
        //判断浏览器 如果是火狐浏览器,需要不同的编码处理
        if (agent.contains("firefox")) {
            response.setHeader("Content-Disposition", new String(("attachment;filename=" + workBook.getFileName()).getBytes("GB2312"), "ISO8859-1"));
        } else if (agent.contains("safari")) {
            response.setHeader("Content-Disposition", new String(("attachment;filename=" + workBook.getFileName()).getBytes("UTF-8"), "ISO8859-1"));
        } else
            response.setHeader("Content-Disposition", new String(("attachment;filename=" + toUtf8String(workBook.getFileName())).getBytes("GB2312"), "UTF-8"));
        ServletOutputStream out = response.getOutputStream();
        HSSFWorkbook wb = workBook.getWookBook();
        wb.write(out);
        out.flush();
        out.close();
    }

    public static String toUtf8String(String s) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c >= 0 && c <= 255) {
                sb.append(c);
            } else {
                byte[] b;
                try {
                    b = Character.toString(c).getBytes("utf-8");
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                    b = new byte[0];
                }
                for (int j = 0; j < b.length; j++) {
                    int k = b[j];
                    if (k < 0) k += 256;
                    sb.append("%" + Integer.toHexString(k).toUpperCase());
                }
            }
        }
        return sb.toString();
    }

    public static HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    public static HttpServletResponse getResponse() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
    }


    private HSSFWorkbook getExcelTemplate(String eventListFile) throws IOException {
        //Resource resource = resourcePatternResolver.getResource(eventListFile);
        FileInputStream file = new FileInputStream(eventListFile);
        POIFSFileSystem fs = new POIFSFileSystem(file);
        return new HSSFWorkbook(fs);
    }


    public void readerSearch(EventInfo eventEntity, String searchSheet) {
        try {
            eclReader.setSheet(searchSheet);
            int fristRowNum = eclReader.getFirstRowNum() + 3;
            int lastRowNum = eclReader.getLastRowNum();
            int crrentRowNum = 0;
            for (int i = 0; i < lastRowNum; i++) {
                crrentRowNum = fristRowNum + i;
                ExcelColum excelClm = this.converAttrExcelColumn(crrentRowNum, eclReader);

                if (excelClm.getDbencolumn() != null && !"".equals(excelClm.getDbencolumn())) {
                    Attribute attr = new Attribute();
                    attr.setDbencolumn(excelClm.getDbencolumn());
                    attr.setDbjpcolumn(excelClm.getDbjpcolumn());
                    attr.setDbpkey(excelClm.isDbpkey());
                    attr.setMsAttributeName(excelClm.getMsAttributeName());
                    attr.setAttrMethodName(excelClm.getMsAttrMethodName());
                    attr.setMsAttributeType(excelClm.getMsAttributeType());
                    if (excelClm.isDbpkey()) {
                        eventEntity.setKeyAttr(excelClm.getMsAttributeName());
                        eventEntity.setKeyDbAttr(excelClm.getDbencolumn());
                    }
                    attr.setFieldLength(excelClm.getLength());
                    attr.setFieldType(excelClm.getMysqlType());
                    attr.setFieldNull(excelClm.getFieldvalue());
                    eventEntity.getAttrList().add(attr);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<EventInfo> reader() {

        List<Attribute> retList = null;
        List<EventInfo> tableList = new ArrayList<EventInfo>();

        try {
            Map<String, CacheParamter> cacheparam = readercacheParam();
            Map<String, CacheService> cacheser = readercacheSer();
            sedidxMap = readersecondaryindex();
            Map<String, String> sizingMap = readSizing();
            eclReader.setSheet(sheetName);
            int fristRowNum = eclReader.getFirstRowNum() + 3;
            int lastRowNum = eclReader.getLastRowNum();
            int crrentRowNum = 0;
            String talbeName = "";
            for (int i = 0; i < lastRowNum; i++) {
                crrentRowNum = fristRowNum + i;
                ExcelColum excelClm = this.converExcelColumn(crrentRowNum, eclReader);
                if (excelClm.getTableJpname() == null && excelClm.getMsEventName() == null) {
                    break;
                }
                if (isNewTable(excelClm)) {
                    retList = null;
                    EventInfo eventinfo = createEventInfo(excelClm);
                    talbeName = eventinfo.getTableEnname();
                    eventinfo.addCacheParam(cacheparam);
                    eventinfo.addCacheService(cacheser);
                    if (eventinfo.isDbflag()) {
                        String sizing = sizingMap.get(talbeName);
                        addTableList(excelClm, sizing);
                    }
                    tableList.add(eventinfo);
                    evtMap.put(eventinfo.getTableJpname(), eventinfo);
                    retList = eventinfo.getAttrList();
                    // }
                }
                if (retList != null) {
                    Attribute attr = createAttribute(excelClm);
                    String idxstring = sedidxMap.get(talbeName + "@" + attr.getDbencolumn());
                    addindexInfo(attr, idxstring);
                    retList.add(attr);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return tableList;
    }

    private boolean isNewTable(ExcelColum excelClm) {
        boolean changeTableFlag = true;
        if ("".equals(excelClm.getEventId()) || excelClm.getEventId() == null) {
            changeTableFlag = false;
        }
        return changeTableFlag;
    }


    public static String toLowerCaseFirstOne(String s) {
        if (Character.isLowerCase(s.charAt(0)))
            return s;
        else
            return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
    }

    public static String toUpperCaseFirstOne(String s) {
        if (Character.isUpperCase(s.charAt(0)))
            return s;
        else
            return (new StringBuilder()).append(Character.toUpperCase(s.charAt(0))).append(s.substring(1)).toString();
    }

    /**
     * @return
     * @throws Exception
     */
    private ExcelColum converExcelTableNmColumn(int crrentRowNum, ExcelReader eclReader) throws Exception {

        ExcelColum rd = new ExcelColum();
        rd.setTableEnname(eclReader.getString(crrentRowNum, 1));
        rd.setTableJpname(eclReader.getString(crrentRowNum, 2));
        rd.setDomain(eclReader.getString(crrentRowNum, 3));
        return rd;
    }

    /**
     * @return
     * @throws Exception
     */
    private ExcelColum converAttrExcelColumn(int crrentRowNum, ExcelReader eclReader) throws Exception {

        ExcelColum rd = new ExcelColum();

        // ���ږ��i�_���j
        rd.setDbjpcolumn(eclReader.getString(crrentRowNum, 1));

        if (!"".equals(eclReader.getString(crrentRowNum, 2))){
            rd.setDbencolumn(eclReader.getString(crrentRowNum, 2));
            rd.setHaveDbencolumn(true);
        }else {
            rd.setHaveDbencolumn(false);
            if (rd.getDbjpcolumn() != null && !rd.getDbjpcolumn().equals("") && eclReader.getString(crrentRowNum, 3) != null && !eclReader.getString(crrentRowNum, 3).equals("")) {
                //字段自动翻译及插入_
                String q = rd.getDbjpcolumn();
                String from = "zh_CHS";
                String to = "EN";
                String appKey = "0cbef8a6ec0eaee4";
                String salt = String.valueOf(System.currentTimeMillis());
                String key = "ezkmMG07dQ4XMW2hd1OYI1dsQlucdGfU";
                String sign = md5(appKey + q + salt + key);
                String url = "http://openapi.youdao.com/api?q={q}&from={from}&to={to}&appKey={appKey}&salt={salt}&sign={sign}";
                Map<String, Object> uriVariables = new HashMap<String, Object>();
                uriVariables.put("q", q);
                uriVariables.put("from", from);
                uriVariables.put("to", to);
                uriVariables.put("appKey", appKey);
                uriVariables.put("salt", salt);
                uriVariables.put("key", key);
                uriVariables.put("sign", sign);
                HttpHeaders headers = new HttpHeaders();
                MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
                headers.setContentType(type);
                headers.add("Accept", MediaType.APPLICATION_JSON.toString());

                RestConfiguration restConfiguration = new RestConfiguration();
                Map<String, Object> result = restConfiguration.restOperations().getForObject(url, Map.class, uriVariables);
                PorterStemmer porterStemmer = new PorterStemmer();
                List<String> translate = (List<String>) result.get("translation");
                String[] strList = toLowerCaseFirstOne(translate.get(0)).split(" ");
                String str = "";
                for (String s : strList) {
                    if (!"".equals(s) && s.indexOf("the") != 0 && s.indexOf("is") != 0 && s.indexOf("of") != 0&& s.indexOf("if") != 0&& s.indexOf("to") != 0) {
                        str = str + "," + porterStemmer.stem(s);
                        if (str.indexOf(",") == 0) {
                            str = str.replaceAll(",", "");
                        }
                    }
                }
                String dbencolumn = str.replaceAll(",", "_");
                rd.setDbencolumn(dbencolumn);
                if (q.equals("创建时间")){
                    rd.setDbencolumn("created_at");
                }else if (q.equals("创建者")){
                    rd.setDbencolumn("created_by");
                }else if (q.equals("最后更新时间")){
                    rd.setDbencolumn("updated_at");
                }else if (q.equals("最后更新者")){
                    rd.setDbencolumn("updated_by");
                }else if (q.equals("版本")){
                    rd.setDbencolumn("version");
                }
            }
        }



        // ���ږ��iDB�����j
//		rd.setDbencolumn(eclReader.getString(crrentRowNum, 2));

        // "PK(DB)"
        String pkey = eclReader.getString(crrentRowNum, 5);
        if ("PK".equals(pkey)) {
            rd.setDbpkey(true);
        } else if ("UNIQUE".equals(pkey)) {
            rd.setBpkeyflag(true);
        }
        // �f�[�^�^
        rd.setDbColumType(eclReader.getString(crrentRowNum, 3));

        String lengthStr = eclReader.getString(crrentRowNum, 4);
        String fieldValue = eclReader.getString(crrentRowNum, 6);
        String fieldEmpty = eclReader.getString(crrentRowNum, 7);
        String regex = eclReader.getString(crrentRowNum, 8);
        // set field length
        rd.setLength(lengthStr);
        rd.setFieldvalue(fieldValue);
        rd.setRegex(regex);
        boolean floatflag = false;
        int length = 0;
        if (lengthStr != null && !"".equals(lengthStr)) {
            if (lengthStr.indexOf(",") != -1 || lengthStr.indexOf("�C") != -1) {
                int point = 0;
                if (lengthStr.indexOf(",") > 0) {
                    point = lengthStr.indexOf(",");
                }
                if (lengthStr.indexOf("�C") > 0) {
                    point = lengthStr.indexOf("�C");
                }
                String afterStr = lengthStr.substring(point + 1);
                int afterint = Integer.parseInt(afterStr);
                if (afterint > 0) {
                    floatflag = true;
                }
                String beforeStr = lengthStr.substring(0, point);
                length = Integer.parseInt(beforeStr);
            }
        }

        if (!"".equals(rd.getDbencolumn()) && rd.getDbencolumn() != null) {
            String attr = rd.getDbencolumn();
            String attrname = "";
            if (attr.indexOf("_") != -1) {
                String[] tempsub = attr.split("_");
                for (String tempPre : tempsub) {
                    String preChar = tempPre.substring(0, 1).toUpperCase();
                    String fixChar = tempPre.substring(1).toLowerCase();
                    attrname = attrname + preChar + fixChar;
                }
            } else {
                String preChar = attr.substring(0, 1).toUpperCase();
                String fixChar = attr.substring(1).toLowerCase();
                attrname = preChar + fixChar;
            }
            String preChar = attrname.substring(0, 1).toLowerCase();
            String fixChar = attrname.substring(1);
            rd.setMsAttributeName(preChar + fixChar);
            rd.setMsAttrMethodName(attrname);
            rd.setFieldEmpty(fieldEmpty);
            // rd.setMsAttributeName(eventname);

        }
        // �ϐ��^
        rd.setMsAttributeType(TypeUtil.dbtypeTojavatype(rd.getDbColumType(), length, floatflag));
        rd.setMysqlType(TypeUtil.dbtypeToMySqltype(rd.getDbColumType(), length, floatflag));
        return rd;
    }

    /**
     * @return
     * @throws Exception
     */
    private ExcelColum converExcelColumn(int crrentRowNum, ExcelReader eclReader) throws Exception {

        ExcelColum rd = new ExcelColum();
        rd.setEventId(eclReader.getString(crrentRowNum, 2));

        // "MS�v��"
        String msflag = eclReader.getString(crrentRowNum, 3);
        if ("��".equals(msflag)) {
            rd.setMsflag(true);
        }
        // jmon�v��
        String jmonObject = eclReader.getString(crrentRowNum, 4);
        rd.setPhtype(jmonObject);

        // "POF�v��"
        String pofflag = eclReader.getString(crrentRowNum, 5);
        if ("��".equals(pofflag)) {
            rd.setPofflag(true);
        }
        // "Cache Index�v��"
        String cacheindexflag = eclReader.getString(crrentRowNum, 6);
        if ("��".equals(cacheindexflag)) {
            rd.setIndexflag(true);
        }
        // "DB�v��"
        String dbflag = eclReader.getString(crrentRowNum, 7);
        if ("��".equals(dbflag)) {
            rd.setDbflag(true);
        }
        // cacheload�v��
        String initLoad = eclReader.getString(crrentRowNum, 8);
        if ("��".equals(initLoad)) {
            rd.setInitLoadflag(true);
        }
        // ���B�Ď��Ώۗv��
        String arrivemonior = eclReader.getString(crrentRowNum, 9);
        if ("��".equals(arrivemonior)) {
            rd.setArrivedMonitorObject(true);
        }
        // LocalCacheDump�v��
        String dumpflag = eclReader.getString(crrentRowNum, 10);
        if ("��".equals(dumpflag)) {
            rd.setDumpflag(true);
        }
        // �e�[�u�����i�_���j
        rd.setTableJpname(eclReader.getString(crrentRowNum, 11));
        // �e�[�u�����i�����j
        rd.setTableEnname(eclReader.getString(crrentRowNum, 12));
        // ���ږ��i�_���j
        rd.setDbjpcolumn(eclReader.getString(crrentRowNum, 13));

        // ���ږ��iDB�����j
        rd.setDbencolumn(eclReader.getString(crrentRowNum, 14));
        // "PK(DB)"
        String pkey = eclReader.getString(crrentRowNum, 15);
        if ("��".equals(pkey)) {
            rd.setDbpkey(true);
        }
        // �f�[�^�^
        rd.setDbColumType(eclReader.getString(crrentRowNum, 16));

        // �R�[�h���� 17

        // "�����i�������j"
        rd.setDbColumFrontLength(eclReader.getStringNumber(crrentRowNum, 18));
        // "�����i�������j"
        rd.setDbColumFixLength(eclReader.getStringNumber(crrentRowNum, 19));
        // TransactionID
        String transColumn = eclReader.getString(crrentRowNum, 20);
        if ("��".equals(transColumn)) {
            rd.setTransIdflag(true);
        }
        // MeasurementID
        String measColumn = eclReader.getString(crrentRowNum, 21);
        if ("��".equals(measColumn)) {
            rd.setMeasurementIdflag(true);
        }
        // ���ʃJ����
        String commonColumn = eclReader.getString(crrentRowNum, 22);
        if ("��".equals(commonColumn)) {
            rd.setCommonColumnflag(true);
        }
        // "Event��(MS����)"
        rd.setMsEventName(eclReader.getString(crrentRowNum, 23));
        // "���ږ�(MS����)"
        rd.setMsAttributeName(eclReader.getString(crrentRowNum, 24));
        // �ϐ��^
        rd.setMsAttributeType(eclReader.getString(crrentRowNum, 25));
        // �ϐ��^"index�Ώ�"
        String msIndexName = eclReader.getString(crrentRowNum, 26);
        if ("��".equals(msIndexName)) {
            rd.setMsIndex(true);
        }

        // "PK(MS)"
        String mspkey = eclReader.getString(crrentRowNum, 27);
        if ("��".equals(mspkey)) {
            rd.setMsPkey(true);
        }

        // "SubKey(MS)"
        String mssubkey = eclReader.getString(crrentRowNum, 28);
        if ("��".equals(mssubkey)) {
            rd.setMsSubKey(true);
        }
        // "SubKey2(MS)"
        String mssub2key = eclReader.getString(crrentRowNum, 29);
        if ("��".equals(mssub2key)) {
            rd.setMsSubKey2(true);
        }
        // �͈͔�r���� 30
        // "TimeKey(MS)"
        String msTimeKey = eclReader.getString(crrentRowNum, 31);
        if ("��".equals(msTimeKey)) {
            rd.setMsTimeKey(true);
        }

        // "SortKey(MS)"
        String msSortKey = eclReader.getString(crrentRowNum, 32);
        if ("��".equals(msSortKey)) {
            rd.setMsSortKey(true);
        }

        // set�A�N�V�����ő匏�� 33
        // "Event��`�Ώ�"
        if ("��".equals(jmonObject)) {
            rd.setMsJmonflag(true);
        }

        // "Event �R���N�V�������iMS�����j"
        rd.setEventCollectionName(rd.getMsEventName() + "Collection");
        // �R���N�V������`�Ώ�
        String jmonCollObject = eclReader.getString(crrentRowNum, 34);
        if (!"M".equals(jmonCollObject) && !"".equals(jmonCollObject) && !"-".equals(jmonCollObject)) {
            rd.setJmonCollObject(true);
        }
        if (!"".equals(jmonCollObject) && !"-".equals(jmonCollObject)) {
            rd.setMscollObject(true);
        }
        // String generatorflag = eclReader.getString(crrentRowNum, 36);
        // if ("�~".equals(generatorflag)) {
        // rd.setGeneratorflag(false);
        // }
        // �p�^�[��
        String patternid = eclReader.getString(crrentRowNum, 35);
        if ("�p�^�[���A".equals(patternid) || "�p�^�[���@".equals(patternid) || Constants.PATTERN_A_NAME.equals(patternid)) {
            rd.setPatternId(Constants.PATTERN_A_NAME);
        }
        if (Constants.PATTERN_D_NAME.equals(patternid) || "�p�^�[���B".equals(patternid) || "�p�^�[���C".equals(patternid)
                || "�p�^�[���D".equals(patternid) || "�p�^�[���E".equals(patternid) || "�p�^�[���F".equals(patternid)
                || "�p�^�[���G".equals(patternid)) {
            rd.setPatternId(Constants.PATTERN_D_NAME);
        }
        if (Constants.PATTERN_B_NAME.equals(patternid)) {
            rd.setPatternId(Constants.PATTERN_B_NAME);
        }
        if (Constants.PATTERN_C_NAME.equals(patternid)) {
            rd.setPatternId(Constants.PATTERN_C_NAME);
        }

        // DDC�p�^�[�� 36
        // �L���b�V����
        rd.setCacheName(eclReader.getString(crrentRowNum, 37));
        // �C�x���g���X�g�h�~�p�L���b�V��
        String lostcache = eclReader.getString(crrentRowNum, 38);
        if (!"".equals(lostcache)) {
            rd.setLostCacheName(rd.getCacheName());
        }
        // PK(Coheerence) 39
        // Java(POF)�ł̕ϐ��^
        rd.setPofColumType(eclReader.getString(crrentRowNum, 40));
        // 41 Index Cache�ΏۃJ����
        try {
            rd.setCacheindexseq(eclReader.getInt(crrentRowNum, 41));
        } catch (Exception e) {
            String indexseq = eclReader.getStringNumber(crrentRowNum, 41);
            if (indexseq != null && !"".equals(indexseq)) {
                rd.setCacheindexseq(Integer.parseInt(indexseq));
            }
        }

        String cohepattern = eclReader.getString(crrentRowNum, 42);
        rd.setCohepattern(cohepattern);
        if ("�p�^�[��1".equals(cohepattern)) {
            rd.setCohepattern(Constants.PATTERN_A);
        } else {
            rd.setCohepattern(cohepattern);
        }

        // �L���b�V���T�[�r�X�p�^�[��
        String cacheserpattern = eclReader.getString(crrentRowNum, 43);
        rd.setCacheservicepattern(cacheserpattern);
        // �L���b�V���p�����^�p�^�[��
        String cacheparampattern = eclReader.getString(crrentRowNum, 44);
        rd.setCacheparampattern(cacheparampattern);

        // String mstinitLoad = eclReader.getString(crrentRowNum, 46);
        // if ("��".equals(mstinitLoad)) {
        // rd.setInitmstLoadflag(true);
        // }
        // �}�X�^ 45
        // �����f�[�^���[�h�͈� 46
        // �����f�[�^���[�h�pwhere��
        String condition = eclReader.getString(crrentRowNum, 47);
        rd.setConditon(condition);

        //
        rd.setDoublepointtable(eclReader.getString(crrentRowNum, 48));
        rd.setDoublepointindex(eclReader.getString(crrentRowNum, 49));
        String pertationflag = eclReader.getString(crrentRowNum, 48);
        if ("��".equals(pertationflag)) {
            rd.setPertation("Y");
        }
        // System.out.println(crrentRowNum);
        // System.out.println(eclReader.getString(crrentRowNum, 10));
        return rd;
    }

    public EventInfo createEventInfo(ExcelColum excelCol) {
        EventInfo evtInfo = new EventInfo();
        evtInfo.setEventId(excelCol.getEventId());
        // �e�[�u�����i�_���j
        evtInfo.setTableJpname(excelCol.getTableJpname());
        // �e�[�u�����i�����j
        evtInfo.setTableEnname(excelCol.getTableEnname());
        // "DB�v��"
        evtInfo.setDbflag(excelCol.isDbflag());
        // "Event��(MS����)"
        evtInfo.setEventName(excelCol.getMsEventName());
        evtInfo.setMsJmonflag(excelCol.isMsJmonflag());
        evtInfo.setPofflag(excelCol.isPofflag());
        evtInfo.setIndexflag(excelCol.isIndexflag());
        evtInfo.setHasJmonCollObject(excelCol.hasJmonCollObject());
        evtInfo.setMscollObject(excelCol.isMscollObject());
        if (!"".equals(excelCol.getMsEventName())) {
            String tempEventName = excelCol.getMsEventName();
            String preChar = tempEventName.substring(0, 1).toLowerCase();
            String fixChar = tempEventName.substring(1);
            evtInfo.setEventNameVar(preChar + fixChar);
        }

        evtInfo.setParentEventName(excelCol.getEventCollectionName());

        evtInfo.setPatternId(excelCol.getPatternValue());
        evtInfo.setCohepattern(excelCol.getCohepattern());
        // �L���b�V����
        evtInfo.setCacheName(excelCol.getCacheName());
        // �C�x���g���X�g�h�~�p�L���b�V��
        evtInfo.setLostCacheName(excelCol.getLostCacheName());

        evtInfo.setEventAccessType(excelCol.getEventAccessType());

        evtInfo.setInitLoadflag(excelCol.isInitLoadflag());
        evtInfo.setCondition(excelCol.getConditon());
        evtInfo.setCommonColumnflag(excelCol.isCommonColumnflag());
        evtInfo.setMeasurementIdflag(excelCol.isMeasurementIdflag());
        evtInfo.setTransIdflag(excelCol.isTransIdflag());
        evtInfo.setGeneratorflag(excelCol.isGeneratorflag());
        evtInfo.setCacheparampattern(excelCol.getCacheparampattern());
        evtInfo.setCacheservicepattern(excelCol.getCacheservicepattern());
        evtInfo.setInitmstLoadflag(excelCol.isInitmstLoadflag());
        evtInfo.setArrivedMonitorObject(excelCol.isArrivedMonitorObject());
        evtInfo.setDumpflag(excelCol.isDumpflag());
        evtInfo.setPertation(excelCol.getPertation());
        return evtInfo;

    }

    public Attribute createAttribute(ExcelColum excelCol) {
        Attribute attr = new Attribute();
        // "PK(DB)"
        attr.setDbpkey(excelCol.isDbpkey());
        // �f�[�^�^
        attr.setDbColumType(excelCol.getDbColumType());
        // �ϐ��^"index�Ώ�"
        attr.setMsIndex(excelCol.isMsIndex());
        // "PK(MS)"
        attr.setMsPkey(excelCol.isMsPkey());
        // "SubKey(MS)"
        attr.setMsSubKey(excelCol.isMsSubKey());
        // "SubKey2(MS)"
        attr.setMsSubKey2(excelCol.isMsSubKey2());
        // "TimeKey(MS)"
        attr.setMsTimeKey(excelCol.isMsTimeKey());
        // "SortKey(MS)"
        attr.setMsSortKey(excelCol.isMsSortKey());
        // ���ږ��i�_���j
        attr.setDbjpcolumn(excelCol.getDbjpcolumn());
        attr.setMsAttributeType(excelCol.getMsAttributeType());
        // ���ږ��iDB�����j
        attr.setDbencolumn(excelCol.getDbencolumn());
        // "���ږ�(MS����)"
        attr.setMsAttributeName(excelCol.getMsAttributeName());
        if (!"".equals(excelCol.getMsAttributeName())) {
            String tempAttr = excelCol.getMsAttributeName();
            String preChar = tempAttr.substring(0, 1).toUpperCase();
            String fixChar = tempAttr.substring(1);
            attr.setAttrMethodName(preChar + fixChar);
        }
        // Java(POF)�ł̕ϐ��^
        attr.setPofColumType(excelCol.getPofColumType());
        String collength = excelCol.getDbColumFrontLength();
        String colfixlength = excelCol.getDbColumFixLength();
        String coldatatype = "";
        int colbyte = 0;
        int totallen = 0;
        if ("NUMBER".equals(excelCol.getDbColumType())) {
            if (colfixlength != null && !"".equals(colfixlength) && !"0".equals(colfixlength)) {
                coldatatype = excelCol.getDbColumType() + "(" + collength + "," + colfixlength + ")";
                totallen = Integer.parseInt(collength) + 2;
                // �ǉ�
                attr.setColfixlength(colfixlength);
            } else {
                coldatatype = excelCol.getDbColumType() + "(" + collength + ")";
                totallen = Integer.parseInt(collength) + 1;
            }

            colbyte = 21;
        } else if (!"date".equals(excelCol.getDbColumType().toLowerCase())) {
            coldatatype = excelCol.getDbColumType() + "(" + collength + ")";
            if (!"".equals(collength) && collength != null) {
                colbyte = Integer.parseInt(collength);
                totallen = colbyte;
            }
        } else {
            coldatatype = excelCol.getDbColumType();
            colbyte = 7;
            totallen = 17;
        }
        attr.setCollength(totallen);
        attr.setColtypestring(coldatatype);
        attr.setBytelength(colbyte);
        attr.setCacheIndexSeq(excelCol.getCacheindexseq());
        return attr;
    }

    private void addTableList(ExcelColum excelCol, String sizing) {
        TableListColum tablelistcol = new TableListColum();
        tablelistcol.setDoublepointindex(excelCol.getDoublepointindex());
        tablelistcol.setDoublepointtable(excelCol.getDoublepointtable());
        tablelistcol.setTableId(excelCol.getTableEnname());
        tablelistcol.setNumbetofcases(sizing);
        tablelistcol.setPosition(excelCol.getPertation());
        tablelist.add(tablelistcol);

        String patternId = excelCol.getPatternId();
        if (Constants.PATTERN_A.equals(excelCol.getCohepattern())
                && Constants.PATTERN_D_NAME.equals(excelCol.getPatternId())) {
            patternId = Constants.PATTERN_A_NAME;
        }
        if (!Constants.PATTERN_A_NAME.equals(patternId)) {
            TableListColum tablelistp = new TableListColum();
            tablelistp.setDoublepointindex(excelCol.getDoublepointindex());
            tablelistp.setDoublepointtable(excelCol.getDoublepointtable());
            tablelistp.setTableId(excelCol.getTableEnname() + "_P");
            tablelistp.setNumbetofcases(sizing);
            tablelistp.setPosition(excelCol.getPertation());
            tablelist.add(tablelistp);
        }
    }

    public List<TableListColum> getTablelist() {
        return tablelist;
    }

    public Map<String, String> readersecondaryindex() {
        Map<String, String> patternMap = new HashMap<String, String>();
        try {
            eclReader.setSheet(secondaryindexSheetName);
            int fristRowNum = 2;
            int lastRowNum = eclReader.getLastRowNum();
            int crrentRowNum = 0;
            for (int i = 0; i < lastRowNum; i++) {
                crrentRowNum = fristRowNum + i;
                String tableName = eclReader.getString(crrentRowNum, 0);
                String colName = eclReader.getString(crrentRowNum, 1);
                String i1 = eclReader.getStringNumber(crrentRowNum, 3);
                String i2 = eclReader.getStringNumber(crrentRowNum, 4);
                String i3 = eclReader.getStringNumber(crrentRowNum, 5);
                String i4 = eclReader.getStringNumber(crrentRowNum, 6);
                String i5 = eclReader.getStringNumber(crrentRowNum, 7);
                String i6 = eclReader.getStringNumber(crrentRowNum, 8);
                String i7 = eclReader.getStringNumber(crrentRowNum, 9);
                String i8 = eclReader.getStringNumber(crrentRowNum, 10);
                String i9 = eclReader.getStringNumber(crrentRowNum, 11);
                String i10 = eclReader.getStringNumber(crrentRowNum, 12);
                String i11 = eclReader.getStringNumber(crrentRowNum, 13);
                String i12 = eclReader.getStringNumber(crrentRowNum, 14);
                String i13 = eclReader.getStringNumber(crrentRowNum, 15);
                String i14 = eclReader.getStringNumber(crrentRowNum, 16);
                String i15 = eclReader.getStringNumber(crrentRowNum, 17);
                String i16 = eclReader.getStringNumber(crrentRowNum, 18);
                String i17 = eclReader.getStringNumber(crrentRowNum, 19);
                String i18 = eclReader.getStringNumber(crrentRowNum, 20);
                String i19 = eclReader.getStringNumber(crrentRowNum, 21);
                String i20 = eclReader.getStringNumber(crrentRowNum, 22);
                patternMap.put(tableName + "@" + colName,
                        i1 + "|" + i2 + "|" + i3 + "|" + i4 + "|" + i5 + "|" + i6 + "|" + i7 + "|" + i8 + "|" + i9 + "|"
                                + i10 + "|" + i11 + "|" + i12 + "|" + i13 + "|" + i14 + "|" + i15 + "|" + i16 + "|"
                                + i17 + "|" + i18 + "|" + i19 + "|" + i20);
            }
        } catch (Exception e) {

        }
        return patternMap;

    }

    private void addindexInfo(Attribute attr, String indexstring) {
        if (indexstring != null && !"".equals(indexstring)) {
            String[] arridx = indexstring.split("[|]");
            if (arridx.length > 0) {
                attr.setI1(arridx[0]);
            }
            if (arridx.length > 1) {
                attr.setI2(arridx[1]);
            }
            if (arridx.length > 2) {
                attr.setI3(arridx[2]);
            }
            if (arridx.length > 3) {
                attr.setI4(arridx[3]);
            }
            if (arridx.length > 4) {
                attr.setI5(arridx[4]);
            }
            if (arridx.length > 5) {
                attr.setI6(arridx[5]);
            }
            if (arridx.length > 6) {
                attr.setI7(arridx[6]);
            }
            if (arridx.length > 7) {
                attr.setI8(arridx[7]);
            }
            if (arridx.length > 8) {
                attr.setI9(arridx[8]);
            }
            if (arridx.length > 9) {
                attr.setI10(arridx[9]);
            }
            if (arridx.length > 10) {
                attr.setI11(arridx[10]);
            }
            if (arridx.length > 11) {
                attr.setI12(arridx[11]);
            }
            if (arridx.length > 12) {
                attr.setI13(arridx[12]);
            }
            if (arridx.length > 13) {
                attr.setI14(arridx[13]);
            }
            if (arridx.length > 14) {
                attr.setI15(arridx[14]);
            }
            if (arridx.length > 15) {
                attr.setI16(arridx[15]);
            }
            if (arridx.length > 16) {
                attr.setI17(arridx[16]);
            }
            if (arridx.length > 17) {
                attr.setI18(arridx[17]);
            }
            if (arridx.length > 18) {
                attr.setI19(arridx[18]);
            }
            if (arridx.length > 19) {
                attr.setI20(arridx[19]);
            }
        }
    }

    public List<EventInfo> readerArrived(List<EventInfo> list) {

        List<EventInfo> arrList = new ArrayList<EventInfo>();
        try {

            for (int i = 0; i < list.size(); i++) {
                EventInfo evtinfo = list.get(i);
                if (evtinfo.isArrivedMonitorObject()) {
                    EventInfo newEvtinfo = new EventInfo();
                    evtinfo.copy(newEvtinfo);
                    arrList.add(newEvtinfo);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return arrList;
    }

    public Map<String, EventInfo> getEvtMap() {
        return evtMap;
    }

    public static final String ORI_EXCEL_FILE = "C:/Users/lulin/Desktop/�P�̃e�X�g�������c�[��/APT_�C�x���g���f���ꗗ_Ver2.6.xls";

    public Map<String, String> getSedidxMap() {
        return sedidxMap;
    }
//
//    public static void main(String[] args) {
//        try {
//            List<EventInfo> list = EventListReader.getInstance(ORI_EXCEL_FILE).reader();
//            for (EventInfo event : list) {
//                System.out.println(event.getTableJpname());
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

}