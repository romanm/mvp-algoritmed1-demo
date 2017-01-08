package org.algoritmed1;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Mvp1Rest extends Mvp1Db{
	private static final Logger logger = LoggerFactory.getLogger(Mvp1Rest.class);

	@GetMapping("/r/testDbUUID")
	public  @ResponseBody Map<String, Object> testDbUUI() {
		Map<String, Object> map = new HashMap<String, Object>();
		generateNewUuid(map);
		logger.info(" --------- \n"
				+ "/v/testUUI \n" + map);
		return map;
	}

	@GetMapping("/r/testUUID")
	public  @ResponseBody Map<String, Object> testUUI() {
		Map<String, Object> map = new HashMap<String, Object>();
		UUID uuid = addUuid(map);
		map.put("version", uuid.version());
		map.put("variant", uuid.variant());
		map.put("length", uuid.toString().length());
		logger.info(" --------- \n"
				+ "/v/testUUI \n" + map);
		return map;
	}

}
