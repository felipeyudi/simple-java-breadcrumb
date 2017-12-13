package br.com.breadcrumb;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import com.google.gson.Gson;

public class BreadcrumbHelper {

	public Breadcrumb write(ServletRequest request) {

		if (request == null) {
			return null;
		}
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		String path = httpRequest.getRequestURI();
		
		Breadcrumb breadcrumb = new Breadcrumb();
		breadcrumb.setId(this.findPage(path));
		breadcrumb.setPath(path);
		breadcrumb.setParam(this.urlEncodeUTF8(request.getParameterMap()));
		
		return breadcrumb;
	}
	
	public String toJson(Breadcrumb breadcrumb) {
		Gson gson = new Gson();
		return gson.toJson(breadcrumb);
	}
	
	private String findPage(String url)
	{
		String page = "";
	    Pattern pattern = Pattern.compile(".*/([^/#]*)(#.*|$)");
	    Matcher matcher = pattern.matcher(url);
	    if(matcher.find())
	    {
	        page = (matcher.group(1));
	    }
	    return page;
	}
	
	private String urlEncodeUTF8(String s) {
        try {
            return URLEncoder.encode(s, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new UnsupportedOperationException(e);
        }
    }
	
    private String urlEncodeUTF8(Map<?,?> map) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<?,?> entry : map.entrySet()) {
            if (sb.length() > 0) {
                sb.append("&");
            }
            String[] value = (String[]) entry.getValue();
            sb.append(String.format("%s=%s",
                urlEncodeUTF8(entry.getKey().toString()),
                urlEncodeUTF8(value[0].toString())
            ));
        }
        return sb.toString();       
    }

	public boolean validate(ArrayList<Breadcrumb> list, Breadcrumb breadcrumb) {
		boolean isValid = true;
		for (Breadcrumb b : list) {
			if (b.getId().equals(breadcrumb.getId())) {
				isValid = false;
			}
		}
		return isValid;
	}

}
