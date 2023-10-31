//-----logout-----------
function UserManageConsoleLogout(){
    console.log("logout clixked");
}

//---navigate with other containers-----
const home_container = $("#home_container");
const user_manage_container = $("#user_manage_container");
const client_manage_container = $("#client_manage_container");
const admin_manage_container = $("#admin_manage_container");
const finance_data_container = $("#finance_data_container");

const home_nav_icon = $('#home_nav_icon');
const client_nav_icon = $('#client_nav_icon');
const admin_nav_icon = $('#admin_nav_icon');
const finance_nav_icon = $('#finance_nav_icon');

function hideAllContainers(){
    home_container.css('display','none');
    user_manage_container.css('display','none');
    client_manage_container.css('display','none');
    admin_manage_container.css('display','none');
    finance_data_container.css('display','none');

    home_nav_icon.css('fill', 'white');
    client_nav_icon.css('fill', 'white');
    admin_nav_icon.css('fill', 'white');
    finance_nav_icon.css('fill', 'white');
}

function openHomeContainer(){
    hideAllContainers();

    home_container.css('display','block');
    home_nav_icon.css('fill', 'orange');
}

function openClientMngContainer(){
    hideAllContainers();

    client_manage_container.css('display','block');
    client_nav_icon.css('fill', 'orange');
}

function openAdminMngContainer(){
    hideAllContainers();

    admin_manage_container.css('display','block');
    admin_nav_icon.css('fill', 'orange');
}
function openFinanceDataContainer(){
    hideAllContainers();

    finance_data_container.css('display','block');
    finance_nav_icon.css('fill', 'orange');
}

//---Default state home
$(document).ready(function(){
    openHomeContainer();
});
//-----------------------