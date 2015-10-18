<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title></title>
    

  </head>
  
  <body>
  	<s:set></s:set>
  	<s:iterator value="#roleList">
  		${id }
  		${name }
  		${description }
  		<s:a action="role_delete?id=%{id}" onclick="reutrn confim('确认要删除吗')">删除</s:a>
  		<s:a action="role_editUI?id=%{id}">修改</s:a>
  		<br>
  	</s:iterator>
  	<s:a action="role_addUI">添加</s:a>
  </body>
</html>
