package com.cn.lianshou.common.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStreamWriter;

public class ServletUtils {

	private static final Logger logger = LoggerFactory.getLogger(ServletUtils.class);

	private static final String CONTENT_TYPE = "content-type";
	public static final String JS_TYPE = "text/javascript";
	public static final String CODE_UTF8 = "UTF-8";


	public static void writeToResponse(HttpServletResponse response,Object object){
		response.addHeader(CONTENT_TYPE, JS_TYPE);
		response.setContentType("application/json");
		response.setCharacterEncoding(CODE_UTF8);
		OutputStreamWriter out = null;
		try {
			out = new OutputStreamWriter(response.getOutputStream(), CODE_UTF8);
			out.write(JSONObject.toJSONString(object));
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			IOUtils.closeQuietly(out);
		}
	}






}
