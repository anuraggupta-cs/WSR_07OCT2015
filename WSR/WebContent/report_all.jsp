<%@page import="com.wsr.services.Services"%>
<%@page import="com.wsr.pojos.BarebonePJ"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<style type="text/css">
<!--
@import url("style.css");
-->
</style>
<head>

<title>Table Report</title>
</head>
<body>
   
   		<%!String track = null; %>
		<%!String sdate = null;%>
		<%!String edate = null;%>
		<%!ArrayList<BarebonePJ> listofRows = null; %>
		
		<%
			System.out.println("In report_all jsp page");		
			
			@SuppressWarnings("unchecked")
			Map<String, ResultSet> allReportMap = (Map<String, ResultSet>) request.getSession().getAttribute("allReportMap");
		
			System.out.println(allReportMap);
		%>
		
		 <% 
		 	 	track = (String) request.getSession().getAttribute("track");
				sdate = (String) request.getSession().getAttribute("sdate");
				edate = (String) request.getSession().getAttribute("edate");
				
				System.out.println(track+ " " +sdate+ " " +edate);
			%>
		
	<div align="left">	
	<table>
	<tr>
     		 <td colspan="5">
     		 Track: <%= track %><br>
             Start Date: <%= sdate %> <br>
             End Date: <%= edate %><br>
            </td>
      </tr>
      </table>
      </div>
	
			
    <div align="right">
      <!--  <table style="width=100%;" id="ver-minimalist"> -->
         
          <%
		
			Services serviceobj=new Services();
		
			if(allReportMap != null){
				
				System.out.println(allReportMap);
				
			for (Map.Entry<String, ResultSet> entry : allReportMap.entrySet()) {	
		
				String key = entry.getKey();
				ResultSet rs = entry.getValue();
				
				System.out.println(rs);
				
				listofRows = serviceobj.getdataforReporttable(rs);	          
		%>
		<br><br>
		 <table align="left" cellpadding="5" border="1">  
		 <tr><td colspan="100">
            <h2>Report Structure – By Application / <%= key %> </h2>
               </td></tr>    
           <% 
       					for(BarebonePJ temp:listofRows)
						{
    	   %>
     <tr>           
       <td><%= temp.getCol1() %></td>
       <td><%= temp.getCol2() %></td>
       <td><%= temp.getCol3() %></td>
       <td><%= temp.getCol4() %></td>
       <td><%= temp.getCol5() %></td>                     
    </tr>            
     
         <%	} } %></table><br><br><% } %>
			
    </div>
</body>
</html>