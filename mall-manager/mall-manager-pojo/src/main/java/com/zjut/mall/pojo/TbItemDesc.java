package com.zjut.mall.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author wcyong
 * 
 * @date 2018-09-01
 */
public class TbItemDesc implements Serializable  {
    
	private static final long serialVersionUID = -275822200314810134L;

    private Long itemId;

    private String itemDesc;

    private Date created;

    private Date updated;

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getItemDesc() {
        return itemDesc;
    }

    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc == null ? null : itemDesc.trim();
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