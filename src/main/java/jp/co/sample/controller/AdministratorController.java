package jp.co.sample.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.sample.domain.Administrator;
import jp.co.sample.form.InsertAdministratorForm;
import jp.co.sample.form.LoginForm;
import jp.co.sample.service.AdministratorService;

/**
 * 管理者登録画面を表示やログイン処理を行うクラス
 * @author funaba
 *
 */
@Controller
@RequestMapping ("/")
public class AdministratorController {
	
	@Autowired
	private AdministratorService AdministratorService;
	
	@Autowired
	private HttpSession session;
	
	/**
	 * InsertAdministratorForm　をインスタンス化するメソッド
	 * @return InsertAdministratorForm
	 */
	@ModelAttribute
	public InsertAdministratorForm setUpInsertAdministratorForm() {
		return new InsertAdministratorForm();
	}
	/**
	 * LoginForm をインスタンス化するメソッド
	 * @return　LoginForm
	 */
	@ModelAttribute
	public LoginForm setUpLoginForm() {
		return new LoginForm();
	}
	
	/**
	 * 「administrator/login.html」にフォワードするクラス
	 * @return administrator/login.html
	 */
	@RequestMapping ("/")
	public String toLogin() {
		return "administrator/login.html";
	}
	
	/**
	 * 入力情報からログインを行うメソッド
	 * @param loginForm
	 * @param model
	 * @return forward:/employee/showList
	 */
	@RequestMapping ("/login")
	public String login(LoginForm loginForm, Model model) {
		Administrator administrator = AdministratorService.login(loginForm.getMailAddress(), loginForm.getPassword());
		if(administrator==null) {
			model.addAttribute("msg", "メールアドレスまたはパスワードが不正です。");
			return "administrator/login.html" ;
		}
		session.setAttribute("administratorName", administrator.getName());
		return "forward:/employee/showList";
	
	}
	
	
	
	
	/**
	 * 「administrator/insert.html」にフォワードするメソッド
	 * @return administrator/insert.html
	 */
	@RequestMapping ("/toInsert")
	public String toInsert() {
		return "administrator/insert";
	}
	/**
	 * 入力された管理者情報を登録するクラス
	 * @param insertAdministratorForm
	 * @return　「/」　(ログイン画面)にリダイレクトする
	 */
	@RequestMapping ("/insert")
	public String insert(InsertAdministratorForm insertAdministratorForm ) {
		Administrator administrator = new Administrator();
		BeanUtils.copyProperties(insertAdministratorForm, administrator);
		AdministratorService.insert(administrator);
		return "/";
	}
	
}
