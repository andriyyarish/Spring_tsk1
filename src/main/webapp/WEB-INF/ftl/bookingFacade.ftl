<form name="booking" action="booking/ticketPrice" method="POST">
    User <select name="user">
<#list users as u>
    <option>${u}</option>
</#list>
</select>
    <br/>

    Event <select name="event">
<#list events as e>
    <option>${e}</option>
</#list>
</select>
    <br/>

    Auditorium <select name="auditorium">
<#list auditoriums as a>
    <option>${a}</option>
</#list>
</select>
    <br/>

    Date <input type="date" name="date">
    <br/>

    Seat <input type="number" name="seat">
    <input type="submit" value="GetPrice">
</form>

<form name="booking" action="booking/orderTicket" method="POST">
    User <select name="user">
<#list users as u>
    <option>${u}</option>
</#list>
</select>
    <br/>

    Event <select name="event">
<#list events as e>
    <option>${e}</option>
</#list>
</select>
    <br/>

    Auditorium <select name="auditorium">
<#list auditoriums as a>
    <option>${a}</option>
</#list>
</select>
    <br/>

    Date <input type="date" name="date">
    <br/>

    Seat <input type="number" name="seat">
    <input type="submit" value="Order">
</form>



<#if price??>
<h1 style="color: green">Price for the ticket is ${price?if_exists}: </h1>
</#if>
