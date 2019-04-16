package com.test.activiti.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.test.activiti.vo.GroupVo;

@Mapper
@Repository("groupDao")
public interface GroupDao extends BaseMapper<GroupVo>{

}
