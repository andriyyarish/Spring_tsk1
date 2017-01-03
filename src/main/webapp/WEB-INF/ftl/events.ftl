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
        <legend>Add Event</legend>
        <form name="event" action="events" method="post">
            EventName: <input type="text" name="name" />	<br/>
            EventRating: <input type="text" name="eventRating" />	<br/>
            EventPrice: <input type="number" name="basePrice" />	<br/>
            EventDate: <input type="datetime" name="date" />	<br/>
            <input type="submit" value="   Save   " />
        </form>
    </fieldset>
    <br/>
    <table class="datatable">
        <tr>
            <th>Name</th>  <th>Rating</th> <th>Price</th> <th>Date</th>
        </tr>
    <#list model["eventList"] as event>
        <tr>
            <td>${event.name}</td>
            <td>${event.eventRating!"UNKOWN"}</td>
            <td>${event.basePrice}</td>
            <td>${event.date?if_exists}</td>
        </tr>
    </#list>
    </table>

</div>
</body>
</html>