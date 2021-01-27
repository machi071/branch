package com.example.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.dto.AdditionFaceDto;

/**
 * 加入フェース～被保険者Mapper
 * <br>
 * @author katomab
 */
@Mapper
public interface GroupDao {

	/** 加入フェース、明細送り状取得 */
	List<AdditionFaceDto> getAdditionFaceDto();

	/** 加入フェース登録 */
	int insertAdditionFace(@Param("afList")List<AdditionFaceDto> afList);

	/** 加入フェース更新 */
	int updateAdditionFace(@Param("afList")List<AdditionFaceDto> afList);

}
