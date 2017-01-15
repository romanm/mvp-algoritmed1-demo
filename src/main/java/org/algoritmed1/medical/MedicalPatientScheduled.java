package org.algoritmed1.medical;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MedicalPatientScheduled {
	private static final Logger log = LoggerFactory.getLogger(MedicalPatientScheduled.class);

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

	@Scheduled(fixedRate = 222000)
	public void reportCurrentTime() {
		log.info("The time is now {}", dateFormat.format(new Date()));
	}

}
