package com.zjut.mall.pojo;

import java.util.Date;

/**
 * ��Ʒ��
 * 
 * @author wcyong
 * 
 * @date 2018-09-01
 */
public class TbItem {
    /**
     * ��Ʒid��ͬʱҲ����Ʒ���
     */
    private Long id;

    /**
     * ��Ʒ����
     */
    private String title;

    /**
     * ��Ʒ����
     */
    private String sellPoint;

    /**
     * ��Ʒ�۸񣬵�λΪ����
     */
    private Long price;

    /**
     * �������
     */
    private Integer num;

    /**
     * ��Ʒ������
     */
    private String barcode;

    /**
     * ��ƷͼƬ
     */
    private String image;

    /**
     * ������Ŀ��Ҷ����Ŀ
     */
    private Long cid;

    /**
     * ��Ʒ״̬��1-������2-�¼ܣ�3-ɾ��
     */
    private Byte status;

    /**
     * ����ʱ��
     */
    private Date created;

    /**
     * ����ʱ��
     */
    private Date updated;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getSellPoint() {
        return sellPoint;
    }

    public void setSellPoint(String sellPoint) {
        this.sellPoint = sellPoint == null ? null : sellPoint.trim();
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode == null ? null : barcode.trim();
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image == null ? null : image.trim();
    }

    public Long getCid() {
        return cid;
    }

    public void setCid(Long cid) {
        this.cid = cid;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }
}