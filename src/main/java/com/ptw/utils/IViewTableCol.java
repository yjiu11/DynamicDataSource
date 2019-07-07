package com.ptw.utils;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * iview ui中Table的Columns设置
 * @author fh
 *
 */
@Data
@Accessors(chain=true)
public class IViewTableCol {
	private String type;
	private String title;
	private String key;
	private Integer width;
	private Integer minWidth;
	private Integer maxWidth;
	private String align;//默认是left,可选right、center
	private String sortable;//customer是远程排序
}
