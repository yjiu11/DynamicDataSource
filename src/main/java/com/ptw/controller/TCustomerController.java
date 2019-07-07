package com.ptw.controller;


import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.ptw.pojo.TCustomer;
import com.ptw.pojo.TTeam;
import com.ptw.service.TCustomerService;
import com.ptw.service.TTeamService;
import com.ptw.utils.PTWResult;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author yjiu123
 * @since 2019-01-12
 */
@Controller
public class TCustomerController {
	@Autowired
	private TCustomerService ttService;
	@Autowired
	private TTeamService teamService;
	@RequestMapping("/customer/list/data")
	@ResponseBody
	public PTWResult data_ProjectList(@RequestParam(value="page",required=false,defaultValue="1") Integer page,
			@RequestParam(value="limit",required=false,defaultValue="10") Integer limit,
			@RequestParam(value="key",required=false,defaultValue="touristId") String field,
			@RequestParam(value="order",required=false,defaultValue="asc") String order,
			String teamNo,String touristName) {
		List<TCustomer> lists = ttService.selectByPage(new Page<>(page,limit),field,order,teamNo,touristName);
		EntityWrapper<TCustomer> wrapper = new EntityWrapper<TCustomer>();
		if(StringUtils.isNotBlank(teamNo)) {
			wrapper.eq("team_no", teamNo);
		}
		if(StringUtils.isNotBlank(touristName)) {
			wrapper.eq("tourist_name", touristName);
		}
		JSONObject obj = new JSONObject();
		obj.put("infos", lists);
		obj.put("total", ttService.selectCount(wrapper));
//		obj.put("columns", new TCustomer().TableColumns());
		PTWResult result = PTWResult.ok(obj);
		return result;
	}
	@RequestMapping(value="/customer/save",method=RequestMethod.POST)
	@ResponseBody
	public PTWResult save(@RequestBody TCustomer project) {
		try {
			ttService.insert(project);
			return PTWResult.ok();
		}catch(Exception e) {
			return PTWResult.build(500, e.getMessage().toString());
		}
	}
	
	@RequestMapping("/customer/update")
	@ResponseBody
	public PTWResult update(TCustomer project) {
		try {
			ttService.update(project, new EntityWrapper<TCustomer>().eq("tourist_id", project.getTouristId()));
			return PTWResult.ok();
		}catch(Exception e) {
			return PTWResult.build(500, e.getMessage().toString());
		}
	}
	
	@RequestMapping("/customer/batchDel")
	@ResponseBody
	public PTWResult batchDel(String ids) {
		try {
			if(ids.contains(",")) {
				String[] idss = ids.split(",");
				ttService.deleteBatchIds(Arrays.asList(idss));
			}else {
				ttService.deleteById(Integer.parseInt(ids));
			}
			return PTWResult.ok();
		}catch(Exception e) {
			e.printStackTrace();
			return PTWResult.build(500, e.getMessage().toString());
		}
	}
	@RequestMapping("/team/select")
	@ResponseBody
	public PTWResult toPage_ProjectAdd(@RequestParam(value="touristId",required=false)String touristId) {
		List<TTeam> lists = teamService.selectList(null);
		JSONObject obj = new JSONObject();
		obj.put("team", lists);
		if(StringUtils.isNotBlank(touristId)) {
			TCustomer pro = ttService.selectOne(new EntityWrapper<TCustomer>().eq("tourist_id", touristId));
			obj.put("pro", pro);		 //如果是update，传过去
		}
		return PTWResult.ok(obj);
	}
}

