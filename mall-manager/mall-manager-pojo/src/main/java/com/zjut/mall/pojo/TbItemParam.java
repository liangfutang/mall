package com.zjut.mall.pojo;

import java.util.Date;

/**
 * ��Ʒ�������
 * 
 * @author wcyong
 * 
 * @date 2018-09-01
 */
public class TbItemParam {
    private Long id;

    /**
     * ��Ʒ��ĿID
     */
    private Long itemCatId;

    /**
     * �������ݣ���ʽΪjson��ʽ
     */
    private String paramData;

    private Date created;

    private Date updated;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getItemCatId() {
        return itemCatId;
    }

    public void setItemCatId(Long itemCatId) {
        this.itemCatId = itemCatId;
    }

    public String getParamData() {
        return paramData;
    }

    public void setParamData(String paramData) {
        this.paramData = paramData == null ? null : paramData.trim();
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