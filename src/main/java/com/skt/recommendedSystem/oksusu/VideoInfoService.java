package com.skt.recommendedSystem.oksusu;

import com.skt.recommendedSystem.oksusu.model.VideoInfoSearchModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class VideoInfoService {

	@Autowired
	private VideoInfoMapper videoInfo;

    /**
     * oksusu 정보 조회
     * @param filter
     * @return
     * @throws Exception
     */
	public List<Map<String,Object>> getWatchVideoList(VideoInfoSearchModel filter) throws Exception {
		filter.setUserId("1");
		List<Map<String,Object>> result = videoInfo.selectWatchVideoList(filter);
		return result;
	}
}
