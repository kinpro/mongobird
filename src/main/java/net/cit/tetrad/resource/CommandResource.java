/*******************************************************************************
 * "mongobird" is released under a dual license model designed to developers 
 * and commercial deployment.
 * 
 * For using OEMs(Original Equipment Manufacturers), ISVs(Independent Software
 * Vendor), ISPs(Internet Service Provider), VARs(Value Added Resellers) 
 * and another distributors, or for using include changed issue
 * (modify / application), it must have to follow the Commercial License policy.
 * To check the Commercial License Policy, you need to contact Cardinal Info.Tech.Co., Ltd.
 * (http://www.citsoft.net)
 *  *
 * If not using Commercial License (Academic research or personal research),
 * it might to be under AGPL policy. To check the contents of the AGPL terms,
 * please see "http://www.gnu.org/licenses/"
 ******************************************************************************/
package net.cit.tetrad.resource;

import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.cit.tetrad.model.CommonDto;
import net.cit.tetrad.model.PersonJson;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CommandResource extends DefaultResource{

	private Logger log = Logger.getLogger(this.getClass());
	
	@RequestMapping("/cmdExView.do")
	public ModelAndView cmdExView(CommonDto dto) throws Exception{
		log.debug("start - cmdExView()");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("cmdExView");
		log.debug("end - cmdExView()");
		return mav;
	}
	
	@RequestMapping("/commamdView.do")
	public ModelAndView commamdView(CommonDto dto) throws Exception{
		log.debug("start - commamdView()");
		ModelAndView mav = new ModelAndView();
		mav.addObject("comm",dto);
		mav.setViewName("commandView");
		
		log.debug("end - commamdView()");
		return mav;
	}
	
	@RequestMapping("/runCommamd.do")
	public void loginView(HttpServletResponse response,CommonDto dto)throws Exception{		
		Map<String, Object> serverStatusFromMongo = new HashMap<String, Object>();
		serverStatusFromMongo = comandService.insertCommand(dto.getDeviceCode(),dto.getMemo());
		
		List<Object> arrTotInfo = new ArrayList<Object>();
		arrTotInfo.add(serverStatusFromMongo);
		
		PersonJson result = new PersonJson();
		result.setAaData(arrTotInfo);
		
		JSONObject jsonObject = JSONObject.fromObject(result);
		
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		
		Writer writer = response.getWriter();
		writer.write(jsonObject.toString());

		writer.flush();
	}
	

	@RequestMapping("/runServerStatus.do")
	public void runServerStatus(HttpServletResponse response,CommonDto dto)throws Exception{		
		String serverStatusFromMongo = comandService.allServerStatus(dto.getDeviceCode());
		
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		
		Writer writer = response.getWriter();
		writer.write(serverStatusFromMongo);

		writer.flush();
	}
	
	@RequestMapping("/daemonDetailServerStatus.do")
	public void daemonDetailServerStatus(HttpServletResponse response,CommonDto dto)throws Exception{		
		String serverStatusFromMongo = comandService.allServerStatus(dto.getDeviceCode());
		JSONObject jsonObj = JSONObject.fromObject(JSONSerializer.toJSON(serverStatusFromMongo));
		JSONObject recordStats = (JSONObject) jsonObj.get("recordStats");
		if(recordStats != null){
			Object ok = jsonObj.get("ok");
			jsonObj.remove("recordStats");
			jsonObj.remove("ok");
			
			Object accessNotInMemory = recordStats.get("accessesNotInMemory");
			Object pageFaultExceptionsThrown = recordStats.get("pageFaultExceptionsThrown");
			recordStats.remove("accessesNotInMemory");
			recordStats.remove("pageFaultExceptionsThrown");
			
			JSONObject recordStatsTotal = new JSONObject();
			recordStatsTotal.put("accessesNotInMemory", accessNotInMemory);
			recordStatsTotal.put("pageFaultExceptionsThrown", pageFaultExceptionsThrown);
			jsonObj.put("totalRecordStats", recordStatsTotal);
			jsonObj.put("recordStats", recordStats);
			jsonObj.put("ok", ok);
		}
		
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		
		Writer writer = response.getWriter();
		writer.write(jsonObj.toString());
		
		writer.flush();
	}
	
}
