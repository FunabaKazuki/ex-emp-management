package jp.co.sample.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
//import javax.validation.constraints.Pattern;

/**
 * 管理者情報登録時に使⽤するフォーム
 * @author funaba
 *
 */
public class InsertAdministratorForm {
	
	/**
	 * 名前
	 */
	@NotBlank(message = "名前は必須です")
	private String name;
	/**
	 * メールアドレス
	 */
	@NotBlank(message = "メールアドレスは必須です")
	@Email(message = "Eメールの形式が不正です")
	private String mailAddress;
	/**
	 * パスワード
	 */
	@NotBlank(message = "パスワードは必須です")
	//@Pattern( regexp = "/^(?=.*?[a-z])(?=.*?\d)[a-z\\d]{8,100}$/i" , message = "" )
	private String password;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMailAddress() {
		return mailAddress;
	}
	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "InsertAdministratorForm [name=" + name + ", mailAddress=" + mailAddress + ", password=" + password
				+ "]";
	}

}
