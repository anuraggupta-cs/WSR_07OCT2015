package com.wsr.services;



import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;




public class Main {
	
	
	public JSONArray getTickets(String typeOfChart,String app,String  typeofReport,String sdate,String edate) throws Exception {
		
	Services serviceobj=new Services();
	
	ResultSet rst=serviceobj.getResultSetForReport(app, sdate, edate, typeofReport);
		ResultSetMetaData rsmd = rst.getMetaData();
		System.out.println(rsmd.getColumnName(1)+" "+rsmd.getColumnName(2)+" "+rsmd.getColumnName(3)+" "+rsmd.getColumnName(4)+" "+rsmd.getColumnName(5)+" "); 
		
		 ArrayList<String> list=new ArrayList<String>();
	
	
	JSONArray arr=new JSONArray();
	
System.out.println("IN MAIN DB SERVICE");
		 System.out.println("In Input Collector : App name : "+app+"  sdate  : "+sdate+" edate : "+edate+" type of chart  : "+typeOfChart+" type of report :"+typeofReport );
	
		   list.add(rsmd.getColumnName(3));
		   list.add(rsmd.getColumnName(4));
		   list.add(rsmd.getColumnName(5));
		   if(rst.next())
		   {
			   int[] b = new int[3];  
			   JSONObject jsonmain = new JSONObject();
			   
		do{
			 
			
			 
		  if(rst.getString(1).equalsIgnoreCase("Total"))
		  {
			  break;
		  }
			  if(typeofReport.equalsIgnoreCase("status"))
			  {
			  
				 if(rst.getString(1).equalsIgnoreCase("ocs")||rst.getString(1).equalsIgnoreCase("eOBA"))
				 {
		        int[] a = new int[2];
		        a[0]=Integer.valueOf( rst.getString(3));
		        a[1]=Integer.valueOf( rst.getString(4));
		        JSONObject json1 = new JSONObject();
		        json1.put("name", rst.getString(1));
		        
				json1.put("data", a);
				arr.put(json1);
				 }
		        
			  }
			  else if(typeofReport.equalsIgnoreCase("priority"))
			  {
				 
				 
			        b[0]+=Integer.valueOf( rst.getString(2))+Integer.valueOf( rst.getString(3));
			        b[1]+=Integer.valueOf( rst.getString(4));
			        b[2]+=Integer.valueOf( rst.getString(5));
			      //  JSONObject json1 = new JSONObject();
			      //  json1.put("name", rst.getString(1));
			        
					
					//arr.put(json1);
			  }
	
			 
			
				
			
				
		  } while(rst.next());
		 jsonmain.put("data", b);//adding up all the ocs interfaces to make one array 
		  arr.put(jsonmain);
		
		   }
		   else{
			  System.out.println("No Data Received from Db");
		   }
		  
				 
					
			
			
			  
		
			   
			   JSONObject json2 = new JSONObject();
			 
		       		json2.put("name", "list");	
					json2.put("data", list);
			
				
				arr.put(json2);
			
				  System.out.println(arr.toString());
				   
				
				
				
				

		
		  return arr;
	        		
	}
}
	
	