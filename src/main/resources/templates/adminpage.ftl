<#import "parts/common.ftl" as c>

<@c.page>
    <h2>Users list</h2><br>
    <a class="btn btn-primary" href="/user/new" role="button">Create user</a>
    <div class="card-columns">
    <#list users as user>
        <div class="card my-3">
            <div class="m-2">
                <i>ID: ${user.id}</i><br>
                <i>User name:</i> ${user.username}<br>
            </div>
            <div class="card-footer text-muted">
                <i>First name:</i> <span>${user.firstName}</span><br>
                <i>Last name:</i> <span>${user.lastName}</span><br>
                <i>Created At:</i> ${user.createdAt.toString()}<br>
                <i>Status:</i> <#if user.isActive() != false> Active<#else> Inactive</#if><br>
                <#list user.roles as role>
                   Role: ${role.name()}
                </#list>
                </br><a class="btn btn-primary" href="/user/${user.id}/edit" role="button">Edit info</a>
            </div>
        </div>
    <#else>
        No users
    </#list>
</@c.page>