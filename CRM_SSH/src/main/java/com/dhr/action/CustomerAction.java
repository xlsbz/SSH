package com.dhr.action;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.dhr.domain.Customer;
import com.dhr.service.CustomerService;
import com.dhr.util.PageBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

/**
 * @author Mr DU 客户管理
 */
public class CustomerAction extends ActionSupport implements ModelDriven<Customer> {

	// 注入模型驱动
	private Customer customer = new Customer();

	/**
	 * @return
	 */
	@Override
	public Customer getModel() {
		return customer;
	}

	// 注入service
	private CustomerService customerService;

	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

	// 注入属性当前页
	private int pageNumber = 1;

	public void setPageNumber(Integer pageNumber) {
		if (pageNumber != null) {
			this.pageNumber = pageNumber.intValue();
		}
	}

	// 注入属性页数大小
	private int pageSize = 3;

	public void setPageSize(Integer pageSize) {
		if (pageSize != null) {
			this.pageSize = pageSize.intValue();
		}
	}

	// 配置文件 上传的三个属性
	private String uploadFileName;// 文件上传名 ：表单的文件名+FileName
	private File upload;// 上传的文件
	private String uploadContentType;// 文件上传类型：表单的文件名+ContentType

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	/**
	 * 添加客户页面
	 * 
	 * @return
	 */
	public String saveUI() {
		// 异步查询字典
		return "saveUI";
	}

	/**
	 * 执行保存
	 * 
	 * @return
	 * @throws IOException
	 */
	public String save() throws IOException {
		// 上传客户图片
		// 判断是否上传了文件
		String pathName = null;
		if (upload != null) {
			// 1.取出文件的后缀
			String suffix = uploadFileName.substring(uploadFileName.indexOf("."));
			// 2.为文件设置一个随机名
			String fileName = UUID.randomUUID().toString().replace("-", "").toLowerCase() + suffix;
			// 3.目录打散
			int code1 = fileName.hashCode();
			int d1 = code1 & 0xf;
			int code2 = code1 >>> 4;
			int d2 = code2 & 0xf;
			// 4.设置上传路径
			pathName = "D:\\workspaceEclips2018\\CRM_SSH\\src\\main\\webapp\\upload\\" + d1 + "\\" + d2;
			File f = new File(pathName);
			if (!f.exists()) {
				f.mkdirs();
			}
			File file = new File(f, "\\" + fileName);
			// 上传文件
			FileUtils.copyFile(upload, file);
			// 设置图片的路径
			customer.setCust_image(pathName + "\\" + fileName);
		}
		customerService.save(customer);
		return "success";
	}

	/**
	 * 分页查询客户
	 * 
	 * @return
	 */
	public String findByPageCustomer() {
		// 注入属性--当前页
		// 调用业务层查询
		// 设置离线查询条件
		DetachedCriteria criteria = DetachedCriteria.forClass(Customer.class);
		// 设置查询条件
		if (customer.getCust_name() != null) {
			if (customer.getCust_name() != null && !"".equals(customer.getCust_name())) {
				criteria.add(Restrictions.like("cust_name", "%" + customer.getCust_name() + "%"));
			}
		}
		if (customer.getBaseDictSource() != null) {
			if (customer.getBaseDictSource().getDict_id() != null
					&& !"".equals(customer.getBaseDictSource().getDict_id())) {
				criteria.add(Restrictions.eq("baseDictSource.dict_id", customer.getBaseDictSource().getDict_id()));
			}
		}
		if (customer.getBaseDictIndustry() != null) {
			if (customer.getBaseDictIndustry().getDict_id() != null
					&& !"".equals(customer.getBaseDictIndustry().getDict_id())) {
				criteria.add(Restrictions.eq("baseDictIndustry.dict_id", customer.getBaseDictIndustry().getDict_id()));
			}
		}
		if (customer.getBaseDictLevel() != null) {
			if (customer.getBaseDictLevel().getDict_id() != null
					&& !"".equals(customer.getBaseDictLevel().getDict_id())) {
				criteria.add(Restrictions.eq("baseDictLevel.dict_id", customer.getBaseDictLevel().getDict_id()));
			}
		}
		PageBean<Customer> pageBean = customerService.findByPageCustomer(pageNumber, pageSize, criteria);
		// 把数据反倒值栈
		ActionContext.getContext().getValueStack().push(pageBean);
		return "customerPage";
	}

	/**
	 * 去修改页面
	 * 
	 * @return
	 */
	public String edit() {
		// 数据回显
		customer = customerService.findCustomerById(customer.getCust_id());
		return "toEdit";
	}

	/**
	 * 执行更新
	 * 
	 * @return
	 * @throws IOException
	 */
	public String update() throws IOException {
		// 判断是否选择了图片
		if (upload != null) {
			// 删除原来存在的图片
			String oldImage = customer.getCust_image();
			if (oldImage != null && !"".equals(oldImage)) {
				File fileOld = new File(oldImage);
				if (fileOld.exists()) {
					fileOld.delete();
				}
			}
			// 上传新的图片
			// 获取文件名
			String suffix = uploadFileName.substring(uploadFileName.indexOf("."));
			// 设置随机名
			String newFileName = UUID.randomUUID().toString().replace("-", "").toLowerCase() + suffix;
			// 目录打散
			int code1 = uploadFileName.hashCode();
			int d1 = code1 & 0xf;
			int code2 = code1 >>> 4;
			int d2 = code2 & 0xf;
			// 创建文件路径
			String pathName = "D:\\workspaceEclips2018\\CRM_SSH\\src\\main\\webapp\\upload\\" + d1 + "\\" + d2;
			File file = new File(pathName);
			if (!file.exists()) {
				file.mkdirs();
			}
			File newFile = new File(file, "\\" + newFileName);
			FileUtils.copyFile(upload, newFile);
			// 设置图片路径
			customer.setCust_image(pathName + "\\" + newFileName);
		}
		customerService.updateCustomer(customer);

		return "updateSuccess";
	}

	/**
	 * 删除客户
	 * 
	 * @return
	 */
	public String delete() {
		// 先查客户
		customer = customerService.findCustomerById(customer.getCust_id());
		// 删除图片
		if (customer != null) {
			String cust_image = customer.getCust_image();
			if (cust_image != null && !"".equals(cust_image)) {
				System.out.println(1);
				File file = new File(cust_image);
				if (file.exists()) {
					file.delete();
				}
			}
		}
		// 删除客户
		customerService.deleteCustomer(customer);
		return "deleteSuccess";
	}

	/**
	 * 查询所有客户
	 * 
	 * @return
	 * @throws IOException
	 */
	public String findAllCustomer() throws IOException {
		List<Customer> customers = customerService.findCustomer();
		// 把集合转为json
		JsonConfig config = new JsonConfig();
		String[] excludes = { "cust_source", "cust_industry", "cust_level", "cust_phone", "cust_mobile", "cust_image",
				"baseDictSource", "baseDictIndustry", "baseDictLevel", "linkMans" };
		;
		config.setExcludes(excludes);
		JSONArray array = JSONArray.fromObject(customers, config);
		// 响应到页面
		ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
		ServletActionContext.getResponse().getWriter().println(array.toString());
		return NONE;
	}
}
