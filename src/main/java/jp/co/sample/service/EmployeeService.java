package jp.co.sample.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.sample.domain.Employee;
import jp.co.sample.repository.EmployeeRepository;

/**
 * 従業員情報を検索する処理を行うクラス
 * @author funaba
 *
 */
@Service
@Transactional
public class EmployeeService {
	@Autowired
	private EmployeeRepository employeeRepository;
	
	/**
	 * EmployeeRepositoryを利用し、従業員情報を全件取得するメソッド
	 * @return　List<Employee>
	 */
	public List<Employee> showList() {
		return employeeRepository.findAll();
	}
	
	/**
	 * 主キーにより1人分の従業員情報を取得するメソッド
	 * @param id
	 * @return Employee
	 */
	public Employee showDetail(Integer id) {
		return employeeRepository.load(id);
	}
}