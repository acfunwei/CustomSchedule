package com.example.schedule.sys.bean;


import java.util.Date;

/**
 * @author ZW
 */
public class FbpSysJobs {
	private static final long serialVersionUID = 1L;
	private Long id;
	private Date createDate;
	private String createBy;
	private Date updateDate;
	private String updateBy;
	private Short enableFlag;
	/*** 任务编号 */
	private String jobCode;
	/*** 任务名称 */
	private String jobName;
	/*** 类型 */
	private String jobType;

	/**
	 * 定时触发表达式
	 */
	private String cornExpress;

	private String timeUnit;

	private Integer intervalNum;

	/*** 备注 */
	private String remark;
	/*** 任务执行时间 */
	private Date finalExecTime;
	/*** 最后成功同步时间 */
	private Date syncDate;
	/*** 接口指定特殊参数，JSON格式 */
	private String parameter;
	public String getJobCode() {
		return jobCode;
	}
	public void setJobCode(String jobCode) {
		this.jobCode = jobCode;
	}
	public String getJobName() {
		return jobName;
	}
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	public String getJobType() {
		return jobType;
	}
	public void setJobType(String jobType) {
		this.jobType = jobType;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Date getFinalExecTime() {
		return finalExecTime;
	}
	public void setFinalExecTime(Date finalExecTime) {
		this.finalExecTime = finalExecTime;
	}
	public Date getSyncDate() {
		return syncDate;
	}
	public void setSyncDate(Date syncDate) {
		this.syncDate = syncDate;
	}
	public String getParameter() {
		return parameter;
	}
	public void setParameter(String parameter) {
		this.parameter = parameter;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public Short getEnableFlag() {
		return enableFlag;
	}

	public void setEnableFlag(Short enableFlag) {
		this.enableFlag = enableFlag;
	}

	public String getCornExpress() {
		return cornExpress;
	}

	public void setCornExpress(String cornExpress) {
		this.cornExpress = cornExpress;
	}

	public String getTimeUnit() {
		return timeUnit;
	}

	public void setTimeUnit(String timeUnit) {
		this.timeUnit = timeUnit;
	}

	public Integer getIntervalNum() {
		return intervalNum;
	}

	public void setIntervalNum(Integer intervalNum) {
		this.intervalNum = intervalNum;
	}
}
