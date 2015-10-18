<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title></title>
    

  </head>
  
  <body>
  	<s:form action="role_edit">
			<s:textfield name="name"></s:textfield>
			<s:hidden name="id"></s:hidden>
			<s:textarea name="description"></s:textarea>
			<s:submit value="提交"></s:submit>
		</s:form>
  </body>
</html>
