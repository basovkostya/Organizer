//// ================ Left Sidebar js ============= //
$('#sidebar li').on('click' , function() {
    $('#sidebar li.active').removeClass('active');
    $(this).addClass('active');
});

// ================ Left menu sidebar dp toggle ============= //
//$(".sub-menu ul").hide();
//$(".sub-menu a").click(function(){
//    $(this).parent(".sub-menu").children("ul").slideToggle("100");
//    $(this).find(".right").toggleClass("fa-caret-up fa-caret-down");
//});

// ================== Sidebar Toggle ======================//
$(document).ready(function() {
    $('#toggleSidebar').click(function(){
    $('.left-menu').toggleClass('hide');
    $('.content-wrapper').toggleClass('hide');
    });
});

// ================= Show data table ==================== //
$(document).ready(function() {
    $('#employee-table').DataTable();
});

// =================== Read Russian Language ============== //
var table = new DataTable('#employee-table', {
    language: {
        url: 'http://localhost:8080/json/ru.json',
    },
});

// ================= Для динамической загрузки модального окна ============== //
const deleteEmployee = document.getElementById('deleteEmployee');
if (deleteEmployee) {
  deleteEmployee.addEventListener('show.bs.modal', event => {
    const button = event.relatedTarget
    const employeeId = button.getAttribute('data-bs-Id');
    const employeeFullName = deleteEmployee.querySelector('.employee-fullName');

    if(employeeId){
        $.get({
            url: '/employees/' + employeeId,
            success: (data) =>{
            let modal = $(this)
            employeeFullName.textContent = 'Вы действительно хотите удалить сотрудника: '+data.lastName+' '+data.firstName+' '+data.surName+'?'
            },
            error: (err) => {
            alert(err);
            }
        });
    }
  });
}
//==============Удаление сотрудника ============= //
$(document).ready(function() {
    $('table #deleteButtonEmployee').on('click', function(event){
        event.preventDefault();
        var href = $(this).attr('href');
        $('#deleteEmployee #delRefEmployee').attr('href', href);
        $('#deleteEmployee').modal();
    });
});
// ================== Для динамической загрузеи МО заявки на создание сотрудника ============ //
const deleteEmployeeAddApp = document.getElementById('deleteEmployeeAddApp');
if (deleteEmployeeAddApp) {
  deleteEmployeeAddApp.addEventListener('show.bs.modal', event => {
    const button = event.relatedTarget
    const employeeAddAppId = button.getAttribute('data-bs-Id');
    const employeeAddAppName = deleteEmployeeAddApp.querySelector('.employeeAddApp-name');

    if(employeeAddAppId){
        $.get({
            url: '/employeeAddApplications/' + employeeAddAppId,
            success: (data) =>{
            let modal = $(this)
            employeeAddAppName.textContent = 'Вы действительно хотите удалить заявку: '+data.name+'?'
            },
            error: (err) => {
            alert(err);
            }
        });
    }
  });
}
//==============Удаление заявки на сотрудника ============= //
$(document).ready(function() {
    $('table #deleteButtonEmployeeAddApp').on('click', function(event){
        event.preventDefault();
        var href = $(this).attr('href');
        $('#deleteEmployeeAddApp #delRefEmployeeAddApp').attr('href', href);
        $('#deleteEmployeeAddApp').modal();
    });
});

// ================== Для динамической загрузки МО заявки на мероприятие ============ //
const deleteEventApp = document.getElementById('deleteEventApp');
if (deleteEventApp) {
    deleteEventApp.addEventListener('show.bs.modal', event => {
    const button = event.relatedTarget
    const deleteEventAppId = button.getAttribute('data-bs-Id');
    const eventAppName = deleteEventApp.querySelector('.eventApp-name');

    if(deleteEventAppId){
        $.get({
            url: '/eventApplications/' + deleteEventAppId,
            success: (data) =>{
            let modal = $(this)
            eventAppName.textContent = 'Вы действительно хотите удалить заявку: '+data.name+'?'
            },
            error: (err) => {
            alert(err);
            }
        });
    }
  });
}
//==============Удаление заявки на мероприятие ============= //
$(document).ready(function() {
    $('table #deleteButtonEventApp').on('click', function(event){
        event.preventDefault();
        var href = $(this).attr('href');
        $('#deleteEventApp #delRefEventApp').attr('href', href);
        $('#deleteEventApp').modal();
    });
});

// ================== Для динамической загрузки МО заявки на клуб ============ //
const deleteClubApp = document.getElementById('deleteClubApp');
if (deleteClubApp) {
    deleteClubApp.addEventListener('show.bs.modal', event => {
    const button = event.relatedTarget
    const deleteClubAppId = button.getAttribute('data-bs-Id');
    const clubAppName = deleteClubApp.querySelector('.clubApp-name');

    if(deleteClubAppId){
        $.get({
            url: '/clubApplications/' + deleteClubAppId,
            success: (data) =>{
            let modal = $(this)
            clubAppName.textContent = 'Вы действительно хотите удалить заявку: '+data.name+'?'
            },
            error: (err) => {
            alert(err);
            }
        });
    }
  });
}
//==============Удаление заявки на клуб ============= //
$(document).ready(function() {
    $('table #deleteButtonClubApp').on('click', function(event){
        event.preventDefault();
        var href = $(this).attr('href');
        $('#deleteClubApp #delRefClubApp').attr('href', href);
        $('#deleteClubApp').modal();
    });
});
// define the function
var toggleElements = function () {
    if($('#main').val() == "Турнир") {
        $('#tournament').show();
        $('#otherType').hide();
    }else if($('#main').val() == "Разовая встреча"){
        $('#tournament').hide();
        $('#otherType').show();
    }else if($('#main').val() == "Повторяющееся событие"){
             $('#tournament').hide();
             $('#otherType').show();
         }
};

// set the handler
$('#main').on('change', toggleElements);

// execute the function when the page loads
$(document).ready(toggleElements);