package org.algoritmed1.insurance;

import org.algoritmed1.Mvp1Db;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

/**
 * JDBC доступ до БД медичної страховки
 * @author roman
 *
 */
public class InsuranceDb  extends Mvp1Db{
	/**
	 * JDBC доступ до БД медичної страховки через простий SQL
	 */
	@Autowired
	protected JdbcTemplate pgMvpInsuranceAlgoritmed1JdbcTemplate;
	/**
	 * JDBC доступ до БД медичної страховки через параметрезований SQL
	 */
	@Autowired
	protected NamedParameterJdbcTemplate pgMvpInsuranceAlgoritmed1ParamJdbcTemplate;
}
