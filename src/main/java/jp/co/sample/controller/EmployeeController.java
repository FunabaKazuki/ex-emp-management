package jp.co.sample.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
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
	@Autowired
	protected HttpSession session;
	
	
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
	public String showList(Model model,Integer pageNum) {
		if(session.getAttribute("administratorName")==null) {
			return "forward:/";
		}
		
		
		List<Employee> emplist = employeeService.showList();
		int pageSize = emplist.size()/10+1;
		if(emplist.size()%10==0) {
			pageSize--;
		}
		
		
	       int size = 10;   
	       Integer page = 1;
	        for (int i = 0; i < emplist.size(); i += size) {
	        	
	            List<Employee> list = new ArrayList<>();
	            list=emplist.subList(i, Math.min(i + size, emplist.size()));
	            model.addAttribute(page.toString(), list);
	        
	            page++;
	        }

		if(pageNum==null||pageNum==1) {
			model.addAttribute("employeeList",model.getAttribute("1"));
			model.addAttribute("fromPage", 1 );
			model.addAttribute("toPage", 2);
			model.addAttribute("page", 1);
			
		}else {
			
			model.addAttribute("employeeList", model.getAttribute(pageNum.toString()));
			int fromPage=pageNum-1;
			int toPage=pageNum+1;
			model.addAttribute("fromPage",fromPage);
			model.addAttribute("toPage", toPage);
			model.addAttribute("page", pageNum);
			if(pageNum==pageSize) {
				model.addAttribute("toPage", pageNum);
			}
		
		}
		
		
		
		//model.addAttribute("employeeList", employeeService.showList());
		
		return "employee/list";
	}
	
	@RequestMapping ("/showDetail")
	public String showDetail(String id,Model model) {
		if(session.getAttribute("administratorName")==null) {
			return "forward:/";
		}

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
	public String update(@Validated UpdateEmployeeForm updateEmployeeForm,BindingResult result,Model model) {
		if(result.hasErrors()) {
			return showDetail(updateEmployeeForm.getId(), model);
		}
		
		Employee employee = employeeService.showDetail(Integer.parseInt(updateEmployeeForm.getId()));
		employee.setDependentsCount(Integer.parseInt(updateEmployeeForm.getDependentsCount()));
		employee.setCharacteristics(updateEmployeeForm.getCharacteristics());
		employeeService.update(employee);
		
		return "redirect:/employee/showList";
	}

}
