package cn.com.hyun.framework.web.util;

import cn.com.hyun.framework.web.util.verifycode.VerifyCode;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

/**
 * Created by hyunwoo
 */
public final class HttpVerifyCode {
    private static final String DEFAULT_SUFFIX_TYPE = "png";
    private int defaultImgHeight = 35;
    private int defaultCodeLength = 4;
    private int defaultDisturbCount = 50;
    private int rotate = 1;
    private BufferedImage image;
    private Graphics2D graphics;
    private String generateCode;
    private VerifyCode verifyCode;

    public void generate() {
        checkVerifyCodeFactory();
        createImage();
        createGraphics();
        doGenerate();
    }

    private void checkVerifyCodeFactory() {
        if (null == verifyCode) {
            throw new NullPointerException();
        }
    }

    private void createImage() {
        image = new BufferedImage(getDefaultImgWidth(), getDefaultImgHeight(), BufferedImage.TYPE_INT_RGB);
    }

    private void createGraphics() {
        graphics = image.createGraphics();
        Random random = new Random();

        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, getDefaultImgWidth(), getDefaultImgHeight());
        Font font = new Font("楷体", Font.BOLD, getDefaultFontHeight());
        graphics.setFont(font);

        for (int i = 0; i < getDefaultDisturbCount(); i++) {
            int xs = random.nextInt(getDefaultImgWidth());
            int ys = random.nextInt(getDefaultImgHeight());
            int xe = xs + random.nextInt(getDefaultImgWidth() / 8);
            int ye = ys + random.nextInt(getDefaultImgHeight() / 8);
            graphics.setColor(new Color(136, 136, 136));
            graphics.drawLine(xs, ys, xe, ye);
        }
    }


    private void doGenerate() {
        StringBuilder randomCode = new StringBuilder();
        Random random = new Random();
        char[] charSequence = verifyCode.getCharSequence();

        double oldrot = 0;
        for (int i = 0; i < getDefaultCodeLength(); i++) {
            String str = String.valueOf(charSequence[random.nextInt(charSequence.length)]);
            int red = random.nextInt(255);
            int green = random.nextInt(255);
            int blue = random.nextInt(255);
            if (isRotate()) {
                double rot = -0.25 + Math.abs(Math.toRadians(random.nextInt(30)));
                graphics.rotate(-oldrot, 10, 15);
                oldrot = rot;
                graphics.rotate(rot, 15 * i + 10, 15);
            }
            graphics.setColor(new Color(red, green, blue));
            graphics.drawString(str, (i + 1) * getCharacterWidth(), getVerticalPosition());
            randomCode.append(str);
        }

        this.generateCode = randomCode.toString();
    }


    private int getDefaultImgWidth() {
        return getDefaultCodeLength() * 25;
    }

    private int getDefaultFontHeight() {
        return getDefaultImgHeight() - 2;
    }

    private int getCharacterWidth() {
        return getDefaultImgWidth() / (getDefaultCodeLength() + 2);
    }

    private int getVerticalPosition() {
        return getDefaultImgHeight() - 4;
    }

    public int getDefaultImgHeight() {
        return defaultImgHeight;
    }

    public int getDefaultCodeLength() {
        return defaultCodeLength;
    }

    public void setDefaultCodeLength(int defaultCodeLength) {
        this.defaultCodeLength = defaultCodeLength;
    }

    public int getDefaultDisturbCount() {
        return defaultDisturbCount;
    }

    public void setDefaultDisturbCount(int defaultDisturbCount) {
        this.defaultDisturbCount = defaultDisturbCount;
    }

    public String getGenerateCode() {
        return generateCode;
    }

    public VerifyCode getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(VerifyCode verifyCode) {
        this.verifyCode = verifyCode;
    }

    public int getRotate() {
        return rotate;
    }

    private boolean isRotate() {
        return rotate == 1;
    }

    public void setRotate(int rotate) {
        this.rotate = rotate;
    }

    public void write(String path) throws IOException {
        OutputStream os = new FileOutputStream(path);
        this.write(os);
    }

    public void write(OutputStream os) throws IOException {
        ImageIO.write(image, DEFAULT_SUFFIX_TYPE, os);
        os.close();
    }

}
