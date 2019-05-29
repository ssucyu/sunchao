package cn.controller;

import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.core.helpers.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.pojo.Items;
import cn.service.ItemsService;
import cn.vo.QueryVo;

@Controller
public class ItemsController {
	 
	@Autowired
	private ItemsService itmesService;
	
	@RequestMapping("/list")
	public ModelAndView itemsList() throws Exception{
		List<Items> list = itmesService.list();
		
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.addObject("itemList", list);
		modelAndView.setViewName("itemList");
		
		return modelAndView;
	}
	@RequestMapping("/addItem")
	public String addItems(Items item) throws Exception{
		
		
		System.out.println(item);
		UUID uuid = UUIDUtil.getTimeBasedUUID();
		String str = uuid.toString();
		
		System.out.println(uuid);
		
		//cf1e5b51-8046-11e9-ae2b-005056c00001
		
		String str2="";
		if(str != null && !"".equals(str)){
		for(int i=0;i<str.length();i++){
		if(str.charAt(i)>=48 && str.charAt(i)<=57){
		str2+=str.charAt(i);
		            }
		         }
		    }
		str2 = str2.substring(0, 3);
		item.setId(Integer.parseInt(str2));
		//item.setId(123);
		itmesService.addItems(item);
		
		
		return "success";
		
	}
	@RequestMapping("add")
	public String add() throws Exception{
		return "addItem";
	}
	
	/**
	 * springMvc中默认支持的参数类型:也就是说在controller方法中可以加入这些也可以不加,  加不加看自己需不需要,都行.
	 *HttpServletRequest
	 *HttpServletResponse
	 *HttpSession
	 *Model
	 */
	@RequestMapping("/itemEdit")
	public String itemEdit(HttpServletRequest reuqest, 
			 Model model) throws Exception{
		
		String idStr = reuqest.getParameter("id");
		Items items = itmesService.findItemsById(Integer.parseInt(idStr));
		
		//Model模型:模型中放入了返回给页面的数据
		//model底层其实就是用的request域来传递数据,但是对request域进行了扩展.
		model.addAttribute("item", items);
		
		//如果springMvc方法返回一个简单的string字符串,那么springMvc就会认为这个字符串就是页面的名称
		return "editItem";
	}
	
	//springMvc可以直接接收基本数据类型,包括string.spirngMvc可以帮你自动进行类型转换.
	//controller方法接收的参数的变量名称必须要等于页面上input框的name属性值
	//public String update(Integer id, String name, Float price, String detail) throws Exception{
	
	//spirngMvc可以直接接收pojo类型:要求页面上input框的name属性名称必须等于pojo的属性名称
	@RequestMapping("/updateitem")
	public String update(Items items) throws Exception{
		itmesService.updateItems(items);
		
		return "success";
	}
	
	//如果Controller中接收的是Vo,那么页面上input框的name属性值要等于vo的属性.属性.属性.....
	@RequestMapping("/search")
	public ModelAndView search(QueryVo vo) throws Exception{
		System.out.println(vo);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("items", vo);
		modelAndView.setViewName("itemList");
		
		return modelAndView;
	}
}
