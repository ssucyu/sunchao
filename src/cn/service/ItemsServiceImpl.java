package cn.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.dao.ItemsMapper;
import cn.pojo.Items;
import cn.pojo.ItemsExample;
import cn.pojo.ItemsExample.Criteria;

@Service
public class ItemsServiceImpl implements ItemsService {

	@Autowired
	private ItemsMapper itemsMapper;

	@Override
	public List<Items> list() throws Exception {
		//如果不需要任何查询条件,直接将example对象new出来即可
		ItemsExample example = new ItemsExample();
		
		//List<Items> list = itemsMapper.selectByExampleWithBLOBs(example);
		List<Items> list = itemsMapper.selectByExample(example);
		return list;
	}

	@Override
	public Items findItemsById(Integer id) throws Exception {
		Items items = itemsMapper.selectByPrimaryKey(id);
		return items;
	}

	@Override
	public void updateItems(Items items) throws Exception {
		//itemsMapper.updateByPrimaryKeyWithBLOBs(items);
		itemsMapper.updateByPrimaryKey(items);
	}

	@Override
	public void addItems(Items item) throws Exception {
		itemsMapper.insert(item);
		
	}
	
	
}
