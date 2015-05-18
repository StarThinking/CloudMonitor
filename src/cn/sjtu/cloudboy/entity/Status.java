package cn.sjtu.cloudboy.entity;

public class Status {
	private String hostname;
	private String uuid;

	private String timeStamp;

	private String upTime;
	private String cpuNum;
	private String cpuMhz;
	private String cpuUsage;
	private String memCap;
	private String memUsage;
	private String swapCap;
	private String swapUsage;
	private String diskStorageCap;
	private String diskStorageUsage;
	private String diskIOCap;
	private String diskIO;
	private String networkIOCap;
	private String networkIO;

	public static final String EMPTY_VALUE = "-1";

	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getVmname() {
		return hostname;
	}

	public void setVmname(String vmname) {
		this.hostname = vmname;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getUpTime() {
		return upTime;
	}

	public void setUpTime(String upTime) {
		this.upTime = upTime;
	}

	public String getCpuNum() {
		return cpuNum;
	}

	public void setCpuNum(String cpuNum) {
		this.cpuNum = cpuNum;
	}

	public void setCpuMhz(String cpuMhz) {
		this.cpuMhz = cpuMhz;
	}

	public String getCpuMhz() {
		return cpuMhz;
	}
	
	public String getCpuUsage() {
		return cpuUsage;
	}

	public void setCpuUsage(String cpuUsage) {
		this.cpuUsage = cpuUsage;
	}

	public String getMemCap() {
		return memCap;
	}

	public void setMemCap(String memCap) {
		this.memCap = memCap;
	}

	public String getMemUsage() {
		return memUsage;
	}

	public void setMemUsage(String memUsage) {
		this.memUsage = memUsage;
	}

	public void setSwapCap(String swapCap) {
		this.swapCap = swapCap;
	}

	public void setSwapUsage(String swapUsage) {
		this.swapUsage = swapUsage;
	}
	
	public String getSwapCap() {
		return swapCap;
	}

	public String getSwapUsage() {
		return swapUsage;
	}

	public String getDiskStorageCap() {
		return diskStorageCap;
	}

	public void setDiskStorageCap(String diskStorageCap) {
		this.diskStorageCap = diskStorageCap;
	}

	public String getDiskStorageUsage() {
		return diskStorageUsage;
	}

	public void setDiskStorageUsage(String diskStorageUsage) {
		this.diskStorageUsage = diskStorageUsage;
	}

	public String getDiskIOCap() {
		return diskIOCap;
	}

	public void setDiskIOCap(String diskIOCap) {
		this.diskIOCap = diskIOCap;
	}

	public String getDiskIO() {
		return diskIO;
	}

	public void setDiskIO(String diskIO) {
		this.diskIO = diskIO;
	}

	public String getNetworkIOCap() {
		return networkIOCap;
	}

	public void setNetworkIOCap(String networkIOCap) {
		this.networkIOCap = networkIOCap;
	}

	public String getNetworkIO() {
		return networkIO;
	}

	public void setNetworkIO(String networkIO) {
		this.networkIO = networkIO;
	}

	private static void populate(StringBuilder sb, String content,
			int fixedLength) {
		int size = fixedLength;
		if (content != null) {
			sb.append(content);
			size -= content.length();
		} else {
			sb.append(EMPTY_VALUE);
			size -= EMPTY_VALUE.length();
		}
		while (size > 0) {
			sb.append(" ");
			size--;
		}
	}

	public String toString() {
		StringBuilder s = new StringBuilder();

		populate(s, this.hostname, 10);
		populate(s, this.uuid, 40);
		populate(s, this.upTime, 15);
		s.append(this.hostname).append("@").append(this.uuid).append(" ")
				.append(this.upTime).append("\t");
		if (this.cpuNum != null && this.cpuMhz != null && this.cpuUsage != null) {
			s.append("cpu ").append(this.cpuNum).append("/")
					.append(this.cpuMhz).append("/").append(this.cpuUsage)
					.append("\t");
		}
		if (this.memCap != null && this.memUsage != null) {
			s.append("mem ").append(this.memCap).append("/")
					.append(this.memUsage).append("\t");
		}
		if (this.swapCap != null && this.swapUsage != null) {
			s.append("swap ").append(this.swapCap).append("/")
					.append(this.swapUsage).append("\t");
		}
		if (this.diskStorageCap != null && this.diskStorageUsage != null) {
			s.append("disk ").append(this.diskStorageCap).append("/")
					.append(this.diskStorageUsage).append("\t");
		}
		if (this.diskIO != null) {
			s.append("diskIO ").append(this.diskIO).append("\t");
		}
		if (this.networkIO != null) {
			s.append("networkIO ").append(this.networkIO).append("/")
					.append(this.networkIOCap).append("\t");
		}
		return s.toString();
	}

	public static String getFormatedHeader() {
		StringBuilder s = new StringBuilder();

		populate(s, "vmname", 10);
		populate(s, "uuid", 40);
		populate(s, "upTime", 15);
		populate(s, "timestamp", 15);

		populate(s, "cpuNum", 10);
		populate(s, "cpuMhz", 10);
		populate(s, "cpuUsage", 10);
		populate(s, "memCap", 15);
		populate(s, "memUsage", 10);
		populate(s, "swapCap", 15);
		populate(s, "swapUsage", 10);
		populate(s, "diskCap", 10);
		populate(s, "diskUsage", 10);
		populate(s, "diskIOCap", 15);
		populate(s, "diskIO", 15);
		populate(s, "networkIOCap", 15);
		populate(s, "networkIO", 15);

		return s.toString();
	}

	public String toFormatedString() {
		StringBuilder s = new StringBuilder();

		populate(s, this.hostname, 10);
		populate(s, this.uuid, 40);
		populate(s, this.upTime, 15);
		populate(s, this.timeStamp, 15);

		populate(s, this.cpuNum, 10);
		populate(s, this.cpuMhz, 10);
		populate(s, this.cpuUsage, 10);
		populate(s, this.memCap, 15);
		populate(s, this.memUsage, 10);
		populate(s, this.swapCap, 15);
		populate(s, this.swapUsage, 10);
		populate(s, this.diskStorageCap, 10);
		populate(s, this.diskStorageUsage, 10);
		populate(s, this.diskIOCap, 15);
		populate(s, this.diskIO, 15);
		populate(s, this.networkIOCap, 15);
		populate(s, this.networkIO, 15);

		return s.toString();
	}

	public static String getTabbedHeader() {
		StringBuilder s = new StringBuilder();

		s.append("vmname").append("\t");
		s.append("uuid").append("\t");
		s.append("upTime").append("\t");
		s.append("timestamp").append("\t");

		s.append("cpuNum").append("\t");
		s.append("cpuMhz").append("\t");
		s.append("cpuUsage").append("\t");

		s.append("memCap").append("\t");
		s.append("memUsage").append("\t");
		s.append("swapCap").append("\t");
		s.append("swapUsage").append("\t");

		s.append("diskCap").append("\t");
		s.append("diskUsage").append("\t");
		s.append("diskIOCap").append("\t");
		s.append("diskIO").append("\t");

		s.append("networkIOCap").append("\t");
		s.append("networkIO").append("\t");

		return s.toString();
	}

	private void addTabInfo(StringBuilder s, String info) {
		if (info == null) {
			s.append(EMPTY_VALUE).append("\t");
		} else {
			s.append(info).append("\t");
		}
	}

	public String toTabbedString() {
		StringBuilder s = new StringBuilder();

		s.append(this.hostname).append("\t");
		s.append(this.uuid).append("\t");
		s.append(this.upTime).append("\t");
		s.append(this.timeStamp).append("\t");

		this.addTabInfo(s, this.cpuNum);
		this.addTabInfo(s, this.cpuMhz);
		this.addTabInfo(s, this.cpuUsage);

		this.addTabInfo(s, this.memCap);
		this.addTabInfo(s, this.memUsage);
		this.addTabInfo(s, this.swapCap);
		this.addTabInfo(s, this.swapUsage);

		this.addTabInfo(s, this.diskStorageCap);
		this.addTabInfo(s, this.diskStorageUsage);
		this.addTabInfo(s, this.diskIOCap);
		this.addTabInfo(s, this.diskIO);

		this.addTabInfo(s, this.networkIOCap);
		this.addTabInfo(s, this.networkIO);

		return s.toString();
	}

	public String toXMLString() {
		StringBuilder s = new StringBuilder("<status>");

		if (this.hostname != null) {
			s.append("<hn value=\"").append(this.hostname).append("\"/>");
		}
		if (this.uuid != null) {
			s.append("<uuid value=\"").append(this.uuid).append("\"/>");
		}
		if (this.upTime != null) {
			s.append("<ut value=\"").append(this.upTime).append("\"/>");
		}
		if (this.timeStamp != null) {
			s.append("<ts value=\"").append(this.timeStamp).append("\"/>");
		}
		if (this.cpuNum != null) {
			s.append("<cn value=\"").append(this.cpuNum).append("\"/>");
		}
		if (this.cpuMhz != null) {
			s.append("<cmz value=\"").append(this.cpuMhz).append("\"/>");
		}
		if (this.cpuUsage != null) {
			s.append("<cu value=\"").append(this.cpuUsage).append("\"/>");
		}
		if (this.memCap != null) {
			s.append("<mc value=\"").append(this.memCap).append("\"/>");
		}
		if (this.memUsage != null) {
			s.append("<mu value=\"").append(this.memUsage).append("\"/>");
		}
		if (this.swapCap != null) {
			s.append("<sc value=\"").append(this.swapCap).append("\"/>");
		}
		if (this.swapUsage != null) {
			s.append("<su value=\"").append(this.swapUsage).append("\"/>");
		}
		if (this.diskStorageCap != null) {
			s.append("<dsc value=\"").append(this.diskStorageCap)
					.append("\"/>");
		}
		if (this.diskStorageUsage != null) {
			s.append("<dsu value=\"").append(this.diskStorageUsage)
					.append("\"/>");
		}
		if (this.diskIOCap != null) {
			s.append("<dioc value=\"").append(this.diskIOCap).append("\"/>");
		}
		if (this.diskIO != null) {
			s.append("<dio value=\"").append(this.diskIO).append("\"/>");
		}
		if (this.networkIOCap != null) {
			s.append("<nioc value=\"").append(this.networkIOCap).append("\"/>");
		}
		if (this.networkIO != null) {
			s.append("<nio value=\"").append(this.networkIO).append("\"/>");
		}

		s.append("</status>");
		return s.toString();
	}
}
