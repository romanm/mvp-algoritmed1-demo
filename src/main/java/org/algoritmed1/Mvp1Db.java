package org.algoritmed1;

import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

/**
 * Базовий клас MVP по роботі з БД
 * @author roman
 *
 */
public class Mvp1Db {
	/**
	 * SQL insert для запису UUID в БД
	 */
	private @Value("${sql.insertUUI}") String sqlInsertUUID;
	/**
	 * SQL select для зчитування UUID з БД
	 */
	private @Value("${sql.selectUUI_byId}") String sqlSelectUUID_byId;
	/**
	 * Створює новий UUID в БД
	 * @param map об'єкт для внесення даних
	 */
	protected void generateNewUuid(Map<String, Object> map) {
		addUuid(map);
		Integer nextDbId = nextDbId();
		map.put("nextDbId", nextDbId);
		map.put("dbId", nextDbId);
		int update = pgAmMVP1ParamJdbcTemplate.update(sqlInsertUUID, map);
		Map<String, Object> dbUuid = pgAmMVP1ParamJdbcTemplate.queryForMap(sqlSelectUUID_byId, map);
		map.put("dbUuid", dbUuid);
	}
	/**
	 * Генерує випадковий universally unique identifier (UUID), 
	 * додає його до map
	 * @param map об'єкт для довавання новоствореного UUID
	 * @return новостворений UUID
	 */
	protected UUID addUuid(Map<String, Object> map) {
		UUID uuid = UUID.randomUUID();
		map.put("uuid", uuid);
		return uuid;
	}
	/**
	 * JDBC доступ до БД через параметрезований SQL
	 */
	@Autowired
	protected NamedParameterJdbcTemplate pgAmMVP1ParamJdbcTemplate;
	/**
	 * JDBC доступ до БД через простий SQL
	 */
	@Autowired
	protected JdbcTemplate pgAmMVP1JdbcTemplate;

	/**
	 * SQL select - повертає наступний ID единий для всієй БД.
	 */
	private @Value("${sql.nextdbid}") String sqlNextDbId;
	/**
	 * Генератор наступного ID единого для всієї БД.
	 * @return Наступний ID единий для всієй БД.
	 */
	protected Integer nextDbId() {
		Integer nextDbId = pgAmMVP1JdbcTemplate.queryForObject(sqlNextDbId, Integer.class);
		return nextDbId;
	}
}
