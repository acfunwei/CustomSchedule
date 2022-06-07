package com.example.schedule.sys.bean;


import javax.persistence.Transient;
import java.util.Date;

/**
 * @author ZW
 */
public class FbpSysJobsLog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private Date createDate;

	/*** 任务ID */
	private String jobId;
	/*** 任务编码 */
	private String jobCode;
	/*** 任务名称 */
	private String jobName;
	/*** 开始时间 */
	private Date beginTime;
	/*** 结束时间 */
	private Date endTime;
	/*** 耗时（秒） */
	private long timeConsuming;
	/*** 执行结果 */
	private String msg;
	public String getJobId() {
		return jobId;
	}
	public void setJobId(String jobId) {
		this.jobId = jobId;
	}
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
	public Date getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public long getTimeConsuming() {
		return timeConsuming;
	}
	public void setTimeConsuming(long timeConsuming) {
		this.timeConsuming = timeConsuming;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
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
}
