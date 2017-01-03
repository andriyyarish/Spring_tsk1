<html>
<head><title>EPAM university home work</title>
<body>
<div id="header">
    <H2>
        <a href="http://localhost:9093/epam/index.html"><img height="37" width="236" border="0px" src="https://s.dou.ua/storage-files/13116257_10209525941449514_4807616636600582763_o.jpg" align="left"/></a>
        Epam university
    </H2>
</div>

<div id="content">

    <fieldset>
        <legend>Add User</legend>
        <form name="user" action="users" method="post">
            UserName: <input type="text" name="name" />	<br/>
            Email: <input type="text" name="email" />	<br/>
            <input type="submit" value="   Save   " />
        </form>
    </fieldset>
    <br/>
    <table class="datatable">
        <tr>
            <th>Firstname</th>  <th>Lastname</th> <th>Email</th> <th>Date of birth</th>
        </tr>
    <#list model["userList"] as user>
        <tr>
            <td>${user.id}</td>
            <td>${user.name}</td>
            <td>${user.email}</td>
            <td>${user.dateOfBirth?if_exists}</td>
        </tr>
    </#list>
    </table>

</div>
</body>
</html>