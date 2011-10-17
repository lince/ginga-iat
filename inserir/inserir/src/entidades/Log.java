package entidades;

import java.sql.Timestamp;

public class Log {
	private int idLog;
	private Timestamp log_Date;
	private String ip;
	private long size;

	public void setIdLog(int idLog) {
		this.idLog = idLog;
	}

	public int getIdLog() {
		return idLog;
	}

	public void setLog_Date(Timestamp log_Date) {
		this.log_Date = log_Date;
	}

	public Timestamp getLog_Date() {
		return log_Date;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getIp() {
		return ip;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public long getSize() {
		return size;
	}
}
