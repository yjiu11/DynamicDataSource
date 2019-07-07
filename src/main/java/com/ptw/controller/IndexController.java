package com.ptw.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ptw.pojo.TTeam;
import com.ptw.service.OrderInfoService;
import com.ptw.service.TTeamService;

@Controller
public class IndexController {
	@Autowired
	private OrderInfoService orderService;
	@Autowired
	private TTeamService teamService;
	@RequestMapping("/")
	@ResponseBody
	public String tt() {
		String result = orderService.getByOrderNo_TeamNo("DBJ19-01775","SITSBJ-1903-22EU-PCZ13");
		List<TTeam> all = teamService.All();
		System.out.println(all);
		return result;
		
	}
}
