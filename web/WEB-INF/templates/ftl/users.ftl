<html>
    <head>
        <title>Users</title>
    </head>
    <body>

    <div><a href="/users/new">Add new user</a></div> <br/>

    <#if users?has_content>
        <ul>
            <#list users as user>
                <li>${user.name} ${user.surname} ${user.email}</li>
            </#list>
        </ul>
    <#else>
        No users yet.
    </#if>
    </body>
</html>
