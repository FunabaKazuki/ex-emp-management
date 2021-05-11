package jp.co.sample.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
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
	protected HttpSession session;
	
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
	 * @return　LoginFormを返す
	 */
	@ModelAttribute
	public LoginForm setUpLoginForm() {
		return new LoginForm();
	}
	
	/**
	 * 「administrator/login.html」にフォワードするクラス
	 * @return administrator/login.html呼び出し
	 */
	@RequestMapping ("/")
	public String toLogin() {
		return "administrator/login.html";
	}
	
	/**
	 * 入力情報からログインを行うメソッド
	 * @param loginForm
	 * @param model
	 * @return forward:/employee/showList呼び出し
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
	 * @return administrator/insert.html呼び出し
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
	public String insert(@Validated InsertAdministratorForm insertAdministratorForm ,BindingResult result) {
		if(result.hasErrors()) {
			return toInsert();
		}
		Administrator administrator = new Administrator();
		BeanUtils.copyProperties(insertAdministratorForm, administrator);
		AdministratorService.insert(administrator);
		return "redirect:/";
	}
	
	/**
	 * ログアウト処理するクラス
	 * @return ログイン画面呼び出し
	 */
	@RequestMapping("/logout")
	public String logout() {
		session.invalidate();
		return "redirect:/";
	}
	
}
