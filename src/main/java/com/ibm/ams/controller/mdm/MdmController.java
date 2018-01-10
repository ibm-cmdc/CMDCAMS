package com.ibm.ams.controller.mdm;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ibm.ams.controller.base.BaseController;
import com.ibm.ams.util.PageData;


@Controller
public class MdmController extends BaseController {
	
	/**访问资产卡片维护
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/zckp")
	public ModelAndView toLogin()throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		mv.setViewName("mdm/mdm_zckp");
		mv.addObject("pd",pd);
		return mv;
	}

}
