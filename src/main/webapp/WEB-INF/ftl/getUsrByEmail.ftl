<h3>Get user by email </h3>


<form name="emailform" id="emailform" action="getUserByEmail" method="post">
    <input type="text" name="email" id="email" value="andyyarish@gmail.com"/>
    <input type="submit" value="search"/>
</form>


    <#if user?has_content>
    <h1 style="color: green">Search results of email ${email?if_exists}: </h1>
        <h2> ${user?if_exists} </h2>
    <#else>
       <h1 style="color: firebrick"> Sorry, there is no such user with email (${email?if_exists}) </h1>
    </#if>




