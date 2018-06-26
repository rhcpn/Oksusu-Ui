package com.sample.msf.common;

import com.mobigen.framework.security.SessionManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Slf4j
@Service
public class CommonService {

	@Autowired
	private SessionManager sessionManager;
	

	/**
	 * 메뉴조회
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, String>> getMenu() throws Exception {
		List<Map<String, String>> menuList = new ArrayList();
		Map<String, String> m = new HashMap();
		m.put("m1", "Menu1");
		menuList.add(m);

		m = new HashMap();
		m.put("m2", "Menu2");
		menuList.add(m);

		m = new HashMap();
		m.put("m3", "Menu3");
		menuList.add(m);

		m = new HashMap();
		m.put("m4", "Menu4");
		menuList.add(m);

		return menuList;
	}
}
