package com.sample.msf.common;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository("commonMapper")
public interface CommonMapper {

	/**
	 * 메뉴조회
	 * @return
	 * @throws Exception
	 */
	public List<Map> getMenu() throws Exception;
}
