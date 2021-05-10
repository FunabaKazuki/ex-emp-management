package jp.co.sample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.sample.form.InsertAdministratorForm;
import jp.co.sample.service.AdministratorService;

/**
 * 管理者登録画⾯を表⽰する処理を行うクラス
 * @author funaba
 *
 */
@Controller
@RequestMapping ("/")
public class AdministratorController {
	
	@Autowired
	private AdministratorService adoAdministratorService;
	
	/**
	 * フォームをインスタンス化するメソッド
	 * @return InsertAdministratorForm
	 */
	@ModelAttribute
	public InsertAdministratorForm setUpInsertAdministratorForm() {
		return new InsertAdministratorForm();
	}
	
	/**
	 * 「administrator/insert.html」にフォワードするメソッド
	 * @return administrator/insert.html
	 */
	@RequestMapping ("/toInsert")
	public String toInsert() {
		return "administrator/insert";
	}
	
}
