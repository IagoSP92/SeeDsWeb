package com.seeds.web.utils;

import com.seeds.web.config.ConfigurationManager;
import com.seeds.web.config.ConfigurationParameterNames;

public interface ConstantsInterface {
	
	public static int pageSize = Integer.valueOf(
			ConfigurationManager.getInstance().getParameter(
					ConfigurationParameterNames.RESULTS_PAGE_SIZE_DEFAULT)); 

	public static int pagingPageCount = Integer.valueOf(
			ConfigurationManager.getInstance().getParameter(
					ConfigurationParameterNames.RESULTS_PAGING_PAGE_COUNT));

}
