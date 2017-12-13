<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Simple Breadcrumb Example</title>
</head>
<body>


<h1>Simple Breadcrumb Example!!</h1>

<p>Breadcrumb: ||

<%@ include file="breadcrumb.html" %>
</p>

<h3>Menu</h3>
<a href="page/pagina1?param1=oi&param2=ola">Pagina 1</a> |
<a href="page/pagina2">Pagina 2</a> |
<a href="page/pagina3?param1=loading&param2=test">Pagina 3</a>  
</body>
</html>