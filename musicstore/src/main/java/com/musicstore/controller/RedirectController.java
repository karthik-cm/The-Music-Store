package com.musicstore.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.musicstore.constants.MusicStoreConstants;
import com.musicstore.dao.CommonDao;

@Controller
public class RedirectController {
	private static final Logger logger = Logger.getLogger(RedirectController.class);
	
	private static final String PORTLET_NAME = "portletName";
	
	/** To load given portlet
	 */
	@RequestMapping(value=MusicStoreConstants.LOAD_PORTLET, method=RequestMethod.POST)
	@ResponseBody
	public ModelAndView loadPortlet(HttpServletRequest request, ModelAndView mv) {
		logger.info("\n\n");
		logger.info("Inside loadPortlet() :::: RedirectController ");
		HttpSession session = request.getSession();
		String view = null;
		if(session != null) {
			String portletName = request.getParameter(PORTLET_NAME);
			logger.info("Load portlet with name : "+portletName);
			
			if(portletName != null && portletName.length() > 0 && MusicStoreConstants.PORTLET_LIST.contains(portletName)) {
				// view = MusicStoreConstants.PORTLET +"/" +portletName;
				view = portletName;
				logger.info("Redirecting to Portlet : "+view);
			}else {
				view = MusicStoreConstants.MS_REDIRECT_TO_HOME;
			}
			JSONObject userDetails = CommonDao.getMsUserDetails(request);
			mv.addObject(MusicStoreConstants.MS_USER_DETAILS, userDetails);
		}
		else {
			view = MusicStoreConstants.MS_REDIRECT_TO_HOME;
		}
		mv.setViewName(view);
		return mv;
	}
	
	
	/** Go to Dashboard 
	*/
	@RequestMapping(value=MusicStoreConstants.DASHBOARD, method=RequestMethod.GET)
	public String dashboardPageOnload(HttpServletRequest request, ModelMap map) {
		logger.info("\n\n");
		logger.info("Inside dashboardPageOnload() :::: RedirectController");
		String view = null;
		JSONObject userDetails = (JSONObject) CommonDao.getMsUserDetails(request);
		if(userDetails != null && userDetails.length() > 0) {
			logger.info("Active session found. Redirecting to Profile page.");
			map.addAttribute(MusicStoreConstants.MS_USER_DETAILS, userDetails);
			
			// view = MusicStoreConstants.PAGE +"/" +MusicStoreConstants.MS_DASHBOARD_PAGE;
			view = MusicStoreConstants.MS_DASHBOARD_PAGE;
		}
		else {
			logger.info("Session expired. Redirecting to Home page.");
			view = MusicStoreConstants.MS_REDIRECT_TO_HOME;
		}
		return view;
	}
}
