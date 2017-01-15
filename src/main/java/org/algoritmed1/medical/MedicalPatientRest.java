package org.algoritmed1.medical;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.algoritmed1.tmp.WebClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * REST доступ для роботи медиків з пацієнтами.
 * @author roman
 *
 */
@Controller
public class MedicalPatientRest extends MedicalPatientDb{
	private static final Logger logger = LoggerFactory.getLogger(MedicalPatientRest.class);
	private @Autowired WebClient webClient;

	/**
	 * SQL insert для запису нового пацієнта
	 */
	private @Value("${sql.insertPatient}") String sqlInsertPatient;

	/**
	 * Запис нового пацієнта в БД
	 * @param newPatient
	 * @param userPrincipal
	 * @return
	 */
	@PostMapping("/v/saveNewPatient")
	public  @ResponseBody Map<String, Object> saveNewPatient(
			@RequestBody Map<String, Object> newPatient
			, Principal userPrincipal) {
		logger.info(" ------------------------- \n"
				+ "/saveNewPatient"
				+ "\n "+ newPatient);
		Map<String, Object> map = new HashMap<String, Object>();
		generateNewUuid(map);
		map.putAll(newPatient);
		int update = pgMvpMedicalAlgoritmed1ParamJdbcTemplate.update(sqlInsertPatient, map);
		Map<String, Object> patientById = pgMvpMedicalAlgoritmed1ParamJdbcTemplate.queryForMap(sqlSelectPatientById, map);
		map.put("patientById", patientById);
		webClient.hello();
		return map;
	}
	
	/**
	 * SQL select для зчитування всіх пацієнтів медіка
	 */
	private @Value("${sql.selectPatients}") String sqlSelectPatients;
	/**
	 * SQL select для зчитування пацієнта через ID
	 */
	private @Value("${sql.selectPatientById}") String sqlSelectPatientById;

	/**
	 * Зчитування всіх пацієнтів медіка
	 * @return Map об'єкт що містить всіх пацієнтів медіка
	 */
	@GetMapping(value = "/r/medical/patients")
	public @ResponseBody Map<String, Object>  patients() {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> medicPatients = pgMvpMedicalAlgoritmed1JdbcTemplate.queryForList(sqlSelectPatients);
		map.put("medicPatients", medicPatients);
		return map;
	}

	/**
	 * Зчитування даних паціента з БД за його локальним ID
	 * @param id локальний ID паціента в БД
	 * @return дані паціента
	 */
	@GetMapping(value = "/r/patient/{id}")
	public @ResponseBody Map<String, Object>  patientById(@PathVariable Integer id) {
		Map<String, Object> map = new HashMap<String, Object>();
		UUID uuid = UUID.randomUUID();
		map.put("uuid", uuid);
		map.put("id", id);
		Integer nextDbId = nextDbId();
		map.put("nextDbId", nextDbId);
		return map;
	}

}
