
<#import "/spring.ftl" as spring/>

<html>
<head>
    <title>EPAM university home work</title>
    <script src="https://code.jquery.com/jquery-1.10.2.js"></script>
    <script>
        function setRefill(uid) {
            var id = function (a,b) {return a+b;}
            var userId = uid;
            var valLoc = id(userId,"refill");
            var actionLoc = id(userId,"doRefill");

            var value = document.getElementById(valLoc).value;
            var newHref = document.getElementById(actionLoc).getAttribute('href') +'?amount=' + value;

            document.getElementById(actionLoc).setAttribute('href',newHref);
        }
    </script>
</head>
<body>
<div id="header">
    <H2>
        <a href="http://localhost:9093/epam/index.html"><img height="37" width="236" border="0px"
                                                             src="https://s.dou.ua/storage-files/13116257_10209525941449514_4807616636600582763_o.jpg"
                                                             align="left"/></a>
        Epam university
    </H2>
</div>

<div id="content">

    <fieldset>
        <legend>Add User</legend>
        <form name="user" action="users" method="post">
            FirstName: <input type="text" name="firstName"/> <br/>
            LastName: <input type="text" name="lastName"/> <br/>
            Email: <input type="text" name="email"/> <br/>
            DateOfBirth: <input type="date" name="dateOfBirth" required> <br/>
            Password: <input type="password" name="password" required> <br/>
            ConfirmPassword: <input type="password" name="confirmPassword" required> <br/>
            <input type="submit" value="   Save   "/>
        </form>
    </fieldset>

    <legend>Search for user</legend>


    <br/>
    <table class="datatable">
        <tr>
            <th>Id</th>
            <th>Firstname</th>
            <th>Lastname</th>
            <th>Email</th>
            <th>Date of birth</th>
            <th>Current balance</th>
            <th>Delete</th>
            <th>Show tickets</th>
            <th>Refill amount</th>
            <th>Perform refill</th>
        </tr>
    <#list model["userList"] as user>
        <tr>
            <td>${user.id}</td>
            <td>${user.firstName}</td>
            <td>${user.lastName}</td>
            <td>${user.email}</td>
            <td>${user.dateOfBirth?if_exists?date["yyyy-mm-dd"]}</td>
            <td>${user.account.ballance}</td>
            <td><a href="/epam/users/delete?id=${user.id}">Delete</a></td>
            <td><a href="/epam/users/${user.id}/getTickets">Show Booked tickets</a> </td>
            <td><input type="text" name="refill" id=${user.id + 'refill'}></td>

            <td><a id=${user.id + 'doRefill'} href="/epam/users/refill/${user.id}" onclick="setRefill(${user.id})">Refill balance</a> </td>
            <td></td>


        </tr>
    </#list>
    </table>
    <a href="/epam/usersPdfView">Export user list to PDF </a>

</div>
</body>
</html>