package cn.com.hyun.generator.tools;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 * <p>ExportDto class.</p>
 *
 * @author GengChao
 * @version $Id: $Id
 */
public class ExportDto {
    private HSSFWorkbook wookBook;//WorkBook对象
    private String fileName;//文件名

    /**
     * <p>Constructor for ExportDto.</p>
     */
    public ExportDto() {
    }

    /**
     * <p>Constructor for ExportDto.</p>
     *
     * @param wb a {@link org.apache.poi.hssf.usermodel.HSSFWorkbook} object.
     * @param fileName a {@link String} object.
     */
    public ExportDto(HSSFWorkbook wb, String fileName) {
        this.wookBook = wb;
        this.fileName = fileName;
    }

    /**
     * <p>Getter for the field <code>wookBook</code>.</p>
     *
     * @return a {@link org.apache.poi.hssf.usermodel.HSSFWorkbook} object.
     */
    public HSSFWorkbook getWookBook() {
        return wookBook;
    }

    /**
     * <p>Setter for the field <code>wookBook</code>.</p>
     *
     * @param wookBook a {@link org.apache.poi.hssf.usermodel.HSSFWorkbook} object.
     */
    public void setWookBook(HSSFWorkbook wookBook) {
        this.wookBook = wookBook;
    }

    /**
     * <p>Getter for the field <code>fileName</code>.</p>
     *
     * @return a {@link String} object.
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * <p>Setter for the field <code>fileName</code>.</p>
     *
     * @param fileName a {@link String} object.
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
