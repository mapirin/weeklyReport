package com.report.service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import com.report.web.ReportCreateForm;

@Service
public class ReportService {

	@Autowired
	MailSender mailSender;

	/**
	 * 入力フォームで入力したデータをメール本文テンプレートにセットする
	 * 
	 * @param reportForm
	 * @return テキストデータ
	 */
	protected static Context outputParams(ReportCreateForm reportForm) {
		// メールテンプレ用パラメータ
		Map<String, Object> variables = new HashMap<>();
		// 日時指定
		LocalDate lDate = LocalDate.now();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("E");
		if (dtf.format(lDate).equals("土")) {
			lDate = lDate.minusDays(1);
		} else if (dtf.format(lDate).equals("日")) {
			lDate = lDate.minusDays(2);
		}
		LocalDate fDate = lDate.minusDays(4);
		// 表示用にフォーマット
		DateTimeFormatter MonthDisplay = DateTimeFormatter.ofPattern("MM");
		DateTimeFormatter DayDisplay = DateTimeFormatter.ofPattern("dd");
		Integer firstMonth = Integer.valueOf(MonthDisplay.format(fDate));
		Integer firstDay = Integer.valueOf(DayDisplay.format(fDate));
		Integer lastMonth = Integer.valueOf(MonthDisplay.format(lDate));
		Integer lastDay = Integer.valueOf(DayDisplay.format(lDate));

		// セット
		variables.put("firstMonth", firstMonth);
		variables.put("firstDay", firstDay);
		variables.put("lastMonth", lastMonth);
		variables.put("lastDay", lastDay);
		if (!(reportForm.getComment().equals(""))) {
			variables.put("comment", "\r\n" + reportForm.getComment() + "\r\n");
		}
		variables.put("thisWeek1", reportForm.getThisWeek1());
		if (!(reportForm.getThisWeek2().equals(""))) {
			variables.put("thisWeek2", "・" + reportForm.getThisWeek2());
		}
		if (!(reportForm.getThisWeek3().equals(""))) {
			variables.put("thisWeek3", "・" + reportForm.getThisWeek3());
		}
		variables.put("nextWeek1", reportForm.getNextWeek1());
		if (!(reportForm.getNextWeek2().equals(""))) {
			variables.put("nextWeek2", "・" + reportForm.getNextWeek2());
		}
		if (!(reportForm.getNextWeek3().equals(""))) {
			variables.put("nextWeek3", "・" + reportForm.getNextWeek3());
		}
		variables.put("tech1", reportForm.getTech1());
		variables.put("tech2", reportForm.getTech2());
		variables.put("tech3", reportForm.getTech3());
		variables.put("tech4", reportForm.getTech4());
		variables.put("tech5", reportForm.getTech5());
		variables.put("task", reportForm.getTask());
		variables.put("progress", reportForm.getProgress());
		variables.put("thoughts", reportForm.getThoughts());
		variables.put("etc", reportForm.getEtc());

		// 実行してテキストを取得
		Context context = new Context();
		context.setVariables(variables);

		return context;
	}

	/**
	 * メール設定をして送信する
	 * 入力データのテキストファイルからメール本文情報取得してセット
	 * @param reportForm
	 */
	public void send(ReportCreateForm reportForm) {
		// メール設定
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo("*宛先メールアドレスを入力");
		message.setFrom("*自分のメールアドレスを入力");
		message.setSubject("*件名");

		// テンプレートエンジン作成
		ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
		// テンプレートエンジンの種類、エンコード
		templateResolver.setTemplateMode(TemplateMode.TEXT);
		templateResolver.setCharacterEncoding("UTF-8");
		// 上記で設定したテンプレエンジン情報を反映
		SpringTemplateEngine engine = new SpringTemplateEngine();
		engine.setTemplateResolver(templateResolver);

		// テンプレのファイル名とパラメータを取得（一項：ファイル、二項：パラメータ）
		String text = engine.process("/templates/sample.txt", ReportService.outputParams(reportForm));
		message.setText(text);

		this.mailSender.send(message);
	}

	/**
	 * メール本文のお試し出力用
	 * @param reportForm
	 */
	public void memoOutput(ReportCreateForm reportForm) {
		// 入力された情報を上記テキストメール形式で受け取った後、
		// メモを起動して書き込む bufferdWriter FileOutputStream
		// テンプレートエンジン作成
		ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
		// テンプレートエンジンの種類、エンコード
		templateResolver.setTemplateMode(TemplateMode.TEXT);
		templateResolver.setCharacterEncoding("UTF-8");
		// 上記で設定したテンプレエンジン情報を反映
		SpringTemplateEngine engine = new SpringTemplateEngine();
		engine.setTemplateResolver(templateResolver);

		// テンプレのファイル名とパラメータを取得（一項：ファイル、二項：パラメータ）
		// resources/templates内のテキストファイルを使用
		String text = engine.process("/templates/sample.txt", ReportService.outputParams(reportForm));

		// 任意のフォルダ内にメール文を記述したテキストファイルを保存
		try (BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\temp\\WeeklyDemo\\demo.txt"));) {
			bw.write(text);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
