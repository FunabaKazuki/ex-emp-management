package jp.co.sample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.sample.domain.Employee;
import jp.co.sample.form.UpdateEmployeeForm;
import jp.co.sample.service.EmployeeService;

/**
 * 従業員情報の表示や更新処理を行うクラス
 * @author funaba
 *
 */
@Controller
@RequestMapping ("/employee")
public class EmployeeController {
	@Autowired
	private EmployeeService employeeService;
	
	/**
	 * 従業員情報更新用フォームをインスタンス化するメソッド
	 * @return UpdateEmployeeForm
	 */
	@ModelAttribute
	public UpdateEmployeeForm setUpUpdateEmployeeForm() {
		return new UpdateEmployeeForm();
	}
	
	
	/**
	 * 従業員一覧を検索し、「employee/list.html」にフォワードするメソッド
	 * @param model
	 * @return employee/list呼び出し
	 */
	@RequestMapping("/showList")
	public String showList(Model model) {
		model.addAttribute("employeeList", employeeService.showList());
		
		return "employee/list";
	}
	
	@RequestMapping ("/showDetail")
	public String showDetail(String id,Model model) {
		Employee employee = employeeService.showDetail(Integer.parseInt(id));
		model.addAttribute("employee", employee);
		return "employee/detail";
		
	}
	
	/**
	 * 従業員の扶養人数の更新処理を行うメソッド
	 * @param updateEmployeeForm
	 * @return /employee/showList呼び出し
	 */
	@RequestMapping ("/update")
	public String update(UpdateEmployeeForm updateEmployeeForm) {
		Employee employee = employeeService.showDetail(Integer.parseInt(updateEmployeeForm.getId()));
		employee.setDependentsCount(Integer.parseInt(updateEmployeeForm.getDependentsCount()));
		employeeService.update(employee);
		
		return "redirect:/employee/showList";
	}

}
