package com.example.schedule.sys.bean;

import javax.persistence.Transient;
import java.util.Date;

/**
 * ***************************************************************************
 * <p/>
 * Copyright(C) 2020 <b>深圳中兴网信科技有限公司</b>
 * <p/>
 * <b>描述: 接口配置实体类</b>
 * <p/>
 * <p/>
 * <b>Change History</b>
 * <p/>
 * $1.0.0$ 2020年2月10日 上午11:16:21初始版本。
 * <p/>
 * *************************************************************************** <br>
 * 
 * @author LiuQiang
 */
public class FbpConfigWs {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 服务标识
	 */
	private String wsCode;
	/**
	 * 服务名称
	 */
	private String wsName;
	/**
	 * 接口服务地址
	 */
	private String wsUrl;
	/**
	 * 接口服务地址名称
	 */
	@Transient
	private String sysCodeName;
	/**
	 * 接口所属系统编码
	 */
	private String sysCode;
	/**
	 * 接口类型（1：服务调用方 2：服务提供方）
	 */
	private String wsType;
	/**
	 * 服务描述
	 */
	private String remark;
	/**
	 * 参数（提供方为输出参数、调用方为输入参数）
	 */
	private String wsParams;
	/**
	 * 类路径（当接口类型为2提供方时必填）
	 */
	private String classPath;
	/**
	 * 方法名（当接口类型为2提供方时必填）
	 */
	private String methodName;
	/*** 上次成功调用时间 */
	private Date lastCallTime;

	private String version;
	/**
	 * 接口地址前缀
	 */
	@Transient
	private String urlPrefix;

	/**
	 * 服务标识
	 * 
	 * @return
	 */
	public String getWsCode() {
		return wsCode;
	}

	/**
	 * 服务标识
	 * 
	 * @param wsCode
	 */
	public void setWsCode(String wsCode) {
		this.wsCode = wsCode;
	}

	/**
	 * 服务名称
	 * 
	 * @return
	 */
	public String getWsName() {
		return wsName;
	}

	/**
	 * 服务名称
	 * 
	 * @param wsName
	 */
	public void setWsName(String wsName) {
		this.wsName = wsName;
	}

	/**
	 * 接口服务地址
	 * 
	 * @return
	 */
	public String getWsUrl() {
		return wsUrl;
	}

	/**
	 * 接口服务地址
	 * 
	 * @param wsUrl
	 */
	public void setWsUrl(String wsUrl) {
		this.wsUrl = wsUrl;
	}

	/**
	 * 接口类型（1：服务调用方 2：服务提供方）
	 * 
	 * @return
	 */
	public String getWsType() {
		return wsType;
	}

	/**
	 * 接口类型（1：服务调用方 2：服务提供方）
	 * 
	 * @param wsType
	 */
	public void setWsType(String wsType) {
		this.wsType = wsType;
	}

	/**
	 * 服务描述
	 * 
	 * @return
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * 服务描述
	 * 
	 * @param remark
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * 参数（提供方为输出参数、调用方为输入参数）
	 * 
	 * @return
	 */
	public String getWsParams() {
		return wsParams;
	}

	/**
	 * 参数（提供方为输出参数、调用方为输入参数）
	 * 
	 * @param wsParams
	 */
	public void setWsParams(String wsParams) {
		this.wsParams = wsParams;
	}

	public String getClassPath() {
		return classPath;
	}

	public void setClassPath(String classPath) {
		this.classPath = classPath;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public String getSysCodeName() {
		return sysCodeName;
	}

	public void setSysCodeName(String sysCodeName) {
		this.sysCodeName = sysCodeName;
	}

	public String getSysCode() {
		return sysCode;
	}

	public void setSysCode(String sysCode) {
		this.sysCode = sysCode;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	/**
	 * @return the lastCallTime
	 */
	public Date getLastCallTime() {
		return lastCallTime;
	}

	/**
	 * @param lastCallTime the lastCallTime to set
	 */
	public void setLastCallTime(Date lastCallTime) {
		this.lastCallTime = lastCallTime;
	}

	public String getUrlPrefix() {
		return urlPrefix;
	}

	public void setUrlPrefix(String urlPrefix) {
		this.urlPrefix = urlPrefix;
	}
}
