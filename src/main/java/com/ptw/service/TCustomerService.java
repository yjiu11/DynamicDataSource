package com.ptw.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.ptw.pojo.TCustomer;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yjiu123
 * @since 2019-01-12
 */
public interface TCustomerService extends IService<TCustomer> {
	//分页+排序+搜索
	List<TCustomer> selectByPage(Page page,String field,String order);
	//分页+排序+搜索
	List<TCustomer> selectByPage(Page page,String field,String order,String teamNo,String touristName	);
}
