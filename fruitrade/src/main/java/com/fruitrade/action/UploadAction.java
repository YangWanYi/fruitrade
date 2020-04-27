package com.fruitrade.action;

import java.io.File;

import javax.servlet.ServletContext;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.fruitrade.domain.FruitDo;
import com.fruitrade.service.FruitService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class UploadAction extends ActionSupport  implements ModelDriven<FruitDo> {

	private static final long serialVersionUID = -2797544126698498093L;

	@Autowired
	private FruitService fruitService;

	private FruitDo fruitData;

	private File upload;// 动作类上传的属性必须是file类型,upload为表单的name值

	private String uploadFileName; // 上传的文件名称,固定写法:name+FileName;
	
	/**
	 * 去上传页面
	 * @return
	 */
	public String toUploadPage() {
		if (this.fruitData.getId() != null) {
			this.fruitData = this.fruitService.selectFruitById(this.fruitData.getId());
			return SUCCESS;
		} else {
			return ERROR;
		}
	}

	public String uploadPic() throws Exception {
		ActionContext ac = ActionContext.getContext();
		ServletContext sc = (ServletContext) ac.get(ServletActionContext.SERVLET_CONTEXT);
		String path = sc.getRealPath("/").concat("//ui//imgs//upload//");
		File file = new File(path);// 判断路径名是否存在，不存在则创建 mkdir

		if (!file.exists()) {
			file.mkdir();
		}
		FileUtils.copyFile(upload, new File(file, uploadFileName)); // 将页面传过来的数据通过FileUtils 拷贝到刚定义的路径下
		String file2 = new File(file, uploadFileName).toString();// 获取路径名+文件名的字符串
		System.out.println("文件名：" + uploadFileName);
		System.out.println("file2:" + file2);
		if (this.fruitData.getId() != null) {
			this.fruitData = this.fruitService.selectFruitById(this.fruitData.getId());
			this.fruitData.setPic(uploadFileName);// 给数据库图片的属性名赋值
			this.fruitService.updateFruit(this.fruitData);// 调用保存方法保存数据
		}
		return NONE;
	}

	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	@Override
	public FruitDo getModel() {
		this.fruitData = new FruitDo();
		return this.fruitData;
	}

	public FruitDo getFruitData() {
		return fruitData;
	}

	public void setFruitData(FruitDo fruitData) {
		this.fruitData = fruitData;
	}

}