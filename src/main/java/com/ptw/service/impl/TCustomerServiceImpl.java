package com.ptw.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ptw.mapper.TCustomerMapper;
import com.ptw.pojo.TCustomer;
import com.ptw.service.TCustomerService;
import com.ptw.service.TTeamService;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yjiu123
 * @since 2019-01-12
 */
@Service
public class TCustomerServiceImpl extends ServiceImpl<TCustomerMapper, TCustomer> implements TCustomerService {
	@Autowired
	private  TCustomerMapper ttMapper;
	@Autowired
	private  TTeamService teamService;
	@Override
	public List<TCustomer> selectByPage(Page page, String field, String order) {
		boolean flag = StringUtils.equals(order, "desc")?false:true;
		return ttMapper.selectPage(page, new EntityWrapper<TCustomer>().orderBy(field, flag));
	}

	@Override
	public List<TCustomer> selectByPage(Page page, String field, String order, String teamNo,String touristName) {
		boolean flag = StringUtils.equals(order, "desc")?false:true;
		EntityWrapper<TCustomer> wrapper = new EntityWrapper<TCustomer>();
		if(!StringUtils.isEmpty(teamNo)) {
			wrapper.like("team_no", teamNo);
		}
		if(!StringUtils.isEmpty(touristName)) {
			wrapper.like("tourist_name", touristName);
		}
		wrapper.orderBy(field, flag);
		return ttMapper.selectPage(page, wrapper);
	}
}
