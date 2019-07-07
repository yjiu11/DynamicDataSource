package com.ptw.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.ptw.utils.IViewTableCol;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author yjiu123
 * @since 2019-01-12
 */
@Data
@Accessors(chain = true)
@TableName("t_customer")
public class TCustomer implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * UUID编号
     */
    @TableId(value = "tourist_id", type = IdType.AUTO)
    private Integer touristId;
    /**
     * 团号
     */
    @TableField("team_no")
    private String teamNo;
    /**
     * 游客姓名
     */
    @TableField("tourist_name")
    private String touristName;
    /**
     * 游客手机号
     */
    @TableField("phone_num")
    private String phoneNum;
    /**
     * key：题，value：值
     */
    @TableField("response")
    private String response;
    /**
     * 答题结束
     */
    @TableField("end_time")
    private Date endTime;
    
   @TableField(exist=false)
   private TTeam team;
   public List<IViewTableCol> TableColumns() {
	   List<IViewTableCol> result = new ArrayList<>();
	   result.add(new IViewTableCol().setWidth(60).setAlign("center").setType("selection"));
	   result.add(new IViewTableCol().setKey("teamNo").setTitle("团号").setSortable("custom"));
	   result.add(new IViewTableCol().setKey("touristId").setTitle("游客编号").setSortable("custom"));
	   result.add(new IViewTableCol().setKey("touristName").setTitle("游客姓名").setSortable("custom"));
	   result.add(new IViewTableCol().setKey("phoneNum").setTitle("手机号").setSortable("custom"));
	   return result;
   }
   public static void main(String[] args) {
	   TCustomer customer = new TCustomer();
	   List<IViewTableCol> tableColumns = customer.TableColumns();
	   System.out.println(JSON.toJSONString(tableColumns));
   }
}
