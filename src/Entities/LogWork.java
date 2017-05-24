package Entities;

import java.util.Date;

public class LogWork extends BaseEntityWithId {

	
	private int TaskId;
	private int UserId;
	private int logTime;
	private Date logDate;
	
	
	
	public int getLogTime() {
		return logTime;
	}
	public void setLogTime(int logTime) {
		this.logTime = logTime;
	}
	public int getTaskId() {
		return TaskId;
	}
	public void setTaskId(int taskId) {
		TaskId = taskId;
	}
	public int getUserId() {
		return UserId;
	}
	public void setUserId(int userId) {
		UserId = userId;
	}
	public Date getLogDate() {
		return logDate;
	}
	public void setLogDate(Date logDate) {
		this.logDate = logDate;
	}
}
