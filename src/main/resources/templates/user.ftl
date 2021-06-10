<#import "parts/common.ftl" as c>

<@c.page>
    <div class="form-row" xmlns="http://www.w3.org/1999/html">
        <div class="form-group col-md-6">
            <form method="get" action="/user" class="form-inline">
                <input type="text" name="filter" class="form-control" value="${filter!}" placeholder="Search by name">
                <button type="submit" class="btn btn-primary ml-2">Search</button>
            </form>
        </div>
    </div>

    <h2>Users list</h2>
    <div class="card-columns">
        <#list users as user>
            <div class="card my-3">
                <div class="m-2">
                    <i>ID: ${user.id}</i><br>
                    <i>First name:</i> <span>${user.firstName}</span><br>
                    <i>Last name:</i> <span>${user.lastName}</span><br>
                </div>
                <div class="card-footer text-muted">
                    <i>User name:</i> ${user.username}<br>
                    <i>Created At:</i> ${user.createdAt.toString()}
                </div>
            </div>
        <#else>
            No users
        </#list>
    </div>
</@c.page>