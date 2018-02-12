package com.strong.service;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.strong.common.Data;
import com.strong.dataaccess.DLBook;


public class BookService1 {
	private static Logger logger = LoggerFactory.getLogger(BookService1.class);
	
	public Data getBooks(String test) throws SQLException {
		Data data = new DLBook().getBooks(test);

		for (Map.Entry<String, List<LinkedHashMap<String, Object>>> entry : data.data.entrySet()) {
			logger.info(entry.getKey());
			for (Map<String, Object> map : entry.getValue()) {
				for (Map.Entry<String, Object> me : map.entrySet()) {
					logger.info(me.getKey() + "=" + me.getValue()+",");
				}
				logger.info("");
			}
			logger.info("");
		}
		return data;
	}
	
}
