package jp.co.sample.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * 従業員情報更新時に使用するフォーム
 * @author funaba
 *
 */
public class UpdateEmployeeForm {
	/**
	 * 従業員ID
	 */
	private String id;
	/**
	 * 扶養人数
	 */
	@NotBlank(message = "必須項目です")
	@Pattern(message = "数字を入力しててください",regexp = "^[0-9]+$") 
	private String dependentsCount;
	/**
	 * 特性
	 */
	@NotBlank(message = "必須項目です")
	private String characteristics;
	public String getCharacteristics() {
		return characteristics;
	}
	public void setCharacteristics(String characteristics) {
		this.characteristics = characteristics;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDependentsCount() {
		return dependentsCount;
	}
	public void setDependentsCount(String dependentsCount) {
		this.dependentsCount = dependentsCount;
	}
	@Override
	public String toString() {
		return "UpdateEmployeeForm [id=" + id + ", dependentsCount=" + dependentsCount + ", characteristics="
				+ characteristics + "]";
	}
	
	

}
