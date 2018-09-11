package com.mall.common.pojo;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MallResult implements Serializable {

	private static final long serialVersionUID = -3572043346731550781L;

	private static final ObjectMapper MAPPER = new ObjectMapper();

	private String msg;

	private Object data;

	private Integer status;

	public MallResult(Object data) {
		this.data = data;
		this.msg = "OK";
		this.status = 200;
	}

	public MallResult(Integer status, String msg, Object data) {
		this.status = status;
		this.msg = msg;
		this.data = data;
	}

	/**
	 * 返回多种类型
	 * 
	 * @param status
	 * @param msg
	 * @return
	 */
	public static MallResult build(Integer status, String msg) {
		return new MallResult(status, msg, null);
	}

	/**
	 * 返回多种类型
	 * 
	 * @param status
	 * @param msg
	 * @param data
	 * @return
	 */
	public static MallResult build(Integer status, String msg, Object data) {
		return new MallResult(status, msg, data);
	}

	/**
	 * 返回成功
	 * 
	 * @return
	 */
	public static MallResult ok() {
		return new MallResult(null);
	}

	/**
	 * 返回成功
	 * 
	 * @param data
	 * @return
	 */
	public static MallResult ok(Object data) {
		return new MallResult(data);
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public static MallResult formatToPojo(String jsonData, Class<?> clazz) {
		try {
			if (clazz == null) {
				return MAPPER.readValue(jsonData, MallResult.class);
			}
			JsonNode jsonNode = MAPPER.readTree(jsonData);
			JsonNode data = jsonNode.get("data");
			Object obj = null;
			if (clazz != null) {
				if (data.isObject()) {
					obj = MAPPER.readValue(data.traverse(), clazz);
				} else if (data.isTextual()) {
					obj = MAPPER.readValue(data.asText(), clazz);
				}
			}
			return build(jsonNode.get("status").intValue(), jsonNode.get("msg").asText(), obj);
		} catch (Exception e) {
			return null;
		}
	}

	public static MallResult format(String json) {
		try {
			return MAPPER.readValue(json, MallResult.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static MallResult formatToList(String jsonData, Class<?> clazz) {
		try {
			JsonNode jsonNode = MAPPER.readTree(jsonData);
			JsonNode data = jsonNode.get("data");
			Object obj = null;
			if (data.isArray() && data.size() > 0) {
				obj = MAPPER.readValue(data.traverse(),
						MAPPER.getTypeFactory().constructCollectionType(List.class, clazz));
			}
			return build(jsonNode.get("status").intValue(), jsonNode.get("msg").asText(), obj);
		} catch (Exception e) {
			return null;
		}
	}
}
