<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Users list</title>
    <style type="text/css">
        body {
            background-image: url('http://crunchify.com/bg.png');
        }
    </style>
</head>
<body>${message}

<%--<h2>Student Information</h2>--%>
<%--<form:form method="POST" action="/test/users">--%>
    <%--<table>--%>
        <%--<tr>--%>
            <%--<td><form:label path="name">Name</form:label></td>--%>
            <%--<td><form:input path="name" /></td>--%>
        <%--</tr>--%>
        <%--<tr>--%>
            <%--<td><form:label path="email">Email</form:label></td>--%>
            <%--<td><form:input path="email" /></td>--%>
        <%--</tr>--%>
        <%--<tr>--%>
            <%--<td colspan="2">--%>
                <%--<input type="submit" value="Submit"/>--%>
            <%--</td>--%>
        <%--</tr>--%>
    <%--</table>--%>
<%--</form:form>--%>

<div>User list contains ${listSize} users:</div>
<div></div>
<div>${usr0}</div>
<div>${usr1}</div>
<div>${usr2}</div>
<div>${usr3}</div>
<div>${usr4}</div>


<br>
<br>
<div style="font-family: verdana; padding: 10px; border-radius: 10px; font-size: 12px; text-align:center;">


</div>
</body>
</html>