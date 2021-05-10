package jp.co.sample.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.sample.domain.Administrator;
import jp.co.sample.repository.AdministratorRepository;

/**
 * 管理者情報の登録とログイン処理を行うメソッド
 * @author funaba
 *
 */
@Service
@Transactional
public class AdministratorService {
	
	@Autowired
	private AdministratorRepository administratorRepository;
	
	/**
	 * 管理者情報を管理者テーブルに登録する
	 * @param administrator
	 */
	public void insert(Administrator administrator) {
		administratorRepository.insert(administrator);
	}
	
	/**
	 * ログイン処理のための管理者情報をメールアドレスとパスワードから検索するメソッド
	 * @param mailAddress
	 * @param password
	 * @return リポジトリから受け取った情報をそのまま返す
	 */
	public Administrator login(String mailAddress , String password) {
		return administratorRepository.findByMailAddressAndPassword(mailAddress, password);
	}
	
	
}
