<#import "parts/common.ftl" as c>

<@c.page>
    <h2>Edit user.</h2>
    <form action="/user/${user.id}/edit" method="post">
    <div class="form-group row">
        <label class="col-sm-2 col-form-label">User Name :</label>
        <div class="col-sm-6">
            <input type="text"  name="username" value="<#if user??>${user.username}</#if>"
                   class="form-control ${(usernameError??)?string('is-invalid', '')}"
                   placeholder="Enter user name"/>
            <#if usernameError??>
                <div class="invalid-feedback">
                    ${usernameError}
                </div>
            </#if>
        </div>
    </div>
    <div class="form-group row">
        <label class="col-sm-2 col-form-label">First Name:</label>
        <div class="col-sm-6">
            <input type="text" name="firstName" value="<#if user??>${user.firstName}</#if>"
                   class="form-control ${(firstNameError??)?string('is-invalid', '')}"
                   placeholder="Enter firstName"/>
            <#if firstNameError??>
                <div class="invalid-feedback">
                    ${firstNameError}
                </div>
            </#if>
        </div>
    </div>
    <div class="form-group row">
        <label class="col-sm-2 col-form-label">Last Name:</label>
        <div class="col-sm-6">
            <input type="text" name="lastName" value="<#if user??>${user.lastName}</#if>"
                   class="form-control ${(lastNameError??)?string('is-invalid', '')}"
                   placeholder="Enter lastName"/>
            <#if lastNameError??>
                <div class="invalid-feedback">
                    ${lastNameError}
                </div>
            </#if>
        </div>
    </div>
    <div class="form-group row">
        <label class="col-sm-2 col-form-label" for="exampleFormControlSelect1">Role:</label>
        <select class="form-control" id="exampleFormControlSelect1" name="roles">
            <option>USER</option>
            <option>ADMIN</option>
        </select>
    </div>
    <div class="form-group row">
        <label class="col-sm-2 col-form-label" for="exampleFormControlSelect2">Activation:</label>
        <select class="form-control" id="exampleFormControlSelect2" name="isActive">
            <option value="ACTIVE">Active</option>
            <option value="INACTIVE">InActive</option>
        </select>
    </div>
    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
    <div class="form-group">
        <button type="submit" class="btn btn-primary">Save changes</button>
    </div>
</@c.page>