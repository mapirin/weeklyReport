package com.report.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ReportCreateForm implements Serializable {
	public static long serialVersionUID = 1L;

	private String comment;
	private String thisWeek1;
	private String thisWeek2;
	private String thisWeek3;
	private String nextWeek1;
	private String nextWeek2;
	private String nextWeek3;
	private String tech1;
	private String tech2;
	private String tech3;
	private String tech4;
	private String tech5;
	private String task;
	private String progress;
	private String thoughts;
	private String etc;

	public ReportCreateForm() {
		tech1 = "";
		tech2 = "";
		tech3 = "";
		tech4 = "";
		tech5 = "";
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getThisWeek1() {
		return thisWeek1;
	}

	public void setThisWeek1(String thisWeek1) {
		this.thisWeek1 = thisWeek1;
	}

	public String getThisWeek2() {
		return thisWeek2;
	}

	public void setThisWeek2(String thisWeek2) {
		this.thisWeek2 = thisWeek2;
	}

	public String getThisWeek3() {
		return thisWeek3;
	}

	public void setThisWeek3(String thisWeek3) {
		this.thisWeek3 = thisWeek3;
	}

	public String getNextWeek1() {
		return nextWeek1;
	}

	public void setNextWeek1(String nextWeek1) {
		this.nextWeek1 = nextWeek1;
	}

	public String getNextWeek2() {
		return nextWeek2;
	}

	public void setNextWeek2(String nextWeek2) {
		this.nextWeek2 = nextWeek2;
	}

	public String getNextWeek3() {
		return nextWeek3;
	}

	public void setNextWeek3(String nextWeek3) {
		this.nextWeek3 = nextWeek3;
	}

	public String getTech1() {
		return tech1;
	}

	public void setTech1(String tech1) {
		this.tech1 = tech1;
	}

	public String getTech2() {
		return tech2;
	}

	public void setTech2(String tech2) {
		this.tech2 = tech2;
	}

	public String getTech3() {
		return tech3;
	}

	public void setTech3(String tech3) {
		this.tech3 = tech3;
	}

	public String getTech4() {
		return tech4;
	}

	public void setTech4(String tech4) {
		this.tech4 = tech4;
	}

	public String getTech5() {
		return tech5;
	}

	public void setTech5(String tech5) {
		this.tech5 = tech5;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public String getProgress() {
		return progress;
	}

	public void setProgress(String progress) {
		this.progress = progress;
	}

	public String getThoughts() {
		return thoughts;
	}

	public void setThoughts(String thoughts) {
		this.thoughts = thoughts;
	}

	public String getEtc() {
		return etc;
	}

	public void setEtc(String etc) {
		this.etc = etc;
	}

	public List<String> getInfomationList() {
		String tech1 = ReportInfomationEnum.INFOMATION_EXCEL.getInformationName();
		String tech2 = ReportInfomationEnum.INFOMATION_VBA.getInformationName();
		String tech3 = ReportInfomationEnum.INFOMATION_LINUX.getInformationName();
		String tech4 = ReportInfomationEnum.INFOMATION_LOG.getInformationName();
		String tech5 = ReportInfomationEnum.INFOMATION_JAVA.getInformationName();

		List<String> infomationList = new ArrayList<>();

		Collections.addAll(infomationList, tech1, tech2, tech3, tech4, tech5);

		return infomationList;
	}

}
