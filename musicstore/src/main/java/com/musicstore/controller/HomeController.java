package com.musicstore.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.musicstore.constants.MusicStoreConstants;
import com.musicstore.utils.SessionUtil;

@Controller
public class HomeController {
	
	private static final Logger logger = Logger.getLogger(HomeController.class);
	
	/** Navigate to Music Store Home page 
	 */
	@RequestMapping(value=MusicStoreConstants.HOME, method=RequestMethod.GET)
	public static ModelAndView musicStoreOnload(HttpServletRequest request) {
		logger.info("Inside musicStoreOnload() ::::: RedirectController - Request="+MusicStoreConstants.HOME);
		ModelAndView mv = new ModelAndView();
		String view = null;
		try {
			boolean redirectToHomePage = true;
			HttpSession session = request.getSession();
			if(session != null) {
				JSONObject userDetails = (JSONObject) SessionUtil.getAttribute(request, MusicStoreConstants.MS_USER_DETAILS);
				
				if(userDetails != null && userDetails.length() > 0) {
					logger.debug("Valid user details found in Session : "+userDetails);
					String lastVisitedUrl = userDetails.optString("lastVisitedUrl");
					
					if(lastVisitedUrl != null && !lastVisitedUrl.isEmpty() && lastVisitedUrl.length() > 0) {
						// should be changed to last visited URL
						
						redirectToHomePage = false;
					}
				}
			}
			
			if(redirectToHomePage) {
				logger.info("Music Store Home page onload");
				// view = MusicStoreConstants.PAGE +"/" +MusicStoreConstants.MS_HOME_PAGE;
				view = MusicStoreConstants.MS_HOME_PAGE;
			}
			
			mv.setViewName(view);
			logger.info("Successfully redirected to View="+view);
		}
		catch(Exception e) {
			logger.error("\n\nException occurred in musicStoreOnload() :::: RedirectController : "+e); 
		}
		return mv;
	}
	
}
