package com.dhr.action;

import java.io.IOException;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.dhr.domain.BaseDict;
import com.dhr.service.BaseDictService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

/**
 * @author Mr DU 数据字典
 */
public class BaseDictAction extends ActionSupport implements ModelDriven<BaseDict> {

	private BaseDict baseDict = new BaseDict();

	/**
	 * @return
	 */
	@Override
	public BaseDict getModel() {
		return baseDict;
	}

	// 注入service
	private BaseDictService baseDictService;

	public void setBaseDictService(BaseDictService baseDictService) {
		this.baseDictService = baseDictService;
	}

	/**
	 * 根据类型查询数据字典的数据
	 * 
	 * @return
	 * @throws IOException
	 */
	public String findByTypeCode() throws IOException {

		// 获取要查询的类型
		List<BaseDict> baseDicts = baseDictService.findByType(baseDict.getDict_type_code());
		// list转json
		JsonConfig config = new JsonConfig();
		config.setExcludes(new String[] { "dict_item_code", "dict_sort", "dict_enable", "dict_memo" });
		JSONArray array = JSONArray.fromObject(baseDicts, config);
		System.out.println(array.toString());
		// 设置响应编码
		ServletActionContext.getResponse().setContentType("text/html;charSet=utf-8");
		ServletActionContext.getResponse().getWriter().println(array.toString());
		return NONE;
	}
}
