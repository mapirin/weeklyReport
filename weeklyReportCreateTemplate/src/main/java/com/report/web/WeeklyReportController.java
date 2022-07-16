package com.report.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.report.service.ReportService;

@Controller
@SessionAttributes("reportForm")
public class WeeklyReportController {

	@Autowired
	private ReportService reportService;

	@ModelAttribute("reportForm")
	public ReportCreateForm setForm() {
		return new ReportCreateForm();
	}

	// 必要事項入力画面
	@RequestMapping("/report-regist")
	public String reportRegist(@ModelAttribute("reportForm") ReportCreateForm reportForm) {
		return "web/reportCreate";
	}

	// 入力項目確認画面
	// エラーチェック挿入
	@RequestMapping(value = "/report-conf", params = "regist")
	public String registToConf(@ModelAttribute("reportForm") ReportCreateForm reportForm, BindingResult result) {
		ReportInputError reportInputError = new ReportInputError();
		if (reportInputError.inputErrorCheck(reportForm).isEmpty()) {
			return "web/reportConf";
		}
		List<String> errorStrList = reportInputError.inputErrorCheck(reportForm);
		for(String str:errorStrList) {
			result.reject(str);
		}
		return "web/reportCreate";
	}

	// 必要事項入力画面に戻る
	@RequestMapping(value = "/report-end", params = "back")
	public String confToRegist(@ModelAttribute("reportForm") ReportCreateForm reportForm) {
		return "web/reportCreate";
	}

	// メール送信
	@RequestMapping(value = "/report-end", params = "regist")
	public String confToEnd(@ModelAttribute("reportForm") ReportCreateForm reportForm, SessionStatus sessionStatus) {
		reportService.send(reportForm);
		sessionStatus.setComplete();
		return "redirest:/report-end?finish";
	}

	// メール送信完了
	@RequestMapping(value = "/report-end", params = "finish")
	public String end() {
		return "web/reportEnd";
	}

}
