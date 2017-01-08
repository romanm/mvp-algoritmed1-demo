package org.algoritmed1.medic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * REST медіка
 * @author roman
 *
 */
@Controller
public class MedicRest {
	private static final Logger logger = LoggerFactory.getLogger(MedicRest.class);

	@GetMapping(value = "/v/medic")
	public String  medic(Model model) {
		model.addAttribute("quotes", "'");
		model.addAttribute("ng_template", "'/v/mvp1/medic.html'");
		model.addAttribute("ng_app", "medicApp");
		model.addAttribute("ng_controller", "MedicCtrl as medicCtrl");
		return "mvp1";
	}



}
