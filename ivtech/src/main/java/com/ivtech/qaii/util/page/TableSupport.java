package com.ivtech.qaii.util.page;

import com.ivtech.qaii.util.Constants;
import com.ivtech.qaii.util.ServletUtils;

/**
 * 表格数据处理
 *
 * @author ruoyi
 */
public class TableSupport
{
    /**
     * 封装分页对象
     */
    public static PageDomain getPageDomain()
    {
        PageDomain pageDomain = new PageDomain();
        pageDomain.setPageNum(ServletUtils.getParameterToInt(Constants.PAGENUM));
        pageDomain.setPageSize(ServletUtils.getParameterToInt(Constants.PAGESIZE));
        pageDomain.setOrderByColumn(ServletUtils.getParameter(Constants.ORDERBYCOLUMN));
        pageDomain.setIsAsc(ServletUtils.getParameter(Constants.ISASC));
        return pageDomain;
    }

    public static PageDomain buildPageRequest()
    {
        return getPageDomain();
    }

}
