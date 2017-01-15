package org.algoritmed1.insurance;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * REST медична страховака
 * @author roman
 *
 */

@Controller
public class InsuranceRest  extends InsuranceDb{
	private static final Logger logger = LoggerFactory.getLogger(InsuranceRest.class);

	/**
	 * Пошук пацієнтів в БД загальної медичної страховки
	 * @return
	 */
	@GetMapping(value = "/r/insurance/seekPatient/{seekPatient}")
	public @ResponseBody Map<String, Object>  insuranceSeekPatient(@PathVariable String seekPatient) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("seekPatient", "%" + seekPatient +"%");
		logger.info("---------------\n"
				+ "/r/insurance/seekPatient/{seekPatient}" + map);
		List<Map<String, Object>> insurancePatients = pgMvpInsuranceAlgoritmed1ParamJdbcTemplate.queryForList(sqlSeekPatients,map);
		map.put("insurancePatients", insurancePatients);
		return map;
	}
	/**
	 * SQL select для пошуку пацієнтів страховки
	 */
	private @Value("${sql.insurance.seekPatient}") String sqlSeekPatients;

	/**
	 * SQL select для зчитування всіх пацієнтів страховки
	 */
	private @Value("${sql.insurance.selectPatients}") String sqlSelectPatients;

	/**
	 * Зчитування всіх пацієнтів медичної страховки
	 * @return Map об'єкт що містить всіх пацієнтів медіка
	 */
	@GetMapping(value = "/r/insurance/patients")
	public @ResponseBody Map<String, Object>  patients() {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> insurancePatients = pgMvpInsuranceAlgoritmed1JdbcTemplate.queryForList(sqlSelectPatients);
		map.put("insurancePatients", insurancePatients);
		return map;
	}

	/**
	 * REST доступ до сторінки мед.страховки
	 * @param model Стандартна модель від spring MVC
	 * @return Ім'я thymeleaf шаблона для сторінки мед.страховки
	 */
	@GetMapping(value = "/v/insurance")
	public String  medic(Model model) {
		model.addAttribute("quotes", "'");
		model.addAttribute("ng_template", "'/v/mvp1/insurance.html'");
		model.addAttribute("ng_app", "insuranceApp");
		model.addAttribute("ng_controller", "InsuranceCtrl as insuranceCtrl");
		return "mvp1";
	}

}
