<h2>Batch users</h2>

<br> Users added

<table border="1">
    <tr>
        <th>First name</th>
        <th>Last name</th>
        <th>Email address</th>
    </tr>
    <#list users as u>
    <tr>
        <td>${u.firstName?if_exists}</td>
        <td>${u.lastName?if_exists}</td>
        <td>${u.email?if_exists}</td>
    </tr>
    </#list>


</table>