<h3>Get user by id </h3>


<form name="emailform" id="idform" action="getUserById" method="post">
    <input type="number" name="id" id="idd" value=""/>
    <input type="submit" value="search"/>
</form>


    <#if user?has_content >
        <h1 style="color: green">Search result of id ${id?length}:</h1>
        <h2>${user?if_exists}</h2>
    <#else>
        <h1 style="color: firebrick"> Sorry, there is no such user with id (${id?if_exists})</h1>
    </#if>



