<%
    boolean isShowTable = true;
	StringBuilder errorText = new StringBuilder();
	StringBuilder result = new StringBuilder();
	errorText.append("<ul>");
%>

<center>
<h1>The Table</h1>
<form action="table.jsp">
  <table border="0">
   <tr><td>Rows:</td><td><input name="rowsCnt" type="text"/></td></tr>
   <tr><td>Cols:</td><td><input name="colsCnt" type="text"/></td></tr>
   <tr><td></td><td align="right"><input value="Build Table" type="submit"/></td></tr>
  </table>
</form>

<%
	String rows = request.getParameter("rowsCnt");
	String cols = request.getParameter("colsCnt");
	
	if(rows==null){
	    isShowTable = false;		
	}else if(rows.isEmpty()){
		isShowTable = false;
		errorText.append("<li>The 'rows' is empty.</li>");
	}
	if(cols==null){
		isShowTable = false;
	}else if(cols.isEmpty()){
		isShowTable = false;
		errorText.append("<li>The 'cols' is empty.</li>");
	}
	
	if(isShowTable)
	{
	  int rowsCount = 0;
	  int colsCount = 0;
	  try{
	      rowsCount = Integer.parseInt(rows);
		  colsCount = Integer.parseInt(cols);
	  }catch(NumberFormatException e){		
		  isShowTable = false;
		  errorText.append("<li>The 'rows'/'cols' is not a number.</li>");
	  }
	
	  int cnt = 0;
	  String tdStyle = "width:25px; height:25px; text-align:center;";
	  if(isShowTable){
		  result.append("<table style='border:1px black solid; background-color:#a2d9ce;'>");
	      for(int i=0; i<rowsCount; i++){
	          result.append("<tr>");
			  for(int j=0; j<colsCount; j++){
				  cnt++;
				  if(i==j && rowsCount==colsCount){
					  result.append("<td style='background-color:#5dade2;"+tdStyle+"'>"+cnt+"</td>");
				  }else{
				      result.append("<td style='"+tdStyle+"'>"+cnt+"</td>");
				  }
			  }
			  result.append("</tr>");
	      }
		  result.append("</table>");
		  out.write(result.toString());
	  }
	}
	errorText.append("</ul>");
	out.write(errorText.toString());
%>
