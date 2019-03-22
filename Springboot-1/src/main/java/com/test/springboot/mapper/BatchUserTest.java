package com.test.springboot.mapper;

import java.text.MessageFormat;

import java.util.List;
import java.util.Map;



import com.test.springboot.vo.UserTestVo;
/**
 * 如果不使用map文件只使用注解，这里可以专门写针对特定多条件的SQL语句，后面注解可以直接引入
 * @author dell
 *
 */
public class BatchUserTest {
	
	/**
	 * 根据 批量删除大量对象
	 * @param map
	 * @return
	 */
	public String deleteUserByList(Map<String, Object>map) {
		@SuppressWarnings("unchecked")
		List<UserTestVo> lists=(List<UserTestVo>) map.get("list");
		StringBuffer sb=new StringBuffer();
		sb.append("delete from test_user where id in (");
		for (int i = 0; i < lists.size(); i++) {
			sb.append("'").append(lists.get(i).getId()).append("'");
			if (i<lists.size()-1) {
				sb.append(",");
			}
		}
		return sb.toString();
	}
	
	/**
	 * 批量插入
	 * @param map
	 * @return
	 */
	 public  String batStuAdd(@SuppressWarnings("rawtypes") Map map) {
	        @SuppressWarnings("unchecked")
			List<UserTestVo> students = (List<UserTestVo>) map.get("list");
	        StringBuilder sb = new StringBuilder();
	        sb.append("insert into test_user(id,name,code,age) values");
	        MessageFormat mf = new MessageFormat(
	                "(#'{'list[{0}].id},#'{'list[{0}].name},#'{'list[{0}].code},#'{'list[{0}].age})"
	        );
	        for (int i = 0; i < students.size(); i++) {
	            sb.append(mf.format(new Object[]{i}));
	            if (i < students.size() - 1) {
	                sb.append(",");
	            }
	        }
	        return sb.toString();
	    }
	
	/**
	 * 删除
	 * @param map
	 * @return
	 */
	 public String deleteByMap(@SuppressWarnings("rawtypes") Map map) {
		 UserTestVo vo=(UserTestVo) map.get("userTestVo");
		 
		 StringBuffer sb=new StringBuffer();
		 sb.append(" delete from test_user where 1=1 ");
		 if (vo.getId()!=null&&!"".equals(vo.getId())) {
			sb.append(" and id='"+vo.getId()+"'");
		}else if (vo.getName()!=null&&!"".equals(vo.getName())) {
			sb.append(" and name='"+vo.getName()+"'");
		}else if (vo.getAge()!=null&&!"".equals(vo.getAge())) {
			sb.append(" and age='"+vo.getAge()+"' ");
		}else if (vo.getCode()!=null&&!"".equals(vo.getCode())) {
			sb.append(" and code='"+vo.getCode()+"' ");
		}
		return sb.toString();
	 }
	 
	 
	 
	 
}
