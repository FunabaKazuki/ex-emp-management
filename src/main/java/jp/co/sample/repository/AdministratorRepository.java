package jp.co.sample.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jp.co.sample.domain.Administrator;

/**
 * 管理者テーブルアクセス用リポジトリ
 * @author funaba
 *
 */
@Repository
public class AdministratorRepository {
	
	private static final RowMapper<Administrator> ADMINISTRATOR_ROW_MAPPER = (rs,i) -> {
		Administrator administrator = new Administrator();
		administrator.setId(rs.getInt("id"));
		administrator.setName(rs.getString("name"));
		administrator.setMailAddress(rs.getString("mail_address"));
		administrator.setPassword(rs.getString("password"));
		return administrator;
	};
	 
	@Autowired
	private NamedParameterJdbcTemplate template;
	
	/**
	 * 管理者テーブルに管理者を挿入するメソッド
	 * @param administrator
	 */
	public void insert(Administrator administrator) {
		String sql = "INSERT INTO administrators (name,mail_address,password) "
				+ " VALUES ( :name , :mailAddress , :password )";
		SqlParameterSource param = new MapSqlParameterSource().addValue("name", administrator.getName()).addValue("mailAddress", administrator.getMailAddress()).addValue("password", administrator.getPassword());
		template.update(sql, param);
	}
	
	/**
	 * メールアドレスとパスワードから管理者テーブルの情報を取得するメソッド
	 * 　該当する管理者存在しなかったらnullを返す
	 * @param mailAddress
	 * @param password
	 * @return　該当する管理者情報が存在すれば管理者情報を返す。該当する管理者情報がなければNULLを返す。
	 */
	public Administrator findByMailAddressAndPassword(String mailAddress, String password) {
		String sql = "SELECT * FROM administrators WHERE mail_address=:mailAddress AND password=:password ";
		SqlParameterSource param = new MapSqlParameterSource().addValue("mailAddress", mailAddress).addValue("password", password);
		List<Administrator> administratorList= template.query(sql, param, ADMINISTRATOR_ROW_MAPPER);
		if (administratorList.size() == 0) {
			return null;
			}
			return administratorList.get(0);
	}
	
	
}
