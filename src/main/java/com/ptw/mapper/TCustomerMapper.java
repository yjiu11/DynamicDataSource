package com.ptw.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.ptw.pojo.TCustomer;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author yjiu123
 * @since 2019-01-12
 */
@Mapper
public interface TCustomerMapper extends BaseMapper<TCustomer> {
	/*List<TCustomer> getResponses(@Param("teamNo") String teamNo);*/
}
