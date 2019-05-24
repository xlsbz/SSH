package com.dhr.action;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.dhr.domain.CategorySecond;
import com.dhr.domain.Product;
import com.dhr.service.ICategorySecondService;
import com.dhr.service.IProductService;
import com.dhr.util.PageBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
/**
 * 管理员管理商品
 * @author Mr DU
 *
 */
public class AdminProductAction extends ActionSupport implements ModelDriven<Product>{
	private static final long serialVersionUID = 1L;

	private Product product = new Product();
	@Override
	public Product getModel() {
		return product;
	}
	
	//注入productService
	private IProductService productService;
	public void setProductService(IProductService productService) {
		this.productService = productService;
	}

	//注入二级分类service
	private ICategorySecondService categorySecondService;
	public void setCategorySecondService(ICategorySecondService categorySecondService) {
		this.categorySecondService = categorySecondService;
	}

	//接收pageNumber
	private int pageNumber;
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	//获取上传参数
	private File upload;//上传文件
	private String uploadFileName;//上传文件名
	private String uploadContextType;//文件类型
	public void setUpload(File upload) {
		this.upload = upload;
	}
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
	public void setUploadContextType(String uploadContextType) {
		this.uploadContextType = uploadContextType;
	}

	/**
	 * 分页查询商品
	 * @return
	 */
	public String findPageProduct() {
		PageBean<Product> pageBean = productService.findAllProduct(pageNumber);
		//保存到值栈
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "pageProduct";
	}

	/**'
	 * 去添加页面
	 * @return
	 */
	public String toaddProduct() {
		List<CategorySecond> seconds = categorySecondService.finAllSecond();
		//查询二级分类
		ActionContext.getContext().getValueStack().set("categoryseconds", seconds);
		return "toaddProduct";
	}
	
	/**
	 * 上传商品
	 * @return
	 */
	public String uploadProduct() {
		try {
			//封装参数
			product.setPdate(new Date());
			//文件上传
			if(upload!=null) {
				//获取文件保存在服务器的真实路径
				String realPath = ServletActionContext.getServletContext().getRealPath("products/1");
				//创建文件
				File file = new File(realPath+"//"+uploadFileName);
				//拷贝
				FileUtils.copyFile(upload, file);
				product.setImage("products/1/"+uploadFileName);
				
			}
			productService.uploadProduct(product);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "uploadProduct";
	}
	
	/**
	 * 去修改商品页面
	 * @return
	 */
	public String toEdit() {
		//查询出所属的二级分类以及要修改的商品id
		product = productService.findById(product.getPid());
		List<CategorySecond> seconds = categorySecondService.finAllSecond();
		//保存到值栈
		ActionContext.getContext().getValueStack().set("categorysceonds", seconds);
		return "toUpdateProduct";
	}
	
	/**
	 * 修改商品
	 * @return
	 * @throws IOException 
	 */
	public String updateProduct() throws IOException {
		//设置修改时间
		product.setPdate(new Date());
		//获取上传文件
		if(upload!=null) {
			//获取文件存放的真实路径
			String realPath = ServletActionContext.getServletContext().getRealPath("/products/1");
			//创建文件
			File file = new File(realPath+"//"+uploadFileName);
			//拷贝
			FileUtils.copyFile(upload, file);
			//设置图片地址
			product.setImage("/products/1/"+uploadFileName);
		}
		productService.updateProduct(product);
		return "updateProduct";
	}
	
	/**
	 * 删除商品
	 * @return
	 */
	public String delProduct() {
		//获取商品ID
		//查询商品再删除
		product = productService.findById(product.getPid());
		try {
			productService.delProduct(product);
		}catch(Exception e) {
			try {
				ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
				ServletActionContext.getResponse().getWriter().print("<script>alert('这是用户的订单，不能删除!');</script>");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			return NONE;
		}
		//删除商品图片
		String realPath = ServletActionContext.getServletContext().getRealPath("/");
		File file = new File(realPath+"//"+product.getImage());
		if(file!=null) {
			file.delete();
		}
		return "delProduct";
	}
}
