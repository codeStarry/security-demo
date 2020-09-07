package org.lsy.learn.security.validate;

import cn.hutool.core.img.ImgUtil;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 图形验证码
 */
public class ImageCode {

    private BufferedImage image;
    private Integer height;
    private Integer width;
    private Integer lineCount;
    private Integer numCount;
    private String code;
    private LocalDateTime expiredTime;
    //指定多少分钟后过期
    private Integer minute;
    private String stringSample;
    private ThreadLocalRandom random = ThreadLocalRandom.current();
    private static String defaultSample = "0123456789qwertyuioplkjhgfdsazxcvbnmMNBVCXZASDFGHJKLPOIUYTREWQ";

    public ImageCode() {
        this(120, 40, 4, 100);
    }

    public ImageCode(Integer width, Integer height, Integer numCount, Integer lineCount) {
        this(width, height, numCount, lineCount, null, 2);
    }

    public ImageCode(Integer width, Integer height, Integer numCount, Integer lineCount, String stringSample, Integer minute) {
        this.width = width;
        this.height = height;
        this.numCount = numCount;
        this.lineCount = lineCount;
        this.stringSample = stringSample;
        this.minute = minute;
        this.expiredTime = minute < 0 ? null : LocalDateTime.now().plusMinutes(minute);
        this.createCode();
    }

    /**
     * 创建验证码
     */
    private void createCode() {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = image.createGraphics();
        graphics.fillRect(0, 0, width, height);
        this.drawLine(graphics);
        String code = this.randomCode();
        this.drawString(graphics, code);
        this.image = image;
        this.code = code;
    }

    /**
     * 是否过期
     *
     * @return  true:过期  or  false:未过期
     */
    public Boolean isExpired() {
        if (null == expiredTime) {
            return false;
        }
        return LocalDateTime.now().isAfter(expiredTime);
    }

    /**
     * 绘制干扰线
     *
     * @param graphics
     */
    private void drawLine(Graphics2D graphics) {

        for (int i = 0; i < this.lineCount; ++i) {
            int xs = random.nextInt(this.width);
            int ys = random.nextInt(this.height);
            int xe = xs + random.nextInt(this.width / 8);
            int ye = ys + random.nextInt(this.height / 8);
            graphics.setColor(ImgUtil.randomColor(random));
            graphics.drawLine(xs, ys, xe, ye);
        }
    }

    /**
     * 绘制字符串
     *
     * @param graphics
     * @param str
     * @return
     */
    private void drawString(Graphics2D graphics, String str) {
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Font font = new Font("SansSerif", 0, (int)((double)this.height * 0.75D));
        graphics.setFont(font);
        int len = str.length();
        int charWidth = width / len;

        int y = getCenterY(graphics);

        for (int i = 0; i < len; ++i) {
            graphics.setColor(new Color(77, 123, 252));
            graphics.drawString(String.valueOf(str.charAt(i)), i * charWidth, y);
        }

    }

    /**
     * 获取 Y 轴
     *
     * @param graphics
     * @return
     */
    private int getCenterY(Graphics2D graphics) {
        FontMetrics metrics = graphics.getFontMetrics();
        int y;
        if (Objects.nonNull(metrics)) {
            y = (height - metrics.getHeight()) / 2 + metrics.getAscent();
        }else {
            y = height / 3;
        }

        return y;
    }

    /**
     * 生成随机字符串
     *
     * @return  String
     */
    private String randomCode() {
        String string = null != stringSample ? stringSample : defaultSample;

        if (numCount < 1) {
            numCount = 1;
        }
        StringBuilder sb = new StringBuilder(numCount);
        for (int i = 0; i < numCount; ++i) {
            int randomIndex = random.nextInt(string.length());
            sb.append(string.charAt(randomIndex));
        }

        return sb.toString();
    }

    public BufferedImage getImage() {
        return this.image;
    }

    public Integer getHeight() {
        return this.height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getWidth() {
        return this.width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getLineCount() {
        return this.lineCount;
    }

    public void setLineCount(Integer lineCount) {
        this.lineCount = lineCount;
    }

    public Integer getNumCount() {
        return this.numCount;
    }

    public void setNumCount(Integer numCount) {
        this.numCount = numCount;
    }

    public String getCode() {
        return this.code;
    }

    public LocalDateTime getExpiredTime() {
        return this.expiredTime;
    }


    public Integer getMinute() {
        return this.minute;
    }

    public void setMinute(Integer minute) {
        this.minute = minute;
    }

    public String getStringSample() {
        return this.stringSample;
    }

    public void setStringSample(String stringSample) {
        this.stringSample = stringSample;
    }

    @Override
    public String toString() {
        return "ImageCode{" +
                "image=" + image +
                ", height=" + height +
                ", width=" + width +
                ", lineCount=" + lineCount +
                ", numCount=" + numCount +
                ", code='" + code + '\'' +
                ", expiredTime=" + expiredTime +
                ", minute=" + minute +
                ", stringSample='" + stringSample + '\'' +
                ", random=" + random +
                '}';
    }
}
