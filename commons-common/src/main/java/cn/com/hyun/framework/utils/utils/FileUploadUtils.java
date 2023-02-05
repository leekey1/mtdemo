package cn.com.hyun.framework.utils.utils;

import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by hyunwoo
 */
public final class FileUploadUtils {
    private FileUploadUtils() {
    }

    public static String upload(MultipartFile multipartFile, String prefix) throws IOException {
        return upload(multipartFile, prefix, null, 1, 0, 0, 0);
    }

    public static String upload(MultipartFile multipartFile, String prefix, String watermarkPath, float alpha, int degreee, int spacingX, int spacingY) throws IOException {
        AssertUtils.notNull(multipartFile, "upload file is null");
        AssertUtils.notBlank(prefix, "prefix is null");

        String extension = FilenameUtils.getExtension(multipartFile.getOriginalFilename());
        Date date = DateUtils.now();
        String year = DateFormatUtils.format(date, "yyyy");
        String monthDay = DateFormatUtils.format(date, "MMdd");
        String randomName = String.valueOf(System.nanoTime());

        StringBuilder generateFilePath = new StringBuilder();
        generateFilePath.append(year).append(File.separator)
                .append(monthDay).append(File.separator).append(randomName).append(".").append(extension);

        StringBuilder destFilePath = new StringBuilder();
        destFilePath.append(prefix).append(File.separator).append(generateFilePath);
        File destFile = new File(destFilePath.toString());

        if (!destFile.getParentFile().exists()) {
            destFile.getParentFile().mkdirs();
        }

        if (StringUtils.isNotBlank(watermarkPath)) {
            // 加水印
            if ("GIF".equals(extension.toUpperCase()) || "JPG".equals(extension.toUpperCase()) || "PNG".equals(extension.toUpperCase())) {
                // 图片加水印
                BufferedImage sourceImg = ImageIO.read(multipartFile.getInputStream());
                BufferedImage watermarkImg = generateWatermark(watermarkPath, alpha, degreee, spacingX, spacingY, sourceImg.getWidth(), sourceImg.getHeight());
                imgAddWatermark(sourceImg, watermarkImg, destFilePath.toString(), extension);
            } else if ("PDF".equals(extension.toUpperCase())) {
                // pdf加水印
                multipartFile.transferTo(destFile);
                PdfReader reader = new PdfReader(multipartFile.getInputStream());
                if (reader.getNumberOfPages() > 0) {
                    com.itextpdf.text.Rectangle mediabox = reader.getPageSize(1);
                    BufferedImage watermarkImg = generateWatermark(watermarkPath, alpha, degreee, spacingX, spacingY, (int) mediabox.getWidth(), (int) mediabox.getHeight());
                    pdfAddWatermark(watermarkImg, destFilePath.toString(), reader);
                }
            } else {
                multipartFile.transferTo(destFile);
            }
        } else {
            multipartFile.transferTo(destFile);
        }

        return generateFilePath.toString();
    }

    private static void imgAddWatermark(BufferedImage sourceImg, BufferedImage watermarkImg, String destFilePath, String extension) throws IOException {
        OutputStream os = null;

        Graphics2D graphics = sourceImg.createGraphics();
        graphics.drawImage(watermarkImg, 0, 0, null);
        try {
            os = new FileOutputStream(destFilePath.toString());
            ImageIO.write(sourceImg, extension.toUpperCase(), os);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != os) {
                    os.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static void pdfAddWatermark(BufferedImage watermarkImg, String destFilePath, PdfReader reader) throws IOException {
        try {
            if (reader.getNumberOfPages() > 0) {
                PdfStamper stamp = new PdfStamper(reader, new FileOutputStream(destFilePath));
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                ImageIO.write(watermarkImg, "PNG", out);
                com.itextpdf.text.Image img = com.itextpdf.text.Image.getInstance(out.toByteArray());// 插入水印
                img.setAbsolutePosition(0, 0);
                for (int i = 1; i <= reader.getNumberOfPages(); i++) {
                    PdfContentByte under = stamp.getOverContent(i);
                    under.addImage(img);
                }
                stamp.close();// 关闭
            }
        } catch (Exception e) {
            e.getStackTrace();
        }

    }

    public static BufferedImage generateWatermark(String watermarkPath, float alpha, int degreee, int spacingX, int spacingY, int maxWidth, int maxHeight) throws IOException {
        // 1、取得水印图片
        BufferedImage watermarkImg = ImageIO.read(new File(watermarkPath));
        Graphics2D watermarkImgGraphics = watermarkImg.createGraphics();
        int watermarkImgWidth = watermarkImg.getWidth() + spacingX;// 水印图片长
        int watermarkImgHeight = watermarkImg.getHeight() + spacingY;// 水印图片宽
        int watermarkImgDiagonal = (int) Math.sqrt(Math.pow(watermarkImgWidth, 2) + Math.pow(watermarkImgHeight, 2)); // 水印图片对角线长度
        double diagonalDegree = Math.toDegrees(Math.atan((double) watermarkImgHeight / (double) watermarkImgWidth));
        // 2、计算旋转后水印图片的长宽
        double diagonalDegreeeAfterRotate = diagonalDegree + (degreee < 0 ? degreee * -1 : degreee);
        int widthAfterRotate = watermarkImgWidth;
        int heightAfterRotate = watermarkImgHeight;
        if (degreee != 0) {
            widthAfterRotate = BigDecimal.valueOf(Math.cos(Math.toRadians(diagonalDegreeeAfterRotate > 90 ? 180 - diagonalDegreeeAfterRotate : diagonalDegreeeAfterRotate)) * watermarkImgDiagonal).setScale(0, RoundingMode.UP).intValue();
            heightAfterRotate = BigDecimal.valueOf(Math.sin(Math.toRadians(diagonalDegreeeAfterRotate)) * watermarkImgDiagonal).setScale(0, RoundingMode.UP).intValue();
        }

        // 3、生成新的水印图片，用来保存旋转后的水印
        BufferedImage newWatermarkImg = watermarkImgGraphics.getDeviceConfiguration().createCompatibleImage(widthAfterRotate, heightAfterRotate, Transparency.TRANSLUCENT);
        watermarkImgGraphics.dispose();
        watermarkImgGraphics = newWatermarkImg.createGraphics();
        Composite composite = watermarkImgGraphics.getComposite();
        // 4、设置边缘锯齿处理
        watermarkImgGraphics.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        // 5、设置不透明度
        watermarkImgGraphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        // 6、设置旋转角度和旋转中心
        watermarkImgGraphics.rotate(Math.toRadians(degreee), (double) widthAfterRotate / 2, (double) heightAfterRotate / 2);
        // 7、将老的水印图片绘制到新的水印图片上
        watermarkImgGraphics.drawImage(watermarkImg, (widthAfterRotate - watermarkImgWidth + spacingX) / 2, (heightAfterRotate - watermarkImgHeight + spacingY) / 2, null);
        watermarkImgGraphics.setComposite(composite);
        watermarkImgGraphics.dispose();

        BufferedImage outputImg = newWatermarkImg;
        int baseWidth = outputImg.getWidth();
        int baseHeight = outputImg.getHeight();

        // 8、将新的水印图片循环拼接
        for (int row = 0; row * baseHeight < maxHeight; row++) {
            int y = row * baseHeight;
            for (int cell = 0; cell * baseWidth < maxWidth; cell++) {
                int x = cell * baseWidth;
                int newWidth = (cell + 1) * baseWidth;
                int newHeight = (row + 1) * baseHeight;

                Graphics2D graphics = outputImg.createGraphics();
                BufferedImage imageNew = graphics.getDeviceConfiguration().createCompatibleImage(newWidth >= outputImg.getWidth() ? newWidth : outputImg.getWidth(), newHeight, Transparency.TRANSLUCENT);
                graphics.dispose();
                graphics = imageNew.createGraphics();
                graphics.drawImage(outputImg, 0, 0, null);
                if (x != 0 || y != 0) {
                    graphics.drawImage(newWatermarkImg, x, y, null);
                }
                graphics.dispose();
                outputImg = imageNew;
            }
        }

        return outputImg;
    }

    public static Map<String, String> upload(MultipartFile[] multipartFiles, String prefix) throws IOException {
        AssertUtils.notEmpty(multipartFiles, "upload files is null");
        AssertUtils.notBlank(prefix, "prefix is null");

        Map<String, String> pathMap = new HashMap<>();
        for (MultipartFile multipartFile : multipartFiles) {
            String fileName = multipartFile.getOriginalFilename();
            String generatePath = upload(multipartFile, prefix);
            pathMap.put(fileName, generatePath);
        }

        return pathMap;
    }
}
