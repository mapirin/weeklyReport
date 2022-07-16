package com.report.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.report.service.ReportService;

@Controller
@SessionAttributes("reportForm")
public class WeeklyReportDemoController {

	@Autowired
	private ReportService reportService;

	@ModelAttribute("reportForm")
	public ReportCreateForm setForm() {
		return new ReportCreateForm();
	}

	// メモ出力
	@RequestMapping(value = "/report-end", params = "demo")
	public String confToDemo(@ModelAttribute("reportForm") ReportCreateForm reportForm, SessionStatus sessionStatus) {
		reportService.memoOutput(reportForm);
		sessionStatus.setComplete();
		return "redirect:/report-end?memoFinish";
	}

	// メモ出力完了
	@RequestMapping(value = "/report-end", params = "memoFinish")
	public String DemoEnd() {
		return "web/reportMemoEnd";
	}
	
	// 再度入力画面へ
	@RequestMapping(value = "/report-regist", params = "reRegist")
	public String endToRegist() {
		return "web/reportCreate";
	}

}
