package jp.co.sample.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jp.co.sample.domain.Employee;

/**
 * 従業員テーブルアクセス用リポジトリ
 * @author funaba
 *
 */
@Repository
public class EmployeeRepository {
	
	private static final RowMapper<Employee> EMPLOYEE_ROW_MAPPER = (rs,i) -> {
		Employee employee = new Employee();
				
		employee.setId(rs.getInt("id"));
		employee.setName(rs.getString("name"));
		employee.setImage(rs.getString("image"));
		employee.setGender(rs.getString("gender"));
		employee.setHireDate(rs.getDate("hire_date"));
		employee.setMailAddress(rs.getString("mail_address"));
		employee.setZipCode(rs.getString("zip_code"));
		employee.setAddress(rs.getString("address"));
		employee.setTelephone(rs.getString("telephone"));
		employee.setSalary(rs.getInt("salary"));
		employee.setCharacteristics(rs.getString("characteristics"));
		employee.setDependentsCount(rs.getInt("dependents_count"));
		
		return employee;
	};

	
	@Autowired
	private NamedParameterJdbcTemplate template;
	
	/**
	 * 従業員⼀覧情報を⼊社⽇順(降順)で取得
     * する(従業員が存在しない場合はサイズ 0
     * 件の従業員⼀覧を返す)
	 * @return List<Employee>
	 */
	public List<Employee> findAll() {
		String sql = "SELECT * FROM employees "
				+ " ORDER BY hire_date DESC ";
		List<Employee> employeeList = template.query(sql, EMPLOYEE_ROW_MAPPER);
		return employeeList;
		
	}
	
	/**
	 * 主キーから従業員情報を取得する(従業員
     * が存在しない場合は Spring が自動的に例
     * 外を発生します)。
	 * @param id
	 * @return Employee
	 */
	public Employee load(Integer id) {
		String sql =  "SELECT * FROM employees "
				+ " WHERE id=:id ";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		return template.queryForObject(sql, param,EMPLOYEE_ROW_MAPPER);
		
	}
	/**
	 * 従業員情報を変更する
	 * @param employee
	 */
	public void update(Employee employee) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(employee);
		String sql = "UPDATE employees SET "
				+ " name=:name , image=:image , gender=:gender , hire_date=:hireDate , mail_address=:mailAddress , zip_code=:zipCode , address=:address , telephone=:telephone , salary=:salary , characteristics=:characteristics , dependents_count=:dependentsCount "
				+ " WHERE id=:id ";
		template.update(sql, param);
		
	}
	
}
